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
	<jsp:param name="titre" value="alb-school" />
</jsp:include>

<div class="container">

<h4>Inscription à la cantine</h4>

<sec:authorize access="isAuthenticated()">
<jsp:useBean id="now" class="java.util.Date" />
<fmt:formatDate pattern="dd/MM/YYYY" value="${now}" var="today"/>
<fmt:formatDate pattern="dd/MM/YYYY" value="${lundi}" var="lundi" />
<fmt:formatDate pattern="dd/MM/YYYY" value="${mardi}" var="mardi" />
<fmt:formatDate pattern="dd/MM/YYYY" value="${jeudi}" var="jeudi" />
<fmt:formatDate pattern="dd/MM/YYYY" value="${vendredi}" var="vendredi"/>

	
	
	<p>semaine du <c:out value="${lundi}" /> au <c:out value="${vendredi}" /> : <b><c:out value="${message}" /></b></p>
	<form method="POST" action="">
	
	
	<table class="table table-striped">
		<thead>
			<tr>
				<th>élève</th>
				<th>lundi</th>
				<th>mardi</th>
				<th>jeudi</th>
				<th>vendredi</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${eleves}" var="eleve">
				<tr>
					<td><c:out value="${eleve.prenom}" /></td>
					
					<td><input type="checkbox" name="lundi[]" 
						<c:forEach items="${inscriptions}" var="inscription" > 
							<fmt:formatDate pattern='dd/MM/YYYY'  value='${inscription.date}' var="inscriptionDate"/>
    						<c:if test="${(inscription.eleve.id == eleve.id) && ( inscriptionDate == lundi)}"><c:out value="checked='checked'" /></c:if>
    					</c:forEach>
    					 <c:if test="${lundiPast}"><c:out value="disabled='true'" /></c:if> 
					 	value="${eleve.id}_${lundi}">
    					
    				</td>

					<td><input type="checkbox" name="mardi[]" 
						<c:forEach items="${inscriptions}" var="inscription" > 
							<fmt:formatDate pattern='dd/MM/YYYY'  value='${inscription.date}' var="inscriptionDate"/>
    						<c:if test="${(inscription.eleve.id == eleve.id) && ( inscriptionDate == mardi)}"><c:out value="checked='checked'" /></c:if>
    					</c:forEach>
    					<c:if test="${mardiPast}">
    					<c:out value="disabled='true'" /></c:if>
					 	value="${eleve.id}_${mardi}">
    					
    				</td>
					<td><input type="checkbox" name="jeudi[]" 
						<c:forEach items="${inscriptions}" var="inscription" > 
							<fmt:formatDate pattern='dd/MM/YYYY'  value='${inscription.date}' var="inscriptionDate"/>
    						<c:if test="${(inscription.eleve.id == eleve.id) && ( inscriptionDate == jeudi)}"><c:out value="checked='checked'" /></c:if>
    					</c:forEach>
    					<c:if test="${jeudiPast}">
    					<c:out value="disabled='true'" /></c:if>
					 	value="${eleve.id}_${jeudi}">
    					
    				</td>
					<td><input type="checkbox" name="vendredi[]" 
						<c:forEach items="${inscriptions}" var="inscription" > 
							<fmt:formatDate pattern='dd/MM/YYYY'  value='${inscription.date}' var="inscriptionDate"/>
    						<c:if test="${(inscription.eleve.id == eleve.id) && ( inscriptionDate == vendredi)}"><c:out value="checked='checked'" /></c:if>
    					</c:forEach>
    					<c:if test="${vendrediPast}">
    					<c:out value="disabled='true'" /></c:if>
					 	value="${eleve.id}_${vendredi}">
    					
    				</td>				
				</tr>
			
			</c:forEach>
		
		</tbody>
		
	
	</table>
	<input type="hidden" name="lundi_SA" value="${lundi}"/>
	<input type="hidden" name="vendredi_SA" value="${vendredi}"/>
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>	
	<button type="submit" class="btn btn-primary">Inscrire</button>
	</form>






</sec:authorize>

</div>
<jsp:include page="../fragments/footer.jsp" />
</html>