package com.tigerbrokers.quant.storage.dao;

import com.tigerbrokers.quant.model.data.Contract;
import com.tigerbrokers.quant.model.entity.SymbolInfo;
import com.tigerbrokers.quant.storage.mapper.SymbolInfoMapper;
import com.tigerbrokers.stock.openapi.client.https.domain.contract.item.ContractItem;
import org.apache.ibatis.session.SqlSession;

/**
 * Description:
 *
 * @author kevin
 * @date 2019/09/02
 */
public class SymbolInfoDAO extends BaseDAO {

    public boolean saveSymbol(ContractItem item) {
        SqlSession sqlSession = openSession();
        Integer i = sqlSession.getMapper(SymbolInfoMapper.class).saveSymbol(item);
        sqlSession.commit();
        sqlSession.close();
        return i > 0;
    }

    public boolean updateSymbol(ContractItem item) {
        SqlSession sqlSession = openSession();
        Integer i = sqlSession.getMapper(SymbolInfoMapper.class).updateSymbol(item);
        sqlSession.commit();
        sqlSession.close();
        return i > 0;
    }
}
