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
	<jsp:param name="titre" value="Cours Résultats ISFCE" />
</jsp:include>

<div class="container">
<h4>Suivi de la communication <i><b><c:out value="${communication.sujet}" /></b></i></h4>

<table class="table">
	<thead>
	<tr>
		<th>communication lue par</th>
		<th>communication non encore lue par</th>
	
	</tr>
	</thead>
	<tbody>
		<tr>
			<td>
			<ul>
			<c:forEach items="${communicationsLues}" var="communication">
				<li><c:out value="${communication.responsable.nom}" /> <c:out value="${communication.responsable.prenom}" /></li>
			
			</c:forEach>
			</ul>
			
			
			
			</td>
			<td>
				<ul>
			<c:forEach items="${communicationsNonLues}" var="communication">
				<li><c:out value="${communication.responsable.nom}" /> <c:out value="${communication.responsable.prenom}" /></li>
			
			</c:forEach>
			</ul>
			
			</td>
		
		</tr>
	
	</tbody>


</table>


</div>
<jsp:include page="../fragments/footer.jsp" />
</html>