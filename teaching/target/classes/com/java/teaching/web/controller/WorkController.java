package com.java.teaching.web.controller;

import com.java.teaching.common.vo.WorkVo;
import com.java.teaching.entity.SysUser;
import com.java.teaching.service.WorkService;
import com.java.teaching.utils.DataGridView;
import com.java.teaching.utils.ResultObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 作业控制器
 */
@RestController
@RequestMapping(value = "/work")
public class WorkController {

    @Autowired
    private WorkService workService;

    /**
     * 加载全部
     * @param workVo
     * @return
     */
    @GetMapping(value = "/loadAllWork.action")
    public DataGridView loadAllWork(WorkVo workVo) {
        return this.workService.loadAllWork(workVo);
    }

    /**
     * 修改作业
     * @param workVo
     * @return
     */
    @PostMapping(value = "/updateWork.action")
    public ResultObj updateWork(WorkVo workVo) {
        try {
            this.workService.updateWork(workVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 添加作业
     * @param workVo
     * @return
     */
    @PostMapping(value = "/addWork.action")
    public ResultObj addWork(WorkVo workVo) {
        try {
            this.workService.addWork(workVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 根据编号删除班级
     * @param id
     * @return
     */
    @PostMapping(value = "/deleteWork.action")
    public ResultObj deleteWork(Integer id) {
        try {
            this.workService.deleteWork(id);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 批量删除
     * @param workVo
     * @return
     */
    @PostMapping(value = "/batchDeleteWork.action")
    public ResultObj batchDeleteWork(WorkVo workVo) {
        try {
            this.workService.batchDeleteWork(workVo);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 模糊查询作业信息
     * @param workVo
     * @return
     */
    @GetMapping(value = "/fuzzyQueryWork.action")
    public DataGridView fuzzyQueryWork(WorkVo workVo) {
        List<WorkVo> list = this.workService.fuzzyQueryWork(workVo);
        return new DataGridView((long) list.size(), list);
    }

    /**
     * 根据课程id查询作业
     * @param workVo
     * @return
     */
    @GetMapping(value = "/loadWorkByCourseId.action")
    public DataGridView loadWorkByCourseId(WorkVo workVo) {
        return this.workService.loadWorkByCourseId(workVo);
    }

    /**
     * 判断作业提交时间是否过了
     * @param date
     * @return
     */
    @PostMapping(value = "/verificationDate.action")
    public ResultObj verificationDate(String date) {
        Date nowDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date oldDate = null;
        try {
            oldDate = sdf.parse(date);
            int i = nowDate.compareTo(oldDate);
            if(i > 0) {
                return ResultObj.TIME_OUT;
            }
        } catch (ParseException e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
        return null;
    }
}
