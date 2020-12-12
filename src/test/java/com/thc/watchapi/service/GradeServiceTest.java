package com.thc.watchapi.service;

import com.thc.watchapi.mapper.GdGroupMapper;
import com.thc.watchapi.mapper.grade.GdStudentMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author thc
 * @Title:
 * @Package com.thc.watchapi.service
 * @Description:
 * @date 2020/12/12 5:44 下午
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GradeServiceTest {

    @Autowired
    private GradeService gradeService;

    @Autowired
    private GdStudentMapper gdStudentMapper;

    @Autowired
    private GdGroupMapper gdGroupMapper;

    @Test
    void queryStudentGrade() {
        gradeService.queryStudentGrade();
    }

    @Test
    void test1() {
        gdStudentMapper.selectList(null).forEach(System.out::println);
    }

    @Test
    void test2() {
        gdGroupMapper.queryInfoByStuIdOrSubId(null, null).forEach(System.out::println);
    }

    @Test
    void test3() {
        // gdGroupMapper.test(1, null);
    }
}