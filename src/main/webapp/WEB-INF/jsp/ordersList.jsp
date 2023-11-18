<%--
  Created by IntelliJ IDEA.
  User: Your Phycologyst
  Date: 18.11.2023
  Time: 17:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="orders" scope="request" type="java.util.List"/>
<c:choose>
    <c:when test="${ sessionScope.role eq null }">
        <% response.sendRedirect("welcome?command=FORBIDDEN_COMMAND"); %>
    </c:when>
    <c:otherwise>
        <tags:header/>
        <tags:links pageTitle="BOOK">
            <hr/>
            <h1>MY ORDERS</h1>
            <table class="table table-striped">
                <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">ROOM</th>
                    <th scope="col">ORDER TYPE</th>
                    <th scope="col">NAME</th>
                    <th scope="col">SURNAME</th>
                    <th scope="col">PHONE NUMBER</th>
                    <th scope="col">EMAIL</th>
                    <th scope="col">STATUS</th>
                    <th scope="col">DATE SENT</th>
                    <th scope="col">PAY STATUS</th>
                    <th scope="col">FROM</th>
                    <th scope="col">TO</th>
                    <th scope="col">ACTIONS</th>
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
                                    YES
                                </c:when>
                                <c:otherwise>
                                    NO
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>${order.dateBegin}</td>
                        <td>${order.dateEnd}</td>
                        <td class="d-flex justify-around">
                            <c:choose>
                                <c:when test="${ order.status == 'CANCELED' }">
                                    YOUR ORDER CANCELED
                                </c:when>
                                <c:otherwise>
                                    <form action="welcome" method="POST">
                                        <input type="hidden" name="command" value="USER_ORDER_LIST" />
                                        <input type="hidden" name="orderID" value="${order.id}" />
                                        <button type="submit" class="btn btn-primary">
                                            CANCEL
                                        </button>
                                    </form>
                                </c:otherwise>
                            </c:choose>

                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </tags:links>
    </c:otherwise>
</c:choose>
