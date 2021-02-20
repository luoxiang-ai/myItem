package com.java.carrent.common.vo;

public class WorkFlowVo {

    /**
     * 分页参数
     */
    private Integer page;
    private Integer limit;

    /**
     * 流程部署的名称
     */
    private String deploymentName;
    /**
     * 流程部署id
     */
    private String deploymentId;
    /**
     * 流程部署id的数组（用于批量删除）
     */
    private String[] ids;
    /**
     * 请假单id
     */
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String[] getIds() {
        return ids;
    }

    public void setIds(String[] ids) {
        this.ids = ids;
    }

    public String getDeploymentId() {
        return deploymentId;
    }

    public void setDeploymentId(String deploymentId) {
        this.deploymentId = deploymentId;
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

    public String getDeploymentName() {
        return deploymentName;
    }

    public void setDeploymentName(String deploymentName) {
        this.deploymentName = deploymentName;
    }
}
