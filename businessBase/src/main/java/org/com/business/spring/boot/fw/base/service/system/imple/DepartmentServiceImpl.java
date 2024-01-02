package org.com.business.spring.boot.fw.base.service.system.imple;

import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import org.com.business.spring.boot.common.utils.BZBeanUtils;
import org.com.business.spring.boot.fw.base.common.form.DepartmentForm;
import org.com.business.spring.boot.fw.base.common.form.DepartmentListForm;
import org.com.business.spring.boot.fw.base.service.system.DepartmentService;
import org.com.business.spring.boot.fw.pages.BZPageInfo;
import org.com.business.spring.boot.fw.persistence.entity.Department;
import org.com.business.spring.boot.fw.persistence.mapper.CustomDepartmentMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;

@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService {

    @Resource
    CustomDepartmentMapper customdepartmentMapper;

    @Override
    public Department getDepartmentById(String id) {
        return customdepartmentMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int addDepartment(String userId, DepartmentForm form) {
        Department department = new Department();
        BZBeanUtils.copyBeanProperties(form, department);

        department.setCreateuserid(userId);
        department.setCreatetime(Calendar.getInstance().getTime());
        department.setUpdateuserid(userId);
        department.setUpdatetime(Calendar.getInstance().getTime());
        return customdepartmentMapper.insert(department);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateDepartment(String userId, DepartmentForm form) {

        Department department = customdepartmentMapper.selectByPrimaryKey(form.getDepartmentId());

        if (department != null) {
            BZBeanUtils.copyBeanProperties(form, department);
            department.setUpdateuserid(userId);
            department.setUpdatetime(Calendar.getInstance().getTime());
            return customdepartmentMapper.updateByPrimaryKey(department);
        } else {
            return 0;
        }

    }

    @Override
    public BZPageInfo<Department> getDepartmentList(DepartmentListForm form, Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);

        Department department = new Department();
        BZBeanUtils.copyBeanProperties(form, department);

        List<Department> list = customdepartmentMapper.selectList(department);
        return new BZPageInfo<>(list);
    }

    /* (非 Javadoc)
     * @see com.co.jp.liushuang.service.system.s01department.DepartmentService#deleteDepartment(java.lang.String)
     */
    @Override
    public int deleteDepartment(String departmentId) {
        return 0;
    }

}
