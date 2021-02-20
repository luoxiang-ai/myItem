package com.java.carrent.web.controller;

import com.java.carrent.common.vo.WorkFlowVo;
import com.java.carrent.common.vo.act.ActDeploymentEntity;
import com.java.carrent.common.vo.act.ActProcessDefinitionEntity;
import com.java.carrent.common.vo.act.ActTaskEntity;
import com.java.carrent.constast.SysConstast;
import com.java.carrent.service.WorkFlowService;
import com.java.carrent.utils.DataGridView;
import com.java.carrent.utils.ResultObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 工作流控制器
 */
@Controller
@RequestMapping(value = "/workFlow")
public class WorkFlowController {

    @Autowired
    private WorkFlowService workFlowService;

    /**
     * 跳转到工作流管理页面
     * @return
     */
    @GetMapping(value = "/toWorkFlowManager.action")
    public String toWorkFlowManager() {

        return "system/workFlow/workFlowManager";
    }

    /**
     * 加载全部流程部署信息
     * @param workFlowVo
     * @return
     */
    @GetMapping(value = "/loadAllProcessDeployment.action")
    @ResponseBody
    public DataGridView loadAllProcessDeployment(WorkFlowVo workFlowVo) {
        List<ActDeploymentEntity> deploymentList = this.workFlowService.queryAllDeployment(workFlowVo);
        return new DataGridView((long) deploymentList.size(), deploymentList);
    }

    /**
     * 加载全部流程定义信息
     * @param workFlowVo
     * @return
     */
    @GetMapping(value = "/loadAllProcessDefinition.action")
    @ResponseBody
    public DataGridView loadAllProcessDefinition(WorkFlowVo workFlowVo) {
        List<ActProcessDefinitionEntity> processDefinitionList = this.workFlowService.queryAllProcessDefinition(workFlowVo);
        return new DataGridView((long) processDefinitionList.size(), processDefinitionList);
    }

    /**
     * 添加工作流
     * @param mf
     * @param deploymentName
     * @return
     */
    @PostMapping(value = "/addWorkFlow.action")
    @ResponseBody
    public Map<String, Object> addWorkFlow(MultipartFile mf, String deploymentName) {

        Map<String, Object> map = new HashMap<>();
        try {
            this.workFlowService.addWorkFlow(mf.getInputStream(), deploymentName);
            map.put("code", 0);
            map.put("msg", SysConstast.DEPLOY_SUCCESS);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", -1);
            map.put("msg", SysConstast.DEPLOY_ERROR);
            return map;
        }
    }

    /**
     * 删除工作流
     * @param deploymentId
     * @return
     */
    @PostMapping(value = "/deleteWorkFlow.action")
    @ResponseBody
    public ResultObj deleteWorkFlow(String deploymentId) {
        try {
            this.workFlowService.deleteWorkFlow(deploymentId, true);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 批量删除工作流
     * @param workFlowVo
     * @return
     */
    @PostMapping(value = "/batchDeleteWorkFlow.action")
    @ResponseBody
    public ResultObj batchDeleteWorkFlow(WorkFlowVo workFlowVo) {
        try {
            String[] ids = workFlowVo.getIds();
            if(ids != null && ids.length != 0) {
                for (String id : ids) {
                    this.deleteWorkFlow(id);
                }
            }
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 查看流程图片
     * @param workFlowVo
     * @param response
     */
    @GetMapping(value = "/viewProcessImage.action")
    public void viewProcessImage(WorkFlowVo workFlowVo, HttpServletResponse response) {
        InputStream stream = null;
        ServletOutputStream outputStream = null;
        try {
            stream = this.workFlowService.queryProcessDeploymentImage(workFlowVo.getDeploymentId());
            BufferedImage image = ImageIO.read(stream);
            outputStream = response.getOutputStream();
            ImageIO.write(image, "JPEG", outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            try {
                if(stream != null) {
                    stream.close();
                }
                if(outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 启动流程
     * @param workFlowVo
     * @return
     */
    @PostMapping(value = "/startProcess.action")
    public ResultObj startProcess(WorkFlowVo workFlowVo) {
        try {
            this.workFlowService.startProcess(workFlowVo.getId());
            return ResultObj.START_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.START_ERROR;
        }
    }

    /**
     * 跳转到任务侍办页面
     * @return
     */
    @GetMapping(value = "/toDoTaskManager.action")
    public String toDoTaskManager() {

        return "system/workFlow/doTaskManager";
    }

    @GetMapping(value = "/loadCurrentUserTask.action")
    @ResponseBody
    public DataGridView loadCurrentUserTask(WorkFlowVo workFlowVo) {
        List<ActTaskEntity> taskList = this.workFlowService.queryCurrentUserTask(workFlowVo);
        return new DataGridView((long) taskList.size(), taskList);
    }
}
