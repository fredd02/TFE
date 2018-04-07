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
<h1>Informations sur l'élève <c:out value="${eleve.nom} ${eleve.prenom}" /></h1>

 <ul class="list-group">
  <li class="list-group-item">Nom: <c:out value="${eleve.nom}" /></li>
  <li class="list-group-item">Prenom <c:out value="${eleve.prenom}" /></li>
  <li class="list-group-item">NRN: <c:out value="${eleve.nrn}" /></li>
  <li class="list-group-item">Date de naissance: <fmt:formatDate pattern="dd/MM/yyyy" value="${eleve.dateNaissance}"/></li>
  <li class="list-group-item">Sexe: 
   		<c:choose>
   			<c:when test="${eleve.sexe == 0}" >
   				masculin
     		</c:when>
     		<c:when test="${eleve.sexe == 1}" >
   				feminin
     		</c:when>
   		
   		
   		</c:choose>
   	</li >
   	<li class="list-group-item">Inscriptions
   		<ul>
   			<c:forEach items="${eleve.inscriptions}" var="inscription">
   				<li><fmt:formatDate pattern="dd/MM/yyyy" value="${inscription.dateEntree}"/> - 
   				<fmt:formatDate pattern="dd/MM/yyyy" value="${inscription.dateSortie}"/> : 
   					<c:out value="${inscription.classe.code}" /></li>
   			
   			</c:forEach>
   			<li><a href="${eleve.nrn}/sup">changer de classe</a></li>
   		</ul>
   	</li>
   	
   	<li class="list-group-item">Responsables
   		<ul>
   			<c:forEach items = "${eleve.relations}" var = "relation">
   				<li><a href="../responsable/${relation.responsable.nrn}"><c:out value="${relation.responsable.nom}" /> <c:out value="${relation.responsable.prenom}" /></a></li>
   			</c:forEach>
   		</ul>
   	</li>
</ul> 

<s:url value="/responsable/add/${eleve.nrn}" var ="addResponsableURL" />
<div class="btn-group">
	<button class="btn btn-primary" onclick="location.href='${addResponsableURL}'">ajouter un nouveau responsable</button>
	<button class="btn btn-primary" onclick="location.href='${addResponsableURL}'">ajouter un responsable existant</button>
</div>


</div>
<jsp:include page="../fragments/footer.jsp" />
</html>