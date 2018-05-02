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

<fmt:formatDate pattern="dd/MM/yyyy" value="${date}" var="dateFmt"/>
<fmt:formatDate pattern="ddMMyyyy" value="${date}" var="dateFmt2"/>

<div class="container">

<h4>Inscription d'un élève à la cantine pour le <c:out value="${dateFmt}" /></h4>

<form class="form-inline" action="" method="POST">
	<div class="form-group">
    <label for="nom">Nom de l'élève:</label>
    <input type="text" class="form-control" id="nom" name="nom">
  </div>
  
  <!--             protection contre les attaques CSRF -->
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>	
            
<%--             <input type="hidden" name="date" value="${date}" > --%>
  
  <button type="submit" class="btn btn-default">Rechercher</button>

</form>
<br><br>

<h4>Elèves correspondant à la requète</h4>
<c:choose>
	<c:when test="${empty eleves}">
		<p>aucun élève ne correspond à la recherche</p>
	
	</c:when>
	<c:otherwise>
		<form class="form-inline" method="POST" action="../inscriptionEleve/${dateFmt2}">
			<ul class="list-group">
			
			<c:forEach items="${eleves}" var="eleve">
				<c:forEach items="${inscriptions}" var="inscription">
 							<c:if test="${inscription.eleve.id eq eleve.id}">
 								<c:set var="inscrit" value="true"/>
 							
 							</c:if>
 				</c:forEach>
 				<c:forEach items="${elevesWithCompte}" var="eleveWithCompte">
 							<c:if test="${eleveWithCompte.id eq eleve.id}">
 								<c:set var="compte" value="true"/>
 							
 							</c:if>
 				</c:forEach>			
			
			
				<li class="list-group-item">
 				<div class="checkbox">
 				
 					<c:choose>
 						<c:when test="${inscrit eq 'true' }">
 							<label><input type="checkbox" name="elevesId[]" value="${eleve.id}" <c:out value="disabled='true'" />> <c:out value="${eleve.nom} ${eleve.prenom}" />
 							&emsp;<span class="bg-danger">élève déjà inscrit pour ce jour</span>
 					
 					</label>
 						
 						
 						
 						</c:when>
 						<c:otherwise>
 							<c:choose>
 								<c:when test="${compte eq 'true'}">
 									<label><input type="checkbox" name="elevesId[]" value="${eleve.id}"> <c:out value="${eleve.nom} ${eleve.prenom}" />
 							
 									</label>
 								
 								</c:when>
 								<c:otherwise>
 									<label><input type="checkbox" name="elevesId[]" value="${eleve.id}" <c:out value="disabled='true'" />> <c:out value="${eleve.nom} ${eleve.prenom}" />
 									&emsp;<span class="bg-danger">cet élève ne possède pas de compte</span>
 									</label>
 								
 								
 								</c:otherwise>
 							
 							
 							
 							</c:choose>
 						
 							
 						
 						</c:otherwise>
 					
 					</c:choose>
 					
- 					
 				</div>
 				
 				</li>
 	
 			</c:forEach>
 			</ul>
 			
 			<!--             protection contre les attaques CSRF -->
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>	
            
            <input type="hidden" name="date" value="${date}">
            
			<button type="submit" class="btn btn-primary">Inscrire les élèves sélectionnés</button>
		
		</form>
		
	
	
	</c:otherwise>


</c:choose>

</div>
<jsp:include page="../fragments/footer.jsp" />
</html>