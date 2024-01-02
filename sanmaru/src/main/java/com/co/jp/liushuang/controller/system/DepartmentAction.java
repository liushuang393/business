package com.co.jp.liushuang.controller.system;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.junit.runners.Parameterized.Parameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.co.jp.liushuang.common.Messages;
import com.co.jp.liushuang.controller.form.DepartmentForm;
import com.co.jp.liushuang.controller.form.UserLoginForm;
import com.co.jp.liushuang.core.BaseAction;
import com.co.jp.liushuang.persistence.entity.Department;
import com.co.jp.liushuang.service.system.DepartmentService;

/**
 * 部署画面処理。 Springのスコープはプロトタイプにすること。
 */
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@Controller
@RequestMapping("/department")
@SessionAttributes({ "departmentForm" })
public class DepartmentAction extends BaseAction {

    @Resource()
    private DepartmentService departmentService;
    @Autowired
    private ResourceBundleMessageSource messageSource;

    @Autowired
    HttpSession session;

    @RequestMapping("/departmentListInit.do")
    public ModelAndView loginInit(Model model) {

        DepartmentForm form = new DepartmentForm();
        List<Department> departmentList = departmentService.getDepartmentList(new Department());
        form.setDepartmentList(departmentList);
        model.addAttribute("departmentForm", form);
        // 結果コードを返す
        return new ModelAndView("/system/s01department/s01department01_list");
    }

    @RequestMapping(value = "/addInit.do", method = RequestMethod.POST)
    public String login(Model model) {
        return "/system/s01department/s01department02_edit";
    }

    @RequestMapping(value = "/editInit.do", method = RequestMethod.GET)
    public ModelAndView editInit(
            @RequestParam(value = "departmentId", required = true, defaultValue = "") String departmentId,
            @ModelAttribute("departmentForm") DepartmentForm form,
            Model model) {
        Department department = departmentService.getDepartmentById(departmentId);
        form.setDepartmentId(department.getDepartmentId());
        form.setDepartmentName(department.getDepartmentName());

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        form.setEstablishmentDate(dateFormat.format(department.getEstablishmentDate()));
        form.setResponsiblePerson(department.getResponsiblePerson());

        model.addAttribute("departmentForm", form);
        return new ModelAndView("/system/s01department/s01department02_edit");
    }

    @RequestMapping(value = "/conf.do", method = RequestMethod.POST)
    public String conf(@Valid @ModelAttribute("departmentForm") DepartmentForm form,
            Errors result, Model model) {

        if (result.hasErrors()) {
            result.reject(Messages.MESSAGE_EU000001);
            return "/system/s01department/s01department02_edit";
        }
        return "/system/s01department/s01department03_conf";
    }

    @RequestMapping(value = "/update.do", method = RequestMethod.POST)
    public String update(@Valid @ModelAttribute("departmentForm") DepartmentForm form,
            Errors result, Model model) {

        Department department = departmentService.getDepartmentById(form.getDepartmentId());
        if (department == null) {

            department = new Department();
            // 部署ID
            department.setDepartmentId(form.getDepartmentId());
            // 部署名
            department.setDepartmentName(form.getDepartmentName());
            // 責任者
            department.setResponsiblePerson(form.getResponsiblePerson());

            Calendar c1 = Calendar.getInstance();
            String[] dates = form.getEstablishmentDate().split("-");
            c1.set(Integer.parseInt(dates[0]),
                    Integer.parseInt(dates[1]), Integer.parseInt(dates[2]));
            //成立日
            department.setEstablishmentDate(c1.getTime());



            //共通項目
            UserLoginForm user = (UserLoginForm) session.getAttribute("logInUser");

            department.setCreateuserid(user.getUserId());
            department.setCreatetime(Calendar.getInstance().getTime());
            department.setUpdateuserid(user.getUserId());
            //department.setUpdatetime(Calendar.getInstance().getTime());

            //BeanUtils.copyProperties(baceEntiy, entiy);

            department.setResponsiblePerson(form.getResponsiblePerson());
            departmentService.addDepartment(department);
        } else {
            // BeanUtils.copyBeanProperties(department, form);

            // 部署ID
            department.setDepartmentId(form.getDepartmentId());
            // 部署名
            department.setDepartmentName(form.getDepartmentName());
            // 責任者
            department.setResponsiblePerson(form.getResponsiblePerson());

            Calendar c1 = Calendar.getInstance();
            String[] dates = form.getEstablishmentDate().split("-");
            c1.set(Integer.parseInt(dates[0]),
                    Integer.parseInt(dates[1]), Integer.parseInt(dates[2]));
            c1.add(Calendar.MONTH, -1);
            //成立日
            department.setEstablishmentDate(c1.getTime());

            //共通項目
            UserLoginForm user = (UserLoginForm) session.getAttribute("logInUser");
            department.setUpdateuserid(user.getUserId());
            if (form.getUpdatetime() == null) {
                department.setUpdatetime(Calendar.getInstance().getTime());
            } else {
                department.setUpdatetime(form.getUpdatetime());
            }

            departmentService.updateDepartment(department);
        }

        return "/system/s01department/s01department04_done";
    }
}