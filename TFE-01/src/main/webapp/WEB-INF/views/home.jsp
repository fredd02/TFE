<!DOCTYPE html>
<%@ page session="false" language="java"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>




<jsp:include page="fragments/header.jsp">
	<jsp:param name="titre" value="Home Page TFE" />
</jsp:include>

<div class="container">
<sec:authorize access="isAnonymous()">
	<div class="jumbotron">
			<font color="white"><h1>Bienvenue!</h1>
				Bienvenue sur le site de l'école primaire d'Avernas-Le-Bauduin.<br> 
				Vous trouverez ici toutes les informations pratiques concernant notre école.
			</font>
		</div>
</sec:authorize>

<sec:authorize access="isAuthenticated()">
	<sec:authentication property="authorities" var="roles" scope="page" />
	
	<c:choose>
		<c:when test="${fn:contains(roles, ADMIN)}">
				<div class="jumbotron">
					<font color="white"><h1>Bienvenue <sec:authentication property="principal.username" /></h1>
						Bienvenue sur le site de l'école primaire d'Avernas-Le-Bauduin.<br> 
						Vous pouvez gérer ici l'administration de l'école.
					</font>
				</div>
		
	</c:when>
	<c:when test="${fn:contains(roles, PARENT)}">
				<div class="jumbotron">
					<font color="white"><h1>Bienvenue <sec:authentication property="principal.username" /></h1>
						Bienvenue sur le site de l'école primaire d'Avernas-Le-Bauduin.<br> 
						Vous pouvez gérer ici la scolarité de vos enfants.
					</font>
				</div>
		
	</c:when>
	
</c:choose>

</sec:authorize>




<sec:authorize access="isAuthenticated()"> 
		<h4>
			username: <sec:authentication property="principal.username" />
		</h4>

		
		
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