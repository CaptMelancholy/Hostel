<%--
  Created by IntelliJ IDEA.
  User: Your Phycologyst
  Date: 13.11.2023
  Time: 16:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="users" scope="request" type="java.util.List"/>

<c:choose>
    <c:when test="${ sessionScope.role eq null || sessionScope.role eq 'client'}">
        <% response.sendRedirect("welcome?command=FORBIDDEN_COMMAND"); %>
    </c:when>
    <c:otherwise>
        <tags:header />
        <tags:links pageTitle="ADMIN">
            <h1>ADMIN PANEL</h1>
            <hr />
            <h1>USERS OF WEBSITE</h1>
            <table class="table table-striped">
                <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">LOGIN</th>
                    <th scope="col">EMAIL</th>
                    <th scope="col">NAME</th>
                    <th scope="col">SURNAME</th>
                    <th scope="col">ADMIN STATUS</th>
                    <th scope="col">DISCOUNT STATUS</th>
                    <th scope="col">BAN STATUS</th>
                    <th scope="col">ACTIONS</th>
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
                                    "YES"
                                </c:when>
                                <c:otherwise>
                                    "NO"
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>${user.discount * 100}%</td>
                        <td>
                            <c:choose>
                                <c:when test="${ user.banStatus == true}">
                                    "YES"
                                </c:when>
                                <c:otherwise>
                                    "NO"
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
                                            UNBAN
                                        </c:when>
                                        <c:otherwise>
                                            BAN
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
                                            UNSET
                                        </c:when>
                                        <c:otherwise>
                                            SET
                                        </c:otherwise>
                                    </c:choose>
                                    ADMIN</button>
                            </form>
                            <form action="welcome" method="POST" class="d-flex">
                                <input type="hidden" name="command" value="ADMIN_PAGE" />
                                <input type="hidden" name="subCommand" value="SET_DISCOUNT_COMMAND" />
                                <input type="hidden" name="login" value="${user.login}" />
                                <button type="submit" class="btn btn-primary">SET DISCOUNT</button>
                                <div>
                                    <input type="range" name="discount" class="form-range" min="0" max="1" step="0.05" id="discount_input_${user.login}">
                                    <p>Discount: <output id="value_${user.login}"></output> %</p>

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

