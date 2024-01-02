package org.com.business.spring.boot.fw.base.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.com.business.spring.boot.common.BZConstants;
import org.com.business.spring.boot.common.BZMessages;
import org.com.business.spring.boot.common.utils.itextpdf.PdfReportView;
import org.com.business.spring.boot.fw.base.BaseAction;
import org.com.business.spring.boot.fw.base.common.form.DepartmentListForm;
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
import org.springframework.web.servlet.ModelAndView;

import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;

/**
 * 部署検索画面。
 */
@Controller
@RequestMapping("/system")
@SessionAttributes(types = {DepartmentListForm.class})
public class BZBS0001Controller extends BaseAction {

	private static final String FORM_BEAN = "departmentListForm";
	private static final String MAIN_URL = "/BZBS0001";
	private static final String MAIN_URL_INIT = MAIN_URL + "Display";
	private static final String HTML_VIEW = "/system/BZBS0001";
	private static final String PARAMS_CLEAR = "clearBtn";
	private static final String PARAMS_SERCH = "searchBtn";
	private static final String FORWARD_VIEW_NEXT = "forward:/system/departmentListDisplay";

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
	public DepartmentListForm getForm() throws Exception {
		return initForm(DepartmentListForm.class);
	}

	@Token(save = true)
	@RequestMapping(value = MAIN_URL_INIT, method = {RequestMethod.GET,
			RequestMethod.POST})
	public String display(@ModelAttribute(FORM_BEAN) DepartmentListForm form,
			BindingResult result, Model model) {
		model.addAttribute(FORM_BEAN, form);

		return HTML_VIEW;
	}

	@RequestMapping(value = MAIN_URL, method = {RequestMethod.GET,
			RequestMethod.POST}, params = {PARAMS_CLEAR})
	public String clear(Model model) throws Exception {
		model.addAttribute(FORM_BEAN, initForm(DepartmentListForm.class));

		return HTML_VIEW;
	}

	@Token(remove = true)
	@RequestMapping(value = MAIN_URL, method = {RequestMethod.POST}, params = {
			PARAMS_SERCH})
	public String serch(
			@Validated @ModelAttribute(FORM_BEAN) DepartmentListForm form,
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

	@RequestMapping(value = MAIN_URL, method = {RequestMethod.POST}, params = {
			"pdfOutBtn"})
	public ModelAndView pdfOutBtn(
			@ModelAttribute(FORM_BEAN) DepartmentListForm form,
			BindingResult result, Model model) {
		// イベント名
		form.setEventName("pdfOutBtn");

		Map<String, Object> map = new HashMap<>();
		map.put("H1", "BB");
		map.put("H2", 3);
		map.put("H3", 4);
		map.put("name1", "田中");
		map.put("name2", "鈴木");
		map.put("name3", "花子");
		map.put("name4", "星野");
		map.put("date", new Date());

		List<Map<String, ?>> dataList = new ArrayList<>();
		dataList.add(map);
		map.put("請求書データ", new JRMapCollectionDataSource(dataList));
		// map.put("title", "レポート名");
		return new ModelAndView(new PdfReportView("reports/請求書.jasper", "請求書"),
				map);

	}

}
