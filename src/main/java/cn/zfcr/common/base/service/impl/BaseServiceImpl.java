package cn.zfcr.common.base.service.impl;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.persistence.Column;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;

import cn.zfcr.common.base.dao.IBaseDao;
import cn.zfcr.common.base.service.IBaseService;
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
	
	public List<T> findAll(Class<T> cla,String orderBy){
		Example example = new Example(cla);
		example.setOrderByClause(orderBy);
		return baseDao.findByExample(example);
	}
	
	@Override
	public List<T> dynamicFind(T obj){
		return baseDao.dynamicFind(obj);
	}
	
	@Override
	public Map<String, Object> findOfPage(T obj,int page,int rows){
		Map<String,Object> result = new HashMap<>();
		long count = baseDao.dynamicCount(obj);
		result.put("count", count);
		if(count<=0){
			result.put("list", new ArrayList<T>());
			return result;
		}
		int beginRow = (page - 1) * rows;
		int endRow = page * rows;
		RowBounds rowBounds = new RowBounds(beginRow,endRow);
		List<T> list = baseDao.findOfPage(obj, rowBounds);
		result.put("list", list);
		return result;
	}
	
//	public List<T> dynamicFind(T obj){
//		return baseDao.dynamicFind(obj);
//		
//		Example example = new Example(obj.getClass());
//		dynamicWhere(example, obj); // 动态添加条件到查询中
//		
//		return baseDao.findByExample(example);
//	}
	
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
	
	public List<T> findByExample(T obj,String orderBy){
		Example example = new Example(obj.getClass());
		dynamicWhere(example, obj); // 动态添加条件到查询中
		example.setOrderByClause(orderBy);
		return baseDao.findByExample(example);
	}
	
	
}
