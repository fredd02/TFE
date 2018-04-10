<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
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
 <s:url value="/resources/css/common.css" var="commonCss" />
<link href="${commonCss}" rel="stylesheet">
 
 </head>
 <body>
<%--  	<c:set var="contextPath" value="${pageContext.request.contextPath}" /> --%>

<s:url value="/login" var="loginUrl" />
<s:url value="/logout" var="logoutUrl" />

<div>
	<img src="/TFE-01/resources/img/bandeau.png">
</div>

<sec:authorize access="hasAnyAuthority('ADMIN','DIRECTEUR','ENSEIGNANT')"> 
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
			  <ul class="nav navbar-nav navbar-right">
				<sec:authorize access="isAuthenticated()">
					<li><a href="" onclick="post('${logoutUrl}',{'${_csrf.parameterName}': '${_csrf.token}'})" >
						<span class="glyphicon glyphicon-log-out"></span>
						Logout <sec:authentication property="principal.username" /></a>
					</li>
				</sec:authorize>
				<sec:authorize access="!isAuthenticated()">
					<li><a href="${loginUrl}"><span class="glyphicon glyphicon-log-in"></span> Login</a>
					</li>
				</sec:authorize>
			</ul>
		</div>
	</nav>





</sec:authorize>

 <sec:authorize access="hasAnyAuthority('PARENT')">
 	<nav class="navbar navbar-default">
 		<div class="container-fluid">
 			<div class="navbar-header">
      			<a class="navbar-brand" href="#">CORRESPONDANCE</a>
   			 </div>
 		
			 <ul class="nav navbar-nav">
			 	<li class="active"><a href="/TFE-01/">Home</a></li>
			    <li class="dropdown">
			      <a class="dropdown-toggle" href="#" data-toggle="dropdown">Présentation
			      	<span class="caret"></span></a>
			      <ul class="dropdown-menu">
			      	<li><a href="/TFE-01/eleve/add">Inscription</a>
			      	<li><a href="/TFE-01/eleve/list">Liste</a>
			      </ul>
			     </li>
			    <li class="dropdown">
			      <a class="dropdown-toggle" href="#" data-toggle="dropdown">Projet pédagogique
			      	<span class="caret"></span></a>
			      <ul class="dropdown-menu">
			      	<li><a href="/TFE-01/enseignant/add">Inscription</a>
			      	<li><a href="/TFE-01/enseignant/list">Liste</a>
			      </ul>
			     </li>
			    <li class="dropdown">
			      <a class="dropdown-toggle" href="#" data-toggle="dropdown">Actualité
			      	<span class="caret"></span></a>
			      <ul class="dropdown-menu">
			      	<li><a href="/TFE-01/classe/list">Liste</a>
			      	<li><a href="/#">#</a>
			      </ul>
			     </li>
			     <li class="dropdown">
			      <a class="dropdown-toggle" href="#" data-toggle="dropdown">Cantine
			      	<span class="caret"></span></a>
			      <ul class="dropdown-menu">
			      	<li><a href="/TFE-01/classe/list">Menu de la semaine</a>
			      	<li><a href="/#">inscription</a>
			      </ul>
			     </li>
			  </ul>
			  <ul class="nav navbar-nav navbar-right">
				<sec:authorize access="isAuthenticated()">
					<li><a href="" onclick="post('${logoutUrl}',{'${_csrf.parameterName}': '${_csrf.token}'})" >
						<span class="glyphicon glyphicon-log-out"></span>
						Logout <sec:authentication property="principal.username" /></a>
					</li>
				</sec:authorize>
				<sec:authorize access="!isAuthenticated()">
					<li><a href="${loginUrl}"><span class="glyphicon glyphicon-log-in"></span> Login</a>
					</li>
				</sec:authorize>
			</ul>
		</div>
	</nav>
</sec:authorize>

<sec:authorize access="isAnonymous()">
 	<nav class="navbar navbar-default">
 		<div class="container-fluid">
 			<div class="navbar-header">
      			<a class="navbar-brand" href="#"></a>
   			 </div>
 		
			 <ul class="nav navbar-nav">
			 	<li class="active"><a href="/TFE-01/">Présentation</a></li>
			    <li class="dropdown">
			      <a class="dropdown-toggle" href="#" data-toggle="dropdown">Projet pédagogique
			      	<span class="caret"></span></a>
			      <ul class="dropdown-menu">
			      	<li><a href="/TFE-01/eleve/add">Inscription</a>
			      	<li><a href="/TFE-01/eleve/list">Liste</a>
			      </ul>
			     </li>
			    <li class="dropdown">
			      <a class="dropdown-toggle" href="#" data-toggle="dropdown">Actualité
			      	<span class="caret"></span></a>
			      <ul class="dropdown-menu">
			      	<li><a href="/TFE-01/enseignant/add">Inscription</a>
			      	<li><a href="/TFE-01/enseignant/list">Liste</a>
			      </ul>
			     </li>
			    <li class="dropdown">
			      <a class="dropdown-toggle" href="#" data-toggle="dropdown">Contact
			      	<span class="caret"></span></a>
			      <ul class="dropdown-menu">
			      	<li><a href="/TFE-01/classe/list">Liste</a>
			      	<li><a href="/#">#</a>
			      </ul>
			     </li>
			  </ul>
			  <ul class="nav navbar-nav navbar-right">
				<sec:authorize access="isAuthenticated()">
					<li><a href="" onclick="post('${logoutUrl}',{'${_csrf.parameterName}': '${_csrf.token}'})" >
						<span class="glyphicon glyphicon-log-out"></span>
						Logout <sec:authentication property="principal.username" /></a>
					</li>
				</sec:authorize>
				<sec:authorize access="!isAuthenticated()">
					<li><a href="${loginUrl}"><span class="glyphicon glyphicon-log-in"></span> Login</a>
					</li>
				</sec:authorize>
			</ul>
		</div>
	</nav>
</sec:authorize>
 
 
 
 