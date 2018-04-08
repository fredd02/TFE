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
<h4>Recherche du responsable pour l'élève <c:out value="${eleve.nom}" /> <c:out value="${eleve.prenom}" /></h4>

<form class="form-inline" action="./${eleve.id}" method="POST">
	<div class="form-group">
    <label for="nom">Nom:</label>
    <input type="text" class="form-control" id="nom" name="nom">
  </div>
  
  <button type="submit" class="btn btn-default">Rechercher</button>

</form>
<br><br>

<h4>Liste des responsables trouvés</h4>
<c:choose>
	<c:when test="${empty responsables}">
		<p>aucun responsable n'a été trouvé</p>
	
	</c:when>
	<c:otherwise>
		<form class="form-inline" method="POST" action="../addSelect/${eleve.id}">
			<ul class="list-group">
			
			<c:forEach items="${responsables}" var="responsable">
				<li class="list-group-item">
 				<div class="checkbox">
 					<label><input type="checkbox" name="responsable[]" value="${responsable.id}"> <c:out value="${responsable.nom} ${responsable.prenom}" /></label>
 					<label for="lien"> - Lien avec l'élève:</label>
  					<input type="text" class="form-control" id="lien" name="lien[]">
 				</div>
 				
 				</li>
 	
 			</c:forEach>
 			</ul>
			<button type="submit" class="btn btn-primary">Ajouter les responsables selectionnés</button>
		
		</form>
		
	
	
	</c:otherwise>


</c:choose>

 


</div>
<jsp:include page="../fragments/footer.jsp" />
</html>