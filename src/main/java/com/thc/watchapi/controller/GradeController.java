package com.thc.watchapi.controller;

import com.thc.watchapi.dto.GradeStudentDto;
import com.thc.watchapi.model.Admin;
import com.thc.watchapi.response.BaseResult;
import com.thc.watchapi.service.GradeService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author thc
 * @Title:
 * @Package com.thc.watchapi.controller
 * @Description:
 * @date 2020/12/12 7:59 下午
 */
@Api("成绩管理")
@RestController
@RequestMapping("/api/v1/web/grade")
public class GradeController {

    @Autowired
    private GradeService gradeService;

    @GetMapping(value = "gradeinfo")
    public BaseResult<List<GradeStudentDto>> userInfo() {
        return BaseResult.success(gradeService.queryStudentGrade());
    }
}
