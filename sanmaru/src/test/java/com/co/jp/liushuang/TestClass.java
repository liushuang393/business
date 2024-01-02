/**
 *
 */
package com.co.jp.liushuang;

import java.lang.reflect.Method;

/**
 * @author liushuang
 *
 */
public class TestClass {

    public void testClass(Class clazz) {
        Method[] mes = clazz.getDeclaredMethods();

        for (Method m: mes) {

        }

        clazz.getDeclaredFields();

    }

}
