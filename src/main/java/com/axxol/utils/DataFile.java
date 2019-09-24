package com.axxol.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DataFile {
    public String path();  //注解元素 为  id
    public String sheet() default "no description"; //设置默认值，
}


