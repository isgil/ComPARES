<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ page import="java.util.List" %>

<body>
	<div class="container about">
		<div class="center item grey lighten-4">
			<h6><b>ComPARES v1.1 (Sep 2024)</b></h6>
			<br/><br/>
			<h6>
				<b>Authors</b>
			</h6>
			<ul>
				<li><b>Creator</b>: Isidoro Gil Leiva</li>
				<li><b>Developer</b>: David López Martínez</li>
			</ul>
		</div>
	</div>
</body>
<script type="text/javascript">
	$(document).ready(function() {
		console.log("ready!");
		$(".title-section").text($("nav #about span").text()).fadeIn(1000);
	});
</script>