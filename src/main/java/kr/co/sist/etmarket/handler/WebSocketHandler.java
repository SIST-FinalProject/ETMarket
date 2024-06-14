package kr.co.sist.etmarket.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.HashMap;

@Component
@RequiredArgsConstructor
public class WebSocketHandler extends TextWebSocketHandler {
    /**
     * 웹소켓 연결을 통해 들어오는 메시지를 처리
     * 클라이언트와 서버 간의 실시간 통신을 관리하는 로직을 구현
     */

    private final HashMap<String, WebSocketSession> sessionMap = new HashMap<>(); // 웹 소켓 담아둘 맵

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // 메시지 발송

        String msg = message.getPayload();
        for(String key : sessionMap.keySet()){
            WebSocketSession webSocketSession = sessionMap.get(key);
            try {
                webSocketSession.sendMessage(new TextMessage(msg));
            } catch (Exception e){
                e.printStackTrace();
            }
        }

    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // 소켓 연결

        super.afterConnectionEstablished(session);
        sessionMap.put(session.getId(), session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        // 소켓 종료

        sessionMap.remove(session.getId());
        super.afterConnectionClosed(session, status);
    }
}
