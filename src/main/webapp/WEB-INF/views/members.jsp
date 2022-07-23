<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 회원 목록 조회 - 뷰 -->


<html>
<head>
 <meta charset="UTF-8">
 <title>Title</title>
</head>
<body>
<a href="/index.html">메인</a>
<table>
 <thead>
 <th>id</th>
 <th>username</th>
 <th>age</th>
 </thead>

 <tbody>
 <!-- 모델에 담아둔 members를 JSP가 제공하는 taglib기능을 사용해서 반복하면서 출력 -->
 <!-- members 리스트에서 member 를 순서대로 꺼내서 item 변수에 담고, 출력하는 과정을 반복 -->
 <c:forEach var="item" items="${members}">
    <tr>
        <td>${item.id}</td>
        <td>${item.username}</td>
        <td>${item.age}</td>
    </tr>
 </c:forEach>
 </tbody>

</table>
</body>
</html>