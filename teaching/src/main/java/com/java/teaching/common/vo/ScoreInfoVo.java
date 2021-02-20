package com.java.teaching.common.vo;

import com.java.teaching.entity.ScoreInfo;

public class ScoreInfoVo extends ScoreInfo {

    private Integer page;
    private Integer limit;
    /**
     * 课程的名称
     */
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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
