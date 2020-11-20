package com.thc.watchapi.controller;

import com.thc.watchapi.mapper.StudentMapper;
import com.thc.watchapi.model.Student;
import com.thc.watchapi.response.BaseResult;
import com.thc.watchapi.response.R;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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
    public R test(@RequestParam("test")String test){
        System.out.println(test);
//        Student student = new Student();
//        student.setName("thc");
//        student.setPassword("testpass");
//        student.setWatchId(1);
//        student.setWatchMac("mactest");
//        studentMapper.insert(student);
        return R.ok().data(test);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name="test",value="test", dataType = "String", required=true, paramType="query", defaultValue = "test1")
    })
    @ApiOperation("test1")
    @GetMapping("test1")
    public BaseResult<String> test1(@RequestParam("test")String test){
        System.out.println(test);
//        Student student = new Student();
//        student.setName("thc");
//        student.setPassword("testpass");
//        student.setWatchId(1);
//        student.setWatchMac("mactest");
//        studentMapper.insert(student);
//        return R.ok();
        return BaseResult.success(test);
    }

    @ApiOperation("测试")
    @ApiResponses({
            @ApiResponse(code=200,message = "请求成功"),
            @ApiResponse(code=401,message = "未登陆"),
    })
    @GetMapping("testBaseResult")
    public BaseResult<List<String>> testBaseResult(){
        List<String> resultString = new ArrayList<>();
        resultString.add("asd");
        resultString.add("fgh");
        return BaseResult.success(resultString);
    }
}
