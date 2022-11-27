package com.seoul.infra.fb;
//본인에게 맞는 package로 변경하도록 합니다.

import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class ChatDao {
	
	@Inject
	@Resource(name="sqlSession")
	private SqlSession sqlSession;
	
	private String namespace = "com.seoul.infra.fb.ChatMapper";
	//본인에게 맞는 package 경로로 변경하도록 합니다.

	public List<Chat> selectChatListFromOne(Integer seq) { return sqlSession.selectList(namespace+".selectChatListFromOne", seq);}
	//현재 로그인 USER의 시퀀스가 연결된 채팅방 리스트를 가져온다.

	public Chat selectChatWithoutLoginUser(Chat chatRoom) { return sqlSession.selectOne(namespace+".selectChatWithoutLoginUser", chatRoom); }
  //로그인한 USER가 가진 채팅방의 채팅상대 시퀀스를 가져온다.	
	
	public void insertChat(Chat dto) { sqlSession.insert(namespace+".insertChat", dto); }

	public void insertChatUser(Chat dto) { sqlSession.insert(namespace+".insertChatUser", dto); }

	public Chat selectOneChat(Chat dto) { return sqlSession.selectOne(namespace+".selectOneChat", dto);}
}