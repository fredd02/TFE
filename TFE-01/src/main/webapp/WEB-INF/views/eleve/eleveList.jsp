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
	<jsp:param name="titre" value="Projet TFE" />
</jsp:include>

<div class="container">
<h4>Liste des élèves&emsp;<span class="badge badge-warning">${fn:length(elevesList)}</span></h4>

 <table class="table table-striped table-bordered nowrap">
 	<thead>
 		<tr>
 			<th>Nom</th>
 			<th>Prénom</th>
 			<th>NRN</th>
 			<th>Date de naissance</th>
 			<th>Sexe</th>
 			<th>Date d'inscription</th>
 			<th>Classe</th>
 		</tr>
 	</thead>
 	<tbody>
 		<c:forEach items="${elevesList}" var="eleve">
 			<tr>
 				<td><a href="./${eleve.id}"><c:out value="${eleve.nom}" /></a></td>
 				<td><c:out value="${eleve.prenom}" /></td>
 				<td><c:out value="${eleve.nrn}" /></td>
 				<td><fmt:formatDate value="${eleve.dateNaissance}" pattern="dd/MM/yyyy" /></td>
 				<td><c:choose>
						<c:when test="${eleve.sexe ==0}">M
						</c:when>
						<c:otherwise>F</c:otherwise>
				
					</c:choose>
				</td>
 				<td><fmt:formatDate value="${eleve.dateInscription}" pattern="dd/MM/yyyy" /></td>
 				<td>
 					<c:forEach items="${inscriptionsActuelles}" var="inscription">
 						<c:choose>
 							<c:when test="${inscription.eleve.nrn == eleve.nrn}">
 								<c:out value="${inscription.classe.code}"  />
 								
 							</c:when>
 						</c:choose>
 						
 					</c:forEach>
 				
 				
 				</td>
 			</tr>
 		</c:forEach>
 	</tbody>
 </table>

</div>
<jsp:include page="../fragments/footer.jsp" />
</html>