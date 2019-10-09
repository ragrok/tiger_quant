package com.tigerbrokers.quant.storage.dao;

import com.tigerbrokers.quant.model.entity.SymbolInfo;
import com.tigerbrokers.quant.storage.mapper.SymbolInfoMapper;
import com.tigerbrokers.stock.openapi.client.https.domain.contract.item.ContractItem;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * Description:
 *
 * @author kevin
 * @date 2019/09/02
 */
public class SymbolInfoDAO extends BaseDAO {

    public boolean saveSymbol(ContractItem item) {
        SqlSession sqlSession = openSession();
        Integer i = sqlSession.getMapper(SymbolInfoMapper.class).saveSymbolInfo(item);
        sqlSession.commit();
        sqlSession.close();
        return i > 0;
    }

    public List<SymbolInfo> queryAllSymbolInfo() {
        SqlSession sqlSession = openSession();
        List<SymbolInfo> items = sqlSession.getMapper(SymbolInfoMapper.class).queryAllSymbolInfo();
        sqlSession.commit();
        sqlSession.close();
        return items;
    }
}
