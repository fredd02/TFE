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

<c:out value="${message}" />

<sf:form method="POST" enctype="multipart/form-data" action="upload">
	<div class="form-group">
    <label for="file">Fichier à uploader:</label>
    <input type="file" class="form-control" id="file" name="file">
  </div>
  
  <button type="submit" class="btn btn-primary" value="Upload">uploader</button>



</sf:form>






<%-- <sf:form method="POST" enctype="multipart/form-data" action="upload"> --%>
<!-- 	<table> -->
<!-- 				<tr><td>File to upload:</td><td><input type="file" name="file" /></td></tr> -->
<!-- 				<tr><td></td><td><input type="submit" value="Uploader" /></td></tr> -->
<!-- 	</table> -->

	
<%-- </sf:form> --%>
<br><br>
<h4>Liste des fichiers uploadés</h4>
<ul>
	<c:forEach items="${files}" var="file">
		<li><a href="<c:out value='${file}' />"><c:out value="${file}" /></a></li>
	
	</c:forEach>
</ul>
</div>
<jsp:include page="../fragments/footer.jsp" />
</html>