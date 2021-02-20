package com.java.teaching.common.vo;

import com.java.teaching.entity.Course;

public class CourseVo extends Course {

    private Integer page;
    private Integer limit;

    private Integer[] ids;
    /**
     * 学生id
     */
    private Integer StudentId;

    // 授课人
    private String realname;

    public Integer getStudentId() {
        return StudentId;
    }

    public void setStudentId(Integer studentId) {
        StudentId = studentId;
    }

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

    public Integer[] getIds() {
        return ids;
    }

    public void setIds(Integer[] ids) {
        this.ids = ids;
    }
}
