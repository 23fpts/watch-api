package com.thc.watchapi.service;

import com.thc.watchapi.mapper.StudentMapper;
import com.thc.watchapi.model.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author thc
 * @Title:
 * @Package com.thc.watchapi.service
 * @Description:
 * @date 2020/11/13 5:14 下午
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class WatchApiServiceTest {

    @Autowired
    private WatchApiService watchApiService;

    @Autowired
    private StudentMapper studentMapper;

    @Test
    void getConnectUrl() {
//        System.out.println(        watchApiService.getReadUrl("1A,E8,C2,1A,2B,F9"));
        Student student = new Student();
        student.setName("thc");
        student.setPassword("testpass");
        student.setWatchId(1);
        student.setWatchMac("mactest");
        studentMapper.insert(student);
    }
}