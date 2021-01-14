<%@page isErrorPage="true" %>

<html>
<head>
    <title>Title</title>
</head>
<body>

<%
    out.println("return back to the previous page because exception happened");
%>

<%@include file="game.jsp" %>

</body>
</html>
