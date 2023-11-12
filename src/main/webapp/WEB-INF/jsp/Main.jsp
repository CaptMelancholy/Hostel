<%--
  Created by IntelliJ IDEA.
  User: Your Phycologyst
  Date: 07.11.2023
  Time: 19:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<tags:header pageTitle="MAIN">
    <h1>HELLO, IT'S YOUR PAGE</h1>
    <form action="welcome" method="get">
        <input type="hidden" name="command" value="REG_COMMAND" />
        <input type="submit" value="nothing" /><br/>
    </form>
</tags:header>