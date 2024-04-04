<%@ include file="../views/html/include.jsp"%>
<%@ tag body-content="empty"%>
<%@ attribute name="id" required="true" type="java.lang.String"%>
<%@ attribute name="testResult" required="true" type="es.um.fcd.web.model.TestResult"%>

<table id="${id}" class="advanced-results-table dataTable highlight centered">
	<tbody>
		<c:forEach var="topResult" items="${testResult.topResults}">
			<tr>
				<td colspan="4" class="top-id">TOP ${topResult.top}</td>
			</tr>
			<tr>
				<td class="top-header">Par</td>
				<td class="top-header">Matching</td>
				<td class="top-header">Same position</td>
				<td class="top-header">Algorithmic proximity</td>
			</tr>
			<c:set var="advancedParResults" value="${topResult.advancedParResults}" />
			<c:set var="numberOfResults" value="${fn:length(advancedParResults)}"/>
			<c:choose>
				<c:when test="${maxNumberOfTops == 0}">
					<tr>
						<td>No results available</td>
					</tr>
				</c:when>
				<c:otherwise>
					<c:set var="nPar" value="1"/>
					<c:forEach var="advancedParResult" items="${advancedParResults}">
						<tr>
						<c:set var="par" value="${advancedParResult.par}"/>
						<td title="${par.testFileSource1.fileName} || ${par.testFileSource2.fileName}"><div class="chip blue lighten-4">Par ${nPar}</div></td>
						<td><fmt:formatNumber value="${(advancedParResult.matching / topResult.top) * 100}" pattern="#.##" />%</td>
						<td>${advancedParResult.samePosition}</td>
						<td><fmt:formatNumber value="${advancedParResult.proximity}" pattern="#.##" />%</td>
						<c:set var="nPar" value="${nPar+1}"/>
						</tr>
					</c:forEach>
					<tr>
						<td class="top-header">Mean</td>
						<td></td>
						<td></td>
						<td><fmt:formatNumber value="${topResult.mean}" pattern="#.##" />%</td>
					</tr>
				</c:otherwise>
			</c:choose>
		</c:forEach>
	</tbody>
</table>