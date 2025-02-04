package com.tigerbrokers.quant.algorithm.algos;

import com.alibaba.fastjson.JSON;
import com.tigerbrokers.quant.algorithm.AlgoTemplate;
import com.tigerbrokers.quant.model.data.Bar;
import com.tigerbrokers.quant.model.data.Order;
import com.tigerbrokers.quant.model.data.Trade;
import com.tigerbrokers.quant.model.data.*;
import com.tigerbrokers.quant.model.enums.BarType;
import com.tigerbrokers.quant.model.enums.OrderType;
import com.tigerbrokers.quant.util.BarGenerator;
import lombok.extern.slf4j.Slf4j;
import org.ta4j.core.*;
import org.ta4j.core.indicators.AbstractIndicator;
import org.ta4j.core.indicators.EMAIndicator;
import org.ta4j.core.indicators.SMAIndicator;
import org.ta4j.core.indicators.helpers.ClosePriceIndicator;
import org.ta4j.core.indicators.helpers.ConvergenceDivergenceIndicator;
import org.ta4j.core.indicators.helpers.FixedBooleanIndicator;
import org.ta4j.core.num.DoubleNum;
import org.ta4j.core.trading.rules.*;

import javax.xml.bind.util.JAXBSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description:
 *
 * @author kevin
 * @date 2019/08/26
 */
@Slf4j
public class MacdAlgo1 extends AlgoTemplate {

    private BarGenerator barGenerator;
    private BarGenerator xminBarGenerator;
    private Map<String, TimeSeries> symbolSeries = new HashMap<>();
    private Map<String, Strategy> symbolStrategy = new HashMap<>();
    private Map<String, TradingRecord> symbolTradingRecord = new HashMap<>();

    private static final List<String> symbols = new ArrayList<>();

    private int buyLimit = 50;
    private int sellLimit = 10;

    static {
        symbols.add("CN1910");
    }

    public MacdAlgo1() {
        init();
    }

    private void init() {
        barGenerator = new BarGenerator(bar -> onBar(bar));
        xminBarGenerator = new BarGenerator(symbols, 25, bar -> on5minBar(bar));
    }

    @Override
    public void onStart() {
        Integer barSize = (Integer) settings.get("bars");

        for (String symbol : symbols) {
            TimeSeries series = new BaseTimeSeries.SeriesBuilder().withName("MacdAlgo1").build();
            series.setMaximumBarCount(400);
            symbolSeries.put(symbol, series);
            TradingRecord tradingRecord = new BaseTradingRecord();
            symbolTradingRecord.put(symbol, tradingRecord);
            Strategy strategy = newMacdStrategy(series);
            symbolStrategy.put(symbol, strategy);
        }

        Map<String, List<Bar>> bars = getBars(symbols, BarType.min1, barSize);
        for (String symbol : bars.keySet()) {
            List<Bar> barList = bars.get(symbol);
            for (Bar bar : barList) {
                symbolSeries.get(symbol).addBar(bar.toBaseBar());
            }
        }
        for (String symbol : symbols) {
            subscribe(getAlgoName(), symbol);
        }
        Asset asset = getAsset();
        log("initial asset:{}", asset);
        List<Position> positions = getAllPositions();
        log("initial positions:{}", positions);
    }

    @Override
    public void onTick(Tick tick) {
        barGenerator.updateTick(tick);
        symbolSeries.get(tick.getSymbol()).addPrice(tick.getLatestPrice());
        makeTradingChoice(tick);
    }

    @Override
    public void onOrder(Order order) {

    }

    @Override
    public void onTrade(Trade trade) {
        symbolSeries.get(trade.getSymbol()).addPrice(DoubleNum.valueOf(trade.getPrice()));
    }

    public void onBar(Bar bar) {
        log("{} onBar {}", getAlgoName(), bar);
        addToSerie(bar);
        xminBarGenerator.updateBar(bar);
    }

    public void on5minBar(Bar bar) {
        log("{} on5minBar {}", getAlgoName(), bar);
    }

    private void addToSerie(Bar bar) {
        TimeSeries timeSeries = symbolSeries.get(bar.getSymbol());
        BaseBar baseBar = bar.toBaseBar();
        if (baseBar.getEndTime().isAfter(timeSeries.getBar(timeSeries.getEndIndex()).getEndTime())) {
            timeSeries.addBar(baseBar);
        }
    }

    public void makeTradingChoice(Tick tick) {
        TimeSeries series = symbolSeries.get(tick.getSymbol());
        Strategy strategy = symbolStrategy.get(tick.getSymbol());
        TradingRecord tradingRecord = symbolTradingRecord.get(tick.getSymbol());
        int endIndex = series.getEndIndex();
        if (strategy.shouldEnter(endIndex, tradingRecord)) {
            log("Strategy {} entered", getAlgoName());
            Position position = getPosition("CN1910");
            log("{} position {}", getAlgoName(), position);
            //仓位控制
            if (position != null && position.getPosition() >= buyLimit) {
                return;
            }
            //干扰信号的过滤
            //抵扣价原理

            double price = tick.getLatestPrice();
            int volume = 1;
            String id = buy(tick.getSymbol(), price, volume, OrderType.LMT);
            log("Entered {} on {} orderId:{},price:{},amount:{}", getAlgoName(), tick.getSymbol(), id, price, volume);
        }
        if (strategy.shouldExit(endIndex, tradingRecord)) {
            log("Strategy {} exit", getAlgoName());

            Position position = getPosition("CN1910");
            log("{} position {}", getAlgoName(), position);
            if (position != null && position.getPosition() < 0 && -position.getPosition() >= sellLimit) {
                return;
            }

            double price = tick.getLatestPrice();
            int volume = 1;
            String id = sell(tick.getSymbol(), price, volume, OrderType.LMT);
            log("Exited {} on {} orderId:{},price:{},amount:{}", getAlgoName(), tick.getSymbol(), id, price, volume);
        }
    }

    private Strategy newMacdStrategy(TimeSeries series) {
        ClosePriceIndicator closePrice = new ClosePriceIndicator(series);
        //均线
        EMAIndicator ema20 = new EMAIndicator(closePrice, 20);
        EMAIndicator ema60 = new EMAIndicator(closePrice, 60);
        SMAIndicator sma20 = new SMAIndicator(closePrice, 20);
        SMAIndicator sma60 = new SMAIndicator(closePrice, 60);

        //开仓平仓的条件 -- 趋势形成，趋势的结束，用均线来定义趋势
        Rule entryRule = new CrossedUpIndicatorRule(ema20,ema60).and(new OverIndicatorRule(sma20,sma60));
        Rule exitRule = new CrossedDownIndicatorRule(ema20,ema60).and(new UnderIndicatorRule(sma20,sma60));
        return new BaseStrategy(entryRule, exitRule);
    }
}
