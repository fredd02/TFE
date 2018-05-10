<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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

<s:url value="https://cdn.datatables.net/1.10.16/css/dataTables.bootstrap.min.css" var="dataTablesCss" />
 <link rel="stylesheet" href="${dataTablesCss}" />
 
 <sec:authorize access="hasAnyAuthority('ADMIN')" var="admin" />
 <sec:authorize access="hasAnyAuthority('DIRECTEUR')" var="directeur" />
 <sec:authorize access="hasAnyAuthority('ENSEIGNANT')" var="enseignant" />
 <sec:authorize access="hasAnyAuthority('PARENT')" var="parent" />

 
 </head>
 <body>
<%--  	<c:set var="contextPath" value="${pageContext.request.contextPath}" /> --%>

<s:url value="/login" var="loginUrl" />
<s:url value="/logout" var="logoutUrl" />
<jsp:useBean id="now" class="java.util.Date" />
<fmt:formatDate pattern="ddMMYYYY" value="${now}" var="today"/>

<div>
	<img src="/TFE-01/resources/img/bandeau.png">
</div>

<c:if test="${admin || directeur || enseignant}">
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
			      <c:if test="${admin || directeur}">
			      	<li><a href="/TFE-01/enseignant/add">Inscription</a>
			      </c:if>	
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
			     <li class="dropdown">
			      <a class="dropdown-toggle" href="#" data-toggle="dropdown">Comptes
			      	<span class="caret"></span></a>
			      <ul class="dropdown-menu">
			      	<li><a href="/TFE-01/compte/add">Créer un compte</a>
			      	<li><a href="/TFE-01/compte/list">Liste des comptes</a>
			      </ul>
			     </li>
			      <li class="dropdown">
			      <a class="dropdown-toggle" href="#" data-toggle="dropdown">Cantine
			      	<span class="caret"></span></a>
			      <ul class="dropdown-menu">
			      	<li><a href="/TFE-01/cantine/inscriptions/${today}">Repas du jour</a>
			      	<li><a href="/TFE-01/compte/list"></a>
			      </ul>
			     </li>
			     <li class="dropdown">
			      <a class="dropdown-toggle" href="#" data-toggle="dropdown">Communication
			      	<span class="caret"></span></a>
			      <ul class="dropdown-menu">
			      	<li><a href="/TFE-01/file/upload">uploader un fichier</a>
			      	<li><a href="/TFE-01/communication/add">nouvelle communication</a>
			      	<li><a href="/TFE-01/communication/list">liste des communications</a>
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





</c:if>

 <sec:authorize access="hasAnyAuthority('PARENT')">
 <sec:authentication property="principal.username" var="username" />
 	<nav class="navbar navbar-default">
 		<div class="container-fluid">
 			<div class="navbar-header">
      			<a class="navbar-brand" href="#">CORRESPONDANCE</a>
   			 </div>
 		
			 <ul class="nav navbar-nav">
			 	<li class="active"><a href="/TFE-01/">Home</a></li>
			    <li class="dropdown">
			      <a class="dropdown-toggle" href="#" data-toggle="dropdown">L'école
			      	<span class="caret"></span></a>
			      <ul class="dropdown-menu">
			      	<li><a href="#">Présentation</a>
			      	<li><a href="#">Projet pédagogique</a>
			      	<li><a href="#">Actualité</a>
			      </ul>
			     </li>
			     <li class="dropdown">
			      <a class="dropdown-toggle" href="#" data-toggle="dropdown">Compte
			      	<span class="caret"></span></a>
			      <ul class="dropdown-menu">
			      	<li><a href="/TFE-01/compte/responsable/${username}">consultation du compte</a>
			      	
			      </ul>
			     </li>
			    
			     <li class="dropdown">
			      <a class="dropdown-toggle" href="#" data-toggle="dropdown">Cantine
			      	<span class="caret"></span></a>
			      <ul class="dropdown-menu">
			      	<li><a href="/TFE-01/cantine/">Menu de la semaine</a>
			      	<li><a href="/TFE-01/cantine/inscription/${username}">inscription pour la semaine</a>
			      	<li><a href="/TFE-01/cantine/repas/${username}">repas facturés</a>
			      </ul>
			     </li>
			     <li class="dropdown">
			      <a class="dropdown-toggle" href="#" data-toggle="dropdown">Communication
			      	<span class="caret"></span></a>
			      <ul class="dropdown-menu">
			      	<li><a href="/TFE-01/communication/${username}/list">Courrier</a>
<!-- 			      	<li><a href="#">Prendre RDV</a> -->
			      	
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
			 	<li class="active"><a href="/TFE-01/">Accueil</a></li>
			    <li><a href="/TFE-01/projetPedagogique">Projet pédagogique</a></li>
			    <li class="dropdown">
			      <a class="dropdown-toggle" href="#" data-toggle="dropdown">Actualité
			      	<span class="caret"></span></a>
			      <ul class="dropdown-menu">
			      	<li><a href="/TFE-01/enseignant/add">Inscription</a>
			      	<li><a href="/TFE-01/enseignant/list">Liste</a>
			      </ul>
			     </li>
			    <li><a href="/TFE-01/contact">Contact</a></li>
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
 
 
 
 