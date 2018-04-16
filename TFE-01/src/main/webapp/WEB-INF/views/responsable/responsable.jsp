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
<h4>Infos sur le responsable </h4>

<ul class="list-group">
  <li class="list-group-item"><b>Nom: </b><c:out value="${responsable.nom}" /></li>
  <li class="list-group-item"><b>Prenom: </b> <c:out value="${responsable.prenom}" /></li>
  <li class="list-group-item"><b>Profession: </b> <c:out value="${responsable.profession}" /></li>
  <li class="list-group-item"><b>Email: </b> <c:out value="${responsable.email}" /></li>
  <li class="list-group-item"><b>Téléphone: </b> <c:out value="${responsable.telephone}" /></li>
  <li class="list-group-item"><b>Adresse: </b>
  	<ul>
  		<li><c:out value="${responsable.rue}" /> <c:out value="${responsable.numero}" /></li>
  		<li><c:out value="${responsable.codePostal}" /> <c:out value="${responsable.ville}" /></li>
  	</ul>
  </li>
  <li class="list-group-item"><b>Elèves inscrits: </b>
  	<ul>
  		<c:forEach items="${relations}" var="relation" >
  			<li><c:out value="${relation.eleve.nom}" /> <c:out value="${relation.eleve.prenom}" /> (<c:out value="${relation.lienParent}" />)
  		</c:forEach>
  	</ul>
  </li>
</ul> 

</div>
<jsp:include page="../fragments/footer.jsp" />
</html>