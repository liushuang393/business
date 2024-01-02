package com.co.jp.liushuang.persistence;

import java.util.List;

import com.co.jp.liushuang.persistence.entity.Department;

public interface DepartmentMapper {
    int deleteByPrimaryKey(String departmentId);

    int insert(Department record);

    int insertSelective(Department record);

    Department selectByPrimaryKey(String departmentId);

    int updateByPrimaryKeySelective(Department record);

    int updateByPrimaryKey(Department record);

    /**
     * @param department Department
     * @return
     */
    List<Department> selectList(Department department);
}