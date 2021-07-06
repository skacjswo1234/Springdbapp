# DBAPP~

### 데이터베이스 생성 방법
```SQL
CREATE user 'korea'@'%' identified by'korea1234';

GRANT ALL PRIVILEGES ON *.* TO 'korea'@'%';

create database koreadb;

```
###추가의존성
```
	<dependency>
    <groupId>org.apache.tomcat</groupId>
    <artifactId>tomcat-jasper</artifactId>
   	 <version>9.0.46</version>
</dependency>
	
	<dependency>
   		<groupId>javax.servlet</groupId>
  		 <artifactId>jstl</artifactId>
 		<version>1.2</version>
	</dependency>
```

###JSTL 태그 라이브러리
```jsp
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
```

### application.yml
```yml
server:
  port: 8000
  servlet:
    encoding:
      charset: UTF-8
      
spring:
  mvc:
    view:
      prefix: /WEB-INF/views/
      suffix: .jsp
      
  datasource:
    driver-class-name: org.mariadb.jdbc.Driver
    username: korea
    password: korea1234
    url: jdbc:mysql://localhost:3306/koreadb
    
  jpa:
   hibernate:
    ddl-auto: none #create, update, none
   show-sql: true

```

### 예비 데이터 생성

```sql
INSERT INTO user(username, PASSWORD) VALUES ('ssar','1234');
INSERT INTO user(username, PASSWORD) VALUES ('cos','1234');

INSERT INTO post(title, content, myid) VALUES('제목1','내용1',1);
INSERT INTO post(title, content, myid) VALUES('제목2','내용2',1);
INSERT INTO post(title, content, myid) VALUES('제목3','내용3',1);
INSERT INTO post(title, content, myid) VALUES('제목4','내용4',2);
INSERT INTO post(title, content, myid) VALUES('제목5','내용5',2);
```

### 주소 아이피 일단 
```
https://www.juso.go.kr/addrlink/addrLinkUrl.do?confmKey=devU01TX0FVVEgyMDIxMDcwNTE3MjgyMzExMTM2MTE=&returnUrl=http://localhost:8000
```

### 주소 아이피 코드 
```
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
	<meta charset=UTF-8"/>
	<title>주소 API</title>
</head>

<body onload="init();">
	<form id="form" name="form" method="post">
		<input type="hidden" id="confmKey" name="confmKey" value=""/>
		<input type="hidden" id="returnUrl" name="returnUrl" value=""/>
		<input type="hidden" id="resultType" name="resultType" value=""/>
	</form>
	
<script>
	function init(){
		var url = location.href;
		var confmKey = "devU01TX0FVVEgyMDIxMDcwNTE3MjgyMzExMTM2MTE=";
		var resultType = "4"; // 도로명주소 검색결과 화면 출력내용, 1 : 도로명, 2 : 도로명+지번+상세보기(관련지번, 관할주민센터), 3 : 도로명+상세보기(상세건물명), 4 : 도로명+지번+상세보기(관련지번, 관할주민센터, 상세건물명)
		var inputYn= "${inputYn}";
		if(inputYn != "Y"){
			console.log("최초 요청");
			document.form.confmKey.value = confmKey;
			document.form.returnUrl.value = url;
			document.form.resultType.value = resultType;
			document.form.action="https://www.juso.go.kr/addrlink/addrLinkUrl.do"; //인터넷망
			//document.form.action="https://www.juso.go.kr/addrlink/addrMobileLinkUrl.do"; //모바일 웹인 경우, 인터넷망
			document.form.submit();
		}else{
			console.log("추후 응답");
			opener.jusoCallBack("${roadFullAddr}");
			window.close();
		}
	}
</script>
</body>

</html>
```