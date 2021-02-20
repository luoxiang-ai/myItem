package com.java.teaching.web.controller;

import com.java.teaching.common.vo.SysSuggestVo;
import com.java.teaching.service.SuggestService;
import com.java.teaching.utils.DataGridView;
import com.java.teaching.utils.ResultObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/suggest")
public class SuggestController {

    @Autowired
    private SuggestService suggestService;

    /**
     * 提交意见
     * @param content
     * @return
     */
    @PostMapping(value = "/addSuggest.action")
    public ResultObj addSuggest(String content) {
        try {
            this.suggestService.addSuggest(content);
            return ResultObj.SUBMIT_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.SUBMIT_ERROR;
        }
    }

    /**
     * 加载全部的建议数据
     * @param sysSuggestVo
     * @return
     */
    @GetMapping(value = "/loadAllSuggest.action")
    public DataGridView loadAllSuggest(SysSuggestVo sysSuggestVo) {
        return this.suggestService.loadNewSuggest(sysSuggestVo);
    }

    /**
     * 加星
     * @param suggestVo
     * @return
     */
    @PostMapping(value = "/starred.action")
    public ResultObj starred(SysSuggestVo suggestVo) {
        try {
            this.suggestService.updateFlag(suggestVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }
}
