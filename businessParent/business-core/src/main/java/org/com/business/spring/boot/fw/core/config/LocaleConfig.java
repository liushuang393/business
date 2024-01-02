/**
 *
 */
package org.com.business.spring.boot.fw.core.config;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.com.business.spring.boot.fw.core.Interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

/**
 * @author liushuang
 *
 */
@Configuration
public class LocaleConfig implements WebMvcConfigurer {

    @Autowired
    MessageSource messageSource;

    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver slr = new SessionLocaleResolver();
        slr.setDefaultLocale(Locale.getDefault());
        return slr;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName(LocaleChangeInterceptor.DEFAULT_PARAM_NAME);
        return lci;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor()).
        excludePathPatterns(Arrays.asList("/error","/commonError","/welcome","/login",
                "/","/changeLocale","/css/**",
                "/images/**","/js/**","/fonts/**"));
        registry.addInterceptor(localeChangeInterceptor());

    }

    @Bean
    @Override
    public Validator getValidator() {
        Map<String, String> validationProperties = new HashMap<>();
        validationProperties.put("providerClass", "org.hibernate.validator.HibernateValidator");
        LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
        validator.setValidationMessageSource(messageSource);
        validator.setValidationPropertyMap(validationProperties);

        return validator;
    }
}
