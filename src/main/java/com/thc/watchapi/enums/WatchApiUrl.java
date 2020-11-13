package com.thc.watchapi.enums;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author thc
 * @Title:
 * @Package com.thc.watchapi.enums
 * @Description: 存贮watchapi的url
 * # 手表api 不含mac
 * # 网关端口
 * watch.url.port = http://127.0.0.1:8888/ac/station
 * # http://127.0.0.1:8888/ac/station?cmd=sendcmd&target=gateway&command=41&gatewayId=10000178&contentType=byte&content=E7,A8,5F,07,85,E3,00,00,00,01,00,02,02,00,03,00,00,00,00,02,32,4B,4E,48,49,50,00,00,02,00,68,68
 * watch.url.cmd = sendcmd
 * watch.url.target = gateway
 * # 链接 command
 * watch.url.connectcommand = 41
 * # 连接的UUID
 * watch.url.UUID = ,10,00,00,00,01,00,02,02,00,03,00,00,00,00,02,32,4B,4E,48,49,50,00,00,02,00,68,68
 * # 读数据
 * watch.url.readcommand = 49
 * # 断开
 * watch.url.closecommand =
 * # gatewayId
 * watch.url.gatewayId = 10000178
 * # contentType
 * watch.url.contentType = byte
 * @date 2020/11/13 4:03 下午
 */
@Data
@Configuration
@PropertySource("classpath:watchapiurl.properties")
public class WatchApiUrl {

    @Value("${watch.url.port}")
    private String port;

    @Value("${watch.url.cmd}")
    private String cmd;

    @Value("${watch.url.target}")
    private String target;

    @Value("${watch.url.UUID}")
    private String UUID;

    @Value("${watch.url.connectcommand}")
    private String connectCommand;

    @Value("${watch.url.readcommand}")
    private String readCommand;

    @Value("${watch.url.closecommand}")
    private String closeCommand;

    @Value("${watch.url.gatewayId}")
    private String gatewayId;

    @Value("${watch.url.contentType}")
    private String contentType;
}
