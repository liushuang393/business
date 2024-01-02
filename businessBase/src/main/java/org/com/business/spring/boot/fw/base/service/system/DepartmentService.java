package org.com.business.spring.boot.fw.base.service.system;

import org.com.business.spring.boot.fw.base.common.form.DepartmentForm;
import org.com.business.spring.boot.fw.base.common.form.DepartmentListForm;
import org.com.business.spring.boot.fw.pages.BZPageInfo;
import org.com.business.spring.boot.fw.persistence.entity.Department;

public interface DepartmentService {

    public int addDepartment(String userId,DepartmentForm department);

    public int updateDepartment(String userId,DepartmentForm department);

    public Department getDepartmentById(String departmentId);

    public BZPageInfo<Department> getDepartmentList(DepartmentListForm department,Integer page, Integer pageSize);

    public int deleteDepartment(String departmentId);
}