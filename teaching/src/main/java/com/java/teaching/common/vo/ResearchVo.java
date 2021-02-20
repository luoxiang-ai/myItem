package com.java.teaching.common.vo;

import com.java.teaching.entity.ResearchResource;

public class ResearchVo extends ResearchResource {

    private Integer page;
    private Integer limit;

    private Integer[] ids;

    /**
     * 课程名称
     */
    private String cname;

    /**
     * 新资源路径
     */
    private String newPath;

    public String getNewPath() {
        return newPath;
    }

    public void setNewPath(String newPath) {
        this.newPath = newPath;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
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
