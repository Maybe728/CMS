package com.cn.cms.annotation;

/**
 * @Auther: HuangShiXing
 * @Date: 2018/12/5 14:25
 * @Description:自定义注解
 */

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {

    String value() default "";

}

