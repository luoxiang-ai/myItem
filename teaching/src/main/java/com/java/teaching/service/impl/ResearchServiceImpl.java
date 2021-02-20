package com.java.teaching.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.java.teaching.common.vo.ResearchVo;
import com.java.teaching.common.vo.StudentWorkVo;
import com.java.teaching.entity.ResearchResource;
import com.java.teaching.mapper.ResearchResourceMapper;
import com.java.teaching.service.ResearchService;
import com.java.teaching.utils.AppFileUtils;
import com.java.teaching.utils.DataGridView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 科研资料服务实现类
 */
@Service
public class ResearchServiceImpl implements ResearchService {

    @Autowired
    private ResearchResourceMapper researchResourceMapper;

    /**
     * 根据教师id加载科研资料
     *
     * @param researchVo
     * @return
     */
    @Override
    public DataGridView loadResearchByTid(ResearchVo researchVo) {
        Page<Object> page = PageHelper.startPage(researchVo.getPage(), researchVo.getLimit());
        List<StudentWorkVo> list = this.researchResourceMapper.loadResearchByTid(researchVo);
        return new DataGridView(page.getTotal(), list);
    }

    /**
     * 修改科研资料
     *
     * @param researchVo
     */
    @Override
    public void updateResearch(ResearchVo researchVo) {
        this.researchResourceMapper.updateByPrimaryKeySelective(researchVo);
    }

    /**
     * 添加科研资料
     *
     * @param researchVo
     */
    @Override
    public void addResearch(ResearchVo researchVo) {
        this.researchResourceMapper.insertSelective(researchVo);
    }

    /**
     * 根据id删除科研资料
     *
     * @param id
     */
    @Override
    public void deleteResearch(Integer id) {
        this.researchResourceMapper.deleteByPrimaryKey(id);
    }

    /**
     * 批量删除科研资料
     *
     * @param ids
     */
    @Override
    public void batchDeleteResearch(Integer[] ids) {
        for (Integer id : ids) {
            this.deleteResearch(id);
        }
    }

    /**
     * 模糊查询科研资料信息
     *
     * @param researchVo
     * @return
     */
    @Override
    public List<ResearchVo> fuzzyQueryResearch(ResearchVo researchVo) {
        Page<Object> page = PageHelper.startPage(researchVo.getPage(), researchVo.getLimit());
        List<ResearchVo> list = this.researchResourceMapper.fuzzyQueryResearch(researchVo);
        return list;
    }

    /**
     * 更新资源
     *
     * @param researchVo
     */
    @Override
    public void updateResource(ResearchVo researchVo) {
        String path = researchVo.getPath();  // 获取旧资源
//        如果资源路径不为空，表示旧资源
        if(path != null && !path.trim().equals("")) {
            // 删除旧资源
            AppFileUtils.deleteFile(path);
//            再更新新资源路径
            researchVo.setPath(researchVo.getNewPath());
            this.researchResourceMapper.updateByPrimaryKeySelective(researchVo);
            return;
        }

//        如果没有旧资源，直接上传
        researchVo.setPath(researchVo.getNewPath());
        this.researchResourceMapper.updateByPrimaryKeySelective(researchVo);
    }
}
