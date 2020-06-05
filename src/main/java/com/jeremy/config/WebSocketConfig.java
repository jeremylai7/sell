package com.jeremy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import javax.websocket.server.ServerEndpoint;

/**
 * @Auther: laizc
 * @Date: 2020/5/10 22:49
 * @Description:
 */
@Component
public class WebSocketConfig {
    //tomcat启动无需该配置
//    @Bean
//    public ServerEndpointExporter serverEndpointExporter(){
//        return new ServerEndpointExporter();
//    }
}
