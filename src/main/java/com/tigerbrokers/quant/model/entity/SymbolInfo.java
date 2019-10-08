package com.tigerbrokers.quant.model.entity;

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
public class SymbolInfo extends BaseEntity {

    /**
     * 用户Id
     */
    private String contractId;

    /**
     * 请求id
     */
    private String symbol;

    /**
     * 请求内容id
     */
    private String currency;

    /**
     * 响应id
     */
    private String exchange;

    /**
     * 响应内容
     */
    private String identifier;

    /**
     * 费用类型
     */
    private String localSymbol;


    /**
     * 用户Id
     */
    private double longInitialMargin;

    /**
     * 请求id
     */
    private double longMaintenanceMargin;

    /**
     * 请求内容id
     */
    private String market;

    /**
     * 响应id
     */
    private String minTick;

    /**
     * 响应内容
     */
    private String name;

    /**
     * 费用类型
     */
    private String primaryExchange;


    /**
     * 请求id
     */
    private String secType;

    /**
     * 请求内容id
     */
    private double shortFeeRate;

    /**
     * 响应id
     */
    private double shortMargin;

    /**
     * 响应内容
     */
    private double shortable;

    /**
     * 费用类型
     */
    private Integer tigerStatus;

    /**
     * 响应id
     */
    private Boolean continuous;

    /**
     * 响应内容
     */
    private Boolean trade;

    /**
     * 费用类型
     */
    private String tradingClass;
}
