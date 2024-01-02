package org.com.business.spring.boot.fw.base.web.system;

import javax.servlet.http.HttpSession;

import org.com.business.spring.boot.common.BZConstants;
import org.com.business.spring.boot.fw.base.BaseAction;
import org.com.business.spring.boot.fw.base.common.form.DepartmentForm;
import org.com.business.spring.boot.fw.base.service.system.DepartmentService;
import org.com.business.spring.boot.fw.core.auth.model.UserLoginForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * 部署リスト画面処理。
 */
@Controller
@RequestMapping("/system")
@SessionAttributes(types = { DepartmentForm.class, UserLoginForm.class })
public class DepartmentConfController extends BaseAction {

    private static final String FORM_BEAN = "departmentForm";
    private static final String MAIN_URL = "/departmentConf";
    private static final String MAIN_URL_INIT = MAIN_URL + "Display";
    private static final String PARAMS_UPDATTN = "updatBtn";
    private static final String PARAMS_BACKBTN = "backBtn";

    private static final String HTML_VIEW = "/system/departmentConf";
    private static final String FORWARD_VIEW_NEXT = "forward:/system/departmentDoneDisplay";
    private static final String FORWARD_VIEW_BACK = "forward:/system/departmentDisplay";

    @Autowired
    private DepartmentService departmentService;

    @RequestMapping(value = MAIN_URL_INIT, method = { RequestMethod.POST,RequestMethod.GET })
    public String init(@SessionAttribute(FORM_BEAN) DepartmentForm form, Model model) {
        model.addAttribute(FORM_BEAN, form);
        return HTML_VIEW;
    }

    @RequestMapping(value = MAIN_URL, method = RequestMethod.POST, params = { PARAMS_UPDATTN })
    public String update(
            @ModelAttribute(BZConstants.SESSION_FORM_NAME) UserLoginForm user,
            @ModelAttribute(FORM_BEAN) DepartmentForm form,
            Model model, HttpSession session) {

        if (form.isUpdateFlag()) {
            departmentService.updateDepartment(user.getUserId(), form);
        } else {
            departmentService.addDepartment(user.getUserId(), form);
        }
        return FORWARD_VIEW_NEXT;
    }

    @RequestMapping(value = MAIN_URL, method = RequestMethod.POST, params = { PARAMS_BACKBTN })
    public String back(@ModelAttribute(FORM_BEAN) DepartmentForm form, Model model) {
        return FORWARD_VIEW_BACK;
    }
}