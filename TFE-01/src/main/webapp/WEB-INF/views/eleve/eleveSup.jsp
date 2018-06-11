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
<h4>changement de classe</h4>
<c:out value="${eleve.nom}" /> <c:out value="${eleve.prenom}" />
actuellement en <c:out value="${classe}" /><br><br>

<sf:form method="POST" class="form-horizontal"  action="sup">
<p>passage en: </p>

	<div class="form-group" >
				<label class="col-sm-3 control-label">
					classe
				</label>
				<div class="col-sm-3 inputGroupContainer">
					<div class="input-group">
						<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
						<select id="classe" class="form-control" name="codeClasse">
						<c:forEach items="${listeClasses}" var="classe">
							<option value="<c:out value='${classe.code}'/>" 
								
								><c:out value="${classe.code}"/></option>
						</c:forEach>
						</select>
						
					</div>
				</div>
					 
	</div>
	
	
		<div class="form-group ${status.error ? 'has-error' : ''}">
			<label  class="col-sm-3 control-label">
				date du changement
			</label>
			<div class="col-sm-4 inputGroupContainer">
				<div class="input-group">
					<span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
					<input type="date"  id="dateEntree" name="dateEntree" class="form-control" placeholder="format jj/mm/aaaa" />
					
				</div>
				
			</div>
				 
		</div>
	
	
	<div class="form-group">
			<label class="col-sm-3 control-label"></label>
  			<div class="col-sm-4"><br>
			
				<button type="submit" class="btn btn-primary">
							valider <span class="glyphicon glyphicon-save"></span>
				</button>
			</div>
		</div>





</sf:form>



</div>
<jsp:include page="../fragments/footer.jsp" />
</html>