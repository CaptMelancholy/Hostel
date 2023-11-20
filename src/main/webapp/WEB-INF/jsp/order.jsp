<%--
  Created by IntelliJ IDEA.
  User: Your Phycologyst
  Date: 18.11.2023
  Time: 21:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="rooms" scope="request" type="java.util.List"/>
<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="messages" />
<c:set var="selected" value=""/>
<c:choose>
    <c:when test="${ sessionScope.role eq null }">
        <% response.sendRedirect("welcome?command=FORBIDDEN_COMMAND"); %>
    </c:when>
    <c:otherwise>
        <tags:header/>
        <tags:links pageTitle="BOOK">
            <h1><fmt:message key="user_book.title"/></h1>
            <hr/>
            <form action="welcome" method="POST">
                <input type="hidden" name="command" value="USER_ORDER_CREATE" />
                <div class="form-group row">
                    <label class="col-4 col-form-label"><fmt:message key="user_book.type"/></label>
                    <div class="col-8">
                        <div class="custom-controls-stacked">
                            <div class="custom-control custom-radio">
                                <input name="type" id="type_0" type="radio" required="required"
                                       class="custom-control-input" value="BOOKED">
                                <label for="type_0" class="custom-control-label"><fmt:message key="user_book.type_book"/></label>
                            </div>
                        </div>
                        <div class="custom-controls-stacked">
                            <div class="custom-control custom-radio">
                                <input name="type" id="type_1" type="radio" required="required"
                                       class="custom-control-input" value="PAID">
                                <label for="type_1" class="custom-control-label"><fmt:message key="user_book.type_pay"/></label>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="name" class="col-4 col-form-label"><fmt:message key="user_book.name"/></label>
                    <div class="col-8">
                        <input id="name" name="name" type="text" class="form-control" required="required">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="surname" class="col-4 col-form-label"><fmt:message key="user_book.surname"/></label>
                    <div class="col-8">
                        <input id="surname" name="surname" type="text" required="required" class="form-control">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="phone" class="col-4 col-form-label"><fmt:message key="user_book.phone"/></label>
                    <div class="col-8">
                        <input id="phone" name="phone" type="text" class="form-control" required="required">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="email" class="col-4 col-form-label"><fmt:message key="user_book.email"/></label>
                    <div class="col-8">
                        <input id="email" name="email" type="email" class="form-control" required="required">
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-4 col-form-label"><fmt:message key="user_book.room"/></label>
                    <div class="col-8">
                        <c:forEach var="roomObj" items="${rooms}">
                            <div class="custom-controls-stacked row-cols-3">
                                <div class="custom-control custom-radio col">
                                    <input name="roomID" id="roomID_${roomObj.id}" type="radio" required="required"
                                           class="custom-control-input" value="${roomObj.id}" hidden="hidden">
                                    <label for="roomID_${roomObj.id}" class="custom-control-label">
                                        <div class="card text-center">
                                            <div class="card-header">
                                                <fmt:message key="user_book.room_room"/> #${ roomObj.id }
                                            </div>
                                            <div class="card-body">
                                                <h5 class="card-title"><fmt:message key="user_book.room_amount"/>: ${ roomObj.num }</h5>
                                                <p class="card-text"><fmt:message key="user_book.room_about"/><br/>${ roomObj.description }</p>
                                            </div>
                                            <div class="card-footer text-muted">
                                                <c:choose>
                                                    <c:when test="${ sessionScope.discount eq 0.0  }">
                                                        <fmt:message key="user_book.room_price"/>: ${ roomObj.price }$
                                                    </c:when>
                                                    <c:otherwise>
                                                        <fmt:message key="user_book.room_price"/>: <del>${ roomObj.price }$</del> <mark>${ roomObj.price - roomObj.price * sessionScope.discount }$</mark>

                                                    </c:otherwise>
                                                </c:choose>

                                            </div>
                                        </div>
                                    </label>

                                </div>
                            </div>
                        </c:forEach>

                    </div>
                </div>
                <div class="form-group row">
                    <label for="startDate" class="col-4 col-form-label"><fmt:message key="user_book.startdate"/></label>
                    <div class="col-8">
                        <input id="startDate" name="startDate" type="date" required="required" class="form-control">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="endDate" class="col-4 col-form-label"><fmt:message key="user_book.enddate"/></label>
                    <div class="col-8">
                        <input id="endDate" name="endDate" type="date" class="form-control" required="required">
                    </div>
                </div>
                <div class="form-group row">
                    <div class="offset-4 col-8">
                        <button name="submit" type="submit" class="btn btn-primary"><fmt:message key="user_book.submit"/></button>
                    </div>
                </div>
            </form>
        </tags:links>
    </c:otherwise>
</c:choose>