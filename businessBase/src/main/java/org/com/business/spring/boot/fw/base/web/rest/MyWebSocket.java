package org.com.business.spring.boot.fw.base.web.rest;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.websocket.OnError;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.com.business.spring.boot.common.BZConstants;
import org.com.business.spring.boot.common.utils.Utils;
import org.com.business.spring.boot.fw.base.service.system.MessageService;
import org.com.business.spring.boot.fw.core.auth.model.UserLoginForm;
import org.com.business.spring.boot.fw.persistence.entity.MessageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

//@Component
@Slf4j
//@ServerEndpoint("/ws")
public class MyWebSocket {

	public static HttpSession httpSession;

	public static MessageService messageService;

	public static int messageCnt;

	@OnOpen
	public void onOpen(Session session) {
		// this.session = session;
		log.info("WebSocket:: someone connect");
		int count = 1;
		UserLoginForm form = (UserLoginForm) httpSession
				.getAttribute(BZConstants.SESSION_FORM_NAME);
		messageCnt = messageCnt == 0 ? 5 : messageCnt;
		while (count <= messageCnt) {
			// sleep times
			double random = Math.round(Math.random() * 10);
			long sleepTime = new Double(random).longValue();
			try {

				List<MessageInfo> list = messageService
						.getMessageById(form.getUserId());

				for (MessageInfo messageInfo : list) {
					Thread.sleep(sleepTime * 1000);
					session.getBasicRemote().sendText(
							Utils.isNull(messageInfo.getMessageTitle())
									? ""
									: messageInfo.getMessageTitle() + ":"
											+ messageInfo.getMessageBody());
				}

			} catch (Exception e) {
				log.error("WebSocket:: something error:"
						+ e.getLocalizedMessage());;
			}
			count++;
		}

	}

	@OnError
	public void onError(Throwable t) {
		log.info("WebSocket:: something error:" + t.getLocalizedMessage());
	}
}