<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<header>
    <a href="meals"><spring:message code="app.title"/></a> | <a href="users"><spring:message code="user.title"/></a> | <a href="${pageContext.request.contextPath}"><spring:message code="app.home"/></a>
    <a href="${requestScope['javax.servlet.forward.request_uri']}?lang=en">en</a> -=- <a href="${requestScope['javax.servlet.forward.request_uri']}?lang=ru">ru</a>
    Your Locale is ${pageContext.response.locale}
</header>