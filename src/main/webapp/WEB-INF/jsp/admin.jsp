<%--
  Created by IntelliJ IDEA.
  User: Your Phycologyst
  Date: 13.11.2023
  Time: 16:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="users" scope="request" type="java.util.List"/>
<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="messages" />
<c:choose>
    <c:when test="${ sessionScope.role eq null || sessionScope.role eq 'client'}">
        <% response.sendRedirect("welcome?command=FORBIDDEN_COMMAND"); %>
    </c:when>
    <c:otherwise>
        <tags:header />
        <tags:links pageTitle="ADMIN">
            <h1><fmt:message key="admin_panel.title"/></h1>
            <hr />
            <h1><fmt:message key="admin_panel.subtitle"/></h1>
            <table class="table table-striped">
                <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col"><fmt:message key="admin_panel.login"/></th>
                    <th scope="col"><fmt:message key="admin_panel.email"/></th>
                    <th scope="col"><fmt:message key="admin_panel.name"/></th>
                    <th scope="col"><fmt:message key="admin_panel.surname"/></th>
                    <th scope="col"><fmt:message key="admin_panel.admin_status"/></th>
                    <th scope="col"><fmt:message key="admin_panel.discount_status"/></th>
                    <th scope="col"><fmt:message key="admin_panel.ban_status"/></th>
                    <th scope="col"><fmt:message key="admin_panel.action"/></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="user" items="${users}">
                    <tr>
                        <th scope="row">${user.id}</th>
                        <td>${user.login}</td>
                        <td>${user.email}</td>
                        <td>${user.userName}</td>
                        <td>${user.userSurname}</td>
                        <td>
                            <c:choose>
                                <c:when test="${ user.adminRole == true}">
                                    <fmt:message key="yes"/>
                                </c:when>
                                <c:otherwise>
                                    <fmt:message key="no"/>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>${user.discount * 100}%</td>
                        <td>
                            <c:choose>
                                <c:when test="${ user.banStatus == true}">
                                    <fmt:message key="yes"/>
                                </c:when>
                                <c:otherwise>
                                    <fmt:message key="no"/>
                                </c:otherwise>
                        </c:choose>
                        </td>
                        <td class="d-flex justify-around">
                            <form action="welcome" method="POST">
                                <input type="hidden" name="command" value="ADMIN_PAGE" />
                                <input type="hidden" name="subCommand" value="BAN_USER_COMMAND" />
                                <input type="hidden" name="login" value="${user.login}" />

                                <button type="submit" class="btn btn-danger">
                                    <c:choose>
                                        <c:when test="${ user.banStatus == true}">
                                            <fmt:message key="admin_panel.action_unban"/>
                                        </c:when>
                                        <c:otherwise>
                                            <fmt:message key="admin_panel.action_ban"/>
                                        </c:otherwise>
                                    </c:choose>
                                    USER</button>
                            </form>

                            <form action="welcome" method="POST">
                                <input type="hidden" name="command" value="ADMIN_PAGE" />
                                <input type="hidden" name="subCommand" value="SET_ADMIN_COMMAND" />
                                <input type="hidden" name="login" value="${user.login}" />
                                <button type="submit" class="btn btn-primary">
                                    <c:choose>
                                        <c:when test="${ user.adminRole == true}">
                                            <fmt:message key="admin_panel.action_admin_unset"/>
                                        </c:when>
                                        <c:otherwise>
                                            <fmt:message key="admin_panel.action_admin_set"/>
                                        </c:otherwise>
                                    </c:choose>
                                    </button>
                            </form>
                            <form action="welcome" method="POST" class="d-flex">
                                <input type="hidden" name="command" value="ADMIN_PAGE" />
                                <input type="hidden" name="subCommand" value="SET_DISCOUNT_COMMAND" />
                                <input type="hidden" name="login" value="${user.login}" />
                                <button type="submit" class="btn btn-primary"><fmt:message key="admin_panel.action_discount"/></button>
                                <div>
                                    <input type="range" name="discount" class="form-range" min="0" max="1" step="0.05" id="discount_input_${user.login}">
                                    <p><fmt:message key="admin_panel.action_discount_range"/>: <output id="value_${user.login}"></output> %</p>

                                </div>
                                <script>
                                    const value_${user.login} = document.querySelector("#value_${user.login}");
                                    const input_${user.login} = document.querySelector("#discount_input_${user.login}");
                                    value_${user.login}.textContent = Math.round(Number(input_${user.login}.value * 100));
                                    input_${user.login}.addEventListener("input", (event) => {
                                        value_${user.login}.textContent = Math.round(Number(event.target.value * 100));
                                    });

                                </script>

                            </form>

                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </tags:links>
    </c:otherwise>
</c:choose>

