<!DOCTYPE html>
<%@ page session="false" language="java"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<%-- <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> --%>
<html>
<jsp:include page="../fragments/header.jsp">
	<jsp:param name="titre" value="Cours Résultats ISFCE" />
</jsp:include>

<div class="container">
<h4>Liste des communications</h4>

 <ul class="list-group">
 <c:forEach items="${communications}" var="communication">
 	<li class="list-group-item"><b>Communication</b>
 	<ul>
 		<li>date: <fmt:formatDate pattern="dd/MM/YYYY" value="${communication.date}"/></li>
 		<li>sujet: <c:out value="${communication.sujet}" /></li>
<%--  		<li>message: <c:out value="${communication.corps}" /></li> --%>
 		<li>fichier joint: <a href="${communication.lienFichier}">ouvrir</a></li>
 	
 	</ul>
 	</li>
 
 
 
 
 
 </c:forEach>
  
</ul> 


</div>
<jsp:include page="../fragments/footer.jsp" />
</html>