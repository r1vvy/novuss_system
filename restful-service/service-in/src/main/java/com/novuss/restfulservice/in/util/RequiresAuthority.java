package com.novuss.restfulservice.in.util;

import com.novuss.restfulservice.domain.UserRole;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequiresAuthority {
    UserRole[] value() default{UserRole.USER};
}
