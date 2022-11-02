package com.seoul.infra.modules.membergroup;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Service
public class MemberGroupServiceImpl implements  MemberGroupService{
	
	@Autowired
	MemberGroupDao dao;
	
	@Override
	public List<MemberGroup> selectMlist(MemberGroup vo) throws Exception{
		return dao.selectMlist(vo);
	}
	
	@Override
	public int selectOneCnt (MemberGroup vo) throws Exception{
		return dao.selectOneCnt(vo);
	}
	@Override
	public MemberGroup selectMOne (MemberGroup dto) throws Exception{
		return dao.selectMOne(dto);
	}
	
	
	
	@Override
	public int addUser(MemberGroup dto) throws Exception{
		
		dto.setMemberPw(UtilSecurity.encryptSha256(dto.getMemberPw()));
		
		return dao.addUser(dto);
	}
	
	@Override
	public MemberGroup selectOneLogin (MemberGroup dto) throws Exception{

		return dao.selectOneLogin(dto);
	}
	public MemberGroup selectOneId (MemberGroup dto) throws Exception{
		return dao.selectOneId(dto);
	}
	
	@Override
	public int isDupleId (MemberGroup vo) throws Exception{
		return dao.isDupleId(vo);
	}
	
	
	public String getAccessToken (String authorize_code) {
		String access_Token = "";
		String refresh_Token = "";
		String reqURL = "https://kauth.kakao.com/oauth/token";
		
		 try {
			
             URL url = new URL(reqURL);
             HttpURLConnection conn = (HttpURLConnection) url.openConnection();

             //    POST 요청을 위해 기본값이 false인 setDoOutput을 true로
             conn.setRequestMethod("POST");
             conn.setDoOutput(true);

             //    POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
             StringBuilder sb = new StringBuilder();
             sb.append("grant_type=authorization_code");
             sb.append("&client_id=0150c62dea4406ba52dbae88e0293319");  //본인이 발급받은 key
             sb.append("&redirect_uri=http://127.0.0.1:8082/kakaologin");     // 본인이 설정해 놓은 경로
             sb.append("&code=" + authorize_code);
             bw.write(sb.toString());
             bw.flush();

             //    결과 코드가 200이라면 성공
             int responseCode = conn.getResponseCode();
             System.out.println("responseCode : " + responseCode);

             //    요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
             BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
             String line = "";
             String result = "";

             while ((line = br.readLine()) != null) {
                 result += line;
             }
             System.out.println("getAccessToken response body : " + result);

             //    Gson 라이브러리에 포함된 클래스로 JSON파싱 객체 생성
             JsonElement element = JsonParser.parseString(result);

             access_Token = element.getAsJsonObject().get("access_token").getAsString();
             refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();

             System.out.println("access_token : " + access_Token);
             System.out.println("refresh_token : " + refresh_Token);

             br.close();
             bw.close();
	         } catch (IOException e) {
	             // TODO Auto-generated catch block
	             e.printStackTrace();
	         }
	
	         return access_Token;
	}
	
	public MemberGroup getUserInfo(String access_Token) {

		// 요청하는 클라이언트마다 가진 정보가 다를 수 있기에 HashMap타입으로 선언
		HashMap<String, Object> userInfo = new HashMap<String, Object>();
		String reqURL = "https://kapi.kakao.com/v2/user/me";
		try {
			URL url = new URL(reqURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");

			// 요청에 필요한 Header에 포함될 내용
			conn.setRequestProperty("Authorization", "Bearer " + access_Token);

			int responseCode = conn.getResponseCode();
			System.out.println("responseCode : " + responseCode);

			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			String line = "";
			String result = "";

			while ((line = br.readLine()) != null) {
				result += line;
			}
			System.out.println("getUserInfo response body : " + result);
			JsonElement element = JsonParser.parseString(result);
//			JsonParser parser = new JsonParser();
//			JsonElement element = parser.parse(result);

			JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
			JsonObject kakao_account = element.getAsJsonObject().get("kakao_account").getAsJsonObject();
			
					
			String memberName = properties.getAsJsonObject().get("nickname").getAsString();
			String memberEmail = kakao_account.getAsJsonObject().get("email").getAsString();
			String memberDob = kakao_account.getAsJsonObject().get("birthday").getAsString();
			String memberGender = kakao_account.getAsJsonObject().get("gender").getAsString();
	
			String idTokenKko = element.getAsJsonObject().get("id").getAsString();

			userInfo.put("memberName", memberName);
			userInfo.put("memberEmail", memberEmail);
			userInfo.put("memberDob", memberDob);
			userInfo.put("memberGender", memberGender);
			userInfo.put("idTokenKko", idTokenKko);

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		//카카오 로그인 후에 토큰으로 얻은 정보로 로그인//회원가입 진행코드
		MemberGroup result = dao.findKkoMember(userInfo);
		//카카오로 로그인한 회원들이 저장된 DB에 접근하여
		//카카오로 로그인한 이력이 있는지 확인해야함. 
		//dao에서 생성한 selectOne 메소드로 db에 접근
		System.out.println("findKkoMember result :: " + result);
		
		if(result == null) {
		//db에 접근한 후에 결과값이 null이면 카카오 정보를 insert하는 메소드에 접근
			dao.kkoLogin(userInfo);
		//카카오 로그인 및 회원가입은 회원가입과 동시에 로그인이 이루어지기 때문에 
		// insert한 후에 다시 selectOne으로 회원 정보를 불러내서 컨트롤러로 보냄
			return dao.findKkoMember(userInfo);
		}else {
			return result;
		}
		
		
	}
	
//	public String memberLogOut(String access_Token) {
//		String reqURL = "https://kapi.kakao.com/v1/user/logout";
//		
//		try {
//			URL url = new URL(reqURL);
//			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//			conn.setRequestMethod("GET");
//
//			// 요청에 필요한 Header에 포함될 내용
//			conn.setRequestProperty("Authorization", "Bearer " + access_Token);
//
//			int responseCode = conn.getResponseCode();
//			System.out.println("responseCode : " + responseCode);
//
//			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//
//			String line = "";
//			String result = "";
//
//			while ((line = br.readLine()) != null) {
//				result += line;
//			}
//		}
//		catch(IOException e){
//			e.printStackTrace();
//		}
//		
//		
//		
//		return access_Token;
//	}


}
