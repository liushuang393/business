package org.com.business.spring.boot;

import java.util.Date;
import java.util.List;

import org.com.business.spring.boot.fw.base.common.form.DepartmentListForm;
import org.com.business.spring.boot.fw.base.service.system.DepartmentService;
import org.com.business.spring.boot.fw.base.web.controller.BZBS0001Controller;
import org.com.business.spring.boot.fw.pages.BZPageInfo;
import org.com.business.spring.boot.fw.persistence.entity.Department;
import org.com.business.spring.boot.fw.persistence.mapper.CustomDepartmentMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BusinessBaseApplicationTests {
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    CustomDepartmentMapper customDepartmentMapper;
    @Autowired
    BZBS0001Controller c;

	@Test
	public void contextLoads() {
	    Department d = new Department();
	    Date dd= new Date(119,4,13,2,0,0);
	    d.setEstablishmentDay(dd);
	    System.out.println(dd);
	    d.setDepartmentId("7");
	    List<Department> list = customDepartmentMapper.selectList(d);

	    System.out.println("検索件数：："+list.size());

	    DepartmentListForm form = new DepartmentListForm();
	    Date ddd= new Date(119,4,13,10,0,0);
	    System.out.println(ddd);
        form.setEstablishmentDay(ddd);
	    form.setDepartmentId("7");
	    BZPageInfo<Department> pageInfo = departmentService.getDepartmentList(form, 1,
               10);

	    System.out.println("S検索件数：："+pageInfo.getTotal());

	}

}
