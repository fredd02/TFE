<!DOCTYPE html>
<%@ page session="false" language="java"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%-- <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> --%>
<html>
<jsp:include page="../fragments/header.jsp">
	<jsp:param name="titre" value="Projet TFE" />
</jsp:include>

<div class="container">
<h4>Ajout d'un responsable pour l'eleve <c:out value="${eleve.nom}" /> <c:out value="${eleve.prenom}" /></h4>
nrn du responsable: <c:out value="${responsable.nrn}" /> <c:out value="${responsable.nom}" />

<sf:form method="POST" class="form-inline" modelAttribute="responsable" action="../add">

	<sf:errors path="*" element="div" cssClass="alert alert-danger" />
	
	<s:bind path="nrn">
		<div class="form-group ${status.error ? 'has-error' : ''}">
			<sf:label path="nrn" class="col-lg-3 control-label">
				numéro de registre national
			</sf:label>
			<div class="col-lg-3 inputGroupContainer">
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
			<sf:label path="nom" class="col-lg-3 control-label">
				nom
			</sf:label>
			<div class="col-lg-3 inputGroupContainer">
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
			<sf:label path="prenom" class="col-lg-3 control-label">
				prenom
			</sf:label>
			<div class="col-lg-3 inputGroupContainer">
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
			<sf:label path="dateNaissance" class="col-lg-3 control-label">
				date de naissance
			</sf:label>
			<div class="col-lg-3 inputGroupContainer">
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
			<sf:label path="sexe" class="col-lg-3 control-label">
				sexe
			</sf:label>
			<div class="col-lg-3 inputGroupContainer">
				<div class="input-group">
					<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
					<sf:input path="sexe" id="sexe" class="form-control" placeholder="sexe" />
					<sf:errors path="sexe" class="control-label" />
				</div>
			</div>
				 
		</div>
	</s:bind>
	
	<s:bind path="profession">
		<div class="form-group ${status.error ? 'has-error' : ''}">
			<sf:label path="profession" class="col-lg-3 control-label">
				profession
			</sf:label>
			<div class="col-lg-3 inputGroupContainer">
				<div class="input-group">
					<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
					<sf:input path="profession" id="profession" class="form-control" placeholder="profession" />
					<sf:errors path="profession" class="control-label" />
				</div>
			</div>
				 
		</div>
	</s:bind>
	
	<s:bind path="email">
		<div class="form-group ${status.error ? 'has-error' : ''}">
			<sf:label path="email" class="col-lg-3 control-label">
				email
			</sf:label>
			<div class="col-lg-3 inputGroupContainer">
				<div class="input-group">
					<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
					<sf:input path="email" id="email" class="form-control" placeholder="email" />
					<sf:errors path="email" class="control-label" />
				</div>
			</div>
				 
		</div>
	</s:bind>
	
	<s:bind path="telephone">
		<div class="form-group ${status.error ? 'has-error' : ''}">
			<sf:label path="telephone" class="col-lg-3 control-label">
				téléphone
			</sf:label>
			<div class="col-lg-3 inputGroupContainer">
				<div class="input-group">
					<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
					<sf:input path="telephone" id="telephone" class="form-control" placeholder="telephone" />
					<sf:errors path="telephone" class="control-label" />
				</div>
			</div>
				 
		</div>
	</s:bind>
	<br><br>
	<fieldset>
		<legend class="text-info">adresse</legend>
		
		<s:bind path="rue">
		<div class="form-group ${status.error ? 'has-error' : ''}">
			<sf:label path="rue" class="col-lg-3 control-label">
				rue
			</sf:label>
			<div class="col-lg-3 inputGroupContainer">
				<div class="input-group">
					<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
					<sf:input path="rue" id="rue" class="form-control" placeholder="rue" />
					<sf:errors path="rue" class="control-label" />
				</div>
			</div>
		</div>
		</s:bind>
		
		<s:bind path="numero">
		<div class="form-group ${status.error ? 'has-error' : ''}">
			<sf:label path="rue" class="col-lg-3 control-label">
				numero
			</sf:label>
			<div class="col-lg-3 inputGroupContainer">
				<div class="input-group">
					<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
					<sf:input path="numero" id="numero" class="form-control" placeholder="numero" />
					<sf:errors path="numero" class="control-label" />
				</div>
			</div>
		</div>
		</s:bind>
		
		<s:bind path="codePostal">
		<div class="form-group ${status.error ? 'has-error' : ''}">
			<sf:label path="rue" class="col-lg-3 control-label">
				code postal
			</sf:label>
			<div class="col-lg-3 inputGroupContainer">
				<div class="input-group">
					<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
					<sf:input path="codePostal" id="codePostal" class="form-control" placeholder="codePostal" />
					<sf:errors path="codePostal" class="control-label" />
				</div>
			</div>
		</div>
		</s:bind>
		
		<s:bind path="ville">
		<div class="form-group ${status.error ? 'has-error' : ''}">
			<sf:label path="ville" class="col-lg-3 control-label">
				ville
			</sf:label>
			<div class="col-lg-3 inputGroupContainer">
				<div class="input-group">
					<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
					<sf:input path="ville" id="numero" class="form-control" placeholder="ville" />
					<sf:errors path="ville" class="control-label" />
				</div>
			</div>
		</div>
		</s:bind>
	</fieldset>
	<br><br>
	
	
		<div class="form-group ${status.error ? 'has-error' : ''}">
			<label class="col-lg-3 control-label">
				lien avec l'élève
			</label>
			<div class="col-lg-3 inputGroupContainer">
				<div class="input-group">
					<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
					<input id="lienParent" name="lienParent" class="form-control" placeholder="lienParent" />
					
				</div>
			</div>
		</div>
		
		<input type="hidden" name="eleve_id" value="${eleve.id}" />
		
	
	<div class="form-group">
			<label class="col-md-4 control-label"></label>
  			<div class="col-md-4"><br>
			
				<button type="submit" class="btn btn-primary">
							enregistrer <span class="glyphicon glyphicon-save"></span>
				</button>
			</div>
		</div>
</sf:form>


</div>
<jsp:include page="../fragments/footer.jsp" />
</html>