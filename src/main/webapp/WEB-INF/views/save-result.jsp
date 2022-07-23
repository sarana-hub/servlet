<%-- 회원 저장 - 뷰 --%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
 <meta charset="UTF-8">
</head>
<body>
성공

<%-- <%= ((Member)req.getAttribute("member")).getId() %>  --%>
<ul>
 <li>id=${member.id}</li>
 <li>username=${member.username}</li>
 <li>age=${member.age}</li>
</ul>
<%-- ${}을 사용하면 req의 attribute에 담긴 데이터를 조회 가능  --%>

<a href="/index.html">메인</a>
</body>
</html>
