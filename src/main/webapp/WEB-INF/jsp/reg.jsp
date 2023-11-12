<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<tags:header pageTitle="REG">


<form name="reg" action="welcome" method="POST">
    <input type="hidden" name="command" value="REG_COMMAND" />
    <input type="hidden" value="" name="key"/><div class="relContainer">
    <label for="login">Login</label><input name="login" id="login" class="saveFiled"></div>
    <div class="relContainer">
        <label for="password">Password</label><input name="password" id="password" class="saveFiled" value="" type="password">
    </div>
    <div class="relContainer"><label for="name">Name</label><input name="name" id="name" class="saveFiled"></div>
    <div class="relContainer"><label for="surname">Surname</label><input name="surname" id="surname" class="saveFiled"></div>
    <button type="submit" class="btn btn-send">Send</button>
</form>
</tags:header>