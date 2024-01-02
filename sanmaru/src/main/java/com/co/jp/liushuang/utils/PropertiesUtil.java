package com.co.jp.liushuang.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.co.jp.liushuang.common.Constants;
import com.co.jp.liushuang.common.SysConstant;

public class PropertiesUtil {

    private static final Logger log = LoggerFactory.getLogger(PropertiesUtil.class);

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
            return getValue(key, Constants.PROPERTIESNAME_EMAIL);
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
