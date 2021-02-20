package com.java.teaching.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.java.teaching.common.vo.CourseSortVo;
import com.java.teaching.entity.CourseSort;
import com.java.teaching.entity.CourseSortExample;
import com.java.teaching.mapper.CourseSortMapper;
import com.java.teaching.service.CourseSortService;
import com.java.teaching.utils.DataGridView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 课程分类服务实现类
 */
@Service
public class CourseSortServiceImpl implements CourseSortService {

    @Autowired
    private CourseSortMapper courseSortMapper;

    /**
     * 加载全部课程分类
     *
     * @param courseSortVo
     * @return
     */
    @Override
    public DataGridView loadAllCourseSort(CourseSortVo courseSortVo) {
        List<CourseSort> sortList = null;
        if(courseSortVo != null && courseSortVo.getPage() != null && courseSortVo.getLimit() != null) {
            Page<Object> page = PageHelper.startPage(courseSortVo.getPage(), courseSortVo.getLimit());
            sortList = this.courseSortMapper.selectByExample(new CourseSortExample());
            return new DataGridView(page.getTotal(), sortList);
        }

        sortList = this.courseSortMapper.selectByExample(new CourseSortExample());
        return new DataGridView(sortList);
    }

    /**
     * 添加课程分类
     *
     * @param courseSortVo
     */
    @Override
    public void addCourseSort(CourseSortVo courseSortVo) {
        this.courseSortMapper.insertSelective(courseSortVo);
    }

    /**
     * 删除课程分类
     *
     * @param id
     */
    @Override
    public void deleteCourseSort(Integer id) {
        this.courseSortMapper.deleteByPrimaryKey(id);
    }

    /**
     * 批量删除课程分类
     *
     * @param courseSortVo
     */
    @Override
    public void batchDeleteCourseSort(CourseSortVo courseSortVo) {
        Integer[] ids = courseSortVo.getIds();
        if(ids != null && ids.length != 0) {
            for (Integer id : ids) {
                this.deleteCourseSort(id);
            }
        }
    }

    /**
     * 修改课程分类信息
     *
     * @param courseSortVo
     */
    @Override
    public void updateCourseSort(CourseSortVo courseSortVo) {
        this.courseSortMapper.updateByPrimaryKeySelective(courseSortVo);
    }

    /**
     * 模糊查询课程分类
     *
     * @param courseSortVo
     * @return
     */
    @Override
    public DataGridView fuzzyQueryCourseSort(CourseSortVo courseSortVo) {
        Page<Object> page = PageHelper.startPage(courseSortVo.getPage(), courseSortVo.getLimit());
        List<CourseSort> sortList = this.courseSortMapper.fuzzyQueryCourseSort(courseSortVo);
        return new DataGridView(page.getTotal(), sortList);
    }
}
