package org.com.business.spring.boot.fw.persistence.mapper;

import java.util.List;

import org.com.business.spring.boot.fw.persistence.entity.Department;

public interface CustomDepartmentMapper extends DepartmentMapper {

    /**
     * @param department
     * @return
     */
    List<Department> selectList(Department department);

}