package com.thc.watchapi.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.thc.watchapi.dto.PageDto;
import com.thc.watchapi.model.Teacher;
import com.thc.watchapi.response.BaseResult;
import com.thc.watchapi.service.TeacherService;
import com.thc.watchapi.utils.ResultPageUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ProjectName: demo
 * @Package: com.thc.watchapi.controller
 * @ClassName: TeacherController
 * @Author: Hasee
 * @Description:
 * @Date: 2020/11/20 20:03
 * @Version: 1.0
 */
@Api(tags = "教员管理")
@RestController
@RequestMapping("/api/v1/web/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @ApiImplicitParams({
            @ApiImplicitParam(name="page",value="第几页",required=true,paramType="query", defaultValue = "1"),
            @ApiImplicitParam(name="limit",value="每页多少数据",required=true,paramType="query", defaultValue = "10"),
            @ApiImplicitParam(name="name",value="模糊查询姓名",required=true,paramType="query", defaultValue = "t"),
    })
    @ApiOperation("分页查询教员")
    @GetMapping("queryPage")
    public BaseResult<PageDto<Teacher>> queryPage(@RequestParam(value = "page", required = false, defaultValue = "") Integer page,
                                                  @RequestParam(value = "limit", required = false, defaultValue = "") Integer limit,
                                                  @RequestParam(value = "name", required = false, defaultValue = "") String name){
        System.out.println(page);
        System.out.println(limit);
        System.out.println(name);
        Page<Teacher> ans = teacherService.queryPage(new Page(page, limit), name);
        PageDto<Teacher> pageDto = ResultPageUtil.pageToDto(ans);
        return BaseResult.success(pageDto);
    }

    @ApiOperation("添加")
    @PostMapping("add")
    public BaseResult<Object> add(@RequestBody Teacher teacher){
        teacherService.add(teacher);
        return BaseResult.success();
    }

    @ApiOperation("更新")
    @PostMapping("update")
    public BaseResult<Object> update(@RequestBody Teacher teacher) {
        teacherService.update(teacher);
        return BaseResult.success();
    }

    @ApiOperation("删除")
    @GetMapping("delete")
    public BaseResult<Object> delete(@RequestParam(value = "id", required = false, defaultValue = "") Integer id) {
        teacherService.delete(id);
        return BaseResult.success();
    }
}

