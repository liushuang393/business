package org.com.business.spring.boot.fw.base.web.system;

import org.com.business.spring.boot.common.BZConstants;
import org.com.business.spring.boot.fw.base.BaseAction;
import org.com.business.spring.boot.fw.base.common.BZBASEMessages;
import org.com.business.spring.boot.fw.base.common.form.DepartmentListForm;
import org.com.business.spring.boot.fw.base.service.system.DepartmentService;
import org.com.business.spring.boot.fw.pages.BZPageInfo;
import org.com.business.spring.boot.fw.persistence.entity.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * 部署リスト画面処理。 Springのスコープはプロトタイプにすること。
 */
@Controller
@RequestMapping("/system")
@SessionAttributes(types = { DepartmentListForm.class })
public class DepartmentListController extends BaseAction {

    private static final String FORM_BEAN = "departmentListForm";
    private static final String MAIN_URL = "/departmentList";
    private static final String MAIN_URL_INIT = MAIN_URL + "Display";
    private static final String PAGE_CHG_URL = "/system" + MAIN_URL + "Display";

    private static final String PARAMS_ADD = "addBtn";
    private static final String PARAMS_BACKBTN = "backbtn";

    private static final String HTML_VIEW = "/system/departmentList";
    private static final String HTML_SEARCH_VIEW = "/system/BZBS0001";
    private static final String FORWARD_VIEW_ADD = "forward:/system/departmentDisplay";
    private static final String FORWARD_VIEW_BACK = "forward:/system/BZBS0001Display";

    @Autowired
    private DepartmentService departmentService;

    //    @Autowired
    //    HttpSession session;

    @ModelAttribute(FORM_BEAN)
    public DepartmentListForm getDepartmentListForm() throws Exception {
        return super.initForm(DepartmentListForm.class);
    }

    @RequestMapping(value = MAIN_URL_INIT, method = { RequestMethod.POST, RequestMethod.GET })
    public String display(Model model, DepartmentListForm form, BindingResult result,
            @RequestParam(defaultValue = BZConstants.PAGE_NUM_DEFAULT_VALUE) Integer pageNum) {

        BZPageInfo<Department> pageInfo = departmentService.getDepartmentList(form, pageNum,
                super.getDefaultPageSize(0));

        if (pageInfo.getTotal() == 0L) {
            result.reject(BZBASEMessages.EBASW0002);
            return HTML_SEARCH_VIEW;
        }

        form.setDepartmentList(pageInfo.getList());
        model.addAttribute(FORM_BEAN, form);

        super.setPageInfo(model, pageInfo, PAGE_CHG_URL);

        return HTML_VIEW;
    }
    //uoc　start

    //uoc　end　
    @RequestMapping(value = MAIN_URL, method = { RequestMethod.POST }, params = { PARAMS_ADD })
    public String add(Model model, RedirectAttributes attributes) {
        return FORWARD_VIEW_ADD;
    }

    @RequestMapping(value = MAIN_URL, method = RequestMethod.POST, params = { PARAMS_BACKBTN })
    public String back(DepartmentListForm form, SessionStatus status) {

        return FORWARD_VIEW_BACK;
    }
}