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

<h4>Création d'un compte</h4>
<p>Recherche des titulaires</p>

<form class="form-inline" action="" method="GET">
	<div class="form-group">
    	<label for="nomResponsable">Nom du titulaire :</label>
    	<input type="text" class="form-control"  name="nomResponsable">
  	</div>
  	
  	
  
  <!--             protection contre les attaques CSRF -->
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>	
  
  <button type="submit" class="btn btn-default">Rechercher</button>

</form>
<br><br>

<c:if test="${not empty responsables}">


	<sf:form class="form-horizontal well" action ="./add" method="POST" modelAttribute="compte">
	<p><b>Selectionner les titulaires du compte:</b></p>
	<sf:errors path="*" element="div" cssClass="alert alert-danger" />
	<ul class="list-group">
		<c:forEach items="${responsables}" var="responsable">
		
		<li class="list-group-item"><input type="checkbox" name="titulaires[]" value="${responsable.username}"
		<c:if test="${not empty responsable.compte}"> disabled="true"</c:if>> <c:out value="${responsable.nom}" /> <c:out value="${responsable.prenom}" />
		<c:if test="${not empty responsable.compte}">   <span> - possède déjà un compte</span></c:if></li>
		
		<input type="hidden" name="responsables[]" value="${responsable.username}"/>
	</c:forEach>
	
	</ul>
	<s:bind path="nom">
		<div class="form-group ${status.error ? 'has-error' : ''}">
	    	<sf:label path="nom" class="control-label">Nom du compte:</sf:label>
	    	<div class="col-lg-3 inputGroupContainer">
					<div class="input-group">
						
						<sf:input path="nom" class="form-control" placeholder="nom" />
						<sf:errors path="nom" class="control-label" />
					</div>
				</div>
	  	</div>
  </s:bind>
  
  <s:bind path="solde">
		<div class="form-group ${status.error ? 'has-error' : ''}">
	    	<sf:label path="solde" class="control-label">Solde du compte:</sf:label>
	    	<div class="col-lg-3 inputGroupContainer">
					<div class="input-group">
						
						<sf:input path="solde" class="form-control" placeholder="solde" />
						<sf:errors path="solde" class="control-label" />
					</div>
				</div>
	  	</div>
  </s:bind>
  
  
  
  
  
		
	<button type="submit" class="btn btn-primary">Créer le compte</button>
	</sf:form>
	



</c:if>


</div>
<jsp:include page="../fragments/footer.jsp" />
</html>