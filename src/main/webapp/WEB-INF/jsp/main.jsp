<%--
  Created by IntelliJ IDEA.
  User: Your Phycologyst
  Date: 07.11.2023
  Time: 19:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="messages" />
<tags:header />
<tags:links pageTitle="MAIN">
    <h1>HELLO, IT'S YOUR PAGE</h1>
</tags:links>