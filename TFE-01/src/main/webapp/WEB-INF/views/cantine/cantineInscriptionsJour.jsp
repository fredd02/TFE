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

<fmt:formatDate pattern="ddMMyyyy" value="${date}" var="dateFmt"/>
<s:url value="/cantine/inscriptions/${dateFmt}/facturer" var="urlFacturer" />

<jsp:useBean id="now" class="java.util.Date" />
<fmt:formatDate pattern="dd/MM/YYYY" value="${now}" var="today"/>
<fmt:formatDate pattern="dd/MM/yyyy" value="${date}" var="date"/>
<fmt:formatDate pattern="ddMMyyyy" value="${nextDay}" var="nextDay"/>
<fmt:formatDate pattern="ddMMyyyy" value="${previousDay}" var="previousDay"/>




<div class="container">

<h4><a href="./${previousDay}"><span class="glyphicon glyphicon-circle-arrow-left"></span></a>
Inscriptions à la cantine pour le <c:out value="${date}" />
<a href="./${nextDay}"><span class="glyphicon glyphicon-circle-arrow-right"></span></a></h4>

<table>
<tbody>
	<tr>
		<td>
			Nombre de repas: <span class="badge badge-info">${fn:length(inscriptions)}</span>&emsp;
		
		</td>
		<td>
			<form method="GET" action="../selectEleve/${dateFmt}">
	
				&emsp;<button type="submit" class="btn btn-primary">ajouter un élève</button>


			</form>
		
		</td>
	</tr>
</tbody>
</table>





	<c:if test="${(date le today) and (repasAFacturer)}">
	<p><button type="button" class="btn btn-primary btn-sm" onClick="location.href='${urlFacturer}'">Facturer les repas</button></c:if></p>


<table class="table">
   
    <tbody>
    	<c:forEach items="${inscriptions}" var="inscription">
    	<tr>
    		<td>
    			<c:out value="${inscription.eleve.nom}" /> <c:out value="${inscription.eleve.prenom}" />
    		
    		</td>
    		<td>
    		<c:choose>
    			<c:when test="${inscription.paye}">&emsp;<span class="bg-success">facturé</span></c:when>
 				<c:otherwise>&emsp;<span class="bg-danger">à facturer</span>
 				

 				</c:otherwise>
 				</c:choose>
    		</td>
    		<td>
    			<c:if test="${!inscription.paye}">
    				<a href="../desinscrire/${dateFmt}/${inscription.eleve.id}">désinscrire l'élève</a>
    			
    			</c:if>
    		
    		
    		</td>
    		
 		
 		</tr>
 	</c:forEach>
    </tbody>
</table>



</div>
<jsp:include page="../fragments/footer.jsp" />
</html>