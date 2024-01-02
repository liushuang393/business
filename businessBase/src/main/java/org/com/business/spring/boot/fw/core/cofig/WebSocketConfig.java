package org.com.business.spring.boot.fw.core.cofig;

import org.com.business.spring.boot.fw.base.service.system.MessageService;
import org.com.business.spring.boot.fw.base.web.rest.MyWebSocket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@Component
@Configuration
// @EnableWebSocketMessageBroker
public class WebSocketConfig {
	// STOMP
	// public class WebSocketConfig implements WebSocketMessageBrokerConfigurer
	// {

	@Value(value = "${system.messageCnt}")
	private static int messageCnt;

	//@Bean
	public ServerEndpointExporter serverEndpointExporter() {
		return new ServerEndpointExporter();
	}

	@Autowired
	public void setMessageService(MessageService chat) {
		MyWebSocket.messageService = chat;
	}

	@Autowired
	public void setMessageCnt() {
		MyWebSocket.messageCnt = messageCnt;
	}
	// @Override
	// public void registerStompEndpoints(
	// StompEndpointRegistry stompEndpointRegistry) {
	// stompEndpointRegistry.addEndpoint("/simple").setAllowedOrigins("*") //
	// 解决跨域问题
	// .withSockJS();
	// }
	//
	// @Override
	// public void configureMessageBroker(MessageBrokerRegistry registry) {
	// registry.enableSimpleBroker("/topic");
	// }

}