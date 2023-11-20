<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="messages" />
<jsp:useBean id="rooms" scope="request" type="java.util.List"/>
<c:choose>
    <c:when test="${ sessionScope.role eq null || sessionScope.role eq 'client'}">
        <% response.sendRedirect("welcome?command=FORBIDDEN_COMMAND"); %>
    </c:when>
    <c:otherwise>
        <tags:header/>
        <tags:links pageTitle="ADMIN-ROOMS">

            <h1><fmt:message key="admin_rooms.title1"/></h1>
            <div class="mt-3 p-5">
                <form action="welcome" method="POST" class="p-2 border rounded border-dark">
                    <input type="hidden" name="command" value="ADMIN_ADD_ROOM"/>
                    <input type="hidden" name="subCommand" value="ADD_ROOM"/>
                    <div class="form-group row">
                        <label for="num" class="col-5 col-form-label"><fmt:message key="admin_rooms.form_amount"/></label>
                        <div class="col-7">
                            <input id="num" name="num" type="number" required="required" class="form-control">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="price" class="col-5 col-form-label"><fmt:message key="admin_rooms.form_price"/></label>
                        <div class="col-7">
                            <input id="price" name="price" placeholder="Enter price in $" type="number"
                                   class="form-control" required="required">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="description" class="col-5 col-form-label"><fmt:message key="admin_rooms.form_description"/></label>
                        <div class="col-7">
                            <textarea id="description" name="description" cols="40" rows="5" class="form-control"
                                      required="required"></textarea>
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="offset-5 col-7">
                            <button name="submit" type="submit" class="btn btn-primary">Submit</button>
                        </div>
                    </div>
                </form>
            </div>
            <hr/>
            <h1><fmt:message key="admin_rooms.title2"/></h1>
            <table class="table table-striped">
                <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col"><fmt:message key="admin_rooms.form_amount"/></th>
                    <th scope="col"><fmt:message key="admin_rooms.form_price"/></th>
                    <th scope="col"><fmt:message key="admin_rooms.form_description"/></th>
                    <th scope="col"><fmt:message key="admin_rooms.action"/></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="room" items="${rooms}">
                    <tr>
                        <th scope="row">${room.id}</th>
                        <td>${room.num}</td>
                        <td>${room.price}</td>
                        <td>${room.description}</td>

                        <td class="d-flex justify-around">
                            <form action="welcome" method="POST">
                                <input type="hidden" name="command" value="ADMIN_EDIT_ROOM" />
                                <input type="hidden" name="id" value="${room.id}" />
                                <button type="submit" class="btn btn-primary">
                                    <fmt:message key="admin_rooms.action_edit"/>
                                </button>
                            </form>
                            <form action="welcome" method="POST">
                                <input type="hidden" name="command" value="ADMIN_ADD_ROOM" />
                                <input type="hidden" name="subCommand" value="DELETE_ROOM" />
                                <input type="hidden" name="id" value="${room.id}" />
                                <button type="submit" class="btn btn-danger">
                                    <fmt:message key="admin_rooms.action_delete"/>
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