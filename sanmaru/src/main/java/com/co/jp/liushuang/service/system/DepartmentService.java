package com.co.jp.liushuang.service.system;

import java.util.List;

import com.co.jp.liushuang.persistence.entity.Department;

public interface DepartmentService {

    public void addDepartment(Department department);

    public void updateDepartment(Department department);

    public Department getDepartmentById(String departmentId);

    public List<Department> getDepartmentList(Department department);

    int deleteDepartment(String departmentId);
}