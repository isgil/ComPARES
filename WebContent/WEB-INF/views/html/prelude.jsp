<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" lang="es">
<%@ include file="head.jsp"%>
<body>
	<%@ include file="/js/util.jspf"%>
	<%@ include file="header.jsp"%>
	<div class="background-fixed"></div>
	<%-- El siguiente bloque muestra el progreso de una petición --%>
	<div class="progress">
      <div class="indeterminate"></div>
      <div class="center">Saving...</div>
    </div>

	<%-- Icono para volver a la parte superior de la pantalla. Para que pueda
	activarse en los bloques script de las diferentes páginas, debe crearse
	en el prelude, ya que, si lo pusiéramos en el pie, la parte de los
	scripts se resolvería antes de que en el DOM estuviera insertado el icono --%>
	<div id="scroll" class="bottom-right">
		<span class="icon scroll back2top" onclick="scrollToTopSuavizado()"></span>
		<span class="icon scroll go2bottom" onclick="scrollToBottomSuavizado()"></span>
	</div>

	<%@ include file="notifications.jspf"%>
	<%-- Inicio Contenido --%>