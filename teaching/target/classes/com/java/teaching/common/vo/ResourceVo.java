package com.java.teaching.common.vo;

import com.java.teaching.entity.Resource;

public class ResourceVo extends Resource {

    private Integer page;
    private Integer limit;

    private Integer[] ids;

    /**
     * 课程名称
     */
    private String cname;

    /**
     * 分类名称
     */
    private String sname;

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
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

    public Integer[] getIds() {
        return ids;
    }

    public void setIds(Integer[] ids) {
        this.ids = ids;
    }
}
