package com.java.carrent.web.controller;

import com.java.carrent.utils.DataGridView;
import com.java.carrent.common.vo.LogInfoVo;
import com.java.carrent.service.LogInfoService;
import com.java.carrent.utils.ResultObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 日志控制器
 */
@RestController
@RequestMapping(value = "/logInfo")
public class LogInfoController {

    @Autowired
    private LogInfoService logInfoService;

    /**
     * 加载全部日志信息
     * @param logInfoVo
     * @return
     */
    @GetMapping(value = "/loadAllLogInfo.action")
    public DataGridView loadAllLogInfo(LogInfoVo logInfoVo) {
        return this.logInfoService.queryAllLogInfo(logInfoVo);
    }

    /**
     * 根据id删除日志信息
     * @param id
     * @return
     */
    @PostMapping(value = "/deleteLogInfo.action")
    public ResultObj deleteLogInfo(Integer id) {
        try {
            this.logInfoService.deleteLogInfo(id);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 批量删除日志
     * @param logInfoVo
     * @return
     */
    @PostMapping(value = "/deleteBatchLogInfo.action")
    public ResultObj batchDeleteLogInfo(LogInfoVo logInfoVo) {
        try {
            this.logInfoService.batchDeleteLogInfo(logInfoVo.getIds());
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }
}
