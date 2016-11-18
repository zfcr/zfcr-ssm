package cn.zfcr.common.base.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import cn.zfcr.common.base.dao.IBaseDao;
import cn.zfcr.common.constants.MyBatisStatementConstans;
import cn.zfcr.entity.City;
import cn.zfcr.mybatis.page.PageInfo;
import cn.zfcr.utils.ReflectionUtils;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.EntityColumn;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.mapperhelper.EntityHelper;

@Repository
public class CommonDaoImpl<T> implements IBaseDao<T> {
	
	private Class<?> cla=null;
	
	private String mybatisNamespace;
	
	private CommonDaoImpl() {
		
	}
	
	public CommonDaoImpl(Class<?> cla) {
		this.cla = cla;
		this.mybatisNamespace = this.cla.getName();
	}

	@Resource
	protected SqlSessionTemplate sqlSession;
	
	@Resource
	protected Mapper<T> mapper;
	
	@Override
	public List<T> findAll(){
		List<T> list = sqlSession.selectList(getSqlStatement(MyBatisStatementConstans.SELECT_STATEMENT_FINDALL));
		return list;
	}
	
	@Override
	public T findByPk(String pkValue){
		return mapper.selectByPrimaryKey(pkValue);
	}
	
	@Override
	public List<T> findByDynamic(T obj){
		return mapper.select(obj);
	}
	
	@Override
	public long dynamicCount(T obj){
		return mapper.selectCount(obj);
	}
	
	@Override
	public List<T> findByDynamic(Example example){
		return mapper.selectByExample(example);
	}
	
	@Override
	public List<T> queryPaging(T obj, PageInfo pageInfo){
		return mapper.selectByRowBounds(obj, pageInfo);
	}
	
	@Override
	public int insert(T obj) {
		return mapper.insert(obj);
	}
	
	@Override
	public int insertSelective(T obj) {
		return mapper.insertSelective(obj);
	}
	
	@Override
	public int updateByPk(T obj) {
		return mapper.updateByPrimaryKey(obj);
	}
	
	@Override
	public int updateByExample(T obj, Example example) {
		return mapper.updateByExample(obj, example);
	}
	
	@Override
	public int updateByExampleSelective(T obj, Example example) {
		return mapper.updateByExampleSelective(obj, example);
	}
	
	@Override
	public int updateByPkSelective(T obj) {
		Example example = getExample(obj);
		return mapper.updateByExampleSelective(obj, example);
	}
	
	@Override
	public int deleteByPk(Object key){
		return mapper.deleteByPrimaryKey(key);
	}
	
	@Override
	public int delete(T obj){
		return mapper.delete(obj);
	}

	/**
	 * 返回带主键条件的example
	 * @param obj
	 * @return
	 */
	protected Example getExample(T obj) {
		Example example = new Example(obj.getClass());
		Set<EntityColumn> pkColumns = EntityHelper.getPKColumns(obj.getClass());
		for (EntityColumn entityColumn : pkColumns) {
			Object value = ReflectionUtils.invokeGetMethodByProp(obj, entityColumn.getColumn());
			example.createCriteria().andCondition(entityColumn.getColumn(), value);
		}
		return example;
	}
	
	private Class<?> getEntityClass() {
		Type[] types = this.getClass().getGenericInterfaces();
		for (Type type : types) {
			if (type instanceof ParameterizedType) {
				ParameterizedType t = (ParameterizedType) type;
				Class<?> returnType = (Class<?>) t.getActualTypeArguments()[0];
				return returnType;
			}
		}
        throw new RuntimeException("无法获取Mapper<T>泛型类型:" );
    }
	
	private String getSqlStatement(String statementId){
		return mybatisNamespace + "." + statementId;
	}
	
	public static void main(String[] args) {
		IBaseDao<City> baseDao = new CommonDaoImpl<City>();
		Type[] types = baseDao.getClass().getGenericInterfaces();
		for (Type type : types) {
			if (type instanceof ParameterizedType) {
				ParameterizedType t = (ParameterizedType) type;
				Type[] typeArray = t.getActualTypeArguments();
				for (Type type2 : typeArray) {
					System.out.println(type2);
				}
				Class<?> returnType = (Class<?>) t.getActualTypeArguments()[0];
//				System.out.println(returnType);
			}
		}
	}
	
}
