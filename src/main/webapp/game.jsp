<%@ page import="domain.Game" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page errorPage="error-page.jsp" %>

<html>
<head>
    <title><%=config.getInitParameter("titleGameJsp")%>
    </title>
    <meta charset="UTF-8">
    <title>Game Page</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
</head>

<body class="bg-secondary text-white">

<%
    Game game = (Game) session.getAttribute("game");
%>

<h5 class="container w-25 my-5">
    <%
        if (game.getCounter() != 0) {
            out.println("Спроба номер: " + game.getCounter());
        } else {
            out.println("Ввдіть перше число: ");
        }
    %>
</h5>

<div class="container w-25 my-5">
    <h5>Введіть число: від 0 до 10</h5>
    <form action="/game-servlet" method="post">
        <input type="number" class="form-control" id="user-name-label" name="number">
        <div class="container ml-5 mt-4" style="width: 100px">
            <button type="submit" class="btn btn-info" style="width: 200px" value="enter">Ввійти</button>
        </div>
    </form>
</div>


<%
    String answering = (String) session.getAttribute("answer");
%>

<h5 class="container w-25 my-5">
    <%
        if (answering == null) {
            out.println("Немає відповіді бо ігра тільки починаеться:");
        } else {
            out.println("Попереднє введене число: " + answering);
        }
    %>
</h5>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
        integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
        integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
        crossorigin="anonymous"></script>
</body>
</html>
