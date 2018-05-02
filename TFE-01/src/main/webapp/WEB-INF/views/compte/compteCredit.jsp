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
<h4>Ajout d'un crédit sur le compte <c:out value="${compte.nom}" /></h4>

 <sf:form method="POST" class="form-horizontal" modelAttribute="ligneCompte" action="">
 <s:bind path="montant">
 	<div class="form-group ${status.error ? 'has-error' : ''}">
			<sf:label path="montant" class="col-sm-4 control-label">
				montant
			</sf:label>
			<div class="col-sm-4 inputGroupContainer">
				<div class="input-group">
					
					<sf:input path="montant" id="montant" class="form-control" placeholder="montant" />
					
				</div>
				<sf:errors path="montant" class="control-label" />
			</div>
  
  </div>
  </s:bind>
  
  
  
  <s:bind path="remarque">
 	<div class="form-group ${status.error ? 'has-error' : ''}">
			<sf:label path="remarque" class="col-sm-4 control-label">
				remarque
			</sf:label>
			<div class="col-sm-4 inputGroupContainer">
				<div class="input-group">
					
					<sf:input path="remarque" id="remarque" class="form-control" placeholder="remarque" />
					
				</div>
				<sf:errors path="remarque" class="control-label" />
			</div>
  
  </div>
  </s:bind>
  
  <button type="submit" class="btn btn-default">créditer le compte</button>
</sf:form> 

</div>
<jsp:include page="../fragments/footer.jsp" />
</html>