<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="example.models.Point" %>

<!DOCTYPE html>
<html lang="ru-RU">

<head>
    <meta charset="UTF-8">
    <link href="style.css" rel="stylesheet">
    <script src="js/hello.js"></script>
    <title>I LOVE WEB STILL</title>
</head>
<header>
    Результаты проверки
</header>
<body>
<table id="result_table" class="result_table">
    <thead>
    <th>X</th>
    <th>Y</th>
    <th>R</th>
    <th>Result</th>
    </thead>
    <%
        List<Point> points = (List<Point>) application.getAttribute("points");
        for (Point point : points) {
    %>
    <tbody>
    <td>
        <%= point.getX() %>
    </td>
    <td>
        <%= point.getY() %>
    </td>
    <td>
        <%= point.getR() %>
    </td>
    <td>
        <%= point.isInArea() ? "<span class=\"success\">success</span>"
                : "<span class=\"fail\">fail</span>" %>
    </td>
    </tbody>
    <% } %>

    <div id="back">
        <a href="index.jsp">Вернуться к форме</a>
    </div>
</table>
</body>
</html>

