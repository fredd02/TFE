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
	<jsp:param name="titre" value="Projet TFE" />
</jsp:include>

<div class="container">
<h1>Liste des classes</h1>

 <table class="table table-striped table-bordered nowrap">
 	<thead>
 		<tr>
 			<th>Code</th>
 			<th>Nom</th>
 			<th>Titulaire</th>
 			
 		</tr>
 	</thead>
 	<tbody>
 		<c:forEach items="${classesList}" var="classe">
 			<tr>
 				<td><a href="${classe.code}"><c:out value="${classe.code}" /></a></td>
 				<td><c:out value="${classe.nom}" /></td>
 				<td>
 				<c:choose>
 					<c:when test="${empty classe.titulaire}">
 						<a href="./${classe.code}/titulaire/add">choisir un titulaire</a>
 					</c:when>
 					<c:otherwise>
 						<c:out value="${classe.titulaire.nom} ${classe.titulaire.prenom }" />
 					</c:otherwise>
 				
 				</c:choose>
 				
 				
 				
 			</tr>
 		</c:forEach>
 	</tbody>
 </table>

</div>
<jsp:include page="../fragments/footer.jsp" />
</html>