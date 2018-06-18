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

<h4>Repas à la cantine facturés</h4>

<table class="table" id="inscriptions">
	<thead>
		<tr>
			<th>date</th>
			<th>élève</th>
		</tr>
	
	</thead>
	<tbody>
		<c:forEach items="${inscriptionsFacturees}" var="inscription">
			<tr>
				<td><fmt:formatDate pattern="dd/MM/YYYY" value="${inscription.date}"/></td>
				<td><c:out value="${inscription.eleve.prenom}" /></td>
			
			</tr>
		
		
		</c:forEach>
	</tbody>


</table>

 


</div>
<jsp:include page="../fragments/footer.jsp" />

<script>
$(document).ready(function() {
	//alert("test");
	//pour pouvoir trier selon la date
	$.fn.dataTable.moment( 'DD/MM/YYYY' );
	
    $('#inscriptions').DataTable( {
        "language": {
            "lengthMenu": "Afficher _MENU_ facturations par page",
            "zeroRecords": "aucun résultat - désolé",
            "info": " page _PAGE_ de _PAGES_",
            "infoEmpty": "Aucun élève",
            "infoFiltered": "(filtré de _MAX_ opérations au total)",
            "paginate":{
            	"previous": "précédent  ",
            	"next": "  suivant"
            },
    		"search" : "recherche"
            
        },
        "columnDefs": [
        	{"orderable": false, "targets": 0}
        ]
    }
    		
    
    );
} );


</script>
</html>