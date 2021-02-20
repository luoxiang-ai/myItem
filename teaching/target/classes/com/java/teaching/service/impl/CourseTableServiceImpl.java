package com.java.teaching.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.java.teaching.common.vo.CourseSortVo;
import com.java.teaching.common.vo.CourseTableVo;
import com.java.teaching.entity.CourseSort;
import com.java.teaching.entity.CourseSortExample;
import com.java.teaching.entity.CourseTable;
import com.java.teaching.entity.CourseTableExample;
import com.java.teaching.mapper.CourseSortMapper;
import com.java.teaching.mapper.CourseTableMapper;
import com.java.teaching.service.CourseTableService;
import com.java.teaching.utils.DataGridView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 课程表服务实现类
 */
@Service
public class CourseTableServiceImpl implements CourseTableService {

    @Autowired
    private CourseTableMapper courseTableMapper;

    /**
     * 添加课程表
     *
     * @param courseTableVo
     */
    @Override
    public void addCourseTable(CourseTableVo courseTableVo) {
        this.courseTableMapper.insertSelective(courseTableVo);
    }

    /**
     * 根据课程id查询课程表
     *
     * @param courseTableVo
     * @return
     */
    @Override
    public DataGridView loadCourseTableByCid(CourseTableVo courseTableVo) {
        Page<Object> page = PageHelper.startPage(courseTableVo.getPage(), courseTableVo.getLimit());
        CourseTableExample example = new CourseTableExample();
        CourseTableExample.Criteria criteria = example.createCriteria();
        criteria.andCidEqualTo(courseTableVo.getCid());
        List<CourseTable> list = this.courseTableMapper.selectByExample(example);
        return new DataGridView(page.getTotal(), list);
    }
}
