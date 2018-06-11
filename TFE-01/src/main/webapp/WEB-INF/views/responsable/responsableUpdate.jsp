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
<div class="well well-sm text-center color2">
<h5><b>Modification des informations du responsable <c:out value="${responsable.nom}" /> <c:out value="${responsable.prenom}" /></b></h5>
</div>
<br><br>




<sf:form method="POST" class="form-horizontal" modelAttribute="responsable" action="update">

	<sf:errors path="*" element="div" cssClass="alert alert-danger" />
	<fieldset>
		<legend>Informations personnelles</legend>
		
		<s:bind path="nom">
		<div class="form-group ${status.error ? 'has-error' : ''}">
			<sf:label path="nom" class="col-sm-3 control-label">
				nom
			</sf:label>
			<div class="inputGroupContainer col-sm-4">
				<div class="input-group ">
					<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
					<sf:input path="nom" id="nom" class="form-control" placeholder="nom" />
					
				</div>
				<sf:errors path="nom" class="control-label" />
			</div>
				 
		</div>
	</s:bind>
	
	<s:bind path="prenom">
		<div class="form-group ${status.error ? 'has-error' : ''}">
			<sf:label path="prenom" class="col-sm-3 control-label">
				prénom
			</sf:label>
			<div class="col-sm-4 inputGroupContainer">
				<div class="input-group">
					<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
					<sf:input path="prenom" id="prenom" class="form-control" placeholder="prénom" />
					
				</div>
				<sf:errors path="prenom" class="control-label" />
			</div>
				 
		</div>
	</s:bind>
	
	<s:bind path="nrn">
		<div class="form-group ${status.error ? 'has-error' : ''}">
			<sf:label path="nrn" class="col-sm-3 control-label">
				numéro de registre national
			</sf:label>
			<div class="col-sm-4 inputGroupContainer">
				<div class="input-group">
					<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
					<sf:input path="nrn" id="nrn" class="form-control" placeholder="nrn" />
					
				</div>
				<sf:errors path="nrn" class="control-label" />
			</div>
				 
		</div>
	</s:bind><br>
	
	
	
	
	
	<s:bind path="dateNaissance">
		<div class="form-group ${status.error ? 'has-error' : ''}">
			<sf:label path="dateNaissance" class="col-sm-3 control-label">
				date de naissance
			</sf:label>
			<div class="col-sm-3 inputGroupContainer">
				<div class="input-group">
					<span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
					<sf:input type="date" path="dateNaissance" id="dateNaissance" class="form-control" placeholder="date de naissance" />
					<sf:errors path="dateNaissance" class="control-label" />
				</div>
			</div>
				 
		</div>
	</s:bind>
	
	<s:bind path="sexe">
		<div class="form-group ${status.error ? 'has-error' : ''}">
			<sf:label path="sexe" class="col-sm-3 control-label">
				sexe
			</sf:label>
			<div class="col-sm-3 inputGroupContainer">
				<div class="input-group">
					<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
					<sf:select path="sexe" id="sexe" class="form-control" placeholder="sexe" >
					<option value="1" ${responsable.sexe eq 1 ? 'selected="selected"' : '' }">féminin</option>
					<option value="0" ${responsable.sexe eq 0 ? 'selected="selected"' : '' }">masculin</option>
					
					</sf:select>
					
				</div>
				<sf:errors path="sexe" class="control-label" />
			</div>
				 
		</div>
	</s:bind>
	
	<s:bind path="profession">
		<div class="form-group ${status.error ? 'has-error' : ''}">
			<sf:label path="profession" class="col-sm-3 control-label">
				profession
			</sf:label>
			<div class="col-sm-4 inputGroupContainer">
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
			<sf:label path="email" class="col-sm-3 control-label">
				email
			</sf:label>
			<div class="col-sm-4 inputGroupContainer">
				<div class="input-group">
					<span class="input-group-addon"><i class="glyphicon glyphicon-pencil"></i></span>
					<sf:input path="email" id="email" class="form-control" placeholder="email" />
					<sf:errors path="email" class="control-label" />
				</div>
			</div>
				 
		</div>
	</s:bind>
	
	<s:bind path="telephone">
		<div class="form-group ${status.error ? 'has-error' : ''}">
			<sf:label path="telephone" class="col-sm-3 control-label">
				téléphone
			</sf:label>
			<div class="col-sm-3 inputGroupContainer">
				<div class="input-group">
					<span class="input-group-addon"><i class="glyphicon glyphicon-earphone"></i></span>
					<sf:input path="telephone" id="telephone" class="form-control" placeholder="telephone" />
					<sf:errors path="telephone" class="control-label" />
				</div>
			</div>
				 
		</div>
	</s:bind>
	
	
	</fieldset>
	
	
<!-- 	<fieldset> -->
<!-- 		<legend>Informations de connexion</legend> -->
		
<%-- 		<s:bind path="username"> --%>
<%-- 		<div class="form-group ${status.error ? 'has-error' : ''}"> --%>
<%-- 			<sf:label path="username" class="col-sm-3 control-label"> --%>
<!-- 				username -->
<%-- 			</sf:label> --%>
<!-- 			<div class="col-sm-4 inputGroupContainer"> -->
<!-- 				<div class="input-group"> -->
<%-- 					<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span> --%>
<%-- 					<sf:input path="username" id="username" class="form-control" placeholder="username" /> --%>
					
<!-- 				</div> -->
<%-- 				<sf:errors path="username" class="control-label" /> --%>
<!-- 			</div> -->
				 
<!-- 		</div> -->
<%-- 	</s:bind> --%>
	
<%-- 	<s:bind path="password"> --%>
<%-- 		<div class="form-group ${status.error ? 'has-error' : ''}"> --%>
<%-- 			<sf:label path="password" class="col-sm-3 control-label"> --%>
<!-- 				mot de passe -->
<%-- 			</sf:label> --%>
<!-- 			<div class="col-sm-4 inputGroupContainer"> -->
<!-- 				<div class="input-group"> -->
<%-- 					<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span> --%>
<%-- 					<sf:password path="password" id="password" class="form-control" placeholder="password" /> --%>
					
<!-- 				</div> -->
<%-- 				<sf:errors path="password" class="control-label" /> --%>
<!-- 			</div> -->
				 
<!-- 		</div> -->
<%-- 	</s:bind> --%>
	
<%-- 	<s:bind path="passwordConfirm"> --%>
<%-- 		<div class="form-group ${status.error ? 'has-error' : ''}"> --%>
<%-- 			<sf:label path="passwordConfirm" class="col-sm-3 control-label"> --%>
<!-- 				confirmer le mot de passe -->
<%-- 			</sf:label> --%>
<!-- 			<div class="col-sm-4 inputGroupContainer"> -->
<!-- 				<div class="input-group"> -->
<%-- 					<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span> --%>
<%-- 					<sf:password path="passwordConfirm" id="passwordConfirm" class="form-control" placeholder="passwordConfirm" /> --%>
<%-- 					<sf:errors path="passwordConfirm" class="control-label" /> --%>
<!-- 				</div> -->
<!-- 			</div> -->
				 
<!-- 		</div> -->
<%-- 	</s:bind> --%>
	
<!-- 	</fieldset> -->
	
	<br><br>
	<fieldset>
		<legend >Adresse</legend>
		
		<s:bind path="rue">
		<div class="form-group ${status.error ? 'has-error' : ''}">
			<sf:label path="rue" class="col-sm-3 control-label">
				rue
			</sf:label>
			<div class="col-sm-3 inputGroupContainer">
				<div class="input-group">
					<span class="input-group-addon"><i class="glyphicon glyphicon-home"></i></span>
					<sf:input path="rue" id="rue" class="form-control" placeholder="rue" />
					<sf:errors path="rue" class="control-label" />
				</div>
			</div>
		</div>
		</s:bind>
		
		<s:bind path="numero">
		<div class="form-group ${status.error ? 'has-error' : ''}">
			<sf:label path="rue" class="col-sm-3 control-label">
				numero
			</sf:label>
			<div class="col-sm-3 inputGroupContainer">
				<div class="input-group">
					<span class="input-group-addon"><i class="glyphicon glyphicon-home"></i></span>
					<sf:input path="numero" id="numero" class="form-control" placeholder="numero" />
					<sf:errors path="numero" class="control-label" />
				</div>
			</div>
		</div>
		</s:bind>
		
		<s:bind path="codePostal">
		<div class="form-group ${status.error ? 'has-error' : ''}">
			<sf:label path="rue" class="col-sm-3 control-label">
				code postal
			</sf:label>
			<div class="col-sm-3 inputGroupContainer">
				<div class="input-group">
					<span class="input-group-addon"><i class="glyphicon glyphicon-home"></i></span>
					<sf:input path="codePostal" id="codePostal" class="form-control" placeholder="codePostal" />
					<sf:errors path="codePostal" class="control-label" />
				</div>
			</div>
		</div>
		</s:bind>
		
		<s:bind path="ville">
		<div class="form-group ${status.error ? 'has-error' : ''}">
			<sf:label path="ville" class="col-sm-3 control-label">
				ville
			</sf:label>
			<div class="col-sm-3 inputGroupContainer">
				<div class="input-group">
					<span class="input-group-addon"><i class="glyphicon glyphicon-home"></i></span>
					<sf:input path="ville" id="numero" class="form-control" placeholder="ville" />
					<sf:errors path="ville" class="control-label" />
				</div>
			</div>
		</div>
		</s:bind>
	</fieldset>
	<br><br>
	
	
		
		
		<input type="hidden" name="eleve_id" value="${eleve.id}" />
		<sf:hidden path="password" />
		
	
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