package com.thc.watchapi.controller;

import com.thc.watchapi.dto.Grade2StudentDto;
import com.thc.watchapi.dto.GradeAcaQuery;
import com.thc.watchapi.dto.GradeStudentDto;
import com.thc.watchapi.model.Admin;
import com.thc.watchapi.model.grade2.BctStudentInfo;
import com.thc.watchapi.model.grade2.BizWListAcademic;
import com.thc.watchapi.response.BaseResult;
import com.thc.watchapi.service.GradeService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @PostMapping("importExcel")
    public BaseResult<Object> importExcel(MultipartFile multipartFile) {
        gradeService.importExcelData(multipartFile);
        return BaseResult.success();
    }

    @PostMapping("queryBctStudent")
    public BaseResult<List<BctStudentInfo>> queryBctStudent(@RequestParam(value = "grade", required = false, defaultValue = "") String grade,
                                                            @RequestParam(value = "college", required = false, defaultValue = "") String college) {

        return BaseResult.success(gradeService.queryBctStu(grade, college));
    }

    @PostMapping("queryBizAca")
    public BaseResult<List<BizWListAcademic>> queryBizAca(@RequestBody GradeAcaQuery grade) {

        return BaseResult.success(gradeService.queryAca(grade.getStartTime(), grade.getEndTime()));
    }


    @PostMapping(value = "grade2info")
    public BaseResult<List<Grade2StudentDto>> userInfo(@RequestBody GradeAcaQuery grade) {
        return BaseResult.success(gradeService.queryStuDto(grade.getGrade(), grade.getCollege(), grade.getStartTime(), grade.getEndTime(), grade.getStuNo()));
    }


}
