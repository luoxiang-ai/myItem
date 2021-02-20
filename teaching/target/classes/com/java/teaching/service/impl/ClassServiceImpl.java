package com.java.teaching.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.java.teaching.common.vo.ClassVo;
import com.java.teaching.constast.SysConstast;
import com.java.teaching.entity.ClassInfo;
import com.java.teaching.entity.ClassInfoExample;
import com.java.teaching.entity.SysUser;
import com.java.teaching.entity.SysUserExample;
import com.java.teaching.mapper.ClassInfoMapper;
import com.java.teaching.mapper.SysUserMapper;
import com.java.teaching.service.ClassService;
import com.java.teaching.utils.DataGridView;
import com.java.teaching.utils.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 班级服务实现类
 */
@Service
public class ClassServiceImpl implements ClassService {

    @Autowired
    private ClassInfoMapper classInfoMapper;
    @Autowired
    private SysUserMapper sysUserMapper;

    /**
     * 加载全部班级
     *
     * @param classVo
     * @return
     */
    @Override
    public DataGridView loadAllClass(ClassVo classVo) {
        List<ClassInfo> classInfos = null;
//        区别表格数据和下拉列表数据
        if(classVo != null && classVo.getPage() != null && classVo.getLimit() != null) {
            Page<Object> page = PageHelper.startPage(classVo.getPage(), classVo.getLimit());
            classInfos = this.queryAllClass();
            return new DataGridView(page.getTotal(), classInfos);
        }

//        获取下拉列表数据
        classInfos = this.queryAllClass();
        return new DataGridView(classInfos);
    }

    /**
     * 查询全部班级
     * @return
     */
    public List<ClassInfo> queryAllClass() {
        ClassInfoExample example = new ClassInfoExample();
        ClassInfoExample.Criteria criteria = example.createCriteria();
        criteria.andFlagEqualTo(true);
        List<ClassInfo> classList = this.classInfoMapper.selectByExample(example);
        return classList;
    }

    /**
     * 添加班级
     *
     * @param classVo
     */
    @Override
    public void addClass(ClassVo classVo) {
        if(classVo != null) {
            classVo.setClassId(RandomUtils.createRandomStringUseTime(SysConstast.CLASS_ID_PREFIX));
            this.classInfoMapper.insertSelective(classVo);
        }
    }

    /**
     * 删除班级
     *
     * @param identity
     */
    @Override
    public void deleteClass(String identity) {
        if(identity != null && !identity.trim().isEmpty()) {
//            班级条件
            SysUserExample example = new SysUserExample();
            SysUserExample.Criteria criteria = example.createCriteria();
            criteria.andClassidEqualTo(identity);

            List<SysUser> userList = this.sysUserMapper.selectByExample(example);
            if(userList != null && !userList.isEmpty()) {
                throw new RuntimeException("该班级下面有学生，不能删除");
            }
//            更新班级状态
            this.classInfoMapper.updateClassInfoFlag(identity);
        }
    }

    /**
     * 批量删除班级
     *
     * @param classVo
     */
    @Override
    public void batchDeleteClass(ClassVo classVo) {
        if(classVo != null) {
            String[] ids = classVo.getIds();
            for (String id : ids) {
                this.deleteClass(id);
            }
        }
    }

    /**
     * 修改班级信息
     *
     * @param classVo
     */
    @Override
    public void updateClass(ClassVo classVo) {
        if(classVo != null) {
            this.classInfoMapper.updateByPrimaryKeySelective(classVo);
        }
    }

    /**
     * 模糊查询班级信息
     *
     * @param classVo
     * @return
     */
    @Override
    public List<ClassInfo> fuzzyQueryClass(ClassVo classVo) {
//        设置班级标识（true:未删除）
        classVo.setFlag(true);
        return this.classInfoMapper.fuzzyQueryClass(classVo);
    }
}
