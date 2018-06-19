<!DOCTYPE html>
<%@ page session="false" language="java"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
 <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<html>
<jsp:include page="../fragments/header.jsp">
	<jsp:param name="titre" value="alb-school" />
</jsp:include>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<div class="container">
<h4>Liste des comptes</h4>

<c:if test="${empty comptes}">
	<p>Il n'a pas de compte crée</p>
</c:if>

 <ul class="list-group">
 	<c:forEach items="${comptes}" var="compte" >
 		<li class="list-group-item"><b>Compte: </b> <a href="${contextPath}/compte/${compte.id}"><c:out value="${compte.nom}" /></a> <b>Solde: </b><fmt:formatNumber value = "${compte.solde}" 
         type = "number" maxFractionDigits="2" minFractionDigits="2"/></li>
 	
 	</c:forEach>
  
</ul> 

</div>
<jsp:include page="../fragments/footer.jsp" />
</html>