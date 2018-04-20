!DOCTYPE html>
<%@ page session="false" language="java"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%-- <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> --%>
<html>
<jsp:include page="fragments/header.jsp">
	<jsp:param name="titre" value="Projet TFE" />
</jsp:include>

<div class="container">

<h1>Welcome to FileDownloader Example</h1>
         
        Click on below links to see FileDownload in action.<br/><br/>
         
         
        <a href="<c:url value='/download/internal' />">Download This File (located inside project)</a>  
        <br/>
        <a href="<c:url value='/download/external' />">Download This File (located outside project, on file system)</a>

</div>
<jsp:include page="fragments/footer.jsp" />
</html>