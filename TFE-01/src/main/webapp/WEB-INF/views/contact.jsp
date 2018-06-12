<!DOCTYPE html>
<%@ page session="false" language="java"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>

<jsp:include page="fragments/header.jsp">
	<jsp:param name="titre" value="Home Page TFE" />
</jsp:include>

<div class="container">
<h4>Contact</h4>

<div class="well blue col-sm-4">
<h5><b>Adresse</b><br><br>
Ecole communale d'Avernas-Le-Bauduin<br>
rue Emile Volont, 3<br>
4280 Hannut</h5>
</div>
<div class="col-sm-1"></div>

<div class="well blue col-sm-4">
<h5><b>Coordonnées téléphoniques</b><br><br>
019/51 30 82</h5>
</div>
<br>

<!-- carte googleMap -->



</div>
carte google
<div id="map"></div>

<jsp:include page="fragments/footer.jsp" />
<script>
      function initMap() {
        var uluru = {lat: -25.363, lng: 131.044};
        var map = new google.maps.Map(document.getElementById('map'), {
          zoom: 4,
          center: uluru
        });
        var marker = new google.maps.Marker({
          position: uluru,
          map: map
        });
      }
    </script>
    <script async defer
    src="https://maps.googleapis.com/maps/api/js?key=AIzaSyC3qaQ7Q58wlP20w-A2LCA-t596j2ODbuk&callback=initMap">
    </script>

</html>