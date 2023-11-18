<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="rooms" scope="request" type="java.util.List"/>
<c:choose>
    <c:when test="${ sessionScope.role eq null || sessionScope.role eq 'client'}">
        <% response.sendRedirect("welcome?command=FORBIDDEN_COMMAND"); %>
    </c:when>
    <c:otherwise>
        <tags:header/>
        <tags:links pageTitle="ADMIN-ROOMS">

            <h1>CREATE A ROOM</h1>
            <div class="mt-3 p-5">
                <form action="welcome" method="POST" class="p-2 border rounded border-dark">
                    <input type="hidden" name="command" value="ADMIN_ADD_ROOM"/>
                    <input type="hidden" name="subCommand" value="ADD_ROOM"/>
                    <div class="form-group row">
                        <label for="num" class="col-5 col-form-label">Amount of Guests</label>
                        <div class="col-7">
                            <input id="num" name="num" type="number" required="required" class="form-control">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="price" class="col-5 col-form-label">Price</label>
                        <div class="col-7">
                            <input id="price" name="price" placeholder="Enter price in $" type="number"
                                   class="form-control" required="required">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="discription" class="col-5 col-form-label">Discription</label>
                        <div class="col-7">
                            <textarea id="discription" name="discription" cols="40" rows="5" class="form-control"
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
            <h1>USERS OF WEBSITE</h1>
            <table class="table table-striped">
                <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">AMOUNT OF GUESTS</th>
                    <th scope="col">PRICE</th>
                    <th scope="col">DESCRIPTION</th>
                    <th scope="col">ACTION</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="room" items="${rooms}">
                    <tr>
                        <th scope="row">${room.id}</th>
                        <td>${room.num}</td>
                        <td>${room.price}</td>
                        <td>${room.discription}</td>

                        <td class="d-flex justify-around">
                            <form action="welcome" method="POST">
                                <input type="hidden" name="command" value="ADMIN_EDIT_ROOM" />
                                <input type="hidden" name="id" value="${room.id}" />
                                <button type="submit" class="btn btn-primary">
                                    EDIT
                                </button>
                            </form>
                            <form action="welcome" method="POST">
                                <input type="hidden" name="command" value="ADMIN_ADD_ROOM" />
                                <input type="hidden" name="subCommand" value="DELETE_ROOM" />
                                <input type="hidden" name="id" value="${room.id}" />
                                <button type="submit" class="btn btn-danger">
                                    DELETE
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