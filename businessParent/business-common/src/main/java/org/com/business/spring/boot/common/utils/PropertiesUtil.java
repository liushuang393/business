package org.com.business.spring.boot.common.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.com.business.spring.boot.common.BZConstants;
import org.com.business.spring.boot.common.SysConstant;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PropertiesUtil {

    /**
    * eMailプロッパテイファイル内容を取得する。
    *
    * @param key
    * @param propertiesName
    * @return
    * @throws IOException
    */
    public static String getEmailValue(String key) {

        try {
            return getValue(key, BZConstants.PROPERTIESNAME_EMAIL);
        } catch (IOException e) {
            log.error(SysConstant.BLANK, e);
            return SysConstant.BLANK;
        }

    }

    /**
     * プロッパテイファイル内容を取得する。
     *
     * @param key
     * @param propertiesName
     * @return
     * @throws IOException
     */
    public static String getValue(String key, String propertiesName) throws IOException {

        InputStream in = ClassLoader.getSystemResourceAsStream(propertiesName);

        Properties p = new Properties();

        p.load(in);

        return p.get(key) == null ? SysConstant.BLANK : (String) p.get(key);

    }

}
