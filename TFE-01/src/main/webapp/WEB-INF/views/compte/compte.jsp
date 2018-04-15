<!DOCTYPE html>
<%@ page session="false" language="java"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
 <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<html>
<jsp:include page="../fragments/header.jsp">
	<jsp:param name="titre" value="Projet TFE" />
</jsp:include>

<div class="container">
<h4>Infos sur le compte</h4>

 <ul class="list-group">
  <li class="list-group-item"><b>Nom du compte: </b> <c:out value="${compte.nom}" /></li>
  <li class="list-group-item"><b>Titulaires: </b>
  		<ul>
  		<c:forEach items="${titulaires}" var="titulaire">
  			<li><c:out value="${titulaire.nom}" /> <c:out value="${titulaire.prenom}" /></li>
  		</c:forEach>
  		</ul></li>
  <li class="list-group-item"><b>Solde: </b><c:out value="${compte.solde}" /></li>
</ul> 

<h4>Liste des 10 dernières opérations</h4>

<table class="table table-striped">
    <thead>
      <tr>
        <th>date</th>
        <th>désignation</th>
        <th>montant</th>
        <th>élève</th>
        <th>remarque</th>
      </tr>
    </thead>
    <tbody>
    	<c:forEach items="${last10Lignes}" var="ligne">
    		<tr>
    			<td><fmt:formatDate pattern="dd/MM/YYYY" value="${ligne.date}"/></td>
    			<td><c:out value="${ligne.designation}" /></td>
    			<td><c:out value="${ligne.montant}" /></td>
    			<td><c:out value="${ligne.eleve.prenom}" /></td>
    			<td><c:out value="${ligne.remarque}" /></td>
    		
    		</tr>
    	
    	</c:forEach>
    	</tbody>
    	</table>
      

</div>
<jsp:include page="../fragments/footer.jsp" />
</html>