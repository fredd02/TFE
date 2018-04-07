<!DOCTYPE html>
<%@ page session="false" language="java"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html>
<jsp:include page="../fragments/header.jsp">
	<jsp:param name="titre" value="projet TFE" />
</jsp:include>

<div class="container">
<h1>Liste des enseignants</h1>

 <table class="table table-striped table-bordered nowrap">
 	<thead>
 		<tr>
 			<th>Nom</th>
 			<th>Prénom</th>
 			<th>NRN</th>
 			<th>Date de naissance</th>
 			<th>Sexe</th>
 			
 		</tr>
 	</thead>
 	<tbody>
 		<c:forEach items="${enseignantsList}" var="enseignant">
 			<tr>
 				<td><a href="./${enseignant.id}"><c:out value="${enseignant.nom}" /></a></td>
 				<td><c:out value="${enseignant.prenom}" /></td>
 				<td><c:out value="${enseignant.nrn}" /></td>
 				<td><fmt:formatDate value="${enseignant.dateNaissance}" pattern="dd/MM/yyyy" /></td>
 				<td><c:choose>
						<c:when test="${eleve.sexe ==0}">M
						</c:when>
						<c:otherwise>F</c:otherwise>
				
					</c:choose>
				</td>
 				
 			</tr>
 		</c:forEach>
 	</tbody>
 </table>

</div>
<jsp:include page="../fragments/footer.jsp" />
</html>