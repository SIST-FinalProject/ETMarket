package kr.co.sist.etmarket.controller;

import kr.co.sist.etmarket.dto.ChatRoomDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ChattingExController {

    private List<ChatRoomDto> roomDtoList = new ArrayList<>();
    static int chatroomId = 0;

    @GetMapping("/et/chat")
    public String chat(@RequestParam String userName, Model model) {
        System.out.println("userName = " + userName);
        model.addAttribute("userName", userName);
        return "chating/ex";
    }
//
//    /**
//     * 방 페이지
//     */
//    @GetMapping("/room")
//    public String room() {
//        return "chating/room";
//    }
//
//    /**
//     * 방 생성하기
//     */
//    @PostMapping("/createRoom")
//    public @ResponseBody List<ChatRoomDto> createRoom(@RequestParam HashMap<Object, Object> params) {
//        String roomName = params.get("roomName").toString();
//
//        if(roomName != null && !roomName.trim().equals("")){
//            ChatRoomDto roomDto = new ChatRoomDto();
//            roomDto.setRoomName(roomName);
//            roomDto.setChatroomId(++chatroomId);
//
//            roomDtoList.add(roomDto);
//        }
//        return roomDtoList;
//    }
//
//    /**
//     * 방 정보 가져오기
//     */
//    @PostMapping("/getRoom")
//    public @ResponseBody List<ChatRoomDto> getRoom(@RequestParam HashMap<Object, Object> params) {
//        return roomDtoList;
//    }
//
//    /**
//     * 채팅방
//     */
//    @GetMapping("/moveChating")
//    public String chating(@RequestParam HashMap<Object, Object> params, Model model) {
//        int chatroomId = Integer.parseInt(params.get("chatroomId").toString());
//
//        List<ChatRoomDto> newRoomDtoList = roomDtoList.stream()
//                .filter(ob -> ob.getChatroomId() == chatroomId)
//                .toList();
//
//        if(newRoomDtoList.size() > 0 && newRoomDtoList != null){
//            model.addAttribute("chatroomId", params.get("chatroomId").toString());
//            model.addAttribute("roomName", newRoomDtoList.get(0).getRoomName());
//
//            return "chating/chat";
//        } else {
//            return "chating/room";
//        }
//    }


}
