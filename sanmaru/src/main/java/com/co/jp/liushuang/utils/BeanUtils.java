package com.co.jp.liushuang.utils;

import java.lang.reflect.InvocationTargetException;

public class BeanUtils {

    public static void copyBeanProperties(Object destBean, Object origBean) {
        try {
            if (destBean == null || origBean == null) {
                return;
            }
            org.apache.commons.beanutils.BeanUtils.copyProperties(destBean, origBean);

        } catch (IllegalAccessException e) {

        } catch (InvocationTargetException e) {

        }
    }
}
