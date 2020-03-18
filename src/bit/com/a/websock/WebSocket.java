package bit.com.a.websock;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class WebSocket extends TextWebSocketHandler {

	private Map<String, WebSocketSession> users = new ConcurrentHashMap<String, WebSocketSession>();
	
	public WebSocket() {
		System.out.println("-----EchoHandler 생성됨 " + new Date());
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		// 연결된 후에 실행										소켓정보
		System.out.println("연결됨"+ new Date());
		users.put(session.getId(), session);	// 소켓정보 담음
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		// 메세지 종료
		System.out.println("연결 종료"+ new Date());
		users.remove(session.getId());
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		// 메시지수신
		// 방을 만들거나 특정 유저에게만 보낼 경우 수정
		System.out.println("메시지 수신"+ message + new Date());
		
		for (WebSocketSession s : users.values()) {
			s.sendMessage(message);
		}
	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		// 예외 발생
		System.out.println("예외발생 : " + session.getId() + " / " + new Date());
	}
	
}
