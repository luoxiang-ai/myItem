package com.java.teaching.common.vo;

import com.java.teaching.entity.SysSuggest;

public class SysSuggestVo extends SysSuggest {

    /* 分页参数 */
    private Integer page;
    private Integer limit;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}
