<%@ include file="../views/html/include.jsp"%>
<%@ tag body-content="empty"%>
<%@ attribute name="id" required="true" type="java.lang.String"%>
<%@ attribute name="testResult" required="true" type="es.um.fcd.web.model.TestResult"%>
<%@ attribute name="maxNumberOfTops" required="true" type="java.lang.Integer"%>
<%@ attribute name="maxTopResults" required="true" type="java.util.LinkedHashMap"%>

<table id="${id}" class="basic-results-table highlight centered">
	<thead>
		<tr class="top-header">
			<th class="top-id">Par</th>
			<c:choose>
				<c:when test="${maxNumberOfTops == 0}">
					<th>Tops</th>
				</c:when>
				<c:otherwise>
					<c:forEach var="topResults" items="${maxTopResults}">
						<c:set var="top" value="${topResults.key}"/>
						<th class="top-id">TOP ${top}</th>
					</c:forEach>
					<th class="top-id">Mean</th>
				</c:otherwise>
			</c:choose>
		</tr>
	</thead>
	<tbody>
		<c:set var="nPar" value="1"/>
		<c:forEach var="parResult" items="${testResult.parResults}">
			<tr>
				<c:set var="par" value="${parResult.par}"/>
				<c:set var="numTitlesSource1" value="${parResult.par.getNumTitlesSource1()}"/>
				<c:set var="numTitlesSource2" value="${parResult.par.getNumTitlesSource2()}"/>
				<td title="${par.testFileSource1.fileName} (${numTitlesSource1} docs) || ${par.testFileSource2.fileName} (${numTitlesSource2} docs)"><div class="chip blue lighten-4">Par ${nPar}</div></td>
				<c:set var="topResults" value="${parResult.topResults}" />
				<c:set var="numberOfTops" value="${fn:length(topResults)}"/>
				<c:choose>
					<c:when test="${maxNumberOfTops == 0}">
						<td>No results available</td>
					</c:when>
					<c:otherwise>
						<c:forEach var="topResult" items="${topResults}">
							<c:set var="topValue" value="${topResult.value}"/>
							<td><fmt:formatNumber value="${topValue}" pattern="#.##" />%</td>
						</c:forEach>
						<c:forEach begin="${numberOfTops+1}" end="${maxNumberOfTops}" var="t">
							<td>-</td>
							<c:set var="t" value="${t+1}"/>
						</c:forEach>
						<td><fmt:formatNumber type="number" maxFractionDigits="2" value="${parResult.mean}"/>%</td>
					</c:otherwise>
				</c:choose>
			</tr>
			<c:set var="nPar" value="${nPar+1}"/>
		</c:forEach>
	</tbody>
</table>