package com.thc.watchapi.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.thc.watchapi.exception.MyException;
import com.thc.watchapi.mapper.TeacherMapper;
import com.thc.watchapi.model.Student;
import com.thc.watchapi.model.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @ProjectName: demo
 * @Package: com.thc.watchapi.service
 * @ClassName: TeacherService
 * @Author: Hasee
 * @Description:
 * @Date: 2020/11/20 19:46
 * @Version: 1.0
 */
@Service
public class TeacherService {

    @Autowired
    private TeacherMapper teacherMapper;

    @Transactional
    public void add(Teacher teacher){
        teacherMapper.insert(teacher);
    }

    @Transactional
    public void delete(Integer id){
        if (teacherMapper.selectById(id)==null){
            // 没有找到，无法更新
            throw new MyException(1, "没有找到，可能教员ID错误");
        }
        teacherMapper.deleteById(id);
    }

    @Transactional
    public void update(Teacher teacher){
        if (teacherMapper.selectById(teacher.getId())==null){
            // 没有找到，无法更新
            throw new MyException(1, "没有找到，可能教员ID错误");
        }
        teacherMapper.updateById(teacher);
    }

    public List<Teacher> query(String name){
        QueryWrapper<Teacher> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(name)){
            wrapper.like("name", name);
        }
        wrapper.orderByDesc("gmt_create");
        return teacherMapper.selectList(wrapper);
    }

    public Page<Teacher> queryPage(Page page, String name){
        System.out.println("service start");
        QueryWrapper<Teacher> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(name)){
            wrapper.like("name", name);
        }
        wrapper.orderByDesc("gmt_create");
        return teacherMapper.selectPage(page, wrapper);
    }
}
