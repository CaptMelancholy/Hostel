<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="room" scope="request" type="com.example.hostel.beans.rooms.Rooms"/>
<c:choose>
    <c:when test="${ sessionScope.role eq null || sessionScope.role eq 'client'}">
        <% response.sendRedirect("welcome?command=FORBIDDEN_COMMAND"); %>
    </c:when>
    <c:otherwise>
        <tags:header/>
        <tags:links pageTitle="ADMIN-ROOMS-EDIT">
            <h1>CREATE A ROOM</h1>
            <div class="mt-3 p-5">
                <form action="welcome" method="POST" class="p-2 border rounded border-dark">
                    <input type="hidden" name="command" value="ADMIN_EDIT_ROOM"/>
                    <input type="hidden" name="subCommand" value="true"/>
                    <input type="hidden" name="id" value="${room.id}">
                    <div class="form-group row">
                        <label for="num" class="col-5 col-form-label">Amount of Guests</label>
                        <div class="col-7">
                            <input id="num" name="num" type="number" required="required" class="form-control" value="${room.num}">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="price" class="col-5 col-form-label">Price</label>
                        <div class="col-7">
                            <input id="price" name="price" placeholder="Enter price in $" type="number"
                                   class="form-control" required="required" value="${room.price}">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="discription" class="col-5 col-form-label">Discription</label>
                        <div class="col-7">
                            <textarea id="discription" name="discription" cols="40" rows="5" class="form-control"
                                      required="required">${room.description}</textarea>
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="offset-5 col-7">
                            <button name="submit" type="submit" class="btn btn-primary">Submit</button>
                        </div>
                    </div>
                </form>
            </div>
        </tags:links>
    </c:otherwise>
</c:choose>
