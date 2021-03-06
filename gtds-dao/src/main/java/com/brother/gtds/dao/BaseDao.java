package com.brother.gtds.dao;

import java.util.List;

/**
 * BaseDao 接口
 */
public interface BaseDao<T> {
	//写操作
	public void saveEntity(T t);
	public void updateEntity(T t);
	public void saveOrUpdateEntity(T t);
	public void deleteEntity(T t);
	public void BatchEntityByHQL(String hql, Object... objects);
	//执行sql语句
	public void executeSql(String sql, Object... objects);
	
	//读操作
	public T loadEntity(Integer id);
	public T loadEntity(String id);
	public T getEntity(Integer id);
	public T getEntity(String id);
	public List<T> findEntityByHQL(String hql, Object... objects);
	//单值检索，要确保查询结果有且只有一条记录
	public Object uniqueResult(String hql, Object... objects);
	//执行sql语句，可以通过制定clazz是否为空指定是否封装为实体
	public List<T> executeSQLQuery(Class<?> clazz, String sql, Object...objects);
}
