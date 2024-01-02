package org.com.business.spring.boot.fw.base.web.system;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.com.business.spring.boot.common.utils.BZBeanUtils;
import org.com.business.spring.boot.fw.base.BaseAction;
import org.com.business.spring.boot.fw.base.common.form.DepartmentForm;
import org.com.business.spring.boot.fw.base.service.system.DepartmentService;
import org.com.business.spring.boot.fw.core.annotation.Token;
import org.com.business.spring.boot.fw.persistence.entity.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.alibaba.druid.util.StringUtils;

/**
 * 部署画面処理。 Springのスコープはプロトタイプにすること。
 */
@Controller
@RequestMapping("/system")
@SessionAttributes(types = { DepartmentForm.class })
public class DepartmentController extends BaseAction {
    private static final String FORM_BEAN = "departmentForm";
    private static final String MAIN_URL = "/department";
    private static final String MAIN_URL_INIT = MAIN_URL+"Display";
    private static final String PARAMS_NEXTBTN = "nextBtn";
    private static final String PARAMS_BACKBTN = "backbtn";

    private static final String HTML_VIEW = "/system/departmentEdit";
    private static final String FORWARD_VIEW_TO_CONF = "forward:/system/departmentConfDisplay";
    private static final String FORWARD_VIEW_BACK = "forward:/system/departmentListDisplay";

    @Autowired
    private DepartmentService departmentService;

    @ModelAttribute(FORM_BEAN)
    public DepartmentForm getDepartmentForm() throws Exception {
        return super.initForm(DepartmentForm.class);
    }

    @Token(save = true)
    @RequestMapping(value = MAIN_URL_INIT, method = {  RequestMethod.GET, RequestMethod.POST })
    public String init(@ModelAttribute(FORM_BEAN) DepartmentForm form, Model model) {
        model.addAttribute(FORM_BEAN, form);
        return HTML_VIEW;
    }

    @Token(save = true)
    @RequestMapping(value = MAIN_URL + "/{departmentId}/link", method = { RequestMethod.GET })
    public String editInit(@PathVariable String departmentId, @ModelAttribute(FORM_BEAN) DepartmentForm form,
            Model model) {
        form.setUpdateFlag(true);

        if (!StringUtils.isEmpty(departmentId)) {
            Department department = departmentService.getDepartmentById(departmentId);
            BZBeanUtils.copyBeanProperties(department, form);

        }
        model.addAttribute(FORM_BEAN, form);
        return HTML_VIEW;
    }

    @Token(remove = true)
    @RequestMapping(value = MAIN_URL, method = RequestMethod.POST, params = { PARAMS_NEXTBTN })
    public String next(@Valid @ModelAttribute(FORM_BEAN) DepartmentForm form,
            BindingResult result, Model model) {

        model.addAttribute(FORM_BEAN, form);
        if (result.hasErrors()) {
            return HTML_VIEW;
        }

        return FORWARD_VIEW_TO_CONF;
    }

    @Token(remove = true)
    @RequestMapping(value = MAIN_URL, method = RequestMethod.POST, params = { PARAMS_BACKBTN })
    public String back(SessionStatus status, HttpSession session) {

        status.isComplete();
        session.removeAttribute(FORM_BEAN);

        return FORWARD_VIEW_BACK;
    }

}