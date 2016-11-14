package cn.zfcr.common.base.service.impl;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.Column;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;

import cn.zfcr.common.base.dao.IBaseDao;
import cn.zfcr.common.base.service.IBaseService;
import cn.zfcr.mybatis.page.PageInfo;
import cn.zfcr.mybatis.page.PageList;
import cn.zfcr.utils.ConvertUtils;
import tk.mybatis.mapper.entity.Example;

@Service
@Transactional
public class BaseServiceImpl<T> implements IBaseService<T>{
	
	@Resource
	protected IBaseDao<T> baseDao;
	
	@Override
	public List<T> findAll(){
		return baseDao.findAll();
	}
	
	@Override
	public List<T> findAll(Class<T> cla,String orderBy){
		Example example = new Example(cla);
		example.setOrderByClause(orderBy);
		return baseDao.findByDynamic(example);
	}
	
	@Override
	public List<T> findByDynamic(T obj){
		return baseDao.findByDynamic(obj);
	}
	
	@Override
	public List<T> findByDynamic(T obj,String orderBy){
		Example example = new Example(obj.getClass());
		dynamicWhere(example, obj); // 动态添加条件到查询中
		example.setOrderByClause(orderBy);
		return baseDao.findByDynamic(example);
	}
	
	@Override
	public PageList<T> queryPaging(T obj,int page,int rows){
		PageInfo pageInfo = new PageInfo(page, rows);
		List<T> list = baseDao.queryPaging(obj, pageInfo);
		PageList<T> pageList = (PageList<T>) list;
		return pageList;
	}
	
	/**
	 * 设置查询的动态条件，子类可自己实现
	 * @param example
	 * @param obj
	 */
	protected void dynamicWhere(Example example, T obj) {
		Field[] fields = obj.getClass().getDeclaredFields();
		for (Field field : fields) {
			Method method = ReflectionUtils.findMethod(obj.getClass(), "get"+StringUtils.capitalize(field.getName()));
			Object value = ReflectionUtils.invokeMethod(method, obj);
			if(!ConvertUtils.isEmptyOrDefault(value)){
				String colName = field.getName();
				Column colAnno = field.getAnnotation(Column.class);
				if(colAnno!=null && StringUtils.isNotEmpty(colAnno.name())){
					colName = colAnno.name();
				}
				example.createCriteria().andCondition(colName+" = ", value);
			}
		}
	}
	
}
