package com.tigerbrokers.quant.storage.mapper;

import com.tigerbrokers.quant.model.entity.SymbolInfo;
import com.tigerbrokers.stock.openapi.client.https.domain.contract.item.ContractItem;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @AUTHOR windli
 * @DATE 2019/10/8 16:37
 * @DESC
 **/
@Mapper
public interface SymbolInfoMapper {


    @Insert({"<script> " +
            "insert into t_symbol_info" +
            "(" +
            "<if test='expiry != null' >f_expiry,</if>" +
            "<if test='contractMonth != null' >f_contract_month,</if>" +
            "<if test='strike != null' >f_strike,</if>" +
            "<if test='right != null' >f_right,</if>" +
            "<if test='multiplier != null' >f_multiplier,</if>" +
            "<if test='shortMargin != null' >f_short_margin,</if>" +
            "<if test='shortFeeRate != null' >f_short_fee_rate,</if>" +
            "<if test='longInitialMargin != null' >f_long_initial_margin,</if>" +
            "<if test='shortable != null' >f_shortable,</if>" +
            "<if test='longMaintenanceMargin != null' >f_long_maintenance_margin,</if>" +
            "f_contract_id,f_identifier,f_symbol,f_sec_type,f_exchange,f_market,f_primary_exchange,f_currency,f_local_symbol,f_name,f_tiger_status,f_min_tick,f_trade,f_continuous,f_trading_class" +
            ")" +
            " value " +
            "(" +
            "<if test='expiry != null' >#{expiry,jdbcType=VARCHAR},</if> " +
            "<if test='contractMonth != null' >#{contractMonth,jdbcType=VARCHAR},</if> " +
            "<if test='strike != null' >#{strike,jdbcType=DOUBLE},</if> " +
            "<if test='right != null' >#{right,jdbcType=VARCHAR},</if> " +
            "<if test='multiplier != null' >#{multiplier,jdbcType=DOUBLE},</if> " +
            "<if test='shortMargin != null' >#{shortMargin,jdbcType=DOUBLE},</if>" +
            "<if test='shortFeeRate != null' >#{shortFeeRate,jdbcType=DOUBLE},</if>" +
            "<if test='longInitialMargin != null' >#{longInitialMargin,jdbcType=DOUBLE},</if> " +
            "<if test='shortable != null' >#{shortable,jdbcType=INTEGER},</if>" +
            "<if test='longMaintenanceMargin != null' >#{longMaintenanceMargin,jdbcType=DOUBLE},</if>" +
            "#{contractId},#{identifier},#{symbol},#{secType},#{exchange},#{market},#{primaryExchange},#{currency},#{localSymbol},#{name},#{status},#{minTick},#{trade},#{continuous},#{tradingClass}" +
            ")" +
            "</script>"})
    Integer saveSymbol(ContractItem item);

    @Update("insert into bar(symbol,open,high,low,close,volume,duration,amount,create_time) value (#{symbol},#{open},#{high},#{low},#{close},#{volume},#{duration},#{amount},now())")
    Integer updateSymbol(ContractItem item);

    @Select("insert into bar(symbol,open,high,low,close,volume,duration,amount,create_time) value (#{symbol},#{open},#{high},#{low},#{close},#{volume},#{duration},#{amount},now())")
    void querySymbol(SymbolInfo symbolInfo);
}
