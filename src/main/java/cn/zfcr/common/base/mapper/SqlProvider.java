package cn.zfcr.common.base.mapper;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.scripting.xmltags.IfSqlNode;
import org.apache.ibatis.scripting.xmltags.MixedSqlNode;
import org.apache.ibatis.scripting.xmltags.SqlNode;
import org.apache.ibatis.scripting.xmltags.StaticTextSqlNode;
import org.apache.ibatis.scripting.xmltags.WhereSqlNode;

import tk.mybatis.mapper.entity.EntityColumn;
import tk.mybatis.mapper.mapperhelper.EntityHelper;
import tk.mybatis.mapper.mapperhelper.MapperHelper;
import tk.mybatis.mapper.mapperhelper.MapperTemplate;

public class SqlProvider extends MapperTemplate{

	public SqlProvider(Class<?> mapperClass, MapperHelper mapperHelper) {
		super(mapperClass, mapperHelper);
	}
	
	public SqlNode findByDynamicAttr(MappedStatement ms){
		Class<?> entityClass =	getEntityClass(ms);
		setResultType(ms, entityClass);
		
		List<SqlNode> sqlNodes = new ArrayList<>();
		String columns = EntityHelper.getSelectColumns(entityClass);
		String tableName = EntityHelper.getEntityTable(entityClass).getName();
		String sqlSelect = "SELECT "+columns+" FROM "+tableName + " WHERE 1=1 "; // sql select部分
		StaticTextSqlNode selectNode = new StaticTextSqlNode(sqlSelect);
		sqlNodes.add(selectNode);
		
		// sql 动态条件部分
		Set<EntityColumn> entityColumns = EntityHelper.getColumns(entityClass);
		List<SqlNode> ifSqlNodes = new ArrayList<>();
		for (EntityColumn entityColumn : entityColumns) {
			StaticTextSqlNode contentSqlNode = new StaticTextSqlNode(" AND "+entityColumn.getColumn()+" = #{"+entityColumn.getColumn()+"}");
			String test = entityColumn.getColumn()+" != null && "+entityColumn.getColumn()+" !='' ";
			IfSqlNode ifSqlNode = new IfSqlNode(contentSqlNode, test);
			ifSqlNodes.add(ifSqlNode);
		}
		sqlNodes.add(new WhereSqlNode(ms.getConfiguration(), new MixedSqlNode(ifSqlNodes)));
		return new MixedSqlNode(sqlNodes);
	}

	public <T> String findAll(QueryParams<T> queryParams){
		String tableName = getTableName(queryParams.getEntity().getClass());
		String columns = "";
		if(queryParams.getColumns()!=null&&queryParams.getColumns().length>0){
			StringUtils.join(queryParams.getColumns(),",");
		}else{
			columns = "*";
		}
		return String.format("select %s from %s",columns,tableName);
	}
	
	public <T> String findById(Class<T> cla,Serializable id){
		String tableName = getTableName(cla);
		String pkName = getPkName(cla);
		return String.format("select * from %s where %s = #{id}", tableName, pkName);
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
	
	/**
	 * 取主键列的列名
	 * @param cla
	 * @return
	 */
	private <T> String getPkName(Class<T> cla){
		String pkName = "id";
		Field[] fields = cla.getDeclaredFields();
		for (Field field : fields) {
			Id idAnnotation = field.getAnnotation(Id.class);
			if(idAnnotation!=null){
				pkName = field.getName();
				return pkName;
			}
		}
		return pkName;
	}
}
