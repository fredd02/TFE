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
<div class="well well-sm text-center color1"><h4>Nouvelle communication</h4></div>



<sf:form method="POST" class="form-horizontal" modelAttribute="communication" action="add">

	<s:bind path="sujet">
		<div class="form-group ${status.error ? 'has-error' : ''}">
			<sf:label path="sujet" class="col-sm-4 control-label">
				sujet
			</sf:label>
			<div class="col-sm-4 inputGroupContainer">
				<div class="input-group">
					<span class="input-group-addon color1"><i class="glyphicon glyphicon-pencil"></i></span>
					<sf:input path="sujet" id="sujet" class="form-control" placeholder="sujet" />
					
				</div>
				<sf:errors path="sujet" class="control-label" />
			</div>
				 
		</div>
	</s:bind>
	
<%-- 	<s:bind path="corps"> --%>
<%-- 		<div class="form-group ${status.error ? 'has-error' : ''}"> --%>
<%-- 			<sf:label path="corps" class="col-sm-4 control-label"> --%>
<!-- 				corps -->
<%-- 			</sf:label> --%>
<!-- 			<div class="col-sm-4 inputGroupContainer"> -->
<!-- 				<div class="input-group"> -->
<%-- 					<span class="input-group-addon color1"><i class="glyphicon glyphicon-pencil"></i></span> --%>
<%-- 					<sf:textarea path="corps" id="corps" class="form-control" placeholder="corps" /> --%>
					
<!-- 				</div> -->
<%-- 				<sf:errors path="corps" class="control-label" /> --%>
<!-- 			</div> -->
				 
<!-- 		</div> -->
<%-- 	</s:bind> --%>
	
	<div class="form-group" >
			<label class="col-sm-4 control-label">
				fichier à envoyer
			</label>
			<div class="col-sm-4 inputGroupContainer">
				<div class="input-group">
					<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
					<select id="fichier" class="form-control" name="nomFichier">
					<c:forEach items="${files}" var="file">
						<option value="${file}">${file}</option>
					</c:forEach>
					</select>
					
				</div>
			</div>
				 
		</div>
		<hr>
		
		<p class="col-sm-4 control-label"><b>classes concernées</b></p>
		
		<div class="form-group">
			<c:forEach items="${classes}" var="classe">
				<div class="checkbox-inline">
  					<label><input type="checkbox" value="${classe.code}" name="classeCode">${classe.code}</label>
				</div>			
			
			</c:forEach>
		
		</div>
		
		<div class="form-group">
			<label class="col-sm-4 control-label"></label>
  			<div class="col-sm-4"><br>
			
				<button type="submit" class="btn btn-primary">
							poster <span class="glyphicon glyphicon-save"></span>
				</button>
			</div>
		</div>
</sf:form>



</div>
<jsp:include page="../fragments/footer.jsp" />
</html>