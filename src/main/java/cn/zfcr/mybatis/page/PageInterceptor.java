package cn.zfcr.mybatis.page;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.executor.ErrorContext;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.ExecutorException;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.ParameterMode;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.property.PropertyTokenizer;
import org.apache.ibatis.scripting.xmltags.ForEachSqlNode;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.TypeHandler;
import org.apache.ibatis.type.TypeHandlerRegistry;

import cn.zfcr.system.utils.SqlUtils;

/**  
 * Package: cn.zfcr.mybatis.page  
 *  
 * File: PageInterceptor.java   
 * 
 * 分页拦截器，拦截所有查询，如果需要分页，则进行分页，返回分页结果集
 *  
 * Author: zf   Email: zhangfeng2124@163.com
 * Date: 2016年11月9日 上午10:59:01 
 *  
 * Copyright @ 2016 Corpration Name  
 *   
 */
@Intercepts({@Signature(type = Executor.class, method = "query",
		args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})})
public class PageInterceptor implements Interceptor{

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		MappedStatement ms = (MappedStatement) invocation.getArgs()[0];
		Object entityObj = invocation.getArgs()[1];
		RowBounds rowBounds = (RowBounds) invocation.getArgs()[2];
		
		// 如果不分页，直接调用查询
		if(!(rowBounds instanceof PageInfo)){
			return invocation.proceed();
		}
		
		PageInfo pageInfo = (PageInfo) rowBounds;
		BoundSql boundSql = ms.getBoundSql(entityObj);
		if(StringUtils.isEmpty(boundSql.getSql())){
			return null;
		}
		int count = count(ms, boundSql, entityObj);
		
		List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
		String pageSql = getPageSql(ms, boundSql.getSql(), pageInfo, parameterMappings);
		SqlSource sqlSource = new SqlSource() {
			
			@Override
			public BoundSql getBoundSql(Object parameterObject) {
				return new BoundSql(ms.getConfiguration(), pageSql, parameterMappings, boundSql.getParameterObject());
			}
		};
		MappedStatement.Builder builder = new MappedStatement.Builder(ms.getConfiguration(), ms.getId(), sqlSource, ms.getSqlCommandType());
		builder.resource(ms.getResource());
		builder.fetchSize(ms.getFetchSize());
		builder.statementType(ms.getStatementType());
		builder.keyGenerator(ms.getKeyGenerator());
		if (ms.getKeyProperties() != null) {
			for (String keyProperty : ms.getKeyProperties()) {
				builder.keyProperty(keyProperty);
			}
		}
		builder.timeout(ms.getTimeout());
		builder.parameterMap(ms.getParameterMap());
		builder.resultMaps(ms.getResultMaps());
		builder.cache(ms.getCache());
		builder.useCache(ms.isUseCache());
		invocation.getArgs()[0] = builder.build();
		Object result = invocation.proceed();
		PageList<Object> list = new PageList<>((List<?>)result, pageInfo.getPage(), pageInfo.getPageRows(), count);
		return list;
	}

	/**
	 * 得到分页的查询sql
	 * @param sql
	 * @param pageInfo
	 * @param parameterMappings 
	 * @return
	 */
	private String getPageSql(MappedStatement ms, String sql, PageInfo pageInfo, List<ParameterMapping> parameterMappings) {
		StringBuilder pageSql = new StringBuilder(sql);
		String sqlFormate = "select * from (%s) temp limit %s,%s ";
		if(StringUtils.isNotEmpty(pageInfo.getOrderByClause())){
			sqlFormate += "ORDER BY " + pageInfo.getOrderByClause();
		}
		return String.format(sqlFormate, pageSql.toString(), pageInfo.getOffset(), pageInfo.getLimit());
	}

	/**
	 * 统计行数
	 * @param ms
	 * @param sql
	 * @param entityObj
	 * @return
	 * @throws SQLException 
	 */
	private int count(MappedStatement ms, BoundSql boundSql, Object entityObj) throws SQLException {
		Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
        	conn = ms.getConfiguration().getEnvironment().getDataSource().getConnection();
        	String countSql = SqlUtils.convertCountSql(boundSql.getSql());
        	ps = conn.prepareStatement(countSql);
            BoundSql countBS = new BoundSql(ms.getConfiguration(), countSql,
                    boundSql.getParameterMappings(), entityObj);
            setParameters(ps, ms, countBS, entityObj);
            rs = ps.executeQuery();
            int count = 0;
            if (rs.next()) {
                count = rs.getInt(1);
            }
            return count;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
            	ps.close();
            }
            if (conn != null) {
            	conn.close();
            }
        }
	}
	
	/**
	 * 设置参数值
	 * @param ps
	 * @param mappedStatement
	 * @param boundSql
	 * @param parameterObject
	 * @throws SQLException
	 */
	@SuppressWarnings("unchecked")
	private void setParameters(PreparedStatement ps, MappedStatement mappedStatement, BoundSql boundSql, Object parameterObject) throws SQLException {
        ErrorContext.instance().activity("setting parameters").object(mappedStatement.getParameterMap().getId());
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        if (parameterMappings != null) {
            Configuration configuration = mappedStatement.getConfiguration();
            TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
            MetaObject metaObject = parameterObject == null ? null :
                    configuration.newMetaObject(parameterObject);
            for (int i = 0; i < parameterMappings.size(); i++) {
                ParameterMapping parameterMapping = parameterMappings.get(i);
                if (parameterMapping.getMode() != ParameterMode.OUT) {
                    Object value;
                    String propertyName = parameterMapping.getProperty();
                    PropertyTokenizer prop = new PropertyTokenizer(propertyName);
                    if (parameterObject == null) {
                        value = null;
                    } else if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
                        value = parameterObject;
                    } else if (boundSql.hasAdditionalParameter(propertyName)) {
                        value = boundSql.getAdditionalParameter(propertyName);
                    } else if (propertyName.startsWith(ForEachSqlNode.ITEM_PREFIX) && boundSql.hasAdditionalParameter(prop.getName())) {
                        value = boundSql.getAdditionalParameter(prop.getName());
                        if (value != null) {
                            value = configuration.newMetaObject(value).getValue(propertyName.substring(prop.getName().length()));
                        }
                    } else {
                        value = metaObject == null ? null : metaObject.getValue(propertyName);
                    }
                    @SuppressWarnings("rawtypes")
					TypeHandler typeHandler = parameterMapping.getTypeHandler();
                    if (typeHandler == null) {
                        throw new ExecutorException("There was no TypeHandler found for parameter " + propertyName + " of statement " + mappedStatement.getId());
                    }
                    typeHandler.setParameter(ps, i + 1, value, parameterMapping.getJdbcType());
                }
            }
        }
    }

	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties properties) {
		
	}

}
