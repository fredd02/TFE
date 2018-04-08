<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ page session="false" language="java"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<head>
<title>${param.titre}</title>
<!-- Required meta tags for bootstrap-->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- responsive to mobile -->
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
 <s:url value="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" var="bootstrapCss" />
 <link rel="stylesheet" href="${bootstrapCss}" />
 <s:url value="/resources/css/style.css" var="styleCss" />
 <link rel="stylesheet" href="${styleCss}" />
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
 <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
 
 </head>
 <body>
<%--  	<c:set var="contextPath" value="${pageContext.request.contextPath}" /> --%>
 
 	<nav class="navbar navbar-default">
 		<div class="container-fluid">
 			<div class="navbar-header">
      			<a class="navbar-brand" href="#">Gestion Ecole</a>
   			 </div>
 		
			 <ul class="nav navbar-nav">
			 	<li class="active"><a href="/TFE-01/">Home</a></li>
			    <li class="dropdown">
			      <a class="dropdown-toggle" href="#" data-toggle="dropdown">Eleves
			      	<span class="caret"></span></a>
			      <ul class="dropdown-menu">
			      	<li><a href="/TFE-01/eleve/add">Inscription</a>
			      	<li><a href="/TFE-01/eleve/list">Liste</a>
			      </ul>
			     </li>
			    <li class="dropdown">
			      <a class="dropdown-toggle" href="#" data-toggle="dropdown">Enseignants
			      	<span class="caret"></span></a>
			      <ul class="dropdown-menu">
			      	<li><a href="/TFE-01/enseignant/add">Inscription</a>
			      	<li><a href="/TFE-01/enseignant/list">Liste</a>
			      </ul>
			     </li>
			    <li class="dropdown">
			      <a class="dropdown-toggle" href="#" data-toggle="dropdown">Classes
			      	<span class="caret"></span></a>
			      <ul class="dropdown-menu">
			      	<li><a href="/TFE-01/classe/list">Liste</a>
			      	<li><a href="/#">#</a>
			      </ul>
			     </li>
			  </ul>
		</div>
	</nav>
 
 
 
 