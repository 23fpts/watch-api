package com.thc.watchapi.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.thc.watchapi.dto.PageDto;
import com.thc.watchapi.model.Student;
import com.thc.watchapi.response.BaseResult;
import com.thc.watchapi.service.StudentService;
import com.thc.watchapi.utils.ResultPageUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author thc
 * @Title:
 * @Package com.thc.watchapi.controller
 * @Description:
 * @date 2020/11/19 11:23 上午
 */
@Api(tags = "学员管理")
@RestController
@RequestMapping("/api/v1/web/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @ApiImplicitParams({
            @ApiImplicitParam(name="page",value="第几页",required=true,paramType="query", defaultValue = "1"),
            @ApiImplicitParam(name="limit",value="每页多少数据",required=true,paramType="query", defaultValue = "10"),
            @ApiImplicitParam(name="name",value="模糊查询姓名",required=true,paramType="query", defaultValue = "t"),
    })
    @ApiOperation("分页查询学员")
    @GetMapping("queryPage")
    public BaseResult<PageDto<Student>> queryPage(@RequestParam(value = "page", required = false, defaultValue = "") Integer page,
                                                  @RequestParam(value = "limit", required = false, defaultValue = "") Integer limit,
                                                  @RequestParam(value = "name", required = false, defaultValue = "") String name){
        System.out.println(page);
        System.out.println(limit);
        System.out.println(name);
        Page<Student> ans = studentService.queryPage(new Page(page, limit), name);
        PageDto<Student> pageDto = ResultPageUtil.pageToDto(ans);
        return BaseResult.success(pageDto);
    }

    @ApiOperation("添加")
    @PostMapping("add")
    public BaseResult<Object> add(@RequestBody Student student){
        studentService.add(student);
        return BaseResult.success();
    }

    @ApiOperation("更新")
    @PostMapping("update")
    public BaseResult<Object> update(@RequestBody Student student) {
        studentService.update(student);
        return BaseResult.success();
    }

    @GetMapping("delete")
    public BaseResult<Object> delete(@RequestParam(value = "id", required = false, defaultValue = "") Integer id) {
        studentService.delete(id);
        return BaseResult.success();
    }
}
