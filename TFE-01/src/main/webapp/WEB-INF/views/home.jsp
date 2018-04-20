<!DOCTYPE html>
<%@ page session="false" language="java"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>

<jsp:include page="fragments/header.jsp">
	<jsp:param name="titre" value="Home Page TFE" />
</jsp:include>

<div class="container">
<h1>Home</h1>


<sec:authorize access="isAuthenticated()"> 
		<h4>
			username: <sec:authentication property="principal.username" />
		</h4>

		
		<sec:authentication property="authorities" var="roles" scope="page" />
		roles:
		<ul>
			<c:forEach var="role" items="${roles}">
				<li>${role}</li>
			</c:forEach>
		</ul>
</sec:authorize>


</div>

<jsp:include page="fragments/footer.jsp" />
</html>