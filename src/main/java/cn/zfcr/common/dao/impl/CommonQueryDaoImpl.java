package cn.zfcr.common.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import cn.zfcr.common.dao.ICommonQueryDao;

@Repository
public class CommonQueryDaoImpl implements ICommonQueryDao {
	
	@Resource
	protected SqlSessionTemplate sqlSession;
	
	@Override
	public List<?> findByStatementId(Class<?> cla, String statementId, Object parameters){
		List<?> list = sqlSession.selectList(getSqlStatement(cla, statementId), parameters);
		return list;
	}
	
	private String getSqlStatement(Class<?> cla, String statementId){
		return cla.getName() + "." + statementId;
	}
	
}
