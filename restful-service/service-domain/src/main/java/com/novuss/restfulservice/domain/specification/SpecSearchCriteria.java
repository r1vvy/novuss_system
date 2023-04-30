package com.novuss.restfulservice.domain.specification;

import com.novuss.restfulservice.domain.SearchOperation;

public class SpecSearchCriteria {
    private String key;
    private SearchOperation operation;
    private Object value;
    private boolean orPredicate;

    public boolean isOrPredicate() {
        return orPredicate;
    }
}
