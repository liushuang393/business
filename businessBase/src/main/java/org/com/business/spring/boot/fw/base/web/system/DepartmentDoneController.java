package org.com.business.spring.boot.fw.base.web.system;

import org.com.business.spring.boot.fw.base.BaseAction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 部署リスト画面処理。 Springのスコープはプロトタイプにすること。
 */
//@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@Controller
@RequestMapping("/system")
public class DepartmentDoneController extends BaseAction {

    private static final String MAIN_URL_INIT = "/departmentDoneDisplay";
    private static final String HTML_VIEW = "/system/departmentDone";

    @RequestMapping(value = MAIN_URL_INIT, method = { RequestMethod.POST })
    public String init() {

        return HTML_VIEW;
    }
}