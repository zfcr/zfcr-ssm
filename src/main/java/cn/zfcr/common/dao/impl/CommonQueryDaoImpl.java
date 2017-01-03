package cn.zfcr.common.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import cn.zfcr.common.constants.MyBatisStatementConstans;
import cn.zfcr.common.dao.ICommonQueryDao;

@Repository
public class CommonQueryDaoImpl implements ICommonQueryDao {
	
	@Resource
	protected SqlSession sqlSession;
	
	@Override
	public List<?> query(Class<?> cla, String statementId, Object parameters){
		List<?> list = sqlSession.selectList(getSqlStatement(cla, statementId), parameters);
		return list;
	}
	
	@Override
	public Object queryFirstResult(Class<?> cla, Object parameters){
		return sqlSession.selectOne(getSqlStatement(cla, MyBatisStatementConstans.QUERY_FIRST_RESULT), parameters);
	}
	
	private String getSqlStatement(Class<?> cla, String statementId){
		return cla.getName() + "." + statementId;
	}

	@Override
	public Object queryFirstResult(Class<?> cla, String string, Object parameters) {
		return sqlSession.selectOne(getSqlStatement(cla, string), parameters);
	}
	
}
