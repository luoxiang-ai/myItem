package com.java.carrent.service;

import com.java.carrent.common.vo.WorkFlowVo;
import com.java.carrent.common.vo.act.ActDeploymentEntity;
import com.java.carrent.common.vo.act.ActProcessDefinitionEntity;
import com.java.carrent.common.vo.act.ActTaskEntity;

import java.io.InputStream;
import java.util.List;

public interface WorkFlowService {

    List<ActDeploymentEntity> queryAllDeployment(WorkFlowVo workFlowVo);

    List<ActProcessDefinitionEntity> queryAllProcessDefinition(WorkFlowVo workFlowVo);

    /**
     * 添加流程部署
     * @param inputStream
     * @param deploymentName
     */
    void addWorkFlow(InputStream inputStream, String deploymentName);

    /**
     * 删除流程
     * @param deploymentId
     * @param flag
     */
    void deleteWorkFlow(String deploymentId, Boolean flag);

    /**
     * 查看流程部署图片
     * @param deploymentId
     * @return
     */
    InputStream queryProcessDeploymentImage(String deploymentId);

    /**
     * 启动流程
     * @param leaveBillId
     */
    void startProcess(Integer leaveBillId);

    List<ActTaskEntity> queryCurrentUserTask(WorkFlowVo workFlowVo);
}
