package com.co.jp.liushuang.controller.common;

import java.util.LinkedHashMap;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.co.jp.liushuang.common.Constants;
import com.co.jp.liushuang.common.Messages;
import com.co.jp.liushuang.common.SysConstant;
import com.co.jp.liushuang.controller.form.UserLoginForm;
import com.co.jp.liushuang.core.BaseAction;
import com.co.jp.liushuang.persistence.entity.User;
import com.co.jp.liushuang.service.common.UserService;
import com.co.jp.liushuang.utils.BeanUtils;

/**
 * ユーザ編集画面処理。 Springのスコープはプロトタイプにすること。
 */
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@Controller
@RequestMapping("/user")
@SessionAttributes({ "logInUser" })
public class UserLoginAction extends BaseAction {

    private String FORM_PARMT_LOCALES = "locales";

    // SpringによるDIで設定するプロパティ @Autowired
    @Resource()
    private UserService userService;

    @Autowired
    private ResourceBundleMessageSource messageSource;

    @RequestMapping("/loginInit.do")
    public ModelAndView loginInit(Model model) {

        UserLoginForm form = new UserLoginForm();
        form.setUserId("lius393");
        model.addAttribute(Constants.SESSION_FORM_NAME, form);
        setLocale(model);
        // 結果コードを返す
        return new ModelAndView("login");
    }

    @RequestMapping("/changeLocale.do")
    public ModelAndView changeLocale(@ModelAttribute("logInUser") UserLoginForm inuser, Model model) {

        model.addAttribute(Constants.SESSION_FORM_NAME, inuser);
        setLocale(model);
        // 結果コードを返す
        return new ModelAndView("login");
    }

    @RequestMapping(value = "/login.do", method = RequestMethod.POST)
    public String login(@Valid @ModelAttribute("logInUser") UserLoginForm inuser,
            Errors result, Model model) {

        try {
            UserLoginForm outFormBean = new UserLoginForm();
            model.addAttribute(Constants.SESSION_FORM_NAME, inuser);
            if (result.hasErrors()) {
                setLocale(model);
                return "login";
            }
            // 前画面から次画面に値を渡すの場合、
            // BeanUtils.copyBeanProperties(inUser, outFormBean);

            // 値を取得する。
            User user = this.userService.getUserById(inuser.getUserId());
            if (user == null) {
                //                String[] localeArr = inuser.getLocale().split("_");
                //                String message = messageSource.getMessage(Messages.MESSAGE_EU000001, null,
                //                        new Locale(localeArr[0], localeArr[1]));
                //                ObjectError error = new ObjectError("userId", message);
                result.reject(Messages.MESSAGE_EU000001);
                setLocale(model);
                return "login";
                // return "redirect:/user/loginInit.do";
            } else {
                String password = user.getPassword();
                if (!password.equals(inuser.getPassword())) {

                    //                    String[] localeArr = inuser.getLocale().split("_");
                    //                    String message = messageSource.getMessage(Messages.MESSAGE_EU000001, null,
                    //                            new Locale(localeArr[0], localeArr[1]));
                    //ObjectError error = new ObjectError("userId", message);
                    result.reject(Messages.MESSAGE_EU000001);
                    setLocale(model);
                    return "login";
                    // return "redirect:/user/loginInit.do";
                }
            }
            outFormBean.setUserId(user.getUserId());
            outFormBean.setUserName(user.getUserName());

            BeanUtils.copyBeanProperties(outFormBean, user);
            model.addAttribute(Constants.SESSION_FORM_NAME, outFormBean);

            // 結果コードを返す

        } catch (Exception e) {
            log.error(SysConstant.BLANK, e);
        }

        return "index";
    }

    @RequestMapping("/introduce.do")
    public ModelAndView introduce() {

        // 結果コードを返す
        return new ModelAndView("introduce");
    }

    @RequestMapping(value = "/logout.do", method = RequestMethod.GET)
    public String logout(Model model) {
        return "redirect:/user/loginInit.do";
    }

    private void setLocale(Model model) {
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<String, String>();

        linkedHashMap.put("en_US", "English");
        linkedHashMap.put("jp_JA", "日本語");
        linkedHashMap.put("zh_CN", "中国语");
        model.addAttribute(FORM_PARMT_LOCALES, linkedHashMap);
    }
}