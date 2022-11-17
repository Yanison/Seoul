package com.seoul.infra.modules.membergroup;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Service
public class NaverLoginService {
	
	public String getAuthCode(String code,String state){
		
		/*
		 * 로그인이 진행 된 후에 callBack Url로 넘겨받은 인가코드와 프론트에서 넘겨받은 
			 state 코드를 통하여 다시 네이버 서버에 access_token를 요청합니다. 
			 다음의 필드는 네이버가
		 */
		String access_token = "";
		String refresh_token = "";
		String token_type = "";
		String expires_in = "";
		/*
		 * 발급받은 아이디와 키
		 */
		String client_id = "5KpwB8RgMCCdOcSjRxPd";
		String client_secret = "1SjTkviz8f";
		try {
			/*
			 * URL을 편하게 조합해주기 위한 StringBuilder 객체를 생성.
			 * StringBuilder를 사용하면 하나한 객체를 생성하지 않고 계속 조합이 가능함. _편리. 
			 */
			StringBuilder urlComb = new StringBuilder();
			urlComb
				.append("https://nid.naver.com/oauth2.0/token?")
				.append("grant_type=authorization_code&")
				.append("client_id="+client_id+"&")
				.append("client_secret="+client_secret+"&")
				//api 요청으로 넘겨받은 code
				.append("code="+code+"&")
				//클라이언트에서 전달받은 stateCode (사용자 지정 보안코드)
				.append("state="+state);
			String requestAccessTokenURL = urlComb.toString();
			System.out.println("NaverLoginService.getAuthCode() requestAccessTokenURL :: " + requestAccessTokenURL);
			
			/*
			 * 생성한 url을 URL 객체를 생성하여 네이버 서버에 access_token을 요청
			 */
			URL url = new URL(requestAccessTokenURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json"); // Content-Type 지정
			conn.setDoOutput(true); // 출력 가능 상태로 변경
			
			//http 응답코드 200 일경우 JSON 형태로 응답받음.  
			int resCode = conn.getResponseCode();
			System.out.println("NaverLoginService.naverLogin() :: "+resCode);
			
			/*
			 *  데이터  읽어오기
			 *  http 통신으로 받은 데이터는 바이트 단위로 잘게잘게 쪼개어진 stream 형태로 전달받고
			 *  HttpURLConnection객체에서 제공해주는 getInputStream()메소드가 이를 읽어드립니다.
			 *  그 다음 BufferedReader객체 생성하고 해당 객체에서 제공해주는 메소드를 사용하여 
			 *  사람이 이해할 수 있는 언어로 변환해주는 Process를 진행해야 합니다.
			 */
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			StringBuilder sb = new StringBuilder();
			String line = "";
			while((line = br.readLine()) != null) { 
				/*
				 *  readLine() 데이터를 한줄씩 읽고 문자열로 반환.
				 *  반환한 문자열을 line에 할당하고 null일때 까지(데이터를 다 읽어들일때 까지)
				 *  반복문 실행
				 */
				sb.append(line); // sb에 계속 문자열을 추가함. 
				
			}

			// 요청 성공시 JSON형태로 데이터를 받아오기 떄문에 String 형태로 Parsing해야함. 
			JSONObject jsonObj = (JSONObject) new JSONParser().parse(sb.toString());
			access_token = jsonObj.get("access_token").toString();
			refresh_token = jsonObj.get("refresh_token").toString();
			token_type = jsonObj.get("token_type").toString();
			expires_in = jsonObj.get("expires_in").toString();
			
			/*
			 * BufferedReader는 Garbage Collector가 존재하기 때문에 
			 * BufferedReader 및 내부 객체들이 자동으로 정리된다. close()메소드는 가끔의 경우를 제외하고 안써도 문제 없다.
			 * 그러나 원시 언어(Native)인경우 개발자가 자원관리를 직접 해주어야하기 때문에 문제 없어도 습관을 가지도록 하는게 좋다고 함. 
			 */
			br.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		System.out.println(
				"NaverLoginService.getAuthCode() :: "+ "\n" +
				"access_token :: " + access_token + "\n" +
				"refresh_token :: " + refresh_token + "\n" +
				"token_type :: " + token_type + "\n" +
				"expires_in :: " + expires_in + "\n" 
		);
		
		return access_token;
	}
	
	public String getUserInfo(String access_token) {
		
		System.out.println("NaverLoginService.getUserInfo() access_token :: " +  access_token);
		
		
		//HashMap<String, Object> userInfo = new HashMap<String, Object>(); Repository에 보낼 빈 HashMap객체 생성. 
		
		/*
		 * 제공받은 access_token을 가지고 로그인하려는 유저의 정보를 요청할 url 입니다.
		 * reqURL은 유저 정보를 요청할 주소입니다. 
		 */
		String reqURL = "https://openapi.naver.com/v1/nid/me";
		
		try {
			URL url = new URL(reqURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");

			/*
			 * 네이버의 가이드에 따르면 요청에 필요한 Header 명을 Authorization으로 설정해야 합니다.
			 * HttpURLConnection에서 제공해주는 setRequestProperty() 메소드로 헤더에 헤더이름과 access_token을 담아서 요청합니다. 
			 */
			conn.setRequestProperty("Authorization", "Bearer " + access_token);
			
			//응답코드 200일 경우 요청성공입니다. json 형태로 응답받습니다.
			int responseCode = conn.getResponseCode();
			System.out.println("responseCode : " + responseCode);
			
			//데이터를 읽기위한 작업. 
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			String line = "";
			String result = "";
			
			//응답받은 http body를 스트링으로 변환하여 선언한 변수 result에 담음.
			while ((line = br.readLine()) != null) {
				result += line;
			}
			System.out.println("getUserInfo response body : " + result);
			
			//응답받은 http body, json 객체를 문자열로 파싱해줌.
			JsonElement element = JsonParser.parseString(result);
			
			/*
			 * 문자열로 파싱한 json 내부에 response라는 객체가 담겨져있음.
			 * 이 객체 안에 유저의 정보다 담겨져 있기 때문에 따로 JsonObject로 추출하는 작업 필요
			 */
			JsonObject response = element.getAsJsonObject().get("response").getAsJsonObject();
			
			System.out.println("response : " + response);
			
			//JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
			
			/**
			 * 네이버 디벨로퍼에서 내 애플리케이션에서 선택한 제공정보를 통해 데이터를 가져옵니다. 
			 */
			String id = response.getAsJsonObject().get("id").getAsString();
			String nickname = response.getAsJsonObject().get("nickname").getAsString();
			String profile_img = response.getAsJsonObject().get("profile_image").getAsString();
			String age = response.getAsJsonObject().get("age").getAsString();
			String gender = response.getAsJsonObject().get("gender").getAsString();
			String email = response.getAsJsonObject().get("email").getAsString();
			String mobile = response.getAsJsonObject().get("mobile").getAsString();
			String mobile_e164 = response.getAsJsonObject().get("mobile_e164").getAsString();
			String name = response.getAsJsonObject().get("name").getAsString();
			String birthday = response.getAsJsonObject().get("birthday").getAsString();
			String birthyear = response.getAsJsonObject().get("birthyear").getAsString();
			
			System.out.println(
					"NaverLoginService.getUserInfo() :: "+ "\n" +
					"id :: " + id + "\n" +
					"nickname :: " + nickname + "\n" +
					"profile_img :: " + profile_img + "\n" +
					"age :: " + age + "\n" +
					"gender :: " + gender + "\n" +
					"email :: " + email + "\n" +
					"mobile :: " + mobile + "\n" +
					"mobile_e164 :: " + mobile_e164 + "\n" +
					"name :: " + name + "\n" +
					"birthday :: " + birthday + "\n" +
					"birthyear :: " + birthyear + "\n"
					);
			
			
			
			/*
			 * userInfo :: 코드라인 121의 HashMap 객체
			 * userInfo.put("infrNaverId", id);
			 * userInfo.put("infrMmNickname",);
			 * userInfo.put("infrMmProfile_img",profile_img);
			 * userInfo.put("infrMmAge",gender);
			 * userInfo.put("infrMmEmail",email);
			 * userInfo.put("infrMmMobile",mobile);
			 * userInfo.put("infrMmMobile_e164",mobile_e164);
			 * userInfo.put("infrMmName",name);
			 * userInfo.put("infrMmBirthday",birthday);
			 * userInfo.put("infrMmBirthyear",birthyear);
			 */
		}catch(Exception e){
			e.printStackTrace();
		}
		
		/*
		 * 여기서부턴 네이버 로그인으로 불러온 정보들을 DAO 로직과 연동하여 return해주시면 됩니다.
		 * 
		 * ex) 네이버 로그인을 시도한 멤버가 이미 어플리케이션 서비스에 가입한 멤버인지 아닌지 분기를 만들어줍니다.
		 * 
		 * MemberGroupDto result = dao.findNaverMember(userInfo);
		 * 
		 * if(result == null){
		 * 		dao.naverLogin(userInfo)
		 * 		return dao.findNaverMember(userInfo);
		 * }else{
		 * 		return result
		 * }
		 */
		
		return "return_MemberGroupDao";
	}

}
