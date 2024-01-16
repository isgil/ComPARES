<%@ include file="../views/html/include.jsp"%>
<%@ tag body-content="empty"%>
<%@ attribute name="id" required="true" type="java.lang.String"%>
<%@ attribute name="sources" required="true" type="java.util.List"%>

<select id="${id}" name="${id}" required>
	<option value="default" disabled selected>Source</option>
	<option value="new_source">&#10133; New Source ...</option>
	<c:forEach items="${sources}" var="source">
		<option value="${source.id}">${source.name}</option>
	</c:forEach>
</select>