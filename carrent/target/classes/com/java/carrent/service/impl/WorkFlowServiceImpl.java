package com.java.carrent.service.impl;

import com.java.carrent.common.vo.WorkFlowVo;
import com.java.carrent.common.vo.act.ActDeploymentEntity;
import com.java.carrent.common.vo.act.ActProcessDefinitionEntity;
import com.java.carrent.common.vo.act.ActTaskEntity;
import com.java.carrent.constast.SysConstast;
import com.java.carrent.entity.SysLeaveBill;
import com.java.carrent.mapper.SysLeaveBillMapper;
import com.java.carrent.service.WorkFlowService;
import com.java.carrent.utils.WebUtils;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

/**
 * 工作流服务类
 */
@Service
public class WorkFlowServiceImpl implements WorkFlowService {

    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;

    @Autowired
    private SysLeaveBillMapper sysLeaveBillMapper;

    @Override
    public List<ActDeploymentEntity> queryAllDeployment(WorkFlowVo workFlowVo) {
        if(workFlowVo.getDeploymentName() == null) {
            workFlowVo.setDeploymentName("");
        }

        String deploymentName = workFlowVo.getDeploymentName();
//        查询流程部署的总记录数
        long count = this.repositoryService.createDeploymentQuery().deploymentNameLike("%" + deploymentName + "%").count();
//        查询数据
        int firstResult = 1;
        int maxResults = 10;
        List<Deployment> deploymentList = this.repositoryService.createDeploymentQuery().deploymentNameLike("%" + deploymentName + "%").list();
        List<ActDeploymentEntity> actDeploymentEntityList = new ArrayList<>();
        for (Deployment deployment : deploymentList) {
            ActDeploymentEntity actDeploymentEntity = new ActDeploymentEntity();
            actDeploymentEntity.setId(deployment.getId());
            actDeploymentEntity.setName(deployment.getName());
            actDeploymentEntity.setKey(deployment.getKey());
            actDeploymentEntity.setCategory(deployment.getCategory());
            actDeploymentEntity.setDeploymentTime(deployment.getDeploymentTime());

            actDeploymentEntityList.add(actDeploymentEntity);
        }

        return actDeploymentEntityList;
    }

    @Override
    public List<ActProcessDefinitionEntity> queryAllProcessDefinition(WorkFlowVo workFlowVo) {
        long count = this.repositoryService.createProcessDefinitionQuery().count();
        if(workFlowVo.getDeploymentName() == null) {
            workFlowVo.setDeploymentName("");
        }
        String deploymentName = workFlowVo.getDeploymentName();
        List<ProcessDefinition> processDefinitionList = this.repositoryService.createProcessDefinitionQuery().processDefinitionNameLike("%" + deploymentName + "%").list();
        List<ActProcessDefinitionEntity> actProcessDefinitionEntityList = new ArrayList<>();
        for (ProcessDefinition processDefinition : processDefinitionList) {
            ActProcessDefinitionEntity actProcessDefinitionEntity = new ActProcessDefinitionEntity();
            actProcessDefinitionEntity.setId(processDefinition.getId());
            actProcessDefinitionEntity.setName(processDefinition.getName());
            actProcessDefinitionEntity.setKey(processDefinition.getKey());
            actProcessDefinitionEntity.setDeploymentId(processDefinition.getDeploymentId());
            actProcessDefinitionEntity.setDiagramResourceName(processDefinition.getDiagramResourceName());
            actProcessDefinitionEntity.setResourceName(processDefinition.getResourceName());
            actProcessDefinitionEntity.setVersion(processDefinition.getVersion());

            actProcessDefinitionEntityList.add(actProcessDefinitionEntity);
        }

        return actProcessDefinitionEntityList;
    }

    /**
     * 添加流程部署
     *
     * @param inputStream
     * @param deploymentName
     */
    @Override
    public void addWorkFlow(InputStream inputStream, String deploymentName) {
        ZipInputStream zip = new ZipInputStream(inputStream);
        this.repositoryService.createDeployment()
                .name(deploymentName)    // 添加部署名称
                .addZipInputStream(zip)   // 添加资源
                .deploy();  // 部署
        try {
            if(zip != null) {
                zip.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除流程
     *
     * @param deploymentId
     * @param flag
     */
    @Override
    public void deleteWorkFlow(String deploymentId, Boolean flag) {

        this.repositoryService.deleteDeployment(deploymentId, flag);
    }

    /**
     * 查看流程部署图片
     *
     * @param deploymentId
     * @return
     */
    @Override
    public InputStream queryProcessDeploymentImage(String deploymentId) {
//        1.根据部署id查询流程定义对象
        ProcessDefinition processDefinition = this.repositoryService.createProcessDefinitionQuery()
                .deploymentId(deploymentId).singleResult();
//        2.从流程定义对象里面得到图片的名称
        String resourceName = processDefinition.getDiagramResourceName();
//        3.使用部署id和图片名称去查询图片
        InputStream stream = this.repositoryService.getResourceAsStream(deploymentId, resourceName);
        return stream;
    }

    /**
     * 启动流程
     * @param leaveBillId
     */
    @Override
    public void startProcess(Integer leaveBillId) {
//        1.找到流程的key
        String processDefinitionKey = SysLeaveBill.class.getSimpleName();
        String businessKey = processDefinitionKey + ":" + leaveBillId;
        Map<String, Object> variables = new HashMap<>();
//        设置流程变量并设置下一个任务办理人
        variables.put("username", WebUtils.getCurrentUserName());
        this.runtimeService.startProcessInstanceByKey(processDefinitionKey, businessKey, variables);
//        更新请假单的状态
        SysLeaveBill leaveBill = this.sysLeaveBillMapper.selectByPrimaryKey(leaveBillId);
//        设置请假单的为审批中
        leaveBill.setState(SysConstast.LEAVEBILL_STATE_ONE);
//        更新sql
        this.sysLeaveBillMapper.updateByPrimaryKeySelective(leaveBill);
    }

    @Override
    public List<ActTaskEntity> queryCurrentUserTask(WorkFlowVo workFlowVo) {
//        得到办理人信息
        String assignee = WebUtils.getCurrentUserName();
//        查询
        List<Task> taskList = this.taskService.createTaskQuery().taskAssignee(assignee).list();
        List<ActTaskEntity> actTaskEntityList = new ArrayList<>();
        for (Task task : taskList) {
            ActTaskEntity actTaskEntity = new ActTaskEntity();
            actTaskEntity.setId(task.getId());
            actTaskEntity.setName(task.getName());
            actTaskEntity.setAssignee(task.getAssignee());
            actTaskEntity.setCreateTime(task.getCreateTime());

            actTaskEntityList.add(actTaskEntity);
        }
        return actTaskEntityList;
    }
}
