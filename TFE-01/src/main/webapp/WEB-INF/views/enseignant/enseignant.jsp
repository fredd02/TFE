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
<h4 class="text-center">Informations sur l'enseignant <c:out value="${enseignant.nom} ${enseignant.prenom}" /></h4>
<br>

<ul class="well list-group col-md-6 col-md-offset-3">
  <li class="list-group-item"><b>Nom: </b><c:out value="${enseignant.nom}" /></li>
  <li class="list-group-item"><b>Prénom: </b> <c:out value="${enseignant.prenom}" /></li>
  <li class="list-group-item"><b>NRN: </b><c:out value="${enseignant.nrn}" /></li>
  <li class="list-group-item"><b>Date de naissance: </b><fmt:formatDate pattern="dd/MM/yyyy" value="${enseignant.dateNaissance}"/></li>
  <li class="list-group-item"><b>Date d'inscription: </b><fmt:formatDate pattern="dd/MM/yyyy" value="${enseignant.dateInscription}"/></li>
  <li class="list-group-item"><b>Sexe: </b> 
   		<c:choose>
   			<c:when test="${enseignant.sexe == 0}" >
   				masculin
     		</c:when>
     		<c:when test="${enseignant.sexe == 1}" >
   				feminin
     		</c:when>
   		
   		</c:choose>
   	</li >
   	<li class="list-group-item"><b>Téléphone: </b><c:out value="${enseignant.telephone}" /></li>
   	<li class="list-group-item"><b>Mail: </b><c:out value="${enseignant.email}" /></li>
   	<li class="list-group-item"><b>Adresse: </b>
  	<ul>
  		<li><c:out value="${enseignant.rue}" /> <c:out value="${enseignant.numero}" /></li>
  		<li><c:out value="${enseignant.codePostal}" /> <c:out value="${enseignant.ville}" /></li>
  	</ul>
  </li>
</ul>

<div class="btn-group text-center">
	<button class="btn btn-primary" onclick="location.href='./update'">modifier les informations</button>
	<button class="btn btn-danger" onclick="location.href='./delete'">désinscrire l'enseignant</button>
</div>



</div>
<jsp:include page="../fragments/footer.jsp" />
</html>