<!DOCTYPE html>
<%@ page session="false" language="java"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>

<jsp:include page="fragments/header.jsp">
	<jsp:param name="titre" value="Home Page TFE" />
</jsp:include>

<div class="container">
<h4>Contact</h4>

<h5><b>Adresse</b></h5>
Ecole communale d'Avernas-Le-Bauduin<br>
rue Emile Volont, 3<br>
4280 Hannut

<h5><b>Coordonnées téléphoniques</b></h5>
019/51 30 82

</div>

<jsp:include page="fragments/footer.jsp" />
</html>