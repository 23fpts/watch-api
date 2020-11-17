package com.thc.watchapi.controller;

import com.thc.watchapi.mapper.StudentMapper;
import com.thc.watchapi.model.Student;
import com.thc.watchapi.response.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author thc
 * @Title:
 * @Package com.thc.watchapi.controller
 * @Description:
 * @date 2020/11/15 2:45 下午
 */
@RestController
@RequestMapping("/api/v1/web/test")
public class TestController {
    @Autowired
    private StudentMapper studentMapper;

    @GetMapping("test")
    public R test(){
        Student student = new Student();
        student.setName("thc");
        student.setPassword("testpass");
        student.setWatchId(1);
        student.setWatchMac("mactest");
        studentMapper.insert(student);
        return R.ok();
    }
}
