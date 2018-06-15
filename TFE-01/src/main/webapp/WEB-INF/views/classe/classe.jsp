<!DOCTYPE html>
<%@ page session="false" language="java"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
<jsp:include page="../fragments/header.jsp">
	<jsp:param name="titre" value="alb-school" />
</jsp:include>

<c:set var="now" value="<%=new java.util.Date()%>" />

<div class="container">
<h2>Classe de <c:out value="${classe.nom}" /></h2>
<h4>Titulaire: <c:out value="${classe.titulaire.nom}" /> <c:out value="${classe.titulaire.prenom}" /></h4>
<h4>Elèves inscrits au : <fmt:formatDate type="date"  value="${now}" /> : ${fn:length(eleves)}</h4>
<br>
<ul>
	<c:forEach items = "${eleves}" var = "eleve" >
		<li><a href="../eleve/${eleve.id}"><c:out value="${eleve.nom}" /> <c:out value="${eleve.prenom}" /></a></li>
	
	</c:forEach>

</ul>

<button class="btn btn-primary" onclick="location.href='${classe.code}/schedule/add'">emploi du temps</button>


</div>
<jsp:include page="../fragments/footer.jsp" />
</html>