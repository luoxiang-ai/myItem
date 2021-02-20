package com.java.teaching.service;

import com.java.teaching.common.vo.ClassVo;
import com.java.teaching.entity.ClassInfo;
import com.java.teaching.utils.DataGridView;

import java.util.List;

/**
 * 班级服务
 */
public interface ClassService {


    /**
     * 加载全部班级
     * @param classVo
     * @return
     */
    DataGridView loadAllClass(ClassVo classVo);

    /**
     * 添加班级
     * @param classVo
     */
    void addClass(ClassVo classVo);

    /**
     * 删除班级
     * @param identity
     */
    void deleteClass(String identity);

    /**
     * 批量删除班级
     * @param classVo
     */
    void batchDeleteClass(ClassVo classVo);

    /**
     * 修改班级信息
     * @param classVo
     */
    void updateClass(ClassVo classVo);

    /**
     * 模糊查询班级信息
     * @param classVo
     * @return
     */
    List<ClassInfo> fuzzyQueryClass(ClassVo classVo);
}
