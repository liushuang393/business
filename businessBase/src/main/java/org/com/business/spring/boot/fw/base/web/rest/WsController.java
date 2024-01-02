package org.com.business.spring.boot.fw.base.web.rest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.com.business.spring.boot.fw.base.service.system.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import lombok.extern.slf4j.Slf4j;

//@Slf4j
//@Controller
public class WsController {

	@Autowired
	private HttpSession httpSession;

	@Autowired
	private MessageService messageService;

	@Value(value = "${system.messageCnt}")
	private int messageCnt;

	@Autowired
	private final SimpMessagingTemplate messagingTemplate;

	@Autowired
	public WsController(SimpMessagingTemplate messagingTemplate) {
		this.messagingTemplate = messagingTemplate;
	}

	@MessageMapping("/welcome")
	@SendTo("/topic/say")
	public ResponseMessage say(RequestMessage message) {
		System.out.println(message.getName());
		return new ResponseMessage("welcome," + message.getName() + " !");
	}

	/**
	 * 定时推送消息
	 */
	@Scheduled(fixedRate = 1000)
	public void callback() {
		// 发现消息
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		messagingTemplate.convertAndSend("/topic/callback",
				"定时推送消息时间: " + df.format(new Date()));
	}
}