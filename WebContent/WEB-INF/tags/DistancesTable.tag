<%@ include file="../views/html/include.jsp"%>
<%@ tag body-content="empty"%>
<%@ attribute name="par" required="true" type="es.um.fcd.model.Par"%>
<%@ attribute name="source1" required="true" type="java.lang.String"%>
<%@ attribute name="source2" required="true" type="java.lang.String"%>

<table class="distances-table highlight centered">
	<thead>
		<tr class="top-header">
			<th class="top-id">Title</th>
			<th class="top-id">Pos. ${source1}</th>
			<th class="top-id">Pos. ${source2}</th>
			<th class="top-id">Distance</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="title" items="${par.getTitles()}">
			<c:set var="pos1" value="${title.positionSource1}" />
			<c:set var="pos2" value="${title.positionSource2}" />
			<c:set var="distance" value="${title.getDistance()}" />
			<tr <c:if test="${pos1 == -1 || pos2 == -1}"> class="missing"</c:if><c:if test="${distance == 0}"> class="exact"</c:if>>
				<td>${title.title}</td>
				<td><c:choose><c:when test="${pos1 != -1}">${pos1}</c:when><c:otherwise><span style="display: none">1000000</span></c:otherwise></c:choose></td>
				<td><c:choose><c:when test="${pos2 != -1}">${pos2}</c:when><c:otherwise><span style="display: none">1000000</span></c:otherwise></c:choose></td>
				<td><c:choose><c:when test="${distance != -1}">${distance}</c:when><c:otherwise><span style="display: none">1000000</span></c:otherwise></c:choose></td>
			</tr>
		</c:forEach>
	</tbody>
</table>