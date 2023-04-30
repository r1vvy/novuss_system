package com.novuss.authservice.in.util;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface RateLimit {
    String key() default "";
    String[] keys() default {};
    long limit() default 1;
    long period() default 1;
}
