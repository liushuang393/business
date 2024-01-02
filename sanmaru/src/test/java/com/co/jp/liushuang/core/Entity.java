package com.co.jp.liushuang.core;

import javax.validation.constraints.Max;

import org.hibernate.validator.constraints.Length;

import com.co.jp.liushuang.core.annotation.NotEmpty;
public class Entity {
    @Max(value=3)//最大值为3

    private int age;
    @Length(min=1,max=3) //字符串长度最大为1,hibernate 扩展的
    @NotEmpty(field = "パスワード")
    private String name;
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}