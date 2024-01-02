package org.com.business.spring.boot.fw.base.web.controller;

import javax.servlet.http.HttpSession;

import org.com.business.spring.boot.fw.base.BaseAction;
import org.com.business.spring.boot.fw.base.common.form.BZSP0001Form;
import org.com.business.spring.boot.fw.base.service.signup.BZSP0001Service;
import org.com.business.spring.boot.fw.core.annotation.Token;
import org.com.business.spring.boot.fw.core.i18n.LocaleMessageSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 申請入力画面。
 */
@Controller
@RequestMapping("/permit")
public class BZSP0001Controller extends BaseAction {

	private static final String FORM_BEAN = "BZSP0001Form";
	private static final String MAIN_URL = "/BZSP0001";
	private static final String MAIN_URL_INIT = MAIN_URL + "Display";
	private static final String HTML_VIEW = "/permit/BZSP0001";
	private static final String PARAMS_UPDATTN = "updatBtn";
	private static final String PARAMS_CLEAR = "clearBtn";
	private static final String FORWARD_VIEW_NEXT = "forward:/permit/BZSP0002Display";

	@Autowired
	LocaleMessageSourceService messageRsource;

	@Autowired
	@Qualifier(value = FORM_BEAN + "Validator")
	private Validator validator;

	@Autowired
	private BZSP0001Service service;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		if (this.validator.supports(binder.getTarget().getClass())) {
			binder.setValidator(this.validator); 
		}
	}

	@ModelAttribute(FORM_BEAN)
	public BZSP0001Form getForm() throws Exception {
		return initForm(BZSP0001Form.class);
	}

	@Token(save = true)
	@RequestMapping(value = MAIN_URL_INIT, method = {RequestMethod.GET,
			RequestMethod.POST})
	public String display(@ModelAttribute(FORM_BEAN) BZSP0001Form form,
			BindingResult result, Model model) {
		model.addAttribute(FORM_BEAN, form);

		return HTML_VIEW;
	}

	@RequestMapping(value = MAIN_URL, method = RequestMethod.POST, params = {
			PARAMS_UPDATTN})
	public String update(
			@Validated @ModelAttribute(FORM_BEAN) BZSP0001Form form,
			BindingResult result, Model model, HttpSession session) {

		model.addAttribute(FORM_BEAN, form);
		if (result.hasErrors()) {
			return HTML_VIEW;
		}

		// Serviceを呼び出しの実装
		service.signUpUser(form);

		return FORWARD_VIEW_NEXT;
	}

	@RequestMapping(value = MAIN_URL, method = {RequestMethod.GET,
			RequestMethod.POST}, params = {PARAMS_CLEAR})
	public String clear(Model model) throws Exception {
		model.addAttribute(FORM_BEAN, initForm(BZSP0001Form.class));

		return HTML_VIEW;
	}

}
