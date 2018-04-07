<!DOCTYPE html>
<%@ page session="false" language="java"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
<jsp:include page="../fragments/header.jsp">
	<jsp:param name="titre" value="Projet TFE" />
</jsp:include>
<div class="container">
<h2>Emploi du temps général pour la classe <c:out value="${code}" /></h2>
<h4>Ajouter une ligne</h4>

<sf:form method="POST" class="form-inline" modelAttribute="segmentHoraire" action="add">

	<div class="form-group">
	    <label for="sel1">matière:</label>
	    <sf:select path="matiere" class="form-control">
	    	<option>piscine</option>
	    	<option>gym</option>
	    	<option>religion</option>
	    	<option>cours généraux</option>
	    	<option>néerlandais</option>
	    </sf:select>
  </div>
  <div class="form-group">
	    <label for="sel1">heure début:</label>
	    <sf:select path="heureDebut" class="form-control">
	    	<option>08:30</option>
	    	<option>09:15</option>
	    	<option>10:30</option>
	    	<option>11:15</option>
	    	<option>13:15</option>
	    	<option>14:30</option>
	    	
	    	
	    </sf:select>
  </div>
  <div class="form-group">
	    <label for="sel1">heure fin:</label>
	    <sf:select path="heureFin" class="form-control">
	     	<option>09:15</option>
	    	<option>10:00</option>
	    	<option>11:15</option>
	    	<option>12:00</option>
	    	<option>14:00</option>
	    	<option>15:15</option>
	    	
	    </sf:select>
  </div>
  <div class="form-group">
	    <label for="sel1">jour:</label>
	    <sf:select path="jour" class="form-control">
	    	<option value="1">lundi</option>
	    	<option value="2">mardi</option>
	    	<option value="3">mercredi</option>
	    	<option value="4">jeudi</option>
	    	<option value="5">vendredi</option>
	    </sf:select>
  </div>
  
  <sf:hidden path="classe" value="${code}"/>
  
  <button type="submit" class="btn btn-default">Submit</button>

</sf:form>
<br><br>


<table class="table table-bordered table-hover">
	<thead>
		<tr>
			<th></th>
			<th>Lundi</th>
			<th>Mardi</th>
			<th>Mercredi</th>
			<th>Jeudi</th>
			<th>Vendredi</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td>8h30 -9h15</td>
			<c:forEach items="${horaire}" var="day">
				<fmt:parseDate var="heureIn" value="08:30" pattern="HH:mm" />
				<fmt:parseDate var="heureOut" value="09:15" pattern="HH:mm" />
				<td>
					<c:forEach items="${day.value}" var="segment">
						
						<c:if test="${(segment.heureDebut le heureIn) and (segment.heureFin ge heureOut)}">
							<c:out value="${segment.matiere}" />
						</c:if>
					
					</c:forEach>
				
				</td>	
			</c:forEach>
		
		</tr>
		<tr>
			<td>9h15 -10h00</td>
			<c:forEach items="${horaire}" var="day">
				<fmt:parseDate var="heureIn" value="09:15" pattern="HH:mm" />
				<fmt:parseDate var="heureOut" value="10:00" pattern="HH:mm" />
				<td>
					<c:forEach items="${day.value}" var="segment">
						
						<c:if test="${(segment.heureDebut le heureIn) and (segment.heureFin ge heureOut)}">
							<c:out value="${segment.matiere}" />
						</c:if>
					
					</c:forEach>
				
				</td>	
			</c:forEach>
		</tr>
		<tr>
			<td>10h30 -11h15</td>
			<c:forEach items="${horaire}" var="day">
				<fmt:parseDate var="heureIn" value="10:30" pattern="HH:mm" />
				<fmt:parseDate var="heureOut" value="11:15" pattern="HH:mm" />
				<td>
					<c:forEach items="${day.value}" var="segment">
						
						<c:if test="${(segment.heureDebut le heureIn) and (segment.heureFin ge heureOut)}">
							<c:out value="${segment.matiere}" />
						</c:if>
					
					</c:forEach>
				
				</td>	
			</c:forEach>
		</tr>
		<tr>
			<td>11h15 -12h00</td>
			<c:forEach items="${horaire}" var="day">
				<fmt:parseDate var="heureIn" value="11:15" pattern="HH:mm" />
				<fmt:parseDate var="heureOut" value="12:00" pattern="HH:mm" />
				<td>
					<c:forEach items="${day.value}" var="segment">
						
						<c:if test="${(segment.heureDebut le heureIn) and (segment.heureFin ge heureOut)}">
							<c:out value="${segment.matiere}" />
						</c:if>
					
					</c:forEach>
				
				</td>	
			</c:forEach>
		</tr>
		
		<tr>
			<td>12h00 -13h15</td>
			<c:forEach items="${horaire}" var="day">
				<fmt:parseDate var="heureIn" value="12:00" pattern="HH:mm" />
				<fmt:parseDate var="heureOut" value="13:15" pattern="HH:mm" />
				<td>
					<c:forEach items="${day.value}" var="segment">
						
						<c:if test="${(segment.heureDebut le heureIn) and (segment.heureFin ge heureOut)}">
							<c:out value="${segment.matiere}" />
						</c:if>
					
					</c:forEach>
				
				</td>	
			</c:forEach>
		</tr>
		<tr>
			<td>13h15 -14h00</td>
			<c:forEach items="${horaire}" var="day">
				<fmt:parseDate var="heureIn" value="13:15" pattern="HH:mm" />
				<fmt:parseDate var="heureOut" value="14:00" pattern="HH:mm" />
				<td>
					<c:forEach items="${day.value}" var="segment">
						
						<c:if test="${(segment.heureDebut le heureIn) and (segment.heureFin ge heureOut)}">
							<c:out value="${segment.matiere}" />
						</c:if>
					
					</c:forEach>
				
				</td>	
			</c:forEach>
		</tr>
		<tr>
			<td>14h30 -15h15</td>
			<c:forEach items="${horaire}" var="day">
				<fmt:parseDate var="heureIn" value="14:30" pattern="HH:mm" />
				<fmt:parseDate var="heureOut" value="15:15" pattern="HH:mm" />
				<td>
					<c:forEach items="${day.value}" var="segment">
						
						<c:if test="${(segment.heureDebut le heureIn) and (segment.heureFin ge heureOut)}">
							<c:out value="${segment.matiere}" />
						</c:if>
					
					</c:forEach>
				
				</td>	
			</c:forEach>
		</tr>
		
		
	
	</tbody>
</table>

<c:forEach items="${lundi}" var="segment">
	<c:out value="${segment.heureDebut}"/>
</c:forEach>

<fmt:parseDate var="testDate1" value="15:00" pattern="HH:mm" />
<fmt:parseDate var="testDate2" value="16:00" pattern="HH:mm" />
<c:if test="${testDate1 gt testDate2}">
	<p>test ok</p>
</c:if>


</div>
<jsp:include page="../fragments/footer.jsp" />
</html>