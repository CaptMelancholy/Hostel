<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
                   <a class="nav-link" href="#">About Us</a>
                   <a class="nav-link" href="#">Book</a>
                   <c:choose>
                       <c:when test="${ sessionScope.role eq null }">
                           <a class="nav-link" href="${pageContext.request.contextPath}/welcome?command=REG_COMMAND">Registration</a>
                       </c:when>
                   </c:choose>
                   <c:choose>
                       <c:when test="${ sessionScope.role eq 'admin' }">
                           <a class="nav-link" href="${pageContext.request.contextPath}/welcome?command=ADMIN_PAGE">Admin Panel</a>
                       </c:when>
                   </c:choose>

               </div>
               <div class="d-flex align-items-center">
                   <c:choose>
                       <c:when test="${ sessionScope.role eq null}">
                           <form class="d-flex" action="welcome" method="POST">
                               <input type="hidden" name="command" value="AUTH_PAGE" />
                               <input class="form-control me-2" name="login" id="login" type="text" placeholder="Login" aria-label="Login">
                               <input class="form-control me-2" name="password" id="password" type="password" placeholder="Password" aria-label="Password">
                               <button class="btn btn-outline-success" type="submit">Sing In</button>
                           </form>
                       </c:when>
                       <c:otherwise>
                           WELCOME ${ sessionScope.name }!
                           <c:choose>
                               <c:when test="${ sessionScope.role eq 'admin' || sessionScope.role eq 'client' }">
                                   <a class="nav-link" href="${pageContext.request.contextPath}/welcome?command=SINGOUT_COMMAND">SING OUT</a>
                               </c:when>
                           </c:choose>
                       </c:otherwise>
                   </c:choose>
               </div>



           </div>
       </div>
    </nav>
</header>
