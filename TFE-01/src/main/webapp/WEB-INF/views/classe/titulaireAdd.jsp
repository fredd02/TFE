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
	<jsp:param name="titre" value="Projet TFE" />
</jsp:include>

<div class="container">
<h4>Ajout d'un titulaire à la classe <c:out value="${classe.nom}" /></h4>

<sf:form method="POST" class="form-inline"  action="add">

 <div class="form-group">
  	<label for="sel1">Select list:</label>
  	<select class="form-control" id="sel1" name="enseignant_id">
    <c:forEach items="${enseignants}" var = "enseignant">
    	<option value="${enseignant.username}">${enseignant.nom} ${enseignant.prenom}</option>
    
    </c:forEach>
  </select>
</div>

<button type="submit" class="btn btn-default">Submit</button> 


</sf:form>

</div>
<jsp:include page="../fragments/footer.jsp" />
</html>