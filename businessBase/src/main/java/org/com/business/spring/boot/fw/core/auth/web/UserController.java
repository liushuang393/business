package org.com.business.spring.boot.fw.core.auth.web;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.com.business.spring.boot.common.BZConstants;
import org.com.business.spring.boot.fw.core.auth.model.UserLoginForm;
import org.com.business.spring.boot.fw.core.i18n.LocaleMessageSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.druid.util.StringUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@SessionAttributes(types = { UserLoginForm.class }, value = { "locale" })
public class UserController {

    private String FORM_PARMT_LOCALES = "locales";

    @Autowired
    LocaleMessageSourceService messageRsource;

    @ModelAttribute(BZConstants.SESSION_FORM_NAME)
    public UserLoginForm getUser() {
        return new UserLoginForm();
    }

    //    @InitBinder
    //    public void initBinder(WebDataBinder binder) {
    //      binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    //    }

    @RequestMapping(value = "/welcome", method = { RequestMethod.GET, RequestMethod.POST })
    public String welcome(@ModelAttribute(BZConstants.SESSION_FORM_NAME) UserLoginForm form,
            @ModelAttribute("error") String errorId,
            Model model) {
        model.addAttribute(BZConstants.SESSION_FORM_NAME, form);
        setLocale(model);
        if (StringUtils.isEmpty(form.getLocale())) {
            Locale locale = Locale.getDefault();
            form.setLocale(locale.toLanguageTag().replaceAll("-", "_"));
        }
        if (!StringUtils.isEmpty(errorId)) {
            model.addAttribute(BZConstants.BZ_MESSAGES, messageRsource.getMessage(errorId));
        }

        return "login";
    }

    @RequestMapping(value = "/loginpre")
    public ModelAndView showLoginPage(@Validated @ModelAttribute(BZConstants.SESSION_FORM_NAME) UserLoginForm inuser,
            BindingResult result, Model model, HttpSession sesstion, @RequestParam Optional<String> error) {
        model.addAttribute(BZConstants.SESSION_FORM_NAME, inuser);
        if (result.hasErrors()) {
            return new ModelAndView("forward:/welcome", "error", error.isPresent() ? error.get() : "");
        }
        return new ModelAndView("forward:/login");
    }

    //    @RequestMapping(value = "/login", method = { RequestMethod.POST })
    //    public String login(@Validated @ModelAttribute(BZConstants.SESSION_FORM_NAME) UserLoginForm inuser,
    //            BindingResult result, Model model, HttpSession sesstion) {
    //
    //        UserLoginForm outFormBean = new UserLoginForm();
    //        model.addAttribute(BZConstants.SESSION_FORM_NAME, inuser);
    //        if (result.hasErrors()) {
    //            setLocale(model);
    //            return "login";
    //        }
    //        // 前画面から次画面に値を渡すの場合、
    //        // BeanUtils.copyBeanProperties(inUser, outFormBean);
    //
    //        // 値を取得する。
    //        User user = this.userService.getUserById(inuser.getUserId());
    //        if (user == null) {
    //            result.reject(BZMessages.MESSAGE_EU000001);
    //            setLocale(model);
    //            return "login";
    //            // return "redirect:/user/loginInit.do";
    //        } else {
    //            String password = user.getPassword();
    //            if (!password.equals(inuser.getPassword())) {
    //
    //                result.reject(BZMessages.MESSAGE_EU000001);
    //                setLocale(model);
    //                return "login";
    //                // return "redirect:/user/loginInit.do";
    //            }
    //        }
    //        outFormBean.setUserId(user.getUserId());
    //        outFormBean.setUserName(user.getUserName());
    //
    //        BZBeanUtils.copyBeanProperties(outFormBean, user);
    //        model.addAttribute(BZConstants.SESSION_FORM_NAME, outFormBean);
    //
    //        // 結果コードを返す
    //
    //        return "/common/menu";
    //    }

    //    @GetMapping("/login")
    //    public String login(Model model, String error, String logout) {
    //        if (error != null)
    //            model.addAttribute("error", "Your username and password is invalid.");
    //
    //        if (logout != null)
    //            model.addAttribute("message", "You have been logged out successfully.");
    //
    //        return "login";
    //    }
    @RequestMapping({ "/menu" })
    public String menuInit(Model model) {

        return "/common/menu";

    }

    @RequestMapping("/changeLocale")
    public ModelAndView changeLocale(
            HttpServletRequest request, HttpServletResponse response,
            @ModelAttribute(BZConstants.SESSION_FORM_NAME) UserLoginForm inuser, Model model) {

        model.addAttribute(BZConstants.SESSION_FORM_NAME, inuser);
        setLocale(model);
        model.addAttribute("locale", inuser.getLocale());
        //LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
        //String lang = inuser.getLocale();

        //if (lang != null && lang.split("_").length == 2) {
        //    String[] split = lang.split("_");
        //    localeResolver.setLocale(request, response, new Locale(split[0], split[1]));
        //}
        // 結果コードを返す
        return new ModelAndView("login");
    }

    @RequestMapping("/introduce")
    public ModelAndView introduce() {

        // 結果コードを返す
        return new ModelAndView("/common/introduce");
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(Model model,HttpSession sesstion,SessionStatus sessionStat) {
    	sessionStat.setComplete();
    	sesstion.invalidate();

        return "redirect:/welcome";
    }

    private void setLocale(Model model) {
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<String, String>();

        linkedHashMap.put("en_US", "English");
        linkedHashMap.put("ja_JP", "日本語");
        linkedHashMap.put("zh_CN", "中国语");
        model.addAttribute(FORM_PARMT_LOCALES, linkedHashMap);
    }
}