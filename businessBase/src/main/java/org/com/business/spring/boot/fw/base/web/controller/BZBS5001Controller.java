package org.com.business.spring.boot.fw.base.web.controller;

import javax.servlet.http.HttpSession;

import org.com.business.spring.boot.common.BZConstants;
import org.com.business.spring.boot.common.BZMessages;
import org.com.business.spring.boot.fw.base.BaseAction;
import org.com.business.spring.boot.fw.base.common.form.BZBS5001Form;
import org.com.business.spring.boot.fw.base.service.system.MessageService;
import org.com.business.spring.boot.fw.core.annotation.Token;
import org.com.business.spring.boot.fw.core.auth.model.UserLoginForm;
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
/**
 * メッセージ通知画面。
 */
@Controller
@RequestMapping("/system")
@SessionAttributes(types = {BZBS5001Form.class, UserLoginForm.class})
public class BZBS5001Controller extends BaseAction {

	private static final String FORM_BEAN = "BZBS5001Form";
	private static final String MAIN_URL = "/BZBS5001";
	private static final String MAIN_URL_INIT = MAIN_URL + "Display";
	private static final String HTML_VIEW = "/system/BZBS5001";
	private static final String PARAMS_CLEAR = "clearBtn";
	private static final String PARAMS_UPDATTN = "updatBtn";
	private static final String FORWARD_VIEW_NEXT = "forward:/system/BZBS5001Display";

	@Autowired
	private LocaleMessageSourceService messageRsource;

	@Autowired
	@Qualifier(value = FORM_BEAN + "Validator")
	private Validator validator;

	@Autowired
	private MessageService service;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        if(this.validator.supports(binder.getTarget().getClass())){
            binder.setValidator(this.validator);
        }
    }

	@ModelAttribute(FORM_BEAN)
	public BZBS5001Form getForm() throws Exception {
		return initForm(BZBS5001Form.class);
	}

	@Token(save = true)
	@RequestMapping(value = MAIN_URL_INIT, method = {RequestMethod.GET,
			RequestMethod.POST})
	public String display(@ModelAttribute(FORM_BEAN) BZBS5001Form form,
			BindingResult result, Model model) {

		service.setForm(form);
		model.addAttribute(FORM_BEAN, form);

		return HTML_VIEW;
	}

	@RequestMapping(value = MAIN_URL, method = {RequestMethod.GET,
			RequestMethod.POST}, params = {PARAMS_CLEAR})
	public String clear(Model model) throws Exception {

		BZBS5001Form form = initForm(BZBS5001Form.class);
		service.setForm(form);

		model.addAttribute(FORM_BEAN, form);

		return HTML_VIEW;
	}

	@RequestMapping(value = MAIN_URL, method = RequestMethod.POST, params = {
			PARAMS_UPDATTN})
	public String update(
			@Validated @ModelAttribute(FORM_BEAN) BZBS5001Form form,
			BindingResult result, Model model, HttpSession session,
			@ModelAttribute(BZConstants.SESSION_FORM_NAME) UserLoginForm user) {

		model.addAttribute(FORM_BEAN, form);
		if (result.hasErrors()) {
			model.addAttribute(BZConstants.BZ_MESSAGES, messageRsource
					.getMessage(BZMessages.BZ_BASE_MES_EBASE0001));
			return HTML_VIEW;
		}

		// Serviceを呼び出しの実装
		service.addMessage(user.getUserId(), form);

		model.addAttribute(BZConstants.BZ_MESSAGES,
				messageRsource.getMessage("EBASW0004"));
		return FORWARD_VIEW_NEXT;
	}

}
