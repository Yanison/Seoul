package com.seoul.infra.fb;
//본인에게 맞는 package로 변경하도록 합니다.

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatServiceImpl implements ChatService{

	@Autowired
	ChatDao dao;

	@Override
	public List<Chat> selectChatListFromOne(Integer seq) throws Exception {
		// TODO Auto-generated method stub
		List<Chat> resultList = new ArrayList<Chat>();

		//현재 로그인한 USER의 seq를 이용해 채팅방 LIST를 불러온 뒤 반복문 실행
		for(Chat chatRoom : dao.selectChatListFromOne(seq)) { 
			
			//채팅방 리스트에서 로그인한 USER의 시퀀스를 제외하고 검색하기 위해 넣어주는 과정
			chatRoom.setCuMember(seq); 
			
			//채팅방 리스트를 가져온 뒤 해당 채팅방에 연결된 USER(로그인한 USER가 아닌)의 시퀀스 추출
			Chat resultRoom = dao.selectChatWithoutLoginUser(chatRoom);
			
			//채팅방 번호 + 채팅 상대 시퀀스가 담긴 DTO를 리스트에 추가
			resultList.add(resultRoom);
		}
		
		//리스트 리턴
		return resultList;
	}

	@Override
	public Chat createChat(int chatUserA, int chatUserB) throws Exception {
		// TODO Auto-generated method stub
		Chat dto = new Chat();
		dao.insertChat(dto);
		
		dto.setCuMember(chatUserA);
		dto.setCuMember(chatUserB);
		dao.insertChatUser(dto);
		
		return dao.selectOneChat(dto);
	}
}