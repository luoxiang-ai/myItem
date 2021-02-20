package com.java.teaching.common.vo;

import com.java.teaching.entity.StudentWork;

public class StudentWorkVo extends StudentWork {

    private Integer page;
    private Integer limit;

    /**
     * 用户姓名
     */
    private String realname;

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
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
