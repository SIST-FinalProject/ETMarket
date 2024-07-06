package kr.co.sist.etmarket.handler;

import kr.co.sist.etmarket.service.ChatRoomService;
import kr.co.sist.etmarket.service.ItemService;
import kr.co.sist.etmarket.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class WebSocketExHandler extends TextWebSocketHandler {

    HashMap<String, WebSocketSession> sessionMap = new HashMap<>(); //웹소켓 세션을 담아둘 맵
//    List<HashMap<String, Object>> roomListSession = new ArrayList<>(); //웹소켓 세션을 담아둘 리스트

    //로그인 한 인원 전체
    private List<WebSocketSession> sessions = new ArrayList<WebSocketSession>();

    private final MessageService messageService;
    private final ChatRoomService chatRoomService;
    private final ItemService itemService;

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // 메세지 발송
        String msg = message.getPayload();
        JSONObject obj = jsonToObjectParser(msg);
        System.out.println("msg = " + obj);

        String userName = obj.get("userName").toString();
        Long itemId = Long.parseLong(obj.get("itemId").toString());
        System.out.println("userName = " + userName);
        System.out.println("itemId = " + itemId);

        System.out.println("sessionMap size: " + sessionMap.size());

        for(String key : sessionMap.keySet()) {
            WebSocketSession wss = sessionMap.get(key);
            try {
                String messageStr = obj.toJSONString();
                System.out.println("Sending message to session: " + key + ", message = " + messageStr);
                wss.sendMessage(new TextMessage(messageStr));
            }catch(Exception e) {
                System.err.println("Error sending message to session " + key);
                e.printStackTrace();
            }
        }

    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // 소켓 연결
        System.out.println("소켓 연결: " + session.getId());
        super.afterConnectionEstablished(session);

        sessionMap.put(session.getId(), session);
        sessions.add(session);

        JSONObject obj = new JSONObject();
        obj.put("type", "getId");
        obj.put("sessionId", session.getId());
        session.sendMessage(new TextMessage(obj.toJSONString()));
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        // 소켓 종료
        System.out.println("소켓 종료: " + session.getId());
        sessionMap.remove(session.getId());
        super.afterConnectionClosed(session, status);
    }

    private static JSONObject jsonToObjectParser(String jsonStr) {
        JSONParser parser = new JSONParser();
        JSONObject obj = null;
        try {
            obj = (JSONObject) parser.parse(jsonStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return obj;
    }
}
