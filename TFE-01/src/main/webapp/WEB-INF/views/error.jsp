<%@ page session="false"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

<jsp:include page="fragments/header.jsp" />

<body>

	<div class="container">

		<h1>Erreur</h1>

<%-- 		<c:set var="message"> --%>
<%-- 			<c:out value="${exception.message}"/> --%>
<%-- 		</c:set> --%>
<%-- 		<h3><s:message code="${message}"/></h3> --%>

	</div>

	<jsp:include page="fragments/footer.jsp" />

</body>
</html>