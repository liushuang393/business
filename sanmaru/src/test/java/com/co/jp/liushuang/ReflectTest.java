/**
 *
 */
package com.co.jp.liushuang;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.junit.Test;

import com.co.jp.liushuang.dto.User;

/**
 * @author liushuang
 *
 */
public class ReflectTest {

    @Test
    public void testReflect() {
        User user = new User();
        //user.setUserId("itecj");
        user.setUserName("アイテック");

        System.out.println(user.getUserId());
        System.out.println(user.getUserName());
    }

    @Test
    public void testReflect2() {
        Class<User> clazz = User.class;
        String name = clazz.getName();
        String simName = clazz.getSimpleName();

        System.out.println(name);
        System.out.println(simName);
    }

    @Test
    public void testConstuctor() throws Exception {
        Class<User> clazz = User.class;
        Constructor<User> constr = clazz.getDeclaredConstructor();
        constr.newInstance();
        //        System.out.println(name);
        //        System.out.println(simName);
        constr = clazz.getDeclaredConstructor(String.class);
        //new User("12345");
        constr.newInstance("12345");
    }

    @Test
    public void testMethod() throws Exception {
        Class<User> clazz = User.class;
        //clazz.forName("xx.xxx.xxx");
        Object obj = clazz.newInstance();
        Method m = clazz.getDeclaredMethod("setUserId", String.class);
        m.setAccessible(true);
        m.invoke(obj, "12345");

        Method m2 = clazz.getDeclaredMethod("getUserId");
        Object obj2 = m2.invoke(obj);
        System.out.println(obj2);

        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            System.out.println(field.getName());
            //            System.out.println(field.getAnnotations());
        }

    }

}
