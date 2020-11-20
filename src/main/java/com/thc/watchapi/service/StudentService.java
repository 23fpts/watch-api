package com.thc.watchapi.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.thc.watchapi.exception.MyException;
import com.thc.watchapi.mapper.StudentMapper;
import com.thc.watchapi.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author thc
 * @Title:
 * @Package com.thc.watchapi.service
 * @Description:
 * @date 2020/11/20 5:06 下午
 */
@Service
public class StudentService {

    @Autowired
    private StudentMapper studentMapper;

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Transactional
    public void add(Student student){
        String encryptedPassword = bCryptPasswordEncoder.encode(student.getPassword());
        student.setPassword(encryptedPassword);
        studentMapper.insert(student);
    }

    @Transactional
    public void delete(Integer id){
        if (studentMapper.selectById(id)==null){
            // 没有找到，无法更新
            throw new MyException(1, "没有找到，可能id错误");
        }
        studentMapper.deleteById(id);
    }

    @Transactional
    public void update(Student student){
        if (studentMapper.selectById(student.getId())==null){
            // 没有找到，无法更新
            throw new MyException(1, "没有找到，可能id错误");
        }
        studentMapper.updateById(student);
    }

    public List<Student> query(String name){
        QueryWrapper<Student> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(name)){
            wrapper.like("name", name);
        }
        wrapper.orderByDesc("gmt_create");
        return studentMapper.selectList(wrapper);
    }

    public Page<Student> queryPage(Page page, String name){
        System.out.println("service start");
        QueryWrapper<Student> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(name)){
            wrapper.like("name", name);
        }
        wrapper.orderByDesc("gmt_create");
        return studentMapper.selectPage(page, wrapper);
    }

}
