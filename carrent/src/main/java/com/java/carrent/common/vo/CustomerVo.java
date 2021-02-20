package com.java.carrent.common.vo;

import com.java.carrent.entity.BusCustomer;

public class CustomerVo extends BusCustomer {

    /**
     * 分页参数
     */
    private Integer page;
    private Integer limit;

    private String[] ids;

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

    public String[] getIds() {
        return ids;
    }

    public void setIds(String[] ids) {
        this.ids = ids;
    }
}
