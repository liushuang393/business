package com.co.jp.liushuang.service.system.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.co.jp.liushuang.persistence.DepartmentMapper;
import com.co.jp.liushuang.persistence.entity.Department;
import com.co.jp.liushuang.service.system.DepartmentService;

@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService {

    private static final Logger logger = LoggerFactory.getLogger(DepartmentServiceImpl.class);

    @Resource
    DepartmentMapper departmentMapper;

    @Override
    public Department getDepartmentById(String id) {
        logger.debug("開始：{}fff", "init");
        logger.info("UserServiceImpl.getUserById()：{}", id);

        Department department = departmentMapper.selectByPrimaryKey(id);

        logger.debug("終了：{}", "init");
        return department;
    }

    @Override
    public void addDepartment(Department Department) {
        departmentMapper.insert(Department);
    }

    @Override
    public void updateDepartment(Department Department) {
        departmentMapper.updateByPrimaryKey(Department);

    }

    @Override
    public List<Department> getDepartmentList(Department department) {
        return departmentMapper.selectList(department);
    }

    /* (非 Javadoc)
     * @see com.co.jp.liushuang.service.system.s01department.DepartmentService#deleteDepartment(java.lang.String)
     */
    @Override
    public int deleteDepartment(String departmentId) {
        return 0;
    }

}
