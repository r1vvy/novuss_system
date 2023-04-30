package com.novuss.restfulservice.in.util;

import org.springframework.stereotype.Component;

@Component
public class PagingUtils {
    public static final String DEFAULT_PAGE_NUMBER = "0";
    public static final String DEFAULT_PAGE_SIZE = "10";
    public static final int MIN_PAGE_SIZE = 1;
    public static final int MAX_PAGE_SIZE = 100;
}
