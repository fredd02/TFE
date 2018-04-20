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
<h4>Courrier</h4>
<p>Nombre de courriers: <span class="badge badge-info"><c:out value="${fn:length(communications)}" /></span></p>
<p>courrier  non lu: </p>


</div>
<jsp:include page="../fragments/footer.jsp" />
</html>