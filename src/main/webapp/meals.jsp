<%@page import="ru.javawebinar.topjava.model.MealTo" %>
<%@page import="ru.javawebinar.topjava.util.TimeUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Meals</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2> <a href="meals?action=add">Новая еда</a>

<table border="1" cellpadding="8" cellspacing="0">
    <tr>
        <th>Date/Time</th>
        <th>Description</th>
        <th>Calories</th>
        <th></th>
        <th></th>
    </tr>
    <c:forEach items="${meals}" var="meal">
        <jsp:useBean id="meal" type="ru.javawebinar.topjava.model.MealTo"/>
        <tr data-mealExcess="<%=meal.isExcess()%>">
            <th><%=ru.javawebinar.topjava.util.TimeUtil.dateToHtml(meal.getDateTime())%>
            </th>
            <th><%=meal.getDescription()%>
            </th>
            <th><%=meal.getCalories()%>
            </th>
            <th><a href="meals?id=${meal.id}&action=edit">Edit</a></th>
            <th><a href="meals?id=${meal.id}&action=delete">Delete</a></th>
        </tr>
    </c:forEach>
</table>
</body>
</html>