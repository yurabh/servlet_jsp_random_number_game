<%@ page import="domain.Game" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>

<head>
    <title><%=config.getInitParameter("titleGameOverJsp")%>
    </title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
          crossorigin="anonymous">
</head>

<body class="bg-danger">

<div class="container w-25 mt-5 text-white">
    <h5><%
        Game game = (Game) session.getAttribute("game");
        if (game.getPlayer().getUserName() != null) {
            out.println("Name User: " + game.getPlayer().getUserName());
        }
    %></h5>
</div>


<h5 class="container w-25 text-white">Result game: <%=session.getAttribute("answer")%>
</h5>

<h5 class="container w-25 text-white">Won: <%=session.getAttribute("rate")%>
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
