package com.java.teaching.common.vo;

import com.java.teaching.entity.SysUser;

import java.math.BigDecimal;

public class UserVo extends SysUser {

    /* 分页参数 */
    private Integer page;
    private Integer limit;
//  接收多个角色的id
    private Integer[] ids;
    /**
     * 班级名称
     */
    private String className;

    /**
     * 新密码
     */
    private String newPwd;
    /**
     * 课程id
     */
    private Integer cid;

    private BigDecimal score;

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getNewPwd() {
        return newPwd;
    }

    public void setNewPwd(String newPwd) {
        this.newPwd = newPwd;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
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
