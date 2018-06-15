<!DOCTYPE html>
<%@ page session="false" language="java"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%-- <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> --%>
<html>
<jsp:include page="../fragments/header.jsp">
	<jsp:param name="titre" value="alb-school" />
</jsp:include>

<s:url value="/responsable/add/${eleve.id}" var ="addResponsableURL" />
<s:url value="/responsable/search/${eleve.id}" var ="searchResponsableURL" />
<s:url value="/eleve/${eleve.id}/update" var ="updateEleveURL" />
<s:url value="/eleve/${eleve.id}/desinscrire" var ="desinscrireEleveURL" />


<div class="container">
<h4>Informations sur l'élève <c:out value="${eleve.nom} ${eleve.prenom}" /></h4>

<sec:authorize access="hasAnyAuthority('ADMIN','DIRECTEUR')">
<div class="btn-group">
	
	<button class="btn btn-primary" onclick="location.href='${updateEleveURL}'">modifier les informations</button>
	<button class="btn btn-danger" onclick="
								if (confirm('<s:message code="eleve.confirmation.desinscrire"/>')) {
								 this.disabled=true; post('${desinscrireEleveURL}',{'${_csrf.parameterName}': '${_csrf.token}'})}">
								 	désinscrire l'élève
							</button>
</div>
</sec:authorize>
<br><br>

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
   			<c:forEach items="${inscriptions}" var="inscription">
   				<li><fmt:formatDate pattern="dd/MM/yyyy" value="${inscription.dateEntree}"/> - 
   				<fmt:formatDate pattern="dd/MM/yyyy" value="${inscription.dateSortie}"/> : 
   					<c:out value="${inscription.classe.code}" /></li>
   			
   			</c:forEach>
   			<li><a href="${eleve.id}/sup">changer de classe</a></li>
   		</ul>
   	</li>
   	
   	<li class="list-group-item">Responsables
   		<ul>
   			<c:forEach items = "${relations}" var = "relation">
   				<li><a href="../responsable/${relation.responsable.username}"><c:out value="${relation.responsable.nom}" /> <c:out value="${relation.responsable.prenom}" /></a>
   					(${relation.lienParent})</li>
   			</c:forEach>
   		</ul>
   	</li>
</ul> 

<sec:authorize access="hasAnyAuthority('ADMIN','DIRECTEUR')">
<div class="btn-group">
	<button class="btn btn-primary" onclick="location.href='${addResponsableURL}'">ajouter un nouveau responsable</button>
	<button class="btn btn-primary" onclick="location.href='${searchResponsableURL}'">ajouter un responsable existant</button>
	
</div>
</sec:authorize>




</div>
<jsp:include page="../fragments/footer.jsp" />
</html>