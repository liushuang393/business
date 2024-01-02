/**
 * 
 */
package org.com.business.spring.boot.fw.core.properties;

import java.util.List;

import org.com.business.spring.boot.common.SysConstant;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.boot.env.PropertiesPropertySourceLoader;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import lombok.extern.slf4j.Slf4j;

/**
 * @author liushuang
 *
 */
@Slf4j
public class SystemPropertiesEnvironmentPostProcessor implements EnvironmentPostProcessor {

    private PropertiesPropertySourceLoader loader = new PropertiesPropertySourceLoader();

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        MutablePropertySources propertySources = environment.getPropertySources();
        Resource resource = new ClassPathResource(SysConstant.SYSTEM_PROPERTIES_NAME);

        try {
            List<PropertySource<?>> ps = loader.load("AnotherPropertiesFile", resource);

            for (PropertySource<?> p : ps) {
                propertySources.addFirst(p);
            }

        } catch (Exception e) {
            log.error("AnotherPropertiesFile load Exception: " + SysConstant.SYSTEM_PROPERTIES_NAME, e);
        }
    }

}
