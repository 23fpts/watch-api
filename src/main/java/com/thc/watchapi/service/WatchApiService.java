package com.thc.watchapi.service;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.thc.watchapi.enums.WatchApiUrl;
import com.thc.watchapi.mapper.StudentMapper;
import com.thc.watchapi.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author thc
 * @Title:
 * @Package com.thc.watchapi.service
 * @Description: 调用网关api实现手表链接和读数据
 * @date 2020/11/13 4:02 下午
 */
@Service
public class WatchApiService {

    /**
     * 用于获取学生的手表的mac
     */
    @Autowired
    private StudentMapper studentMapper;

    /**
     * 网关api地址
     */
    @Autowired
    private WatchApiUrl watchApiUrl;

    /**
     * TODO 先测试库里面所有mac
     * 链接
     */
    public void connect() throws Exception {
        // TODO 查询条件
        List<Student> studentList = studentMapper.selectList(null);
        try{
            // 循环处理所有student
            for (Student student : studentList) {
                // 手表mac
                String mac = student.getWatchMac();
                // 连接的url
                String url = getConnectUrl(mac);
                String result = HttpUtil.get(url);
                // 不需要处理返回结果
//                JSONObject json = JSON.parseObject(result);
//                Map<String, Object> res = new HashMap<>();
//                res.put("data", json);
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception("第三方接口异常");
        }
    }

    public void read() throws Exception {
        // TODO 查询条件
        List<Student> studentList = studentMapper.selectList(null);
        try{
            // 循环处理所有student
            for (Student student : studentList) {
                // 手表mac
                String mac = student.getWatchMac();
                // 连接的url
                String url = getReadUrl(mac);
                String result = HttpUtil.get(url);
                // 不需要处理返回结果
//                JSONObject json = JSON.parseObject(result);
//                Map<String, Object> res = new HashMap<>();
//                res.put("data", json);
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception("第三方接口异常");
        }
    }

    // 关闭接口
    public void close() throws Exception {
        // TODO 查询条件
        List<Student> studentList = studentMapper.selectList(null);
        try{
            // 循环处理所有student
            for (Student student : studentList) {
                // 手表mac
                String mac = student.getWatchMac();
                // 连接的url
                String url = getCloseUrl(mac);
                String result = HttpUtil.get(url);
                // 不需要处理返回结果
//                JSONObject json = JSON.parseObject(result);
//                Map<String, Object> res = new HashMap<>();
//                res.put("data", json);
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception("第三方接口异常");
        }
    }

    // 根据connect的url的处理
    private String getConnectUrl(String mac) {
        String content = mac+watchApiUrl.getUUID();
        String url = UriComponentsBuilder.fromUriString(watchApiUrl.getPort())
                .queryParam("cmd", watchApiUrl.getCmd())
                .queryParam("target", watchApiUrl.getTarget())
                .queryParam("command", watchApiUrl.getConnectCommand())
                .queryParam("gatewayId", watchApiUrl.getGatewayId())
                .queryParam("contentType", watchApiUrl.getContentType())
                .queryParam("content", content)
                .build().toString();
        return url;
    }

    // 读数据
    private String getReadUrl(String mac) {
        String url = UriComponentsBuilder.fromUriString(watchApiUrl.getPort())
                .queryParam("cmd", watchApiUrl.getCmd())
                .queryParam("target", watchApiUrl.getTarget())
                .queryParam("command", watchApiUrl.getReadCommand())
                .queryParam("gatewayId", watchApiUrl.getGatewayId())
                .queryParam("contentType", watchApiUrl.getContentType())
                .queryParam("content", mac)
                .build().toString();
        return url;
    }

    // 断开连接
    private String getCloseUrl(String mac) {
        String url = UriComponentsBuilder.fromHttpUrl(watchApiUrl.getPort())
                .queryParam("cmd", watchApiUrl.getCmd())
                .queryParam("target", watchApiUrl.getTarget())
                .queryParam("command", watchApiUrl.getCloseCommand())
                .queryParam("gatewayId", watchApiUrl.getGatewayId())
                .queryParam("contentType", watchApiUrl.getContentType())
                .queryParam("content", mac)
                .build().toString();
        return url;
    }


}
