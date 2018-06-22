<%@ page session="false"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

<jsp:include page="fragments/header.jsp" />

<body>

	<div class="container">
		<div class="jumbotron">
		<span class="glyphicon glyphicon-alert blanc" style="font-size:30px;"></span>
		<h5><b>Erreur</b></h5>
		
		<c:set var="message">
			<c:out value="${exception.message}"/>
		</c:set>
		<h5><s:message code="${message}"/></h5>
		
		
		
		
		</div>

		

	</div>

	<jsp:include page="fragments/footer.jsp" />

</body>
</html>