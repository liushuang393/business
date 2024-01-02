package org.com.business.spring.boot.fw.base.web.controller;

import org.com.business.spring.boot.fw.base.BaseAction;
import org.com.business.spring.boot.fw.base.common.form.BZSP0001Form;
import org.com.business.spring.boot.fw.core.annotation.Token;
import org.com.business.spring.boot.fw.core.i18n.LocaleMessageSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 申請完了画面。
 */
@Controller
@RequestMapping("/permit")
public class BZSP0002Controller extends BaseAction {

    private static final String FORM_BEAN = "BZSP0001Form";
    private static final String MAIN_URL = "/BZSP0002";
    private static final String MAIN_URL_INIT = MAIN_URL + "Display";
    private static final String HTML_VIEW = "/permit/BZSP0002";
    private static final String PARAMS_NEXTBTN = "nextBtn";
    private static final String FORWARD_VIEW_NEXT = "redirect:/welcome";

    @Autowired
    LocaleMessageSourceService messageRsource;

    @ModelAttribute(FORM_BEAN)
    public BZSP0001Form getForm() throws Exception {
        return initForm(BZSP0001Form.class);
    }

    @Token(save = true)
    @RequestMapping(value = MAIN_URL_INIT, method = { RequestMethod.GET, RequestMethod.POST })
    public String display(Model model) {

        return HTML_VIEW;
    }

    @Token(remove = true)
    @RequestMapping(value = MAIN_URL, method = RequestMethod.POST, params = { PARAMS_NEXTBTN })
    public String next() {

        return FORWARD_VIEW_NEXT;
    }

}
