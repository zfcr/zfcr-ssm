package cn.zfcr.common.base.mapper;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.SelectProvider;

public interface BaseMapper {

	@SelectProvider(type=SqlProvider.class,method="findAll")
	public <T> List<Map<String,Object>> findAll(QueryParams<T> queryParams);
	
	@SelectProvider(type=SqlProvider.class,method="findById")
	public <T> Map<String,Object> findById(Class<T> cla,Serializable id);
	
	@SelectProvider(type=SqlProvider.class,method="dynamicSQL")
	public <T> T findByDynamicAttr(T obj);
}
