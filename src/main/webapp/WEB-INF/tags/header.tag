<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@tag pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="messages"/>
<header>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
       <div class="container-fluid">
           <a href="${pageContext.request.contextPath}/welcome?command=MAIN_PAGE" class="navbar-brand">
               HOSTEL
           </a>
           <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
               <span class="navbar-toggler-icon"></span>
           </button>
           <div class="collapse navbar-collapse d-flex justify-content-between" id="navbarNavAltMarkup">
               <div class="navbar-nav">
                   <a class="nav-link" href="#"><fmt:message key="header.aboutus"/></a>
                   <c:choose>
                       <c:when test="${ sessionScope.role eq 'client' }">
                           <a class="nav-link" href="${pageContext.request.contextPath}/welcome?command=USER_ORDER_CREATE"><fmt:message key="header.book"/></a>
                           <a href="${pageContext.request.contextPath}/welcome?command=USER_ORDER_LIST" class="nav-link"><fmt:message key="header.orders"/></a>
                       </c:when>
                   </c:choose>
                   <c:choose>
                       <c:when test="${ sessionScope.role eq null }">
                           <a class="nav-link" href="${pageContext.request.contextPath}/welcome?command=REG_COMMAND"><fmt:message key="header.registration"/></a>
                       </c:when>
                   </c:choose>
                   <c:choose>
                       <c:when test="${ sessionScope.role eq 'admin' }">
                           <a class="nav-link" href="${pageContext.request.contextPath}/welcome?command=ADMIN_PAGE"><fmt:message key="header.admin_panel"/></a>
                           <a href="${pageContext.request.contextPath}/welcome?command=ADMIN_ADD_ROOM" class="nav-link"><fmt:message key="header.admin_rooms" /> </a>
                           <a href="${pageContext.request.contextPath}/welcome?command=ADMIN_ORDERS" class="nav-link"><fmt:message key="header.admin_orders"/> </a>
                       </c:when>
                   </c:choose>

               </div>

               <div class="d-flex align-items-center justify-content-around g-3">
                   <c:choose>
                       <c:when test="${ sessionScope.role eq null}">
                           <form class="d-flex" action="welcome" method="POST">
                               <input type="hidden" name="command" value="AUTH_PAGE" />
                               <input class="form-control me-2" name="login" id="login" type="text" placeholder="Login" aria-label="Login">
                               <input class="form-control me-2" name="password" id="password" type="password" placeholder="Password" aria-label="Password">
                               <button class="btn btn-outline-success" type="submit"><fmt:message key="singin" /></button>
                           </form>
                       </c:when>
                       <c:otherwise>
                           <fmt:message key="header.welcome" /> ${ sessionScope.name }!
                           <c:choose>
                               <c:when test="${ sessionScope.role eq 'admin' || sessionScope.role eq 'client' }">
                                   <a class="nav-link" href="${pageContext.request.contextPath}/welcome?command=SING_OUT_COMMAND"> <fmt:message key="header.singout" /></a>
                               </c:when>
                           </c:choose>
                       </c:otherwise>
                   </c:choose>
                   <div class="dropdown ms-2">
                        <c:choose>
                            <c:when test="${ sessionScope.lang eq 'ru' || sessionScope.lang eq 'null' }">
                                <a class="dropdown-toggle" href="#" id="Dropdown" role="button" data-mdb-toggle="dropdown" aria-expanded="false">
                                    <i class="flag-russia flag m-0"></i>
                                </a>
                            </c:when>
                            <c:otherwise>
                                <a class="dropdown-toggle" href="#" id="Dropdown" role="button" data-mdb-toggle="dropdown" aria-expanded="false">
                                    <i class="flag-united-kingdom flag m-0"></i>
                                </a>
                            </c:otherwise>
                        </c:choose>
                       <ul class="dropdown-menu" aria-labelledby="Dropdown">
                           <c:choose>
                               <c:when test="${ sessionScope.lang eq 'ru' || sessionScope.lang eq 'null' }">
                                   <li>
                                       <a class="dropdown-item" href="${pageContext.request.contextPath}/welcome?sessionLocale=ru"><i class="flag-russia flag"></i><fmt:message key="lang.ru" /> <i class="fa fa-check text-success ms-2"></i></a>
                                   </li>
                                   <li><hr class="dropdown-divider" /></li>
                                   <li>
                                       <a class="dropdown-item" href="${pageContext.request.contextPath}/welcome?sessionLocale=en"><i class="flag-united-kingdom flag"></i><fmt:message key="lang.en" /></a>
                                   </li>
                               </c:when>
                               <c:otherwise>
                                   <li>
                                       <a class="dropdown-item" href="${pageContext.request.contextPath}/welcome?sessionLocale=en"><i class="flag-united-kingdom flag"></i><fmt:message key="lang.en" /> <i class="fa fa-check text-success ms-2"></i></a>
                                   </li>
                                   <li><hr class="dropdown-divider" /></li>
                                   <li>
                                       <a class="dropdown-item" href="${pageContext.request.contextPath}/welcome?sessionLocale=ru"><i class="flag-russia flag"></i><fmt:message key="lang.ru" /></a>
                                   </li>
                               </c:otherwise>
                           </c:choose>

                       </ul>
                   </div>
               </div>



           </div>
       </div>
    </nav>
</header>
