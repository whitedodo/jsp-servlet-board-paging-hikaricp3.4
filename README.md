# jsp-servlet-board-paging-hikaricp3.4
JSP/Servlet 기반의 board-paging을 hikaricp3.4(커넥션풀)과 Oracle 19g로 구현함.(MVC2 - FrontController, Command 패턴 적용)

### 기본 정보(Information)
##### 제작일자(Create date): 2020-09-29
##### 작성언어(Write language): Java
##### IDE: Eclipse IDE with Spring Tool Suite 4-4.7.2.
##### 제작자(Author): 도도(Dodo) / rabbit.white at daum dot net
##### 프레임워크(Framework): 
##### 라이브러리(Library): 
##### 1. Apache-Maven 3.6.3/1.16.0.2.20200610-1735 (https://maven.apache.org/)
##### (소프트웨어 프로젝트 관리 및 이해 도구)
##### 2. javax.servlet-api (4.0.1) - https://mvnrepository.com/artifact/javax.servlet/javax.servlet-api
##### 3. web.xml 규격 변경(servlet 2.5 -> servlet3.5로)
##### 4. HikariCP (4.0.1) - https://mvnrepository.com/artifact/com.zaxxer/HikariCP
##### 5. javax.servlet-jstl (1.2)
##### 6. taglibs (1.1.2)
##### 7. Maven 3.6.3/1.16.0.20200610-1735
##### 자바 버전(Java-Version): OpenJDK-14.0.2 (https://openjdk.java.net/)

### 1. 소개(Description)
##### 1. 해당 프로젝트는 Maven Project (webapp)기반으로 작성하였다.
##### (The project was created based on Maven Project (webapp).)
##### 2. Oracle 19g와 HikariCP 3.4.2를 커넥션 풀로 연결하였다.
##### (Oracle 19g and HikariCP 3.4.2 are connected through a connection pool.)
##### 3. board 게시판을 설계하였다. 그리고 페이징로직을 적용하였다.
##### (The board board was designed. And paging logic was applied.)
##### 4. 게시판 검색 기능을 "제목" 검색만 구현하였다.
##### (The bulletin board search function was implemented only for "title" search.)

### 2. 시연(Demonstration)
##### 1. Hikaricp, JSP/Servlet Maven MVC2 - Board Paging(게시판 페이징), https://youtu.be/IdkfrGXQSdM, Accessed by 2020-09-30, Last Modified 2020-09-30

### 3. 참고자료(Reference)
##### 1. Maven Repository: Search/Browse/Explore, https://mvnrepository.com/, Accessed by 2020-09-29, Last Modified .
##### 2. Maven - Welcome to Apache Maven, https://maven.apache.org/, Accessed by 2020-09-29, Last Modified .
##### 3. Spring | Tools, https://spring.io/tools, Accessed by 2020-09-29, Last Modified .
##### 4. OpenJDK, https://openjdk.java.net/, Accessed by 2020-09-29, Last Modified .
##### 5
