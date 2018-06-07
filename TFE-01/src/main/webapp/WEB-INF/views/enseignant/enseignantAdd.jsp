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
<div class="well well-sm text-center color2 "><h5><b>Inscription d'un enseignant</b></h5></div>
<br>

<sf:form method="POST" class="form-horizontal" modelAttribute="enseignant" action="add">

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
					
				</div>
				<sf:errors path="nrn" class="control-label" />
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
					
				</div>
				<sf:errors path="nom" class="control-label" />
			</div>
				 
		</div>
	</s:bind>
	
	<s:bind path="prenom">
		<div class="form-group ${status.error ? 'has-error' : ''}">
			<sf:label path="prenom" class="col-md-4 control-label">
				prénom
			</sf:label>
			<div class="col-md-4 inputGroupContainer">
				<div class="input-group">
					<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
					<sf:input path="prenom" id="prenom" class="form-control" placeholder="prénom" />
					
				</div>
				<sf:errors path="prenom" class="control-label" />
			</div>
				 
		</div>
	</s:bind>
	
	<s:bind path="username">
		<div class="form-group ${status.error ? 'has-error' : ''}">
			<sf:label path="username" class="col-md-4 control-label">
				username
			</sf:label>
			<div class="col-md-4 inputGroupContainer">
				<div class="input-group">
					<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
					<sf:input path="username" id="username" class="form-control" placeholder="username" />
					
				</div>
				<sf:errors path="username" class="control-label" />
			</div>
				 
		</div>
	</s:bind>
	
	<s:bind path="password">
		<div class="form-group ${status.error ? 'has-error' : ''}">
			<sf:label path="password" class="col-md-4 control-label">
				mot de passe
			</sf:label>
			<div class="col-md-4 inputGroupContainer">
				<div class="input-group">
					<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
					<sf:password path="password" id="password" class="form-control" placeholder="password" />
					
				</div>
				<sf:errors path="password" class="control-label" />
			</div>
				 
		</div>
	</s:bind>
	
	<s:bind path="passwordConfirm">
		<div class="form-group ${status.error ? 'has-error' : ''}">
			<sf:label path="passwordConfirm" class="col-md-4 control-label">
				confirmer le mot de passe
			</sf:label>
			<div class="col-md-4 inputGroupContainer">
				<div class="input-group">
					<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
					<sf:password path="passwordConfirm" id="passwordConfirm" class="form-control" placeholder="passwordConfirm" />
					
				</div>
				<sf:errors path="passwordConfirm" class="control-label" />
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
					<sf:input type="date" 	path="dateNaissance" id="dateNaissance" class="form-control" placeholder="dateNaissance" />
					
				</div>
				<sf:errors path="dateNaissance" class="control-label" />
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
					<sf:select path="sexe" id="sexe" class="form-control" placeholder="sexe" >
					<option value="1">féminin</option>
					<option value="0">masculin</option>
					
					</sf:select>
					
				</div>
				<sf:errors path="sexe" class="control-label" />
			</div>
				 
		</div>
	</s:bind>
	
	<s:bind path="email">
		<div class="form-group ${status.error ? 'has-error' : ''}">
			<sf:label path="email" class="col-md-4 control-label">
				email
			</sf:label>
			<div class="col-md-4 inputGroupContainer">
				<div class="input-group">
					<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
					<sf:input path="email" id="email" class="form-control" placeholder="email" />
					
				</div>
				<sf:errors path="email" class="control-label" />
			</div>
				 
		</div>
	</s:bind>
	
	<s:bind path="telephone">
		<div class="form-group ${status.error ? 'has-error' : ''}">
			<sf:label path="telephone" class="col-md-4 control-label">
				téléphone
			</sf:label>
			<div class="col-md-4 inputGroupContainer">
				<div class="input-group">
					<span class="input-group-addon"><i class="glyphicon glyphicon-earphone"></i></span>
					<sf:input path="telephone" id="telephone" class="form-control" placeholder="téléphone" />
					
				</div>
				<sf:errors path="telephone" class="control-label" />
			</div>
				 
		</div>
	</s:bind>
	
	<s:bind path="rue">
		<div class="form-group ${status.error ? 'has-error' : ''}">
			<sf:label path="rue" class="col-md-4 control-label">
				rue
			</sf:label>
			<div class="col-md-4 inputGroupContainer">
				<div class="input-group">
					<span class="input-group-addon"><i class="glyphicon glyphicon-envelope"></i></span>
					<sf:input path="rue" id="rue" class="form-control" placeholder="rue" />
					
				</div>
				<sf:errors path="rue" class="control-label" />
			</div>
				 
		</div>
	</s:bind>
	
	<s:bind path="numero">
		<div class="form-group ${status.error ? 'has-error' : ''}">
			<sf:label path="numero" class="col-md-4 control-label">
				numéro
			</sf:label>
			<div class="col-md-4 inputGroupContainer">
				<div class="input-group">
					<span class="input-group-addon"><i class="glyphicon glyphicon-envelope"></i></span>
					<sf:input path="numero" id="numero" class="form-control" placeholder="numéro" />
					
				</div>
				<sf:errors path="numero" class="control-label" />
			</div>
				 
		</div>
	</s:bind>
	
	<s:bind path="codePostal">
		<div class="form-group ${status.error ? 'has-error' : ''}">
			<sf:label path="codePostal" class="col-md-4 control-label">
				code postal
			</sf:label>
			<div class="col-md-4 inputGroupContainer">
				<div class="input-group">
					<span class="input-group-addon"><i class="glyphicon glyphicon-envelope"></i></span>
					<sf:input path="codePostal" id="codePostal" class="form-control" placeholder="code postal" />
					
				</div>
				<sf:errors path="codePostal" class="control-label" />
			</div>
				 
		</div>
	</s:bind>
	
	<s:bind path="ville">
		<div class="form-group ${status.error ? 'has-error' : ''}">
			<sf:label path="ville" class="col-md-4 control-label">
				ville
			</sf:label>
			<div class="col-md-4 inputGroupContainer">
				<div class="input-group">
					<span class="input-group-addon"><i class="glyphicon glyphicon-envelope"></i></span>
					<sf:input path="ville" id="ville" class="form-control" placeholder="ville" />
					
				</div>
				<sf:errors path="ville" class="control-label" />
			</div>
				 
		</div>
	</s:bind>
	
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