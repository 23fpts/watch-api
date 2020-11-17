package com.thc.watchapi.socket;

import com.thc.watchapi.service.Json2DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author thc
 * @Title:
 * @Package com.thc.watchapi.socket
 * @Description:
 * @date 2020/11/13 3:56 下午
 */

@ServerEndpoint("/WebSocket/{id}")
@Component
public class WebSocket {

    // 存储会话
    private static ConcurrentHashMap<String, WebSocket> webSocket = new ConcurrentHashMap<String, WebSocket>();

    private String id;
    private Session session;

//    @Autowired
//    private Json2DatabaseService json2DatabaseService;

    private static Json2DatabaseService json2DatabaseService;

    @Autowired
    public void setJson2DatabaseService(Json2DatabaseService json2DatabaseService) {
        WebSocket.json2DatabaseService = json2DatabaseService;
    }

    /**
     * 接入连接回调
     *
     * @param session 会话对象
     * @param id      会话ID
     * @throws Exception 异常
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("id") String id) throws Exception {
        this.id = id;
        this.session = session;
        webSocket.put(id, this);
        // 检验后端能否正常给前端发送信息
        sendMessageToId(this.id, "前端你好，我是后端，我正在通过WebSocket给你发送消息");
        System.out.println(id + "接入连接");
    }

    /**
     * 关闭连接回调
     */
    @OnClose
    public void onClose() {
        webSocket.remove(this.id);
        System.out.println(this.id + "关闭连接");
    }

    /**
     * 收到客户端发来消息回调
     *
     * @param message
     */
    @OnMessage
    public void onMessage(String message) {

        System.out.println(this.id + "发来消息：" + message);
        System.out.println("abcd");
        // TODO 分情况处理，不一定是insert
        json2DatabaseService.insert(message);
//        messageTest();
//        Json2DatabaseService json2DatabaseService1 = new Json2DatabaseService();
//        setJson2DatabaseService(json2DatabaseService1);
//        json2DatabaseService.insert(message);
        System.out.println("efg");

    }

    /**
     * 会话出现错误回调
     *
     * @param error   错误信息
     */
    @OnError
    public void onError(Throwable error) {

    }

    public void messageTest() {
        System.out.println("onmessageTest");
        for (int i=0; i<20; i++){
            System.out.println(i);
        }
    }

    /**
     * 发送消息给客户端
     *
     * @param message 消息
     * @throws IOException 异常
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    /**
     * 给指定的会话发送消息
     *
     * @param id      会话ID
     * @param message 消息
     * @throws IOException 异常
     */
    public void sendMessageToId(String id, String message) throws IOException {
        webSocket.get(id).sendMessage(message);
    }

    /**
     * 群发消息
     *
     * @param message 消息
     * @throws IOException 异常
     */
    public void sendMessageToAll(String message) throws IOException {
        for (String key : webSocket.keySet()) {
            try {
                webSocket.get(key).sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}


