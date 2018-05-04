<%@ page session="false"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> --%>

<!DOCTYPE html>
<html>

<jsp:include page="fragments/header.jsp" />

<body>

	<div class="container">
		<div class="logo">
			<h1>403</h1>
		</div>

		<h1><s:message code="acces.refuse" /></h1>
		<p><s:message code="droits" /></p>
	</div>

	<jsp:include page="fragments/footer.jsp" />

</body>
</html>