<%@ include file="../views/html/include.jsp"%>
<%@ tag body-content="empty"%>
<%@ attribute name="id" required="true" type="java.lang.String"%>
<%@ attribute name="sources" required="true" type="java.util.List"%>

<select id="${id}">
	<option value="0" disabled selected>Source</option>
	<option value="1">&#10133; New Source ...</option>
	<c:set var="i" value="2" />
	<c:forEach items="${sources}" var="source">
		<option value="${i}">${source.name}</option>
	</c:forEach>
</select>