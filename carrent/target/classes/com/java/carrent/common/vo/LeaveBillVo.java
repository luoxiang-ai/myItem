package com.java.carrent.common.vo;

import com.java.carrent.entity.SysLeaveBill;

public class LeaveBillVo extends SysLeaveBill {

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
