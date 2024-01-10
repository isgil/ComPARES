<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<head>
	<!--Import Google Icon Font-->
	<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
	<!--Import materialize.css-->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
	<!-- Compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">

    <!-- Compiled and minified JavaScript -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
    <link href="https://cdn.datatables.net/v/dt/dt-1.13.8/b-2.4.2/b-colvis-2.4.2/b-html5-2.4.2/fc-4.3.0/fh-3.4.0/sc-2.3.0/datatables.min.css" rel="stylesheet">
<script src="https://cdn.datatables.net/v/dt/dt-1.13.8/b-2.4.2/b-colvis-2.4.2/b-html5-2.4.2/fc-4.3.0/fh-3.4.0/sc-2.3.0/datatables.min.js"></script>
    
    <link type="text/css" rel="stylesheet" href="css/style.css" />
    
	
	<!--Let browser know website is optimized for mobile-->
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="shortcut icon" href="img/favicon.png" />
	
	<title>COMPARES</title>
</head>


<%-- Con el siguiente código conseguimos que el contexto de la aplicación quede
disponible tanto dentro de los ficheros .js (primera línea) como dentro de los
ficheros .jsp (segunda línea) --%>
<script>
	var context = "${pageContext.request.contextPath}";
    <c:set var="context" value="${pageContext.request.contextPath}" />
</script>