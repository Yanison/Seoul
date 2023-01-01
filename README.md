# 황비트 _ OrderBook 알고리즘 기반 금융자산 거래소 시스템 구현 프로젝트

### 프로젝트 개요 및 목적<br>
주식,코인과 같은 자산거래에 필요한 OrderBook 알고리즘 기반 거래소 시스템 구현 프로젝트<br><br>

### 사용언어 및 개발 환경<br>
- System : AWS EC2 : CentOS 7<br>
- DB : AWS RDS : Mysql<br>
- FrontEnd-Language: javaScript,jQeury,Ajax<br>
- BackEnd-Language:Java<br>
- BackEnd-Framework:Spring Legacy,mybatis,maven<br>
- was:tomcat 9.0<br>




### 참고자료
Foundations of Reinforcement Learning with Applications in Finance<br>
https://stanford.edu/~ashlearn/RLForFinanceBook/chapter9.pdf <br>
- Java-Matching-Engine-Core<br>
https://github.com/Laffini/Java-Matching-Engine-Core<br>
 
- WebSocket
https://docs.spring.io/spring-framework/docs/5.2.6.RELEASE/spring-framework-reference/web.html#websocket<br>
https://spring.io/guides/gs/messaging-stomp-websocket/ <br>


## 프로젝트 구조

### Back-End
📦main<br>
 ┣ 📂java<br>
 ┃ ┣ 📂com<br>
 ┃ ┃ ┣ 📂seoul<br>
 ┃ ┃ ┃ ┣ 📂infra<br>
 ┃ ┃ ┃ ┃ ┣ 📂common<br>
 ┃ ┃ ┃ ┃ ┃ ┣ 📂basic<br>
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂crypto<br>
 ┃ ┃ ┃ ┃ ┃ ┣ 📂interceptor<br>
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜CheckLoginSessionInterception.java<br>
 ┃ ┃ ┃ ┃ ┃ ┣ 📂utilFunc<br>
 ┃ ┃ ┃ ┃ ┃ ┗ 📜MailService.java<br>
 ┃ ┃ ┃ ┃ ┣ 📂dto<br>
 ┃ ┃ ┃ ┃ ┣ 📂modules<br>
 ┃ ┃ ┃ ┃ ┃ ┣ 📂exchange                             exchange :: 거래소 Back-End-Side 로직 구현 package <br>
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂controller                         controller :: 거래소 페이지의 의 Controller            
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂dto<br>
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂exchwebsocket                      exchwebsocket :: 프로젝트의 STOMP WebSocket Configure Package<br>
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂orderMatchingSystem                orderMatchingSystem :: 거래기능(OrderBook Algorithm) 관련 서비스로직 Package<br>
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ExchMapper.xml<br>
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ExchangeDao.java<br>
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ExchangeService.java<br>
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ExchangeServiceImpl.java<br>
 ┃ ┃ ┃ ┃ ┃ ┣ 📂helpcenter                           helpcenter :: 고객센터 페이지의 서비스 로직 pakcage<br>
 ┃ ┃ ┃ ┃ ┃ ┣ 📂investments                          investments :: 보유자산 및 자산평가, 거래내역 서비스로직 package<br>
 ┃ ┃ ┃ ┃ ┃ ┣ 📂membergroup                          membergroup :: 유저관리 관련 서비스 로직 package<br>
 ┃ ┃ ┃ ┃ ┃ ┗ 📜A.java<br>
 ┃ ┃ ┃ ┃ ┗ 📜HomeController.java<br>
 ┃ ┃ ┃ ┗ 📜.DS_Store<br>
 ┃ ┃ ┗ 📜.DS_Store<br>
 ┃ ┗ 📜.DS_Store<br>
 ┣ 📂resources<br>
 ┃ ┣ 📜log4j.xml<br>
 ┃ ┣ 📜log4jdbc.log4j2.properties<br>
 ┃ ┗ 📜mybatis-config.xml<br>
 
 
 ### Front-End
 
 ┣ 📂webapp<br>
 ┃ ┣ 📂META-INF<br>
 ┃ ┣ 📂WEB-INF<br>
 ┃ ┃ ┣ 📂lib<br>
 ┃ ┃ ┣ 📂spring<br>
 ┃ ┃ ┃ ┣ 📂appServlet<br>
 ┃ ┃ ┃ ┃ ┣ 📜schedule.xml<br>
 ┃ ┃ ┃ ┃ ┣ 📜servlet-context.xml<br>
 ┃ ┃ ┃ ┃ ┗ 📜stomp.xml<br>
 ┃ ┃ ┃ ┗ 📜root-context.xml<br>
 ┃ ┃ ┣ 📂views                          views :: html(jsp) package<br> 
 ┃ ┃ ┃ ┣ 📂exchange                     exchange :: 거래소<br> 
 ┃ ┃ ┃ ┣ 📂helpcenter                   helpcenter :: 고객센터 <br> 
 ┃ ┃ ┃ ┣ 📂home                         home :: 홈<br> 
 ┃ ┃ ┃ ┣ 📂include                      include :: header, footer 와 같은 include 요소들<br> 
 ┃ ┃ ┃ ┣ 📂investments                  investments :: 보유자산 페이지 <br> 
 ┃ ┃ ┃ ┣ 📂rscs                         rscs :: 외부 resource 관리 include <br>
 ┃ ┃ ┃ ┣ 📂templates<br>
 ┃ ┃ ┃ ┣ 📂userOrderHistory             userOrderHistory :: 유저의 거래내역 페이지<br> 
 ┃ ┃ ┗ 📜web.xml<br> 
 ┃ ┗ 📂resources<br> 
 ┃ ┃ ┣ 📂css<br> 
 ┃ ┃ ┣ 📂font<br> 
 ┃ ┃ ┣ 📂image<br> 
 ┃ ┃ ┣ 📂js<br> 
 ┃ ┃ ┃ ┣ 📂exchange<br> 
 ┃ ┃ ┃ ┃ ┣ 📂BoS<br> 
 ┃ ┃ ┃ ┃ ┣ 📂cryptoList<br> 
 ┃ ┃ ┃ ┃ ┣ 📂exchWebSock                exchWebSock <br> 
 ┃ ┃ ┃ ┃ ┃ ┗ 📜getOBByWebSock.js        getOBByWebSock.js :: stomp websocket 기반 실시간 거래 기능 구현을 위한 front logic script<br> 
 ┃ ┃ ┃ ┃ ┣ 📂transacationHistory<br> 
 ┃ ┃ ┃ ┃ ┃ ┗ 📜transacationHistory.js<br> 
 ┃ ┃ ┃ ┃ ┣ 📜exchange.js<br> 
 ┃ ┃ ┃ ┣ 📂homeComponent                homeComponent :: 로그인 및 회원가입 관연 로직을 포함한 folder<br> 
 ┃ ┃ ┃ ┃ ┣ 📂addUser<br> 
 ┃ ┃ ┃ ┃ ┃ ┣ 📜Validation.js<br> 
 ┃ ┃ ┃ ┃ ┣ 📜header.js<br> 
 ┃ ┃ ┃ ┃ ┣ 📜home.js<br> 
 ┃ ┃ ┃ ┃ ┣ 📜login.js<br> 
 ┃ ┃ ┃ ┣ 📂investment                   investment :: 자산투자 현황 관련 로직을 포함한 folder<br> 
 ┃ ┃ ┃ ┃ ┣ 📜balance.js<br> 
 ┃ ┃ ┃ ┃ ┣ 📜history.js<br> 
 ┃ ┃ ┃ ┃ ┗ 📜wait_orders.js<br> 
 ┃ ┃ ┃ ┣ 📜.js<br> 
 ┃ ┃ ┃ ┣ 📜chart.js<br> 
 ┃ ┃ ┃ ┣ 📜helplcenter.js<br> 
 ┃ ┃ ┃ ┣ 📜pagination.js<br> 
 ┃ ┃ ┃ ┣ 📜paginationSH.js<br> 
 ┃ ┃ ┃ ┣ 📜paging.js<br> 
 ┃ ┃ ┃ ┣ 📜templet.js<br> 
 ┗ 📜.DS_Store<br> 
