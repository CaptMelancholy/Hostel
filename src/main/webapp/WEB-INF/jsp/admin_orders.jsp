<%--
  Created by IntelliJ IDEA.
  User: Your Phycologyst
  Date: 16.11.2023
  Time: 20:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="messages" />
<jsp:useBean id="orders" scope="request" type="java.util.List"/>
<c:choose>
    <c:when test="${ sessionScope.role eq null || sessionScope.role eq 'client'}">
        <% response.sendRedirect("welcome?command=FORBIDDEN_COMMAND"); %>
    </c:when>
    <c:otherwise>
        <tags:header/>
        <tags:links pageTitle="ADMIN-ROOMS-EDIT">
            <hr/>
            <h1><fmt:message key="admin_orders.title"/></h1>
            <table class="table table-striped">
                <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col"><fmt:message key="admin_orders.room"/></th>
                    <th scope="col"><fmt:message key="admin_orders.type"/></th>
                    <th scope="col"><fmt:message key="admin_orders.name"/></th>
                    <th scope="col"><fmt:message key="admin_orders.surname"/></th>
                    <th scope="col"><fmt:message key="admin_orders.phone"/></th>
                    <th scope="col"><fmt:message key="admin_orders.email"/></th>
                    <th scope="col"><fmt:message key="admin_orders.status"/></th>
                    <th scope="col"><fmt:message key="admin_orders.datesent"/></th>
                    <th scope="col"><fmt:message key="admin_orders.pay"/></th>
                    <th scope="col"><fmt:message key="admin_orders.from"/></th>
                    <th scope="col"><fmt:message key="admin_orders.to"/></th>
                    <th scope="col"><fmt:message key="admin_orders.action"/></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="order" items="${orders}">
                    <tr>
                        <th scope="row">${order.id}</th>
                        <td>${order.roomID}</td>
                        <td>${order.type}</td>
                        <td>${order.name}</td>
                        <td>${order.surname}</td>
                        <td>${order.phoneNumber}</td>
                        <td>${order.email}</td>
                        <td>${order.status}</td>
                        <td>${order.date}</td>
                        <td>
                            <c:choose>
                                <c:when test="${ order.orderPaid == true}">
                                    <fmt:message key="yes"/>
                                </c:when>
                                <c:otherwise>
                                    <fmt:message key="no"/>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>${order.dateBegin}</td>
                        <td>${order.dateEnd}</td>
                        <td class="d-flex justify-around">
                            <form action="welcome" method="POST">
                                <input type="hidden" name="command" value="ADMIN_ORDERS" />
                                <input type="hidden" name="subCommand" value="APPROVE_ORDER" />
                                <input type="hidden" name="id" value="${order.id}" />
                                <button type="submit" class="btn btn-primary">
                                    <fmt:message key="admin_orders.action_approve"/>
                                </button>
                            </form>
                            <form action="welcome" method="POST">
                                <input type="hidden" name="command" value="ADMIN_ORDERS" />
                                <input type="hidden" name="subCommand" value="REJECT_ORDER" />
                                <input type="hidden" name="id" value="${order.id}" />
                                <button type="submit" class="btn btn-danger">
                                    <fmt:message key="admin_orders.action_reject"/>
                                </button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </tags:links>
    </c:otherwise>
</c:choose>
