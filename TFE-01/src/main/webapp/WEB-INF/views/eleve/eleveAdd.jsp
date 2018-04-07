<!DOCTYPE html>
<%@ page session="false" language="java"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%-- <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> --%>
<%-- <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> --%>
<html>
<jsp:include page="../fragments/header.jsp">
	<jsp:param name="titre" value="Cours Résultats ISFCE" />
</jsp:include>

<div class="container">
<h1>Inscription d'un élève</h1>


<sf:form method="POST" class="form-inline" modelAttribute="eleve" action="add">

	<sf:errors path="*" element="div" cssClass="alert alert-danger" />
	
	<s:bind path="nrn">
		<div class="form-group ${status.error ? 'has-error' : ''}">
			<sf:label path="nrn" class="col-md-4 control-label">
				numéro de registre national
			</sf:label>
			<div class="col-md-4 inputGroupContainer">
				<div class="input-group">
					<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
					<sf:input path="nrn" id="nrn" class="form-control" placeholder="nrn" />
					<sf:errors path="nrn" class="control-label" />
				</div>
			</div>
				 
		</div>
	</s:bind>
	
	<s:bind path="nom">
		<div class="form-group ${status.error ? 'has-error' : ''}">
			<sf:label path="nom" class="col-md-4 control-label">
				nom
			</sf:label>
			<div class="col-md-4 inputGroupContainer">
				<div class="input-group">
					<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
					<sf:input path="nom" id="nom" class="form-control" placeholder="nom" />
					<sf:errors path="nom" class="control-label" />
				</div>
			</div>
				 
		</div>
	</s:bind>
	
	<s:bind path="prenom">
		<div class="form-group ${status.error ? 'has-error' : ''}">
			<sf:label path="prenom" class="col-md-4 control-label">
				prenom
			</sf:label>
			<div class="col-md-4 inputGroupContainer">
				<div class="input-group">
					<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
					<sf:input path="prenom" id="prenom" class="form-control" placeholder="prenom" />
					<sf:errors path="prenom" class="control-label" />
				</div>
			</div>
				 
		</div>
	</s:bind>
	
	<s:bind path="dateNaissance">
		<div class="form-group ${status.error ? 'has-error' : ''}">
			<sf:label path="dateNaissance" class="col-md-4 control-label">
				date de naissance
			</sf:label>
			<div class="col-md-4 inputGroupContainer">
				<div class="input-group">
					<span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
					<sf:input path="dateNaissance" id="dateNaissance" class="form-control" placeholder="dateNaissance" />
					<sf:errors path="dateNaissance" class="control-label" />
				</div>
			</div>
				 
		</div>
	</s:bind>
	
	<s:bind path="sexe">
		<div class="form-group ${status.error ? 'has-error' : ''}">
			<sf:label path="sexe" class="col-md-4 control-label">
				sexe
			</sf:label>
			<div class="col-md-4 inputGroupContainer">
				<div class="input-group">
					<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
					<sf:input path="sexe" id="sexe" class="form-control" placeholder="sexe" />
					<sf:errors path="sexe" class="control-label" />
				</div>
			</div>
				 
		</div>
	</s:bind>
	
	
		<div class="form-group" >
			<label class="col-md-4 control-label">
				classe
			</label>
			<div class="col-md-4 inputGroupContainer">
				<div class="input-group">
					<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
					<select id="classe" class="form-control" name="codeClasse">
					<c:forEach items="${listeClasses}" var="classe">
						<option value="${classe.code}">${classe.code}</option>
					</c:forEach>
					</select>
					
				</div>
			</div>
				 
		</div>
	
	
	<div class="form-group">
			<label class="col-md-4 control-label"></label>
  			<div class="col-md-4"><br>
			
				<button type="submit" class="btn btn-primary">
							inscrire <span class="glyphicon glyphicon-save"></span>
				</button>
			</div>
		</div>
</sf:form>

</div>
<jsp:include page="../fragments/footer.jsp" />
</html>