package com.tigerbrokers.quant.storage.mapper;

import com.tigerbrokers.quant.model.entity.SymbolInfo;
import com.tigerbrokers.stock.openapi.client.https.domain.contract.item.ContractItem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @AUTHOR windli
 * @DATE 2019/10/8 16:37
 * @DESC
 **/
@Mapper
public interface SymbolInfoMapper {


    Integer saveSymbolInfo(ContractItem item);

    List<SymbolInfo> queryAllSymbolInfo();
}
