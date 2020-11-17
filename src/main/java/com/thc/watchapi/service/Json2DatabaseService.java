package com.thc.watchapi.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.thc.watchapi.mapper.WatchDataHexMapper;
import com.thc.watchapi.mapper.WatchDataMapper;
import com.thc.watchapi.model.WatchData;
import com.thc.watchapi.model.WatchDataHex;
import com.thc.watchapi.utils.WatchDataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author thc
 * @Title:
 * @Package com.thc.bluetoothtest.service
 * @Description:
 * @date 2020/11/1 3:11 下午
 */
@Service
public class Json2DatabaseService {

    @Autowired
    private WatchDataHexMapper watchDataHexMapper;

    @Autowired
    private WatchDataMapper watchDataMapper;

    /**
     * 如果json有data数据九插入数据，如果有addr数据就插入mac地址进表（插入前检查存在性）
     * @param message
     */
    public void insert(String message) {
        System.out.println("insert json");
        System.out.println(message);
//        String data = message;
        JSONObject jsonObject = JSON.parseObject(message);
        if (jsonObject.containsKey("data")){
            String data = jsonObject.getString("data");
            WatchDataHex watchDataHex = new WatchDataHex();
            watchDataHex.setData(data);
            System.out.println("data:::::");
            System.out.println(watchDataHex.getData());
            watchDataHexMapper.insert(watchDataHex);
            // TODO 存正常数据 影响效率
            // WatchData watchData = WatchDataUtil.HexDataToData(data);
            // watchDataMapper.insert(watchData);
        }
        System.out.println("insert complete");
    }

    // TODO mac不这样处理
    public void searchMac(String message) {
        JSONObject jsonObject = JSON.parseObject(message);
        if (jsonObject.containsKey("online")) {
            JSONArray array = jsonObject.getJSONArray("online");
            for (int i=0; i<array.size(); i++) {
                JSONObject jsonObj = array.getJSONObject(i);
                String addr = jsonObj.getString("addr");
                // TODO
                if ("24161FDAA3FD".equals(addr)|| "1AE8C21A2BF9".equals(addr) || "E7A85F0785E3".equals(addr)) {
//                    System.out.println(message);
                    System.out.println(array.getJSONObject(i));
                }
            }
        }
    }
}
