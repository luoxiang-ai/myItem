package com.java.teaching.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.java.teaching.common.vo.SysSuggestVo;
import com.java.teaching.entity.SysSuggest;
import com.java.teaching.entity.SysSuggestExample;
import com.java.teaching.mapper.SysSuggestMapper;
import com.java.teaching.service.SuggestService;
import com.java.teaching.utils.DataGridView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 意见服务实现类
 */
@Service
public class SuggestServiceImpl implements SuggestService {

    @Autowired
    private SysSuggestMapper sysSuggestMapper;

    /**
     * 提交意见
     *
     * @param content
     */
    @Override
    public void addSuggest(String content) {
        SysSuggest sysSuggest = new SysSuggest();
        sysSuggest.setFlag(false);
        sysSuggest.setContent(content);
        this.sysSuggestMapper.insertSelective(sysSuggest);
    }

    /**
     * 加载最新的建议数据
     *
     * @param sysSuggestVo
     * @return
     */
    @Override
    public DataGridView loadNewSuggest(SysSuggestVo sysSuggestVo) {
        Page<Object> page = PageHelper.startPage(sysSuggestVo.getPage(), sysSuggestVo.getLimit());
        SysSuggestExample example = new SysSuggestExample();
        example.setOrderByClause("id DESC");
        SysSuggestExample.Criteria criteria = example.createCriteria();
        criteria.andFlagEqualTo(sysSuggestVo.getFlag());
        List<SysSuggest> list = this.sysSuggestMapper.selectByExample(example);
        return new DataGridView(page.getTotal(), list);
    }

    /**
     * 修改建议标识
     *
     * @param suggestVo
     */
    @Override
    public void updateFlag(SysSuggestVo suggestVo) {
        this.sysSuggestMapper.updateByPrimaryKeySelective(suggestVo);
    }
}
