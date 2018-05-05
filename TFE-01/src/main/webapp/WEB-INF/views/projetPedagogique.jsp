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
<h2>Notre projet pédagogique</h2>
<h4>Objectifs généraux de l'enseignement</h4>
<p>Nos programmes des classes maternelles et primaires ont été élaborés par le Conseil de l'enseignement des communes et des provinces.
Ils sont des outils qui permettent d'atteindre les objectifs généraux de notre système éducatif.</p>
<p><b><i>Notre équipe éducative traduit dans ses pratiques et par des actions concrètes les objectifs d'éducation
que les enfants qui lui sont confiés méritent au quotidien.</i></b></p>

<p><b>Nous assurons pour tous, la maitrise des compétences de base.</b></p>

<p>Nous privilégions le perfectionnement de la lecture et du langage en utilisant divers outils et méthodes adaptées
au niveau de votre enfant (nous donnons du sens à nos apprentissages). Nous proposons régulièrement des activités
variées touchant à différents domaines et favorisant les manipulations, notamment au niveau des mathématiques
et des activités d'éveil.</p>

<p><b>Nous développons l'expression, la communication et la créativité.</b></p>

</div>

<jsp:include page="fragments/footer.jsp" />
</html>