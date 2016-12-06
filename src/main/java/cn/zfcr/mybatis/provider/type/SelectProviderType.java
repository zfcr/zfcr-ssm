package cn.zfcr.mybatis.provider.type;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.mapping.MappedStatement;
import org.springframework.core.annotation.AnnotationConfigurationException;

import tk.mybatis.mapper.mapperhelper.MapperHelper;
import tk.mybatis.mapper.mapperhelper.MapperTemplate;

public class SelectProviderType extends MapperTemplate{
	
	public SelectProviderType(Class<?> mapperClass, MapperHelper mapperHelper) {
		super(mapperClass, mapperHelper);
	}
	public String selectByPk(MappedStatement ms) {
		System.out.println(ms.getParameterMap());
		return "select * from City where id = 2";
	}

}
