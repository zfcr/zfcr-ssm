package cn.zfcr.common.base.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;

import cn.zfcr.common.base.dao.IBaseDao;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

@Repository
public class BaseDaoImpl<T> implements IBaseDao<T> {

	@Resource
	protected Mapper<T> mapper;
	
	@Resource
	protected SqlSessionFactory sqlSessionFactory;
	
	@Override
	public List<T> findAll(){
		return mapper.selectAll();
	}
	
	@Override
	public T findByPk(String pkValue){
		return mapper.selectByPrimaryKey(pkValue);
	}
	
	@Override
	public List<T> dynamicFind(T obj){
		return mapper.select(obj);
	}
	
	@Override
	public List<T> findOfPage(T obj, RowBounds rowBounds){
		return mapper.selectByRowBounds(obj, rowBounds);
	}
	
	@Override
	public long dynamicCount(T obj){
		return mapper.selectCount(obj);
	}
	
	@Override
	public List<T> findByExample(Example example){
		return mapper.selectByExample(example);
	}
}
