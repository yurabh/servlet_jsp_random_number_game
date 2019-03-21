<%@page isErrorPage="true" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    out.println("Всталася помилка поверніться назад на сторінку");
%>
<%@include file="/game.jsp" %>
</body>
</html>
