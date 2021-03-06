<!DOCTYPE html>
<%@ page session="false" language="java"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
 <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<html>
<jsp:include page="../fragments/header.jsp">
	<jsp:param name="titre" value="alb-school" />
</jsp:include>

<div class="container">
<h4>Courrier</h4>
<p>Nombre de courriers: <span class="badge badge-info"><c:out value="${fn:length(communications)}" /></span></p>
<p>courrier  non lu: <c:out value="${communicationsNonLues}" /></p>

 <table class="table">
<thead>
	<tr>
		<th>date</th>
		<th>sujet</th>
		<th></th>
	</tr>

</thead>
<tbody>
	<c:forEach items="${communications}" var="communication">
		<tr>
			<td><fmt:formatDate pattern="dd/MM/YYYY" value="${communication.communication.date}"/></td>
			<c:choose>
				<c:when test="${communication.lu}">
					<td><c:out value="${communication.communication.sujet}" /></td>
				
				</c:when>
				<c:otherwise>	
					<td><b><c:out value="${communication.communication.sujet}" /></b></td>
				
				
				</c:otherwise>
			</c:choose>
			
			
			
			<td><button type="button" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#${communication.communication.id}"
			id="#${communication.communication.id}">
  				lire
				</button>
				<div class="modal fade" id="${communication.communication.id}">
  <div class="modal-dialog">
    <div class="modal-content">

      <!-- Modal Header -->
      <div class="modal-header">
        <h4 class="modal-title"><c:out value="${communication.communication.sujet}" /></h4>
        <button type="button" class="close" data-dismiss="modal">&times;</button>
      </div>

      <!-- Modal body -->
      <div class="modal-body">
        <a href="${communication.communication.lienFichier}">ouvrir le fichier</a>
      </div>

      <!-- Modal footer -->
      <div class="modal-footer">
        <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
      </div>

    </div>
  </div>
</div>

			</td>
		</tr>

	</c:forEach>

</tbody>
</table>



</div>

<jsp:include page="../fragments/footer.jsp" />
<script>
$(document).ready(function(){
	var idClicked;
	var id;
	$(".btn.btn-primary.btn-sm").click(function(e){
		idClicked = e.target.id;
		id = parseInt(idClicked.substring(1));
		//alert(id);
		
	});
	$(".btn.btn-danger").click(function(){
		//alert(id);
		window.location="read/"+id;
		
		
	});
	
});

</script>
</html>