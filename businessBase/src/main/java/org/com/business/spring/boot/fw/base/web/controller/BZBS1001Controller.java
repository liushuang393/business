package org.com.business.spring.boot.fw.base.web.controller;

import javax.servlet.http.HttpSession;

import org.com.business.spring.boot.common.BZConstants;
import org.com.business.spring.boot.common.BZMessages;
import org.com.business.spring.boot.fw.base.BaseAction;
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
import org.springframework.web.bind.annotation.SessionAttributes;

import org.springframework.web.bind.support.SessionStatus;

import org.com.business.spring.boot.fw.base.common.form.BZBS1001SerchForm;
/**
 * ポスト検索画面。
 */
@Controller
@RequestMapping("/system")
@SessionAttributes(types = {BZBS1001SerchForm.class})
public class BZBS1001Controller extends BaseAction {

	private static final String FORM_BEAN = "BZBS1001SerchForm";
	private static final String MAIN_URL = "/BZBS1001";
	private static final String MAIN_URL_INIT = MAIN_URL + "Display";
	private static final String HTML_VIEW = "/system/BZBS1001";
	private static final String PARAMS_CLEAR = "clearBtn";
	private static final String PARAMS_SERCH = "searchBtn";
	private static final String FORWARD_VIEW_NEXT = "forward:/xxxxx";

	@Autowired
	LocaleMessageSourceService messageRsource;

	@Autowired
	@Qualifier(value = FORM_BEAN + "Validator")
	private Validator validator;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		if (this.validator.supports(binder.getTarget().getClass())) {
			binder.setValidator(this.validator);
		}
	}

	@ModelAttribute(FORM_BEAN)
	public BZBS1001SerchForm getForm() throws Exception {
		return initForm(BZBS1001SerchForm.class);
	}

	@Token(save = true)
	@RequestMapping(value = MAIN_URL_INIT, method = {RequestMethod.GET,
			RequestMethod.POST})
	public String display(@ModelAttribute(FORM_BEAN) BZBS1001SerchForm form,
			BindingResult result, Model model) {
		model.addAttribute(FORM_BEAN, form);

		return HTML_VIEW;
	}

	@RequestMapping(value = MAIN_URL, method = {RequestMethod.GET,
			RequestMethod.POST}, params = {PARAMS_CLEAR})
	public String clear(Model model) throws Exception {
		model.addAttribute(FORM_BEAN, initForm(BZBS1001SerchForm.class));

		return HTML_VIEW;
	}

	@Token(remove = true)
	@RequestMapping(value = MAIN_URL, method = {RequestMethod.POST}, params = {
			PARAMS_SERCH})
	public String serch(
			@Validated @ModelAttribute(FORM_BEAN) BZBS1001SerchForm form,
			BindingResult result, Model model) {
		// イベント名
		form.setEventName(PARAMS_SERCH);
		if (result.hasErrors()) {
			model.addAttribute(BZConstants.BZ_MESSAGES, messageRsource
					.getMessage(BZMessages.BZ_BASE_MES_EBASE0001));
			return HTML_VIEW;
		}
		model.addAttribute(FORM_BEAN, form);

		return FORWARD_VIEW_NEXT;
	}

}
