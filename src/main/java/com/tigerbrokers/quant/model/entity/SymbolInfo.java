package com.tigerbrokers.quant.model.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @AUTHOR windli
 * @DATE 2019/10/8 16:21
 * @DESC
 **/
@Getter
@Setter
@ToString
public class SymbolInfo {

    private Integer contractId;
    private String identifier;
    private String symbol;
    private String secType;
    private String expiry;
    private String contractMonth;
    private Double strike;
    @JSONField(name = "right")
    private String right1;
    private Double multiplier;
    private String exchange;
    private String market;
    private String primaryExchange;
    private String currency;
    private String localSymbol;
    private String tradingClass;
    @JSONField(name = "name")
    private String name1;
    private int status;
    private Double minTick;
    private Double shortMargin;
    private Double shortFeeRate;
    private int shortable;
    private Double longInitialMargin;
    private Double longMaintenanceMargin;
    private String lastTradingDate;
    private String firstNoticeDate;
    private Long lastBiddingCloseTime;
    private boolean trade;
    private boolean continuous;
}
