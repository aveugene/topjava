<%@page import="ru.javawebinar.topjava.model.MealTo" %>
<%@page import="ru.javawebinar.topjava.util.TimeUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <jsp:useBean id="meal" type="ru.javawebinar.topjava.model.Meal" scope="request"/>
    <title>Резюме ${meal.description}</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<form method="post" action="meals" enctype="application/x-www-form-urlencoded">
    <input type="hidden" name="id" value="${meal.id}">
    <table border="1" cellpadding="8" cellspacing="0">
        <tr>
            <th>Date/Time</th>
            <th><input type="datetime" name="datetime" size=50
                       value="<%=ru.javawebinar.topjava.util.TimeUtil.dateToHtml(meal.getDateTime())%>"></th>
        </tr>
        <tr>
            <th>Description</th>
            <th><input type="text" name="description" size=50 value="${meal.description}"></th>
        </tr>
        <tr>
            <th>Calories</th>
            <th><input type="text" name="calories" size=50 value="${meal.calories}"></th>
        </tr>
    </table>
    <button type="submit">Сохранить</button>
    <button onclick="window.history.back()">Отменить</button>
</form>
</body>
</html>