package com.java.teaching.service;

import com.java.teaching.common.vo.SysSuggestVo;
import com.java.teaching.utils.DataGridView;

/**
 * 意见服务类
 */
public interface SuggestService {

    /**
     * 提交意见
     * @param content
     */
    void addSuggest(String content);

    /**
     * 加载最新的建议数据
     * @param sysSuggestVo
     * @return
     */
    DataGridView loadNewSuggest(SysSuggestVo sysSuggestVo);

    /**
     * 修改建议标识
     * @param suggestVo
     */
    void updateFlag(SysSuggestVo suggestVo);
}
