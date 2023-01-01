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
📦main
 ┣ 📂java
 ┃ ┣ 📂com
 ┃ ┃ ┣ 📂seoul
 ┃ ┃ ┃ ┣ 📂infra
 ┃ ┃ ┃ ┃ ┣ 📂common
 ┃ ┃ ┃ ┃ ┃ ┣ 📂basic
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂crypto
 ┃ ┃ ┃ ┃ ┃ ┣ 📂interceptor
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜CheckLoginSessionInterception.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📂utilFunc
 ┃ ┃ ┃ ┃ ┃ ┗ 📜MailService.java
 ┃ ┃ ┃ ┃ ┣ 📂dto
 ┃ ┃ ┃ ┃ ┣ 📂modules
 ┃ ┃ ┃ ┃ ┃ ┣ 📂exchange                             exchange :: 거래소 Back-End-Side 로직 구현 package
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂controller                         controller :: 거래소 페이지의 의 Controller                        
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂dto
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂exchwebsocket                      exchwebsocket :: 프로젝트의 STOMP WebSocket Configure Package
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂orderMatchingSystem                orderMatchingSystem :: 거래기능(OrderBook Algorithm) 관련 서비스로직 Package
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ExchMapper.xml
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ExchangeDao.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ExchangeService.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ExchangeServiceImpl.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📂helpcenter                           helpcenter :: 고객센터 페이지의 서비스 로직 pakcage
 ┃ ┃ ┃ ┃ ┃ ┣ 📂investments                          investments :: 보유자산 및 자산평가, 거래내역 서비스로직 package
 ┃ ┃ ┃ ┃ ┃ ┣ 📂membergroup                          membergroup :: 유저관리 관련 서비스 로직 package
 ┃ ┃ ┃ ┃ ┃ ┗ 📜A.java
 ┃ ┃ ┃ ┃ ┗ 📜HomeController.java
 ┃ ┃ ┃ ┗ 📜.DS_Store
 ┃ ┃ ┗ 📜.DS_Store
 ┃ ┗ 📜.DS_Store
 ┣ 📂resources
 ┃ ┣ 📜log4j.xml
 ┃ ┣ 📜log4jdbc.log4j2.properties
 ┃ ┗ 📜mybatis-config.xml
 
 
 ### Front-End
 
 ┣ 📂webapp
 ┃ ┣ 📂META-INF
 ┃ ┣ 📂WEB-INF
 ┃ ┃ ┣ 📂lib
 ┃ ┃ ┣ 📂spring
 ┃ ┃ ┃ ┣ 📂appServlet
 ┃ ┃ ┃ ┃ ┣ 📜schedule.xml
 ┃ ┃ ┃ ┃ ┣ 📜servlet-context.xml
 ┃ ┃ ┃ ┃ ┗ 📜stomp.xml
 ┃ ┃ ┃ ┗ 📜root-context.xml
 ┃ ┃ ┣ 📂views
 ┃ ┃ ┃ ┣ 📂exchange
 ┃ ┃ ┃ ┣ 📂helpcenter
 ┃ ┃ ┃ ┣ 📂home
 ┃ ┃ ┃ ┣ 📂hsySocket
 ┃ ┃ ┃ ┣ 📂include
 ┃ ┃ ┃ ┣ 📂investments
 ┃ ┃ ┃ ┣ 📂rscs
 ┃ ┃ ┃ ┣ 📂templates
 ┃ ┃ ┃ ┣ 📂userOrderHistory
 ┃ ┃ ┗ 📜web.xml
 ┃ ┗ 📂resources
 ┃ ┃ ┣ 📂css
 ┃ ┃ ┣ 📂font
 ┃ ┃ ┣ 📂image
 ┃ ┃ ┣ 📂js
 ┃ ┃ ┃ ┣ 📂exchange
 ┃ ┃ ┃ ┃ ┣ 📂BoS
 ┃ ┃ ┃ ┃ ┣ 📂cryptoList
 ┃ ┃ ┃ ┃ ┣ 📂exchWebSock                exchWebSock 
 ┃ ┃ ┃ ┃ ┃ ┗ 📜getOBByWebSock.js        getOBByWebSock.js :: stomp websocket 기반 실시간 거래 기능 구현을 위한 front logic script
 ┃ ┃ ┃ ┃ ┣ 📂transacationHistory
 ┃ ┃ ┃ ┃ ┃ ┗ 📜transacationHistory.js
 ┃ ┃ ┃ ┃ ┣ 📜exchange.js
 ┃ ┃ ┃ ┣ 📂homeComponent                homeComponent :: 로그인 및 회원가입 관연 로직을 포함한 folder
 ┃ ┃ ┃ ┃ ┣ 📂addUser
 ┃ ┃ ┃ ┃ ┃ ┣ 📜Validation.js
 ┃ ┃ ┃ ┃ ┣ 📜header.js
 ┃ ┃ ┃ ┃ ┣ 📜home.js
 ┃ ┃ ┃ ┃ ┣ 📜login.js
 ┃ ┃ ┃ ┣ 📂investment                   investment :: 자산투자 현황 관려 로직을 포함한 folder
 ┃ ┃ ┃ ┃ ┣ 📜balance.js
 ┃ ┃ ┃ ┃ ┣ 📜history.js
 ┃ ┃ ┃ ┃ ┗ 📜wait_orders.js
 ┃ ┃ ┃ ┣ 📜.js
 ┃ ┃ ┃ ┣ 📜chart.js
 ┃ ┃ ┃ ┣ 📜helplcenter.js
 ┃ ┃ ┃ ┣ 📜pagination.js
 ┃ ┃ ┃ ┣ 📜paginationSH.js
 ┃ ┃ ┃ ┣ 📜paging.js
 ┃ ┃ ┃ ┣ 📜templet.js
 ┗ 📜.DS_Store
