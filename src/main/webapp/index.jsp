<%--
  Created by IntelliJ IDEA.
  User: Maria
  Date: 20.11.2023
  Time: 18:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="example.models.Point" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="author" content="Мария Иванова">
    <meta name="description" content="Лабораторная работа №3 по веб-программированию">
    <title>I LOVE WEB</title>
    <link rel="stylesheet" href="./style.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js" defer></script>
    <script src="js/hello.js" defer></script>
    <script src="js/graph.js" defer></script>
</head>
<header align="center">
    Иванова М.М., Группа P3208, Вариант 534879
</header>
<div class="container">
    <noscript>включите джава скрипт</noscript>
    <div class="form">
        <form method="post" id="form">
            <div class="x"><b>Input X:</b>
                <input type="text" size="40" id="x_value" , placeholder="(-5 ... 3)">
            </div>
            <div  class="y">
                <b>Input Y:</b>
                <input type="checkbox" name="optionY" value="-2" id="y_value_-2" onchange="uncheckAllY(this)"> -2
                <input type="checkbox" name="optionY" value="-1.5" onchange="uncheckAllY(this)"> -1.5
                <input type="checkbox" name="optionY" value="-0.5" onchange="uncheckAllY(this)"> -0.5
                <input type="checkbox" name="optionY" value="0" onchange="uncheckAllY(this)"> 0
                <input type="checkbox" name="optionY" value="0.5" onchange="uncheckAllY(this)"> 0.5
                <input type="checkbox" name="optionY" value="1" onchange="uncheckAllY(this)"> 1
                <input type="checkbox" name="optionY" value="1.5" onchange="uncheckAllY(this)"> 1.5
                <input type="checkbox" name="optionY" value="2" onchange="uncheckAllY(this)"> 2
            </div>
            <div class="r" ><b> Input R:</b>
                <input type="checkbox" name="optionR" value="1" onchange="uncheckAllR(this)"> 1
                <input type="checkbox" name="optionR" value="1.5" onchange="uncheckAllR(this)"> 1.5
                <input type="checkbox" name="optionR" value="2" onchange="uncheckAllR(this)"> 2
                <input type="checkbox" name="optionR" value="2.5" onchange="uncheckAllR(this)"> 2.5
                <input type="checkbox" name="optionR" value="3" onchange="uncheckAllR(this)"> 3
            </div>
        </form>
        <div align="right">
            <button class="check" id="check" onclick="check_values()" type="submit">Check</button>
        </div>
    </div>
    <form class="canvas-form" method="post" action="/controller">
        <canvas width="400" height="410" id="graph"></canvas>
    </form>
    <%--<canvas width="400" height="400" id="graph"></canvas>--%>
    <div class="table">
        <table id="table">
            <thead>
                <%
                List<Point> points = (List<Point>) application.getAttribute("points");
                if (points == null || points.isEmpty()) {%>

                <% } else { %>
            <tr>
                <th>R</th>
                <th>X</th>
                <th>Y</th>
                <th>Status</th>
                <th>Time</th>
            </tr>
                <%

            for (Point point : points) { %>
            <tbody>
            <td><%= point.getX() %>
            </td>
            <td><%= point.getY() %>
            </td>
            <td><%= point.getR() %>
            </td>
            <td><%= point.isInArea() ? "<span class=\"success\">success</span>" : "<span class=\"fail\">fail</span>" %>
            </td>
            <td><%= point.getTime()%>
            </td>
            </tbody>
            <% } %>
        </table>
        <% } %>
    </div>

    <div align="left">
    </div>
</div>
</body>
</html>