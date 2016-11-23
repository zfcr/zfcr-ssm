package cn.zfcr.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import cn.zfcr.mybatis.provider.type.DeleteProviderType;
import cn.zfcr.mybatis.provider.type.SelectProviderType;

public interface CountryMapper<T> {

	@SelectProvider(type=SelectProviderType.class,method="findBy")
	public T findBy(T obj);
	
	@SelectProvider(type=SelectProviderType.class,method="findAll")
	public List<Map<String,Object>> findAll(Class<T> cla);
	
	@DeleteProvider(type=DeleteProviderType.class,method="delete")
	public void delete(@Param("id")long id);
}
