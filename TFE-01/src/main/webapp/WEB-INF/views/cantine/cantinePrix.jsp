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
<h4>Modification du prix des repas</h4>

<c:set value="${prixMaternelles}" var="prixMaternelles" />
<c:set value="${prixPrimaires}" var="prixPrimaires" />

<c:if test="${!empty erreur}">
<div class="alert alert-danger">
  <c:out value="${erreur}" />
</div>
</c:if>
<c:if test="${!empty success}">
<div class="alert alert-success">
  <c:out value="${success}" />
</div>
</c:if>

<form method="POST"  action="" >
<div class="form-group">
    <label for="maternelles" class="control-label">prix pour les classes de maternelle:</label>
    <div class="input-group col-md-3">
    <input type="number" class="form-control" name="prixMaternelles" value=${prixMaternelles}>
    </div>
  </div>
  <div class="form-group">
    <label for="primaires">prix pour les classes de primaire:</label>
    <div class="input-group col-md-3">
    <input type="number" class="form-control" name="prixPrimaires" value=${prixPrimaires}>
    </div>
  </div>

<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
  <button type="submit" class="btn btn-primary">Modifier</button>


</form>

</div>
<jsp:include page="../fragments/footer.jsp" />
</html>