package com.jeremy.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @Auther: laizc
 * @Date: 2020/5/10 22:54
 * @Description: WebSocket连接
 */
@Component
@ServerEndpoint("/webSocket")
@Slf4j
public class WebSocket {

    private Session session;

    private static CopyOnWriteArraySet<WebSocket> webSocketSet = new CopyOnWriteArraySet<>();

    @OnOpen
    public void onOpen(Session session){
        this.session = session;
        webSocketSet.add(this);
        log.info("【websocket】有新的连接,总数:{}",webSocketSet.size());
    }

    @OnClose
    public void onClose(){
        webSocketSet.remove(this);
        log.info("【websocket】连接断开:总数:{}",webSocketSet.size());
    }

    @OnMessage
    public void onMessage(String message){
        log.info("【wesocket】收到客户端发送的消息,message={}",message);
    }

    public void sendMessage(String message){
        log.info("【wesocket】广播消息,message={}",message);
        for (WebSocket webSocket:webSocketSet){
            try {
                webSocket.session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
