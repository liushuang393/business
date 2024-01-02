/**
 *
 */
package org.com.business.spring.boot.fw.base.web.rest;

import java.util.List;

import org.com.business.spring.boot.fw.base.common.form.DepartmentListForm;
import org.com.business.spring.boot.fw.base.service.system.DepartmentService;
import org.com.business.spring.boot.fw.persistence.entity.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liushuang
 *
 */
@RestController
@RequestMapping("/restApi")
public class CommonRestApi {

    @Autowired
    private DepartmentService departmentService;

    @RequestMapping("/departmentList")
    public List<Department> getDepartmentList(
            @RequestParam("departmentId") String departmentId) {
        DepartmentListForm department = new DepartmentListForm();
        department.setDepartmentId(departmentId);
        return departmentService.getDepartmentList(department, new Integer(1), new Integer(20)).getList();

    }
}
