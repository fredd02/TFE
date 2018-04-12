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
  <li class="list-group-item"><b>Titulaires: </b></li>
  <li class="list-group-item"><b>Solde: </b><c:out value="${compte.solde}" /></li>
</ul> 

</div>
<jsp:include page="../fragments/footer.jsp" />
</html>