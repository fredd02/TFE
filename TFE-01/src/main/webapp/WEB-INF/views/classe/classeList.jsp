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
<h4>Liste des classes</h4><br>

 <table class="table table-striped table-bordered nowrap table-condensed texter-center">
 	<thead>
 		<tr>
 			<th class="text-center">Code</th>
 			<th class="text-center">Nom</th>
 			<th class="text-center">Titulaire</th>
 			
 		</tr>
 	</thead>
 	<tbody>
 		<c:forEach items="${classesList}" var="classe">
 			<tr>
 				<td align="center"><a href="${classe.code}"><c:out value="${classe.code}" /></a></td>
 				<td align="center"><c:out value="${classe.nom}" /></td>
 				<td align="center">
 				<c:choose>
 					<c:when test="${empty classe.titulaire}">
 						<a href="./${classe.code}/titulaire/add">choisir un titulaire</a>
 					</c:when>
 					<c:otherwise>
 					<c:out value="${classe.titulaire.nom} ${classe.titulaire.prenom }" />
 						<a href="./${classe.code}/titulaire/add" class="btn btn-primary btn-sm">modifier le titulaire</a>
 					
 					</c:otherwise>
 				
 				</c:choose>
 				
 				
 				
 			</tr>
 		</c:forEach>
 	</tbody>
 </table>

</div>
<jsp:include page="../fragments/footer.jsp" />
</html>