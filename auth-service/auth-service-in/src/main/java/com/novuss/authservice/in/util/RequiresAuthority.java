package com.novuss.authservice.in.util;

import com.novuss.authservice.domain.UserRole;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequiresAuthority {
    UserRole[] value() default{UserRole.USER};
}
