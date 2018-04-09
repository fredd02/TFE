<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>


<html>

<jsp:include page="fragments/header.jsp">
	<jsp:param name="titre" value="Home Page TFE" />
</jsp:include>



<div class="container">


    <form method="POST" action="${contextPath}/login" class="form-signin">
        <h2 class="form-heading text-center">IDENTIFICATION</h2>
        <br>

        <div class="form-group ${error != null ? 'has-error' : ''}">
            <span>${message}</span>
            <input name="username" type="text" class="form-control" placeholder="login"
                   autofocus/>
            <input name="password" type="password" class="form-control" placeholder="mot de passe"/>
            <span>${error}</span>
<!--             protection contre les attaques CSRF -->
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>	

            <button class="btn btn-lg btn-primary btn-block" type="submit">S'identifier</button>
<%--             <h4 class="text-center"><a href="${contextPath}/registration"><s:message code="account.create" /></a></h4> --%>
            
        </div>

    </form>

</div>
<!-- /container -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
<jsp:include page="fragments/footer.jsp" />
</html>