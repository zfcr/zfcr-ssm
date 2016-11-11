package cn.zfcr.mybatis.provider.type;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.annotation.AnnotationConfigurationException;

public class SelectProviderType {

	public <T> String findBy(T obj){
		System.out.println(obj.getClass().getDeclaredFields());
		Field[] fields = obj.getClass().getDeclaredFields();
		for (Field field : fields) {
			try {
				Method method = obj.getClass().getMethod("get"+StringUtils.capitalize(field.getName()));
				System.out.println(method.invoke(obj));
			} catch (NoSuchMethodException | SecurityException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		return "select * from country where code = #{code} ";
	}
	
	public <T> String findById(Class<T> cla){
		String tableName = getTableName(cla);
		String idColName = getIdColName(cla);
		return String.format("select * from %s where %s = ? ", tableName , idColName);
	}
	
	public <T> String findAll(Class<T> cla){
		String sql = "select * from "+ getTableName(cla);
		return sql;
	}
	
	private <T> String getIdColName(Class<T> cla){
		Field[] fields = cla.getDeclaredFields();
		for (Field field : fields) {
			if(field.isAnnotationPresent(Id.class)){
				Column column = field.getAnnotation(Column.class);
				String colName = column.name();
				if(colName==null || "".equals(colName)){
					colName = field.getName();
				}
				return colName;
			}
		}
		throw new AnnotationConfigurationException("实体类没有指定主键字段！");
	}
	
	private <T> String getTableName(Class<T> cla){
		if(cla.isAnnotationPresent(Table.class)){
			Table table = cla.getAnnotation(Table.class);
			String tableName = table.name();
			if(tableName==null || "".equals(tableName)){
				tableName = cla.getSimpleName();
			}
			return tableName;
		}
		return cla.getSimpleName();
	}
	
}
