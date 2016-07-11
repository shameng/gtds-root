package com.brother.gtds.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.brother.gtds.dao.BaseDao;
import com.brother.gtds.model.Teacher;
import com.brother.gtds.service.TeacherService;
import com.brother.gtds.utils.ValidationUtils;

@Service("teacherService")
public class TeacherServiceImpl extends BaseServiceImpl<Teacher> implements
		TeacherService {

	@Resource(name="teacherDao")
	public void setDao(BaseDao<Teacher> dao)
	{
		super.setDao(dao);
	}

	//查找出符合条件的Teacher
	@Override
	public List<Teacher> findByQuery(String idQuery, String nameQuery,
			String positionQuery, String typeQuery) {
		String hql = "from Teacher t where ";
		if(ValidationUtils.validateString(idQuery))
		{
			hql += "t.id = '" + idQuery + "' and ";
		}
		if(ValidationUtils.validateString(nameQuery))
		{
			hql += "t.name = '" + nameQuery + "' and ";
		}
		if(ValidationUtils.validateString(positionQuery))
		{
			hql += "t.position = '" + positionQuery + "' and ";
		}
		if(ValidationUtils.validateString(typeQuery))
		{
			hql += "t.type = '" + typeQuery + "' and ";
		}
		
		//如果查询条件都为空
		if(hql.equals("from Teacher t where "))
			hql = "from Teacher";
		else
			hql = hql.substring(0, hql.length() - 5);
		List<Teacher> list = this.findEntityByHQL(hql);
		//解决懒加载问题
		for(Teacher t : list)
		{
			t.getDepartment().getName();
		}
		return list;
	}

	//更新教师的指导类型
	@Override
	public void updateType(String id, Integer type) {
		this.getEntity(id).setType(type);
	}

}
