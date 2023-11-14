<%--
  Created by IntelliJ IDEA.
  User: Your Phycologyst
  Date: 13.11.2023
  Time: 16:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<tags:links pageTitle="ERROR">
    <div style="width:100%; height: 100%;" class="bg-dark text-white">
        <div class="container py-5 m-auto" >
            <div class="row">
                <div class="col-md-2 text-center">
                    <p><i class="fa fa-exclamation-triangle fa-5x"></i><br/>Status Code: 403</p>
                </div>
                <div class="col-md-10">
                    <h3>OPPSSS!!!! Sorry...</h3>
                    <p>Sorry, your access is refused due to security reasons of our server and also our sensitive data.<br/>Please go back to the previous page to continue browsing.</p>
                    <a class="btn btn-danger" href="javascript:history.back()">Go Back</a>
                </div>
            </div>
        </div>
    </div>
</tags:links>
