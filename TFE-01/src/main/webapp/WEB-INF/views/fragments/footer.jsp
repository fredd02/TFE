<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<div class="container"
>
	<hr>
	<footer>
		<p>Projet TFE - Frederic Appert
			(ISFCE)</p>
	</footer>
</div>


<s:url value="/resources/js/post.js" var="postJs" />
<s:url value="/resources/js/dataTables.bootstrap.min.js" var="datatablesBootstrapJs" />
<s:url value="/resources/js/jquery.dataTables.min.js" var="datatablesJQueryJs" />
<s:url value="/resources/js/datetime-moment.js" var="datetime-momentJs" />

<script src="${postJs}"></script>
<script src="${datatablesBootstrapJs}"></script>
<script src="${datatablesJQueryJs}"></script>
<script src="${datetime-momentJs}"></script>

</body>
