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

<h4>Repas à la cantine facturés</h4>

 <ul class="list-group">
 <c:forEach items="${inscriptionsFacturees}" var="inscription">
 	<li class="list-group-item"><fmt:formatDate pattern="dd/MM/YYYY" value="${inscription.date}"/> <c:out value="${inscription.eleve.prenom}" /></li>
 
 </c:forEach>
  
</ul> 


</div>
<jsp:include page="../fragments/footer.jsp" />
</html>