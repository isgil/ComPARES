<%@ include file="../views/html/include.jsp"%>
<%@ tag body-content="empty"%>
<%@ attribute name="id" required="true" type="java.lang.Integer"%>
<%@ attribute name="testResult" required="true" type="es.um.fcd.web.model.TestResult"%>
<%@ attribute name="maxNumberOfTops" required="true" type="java.lang.Integer"%>
<%@ attribute name="maxTopResults" required="true" type="java.util.LinkedHashMap"%>

<table id="${id}" class="results-table highlight centered">
	<thead>
		<tr>
			<th>Par</th>
			<c:choose>
				<c:when test="${maxNumberOfTops == 0}">
					<th data-a-h="center">Tops</th>
				</c:when>
				<c:otherwise>
					<c:forEach var="topResults" items="${maxTopResults}">
						<c:set var="top" value="${topResults.key}"/>
						<th data-a-h="center">TOP ${top}</th>
					</c:forEach>
					<th data-a-h="center">Mean</th>
				</c:otherwise>
			</c:choose>
		</tr>
	</thead>
	<tbody>
		<c:set var="nPar" value="1"/>
		<c:forEach var="parResult" items="${testResult.parResults}">
			<tr>
				<c:set var="par" value="${parResult.par}"/>
				<td data-a-h="center" title="${par.testFileSource1.fileName} || ${par.testFileSource2.fileName}"><div class="chip blue lighten-4">Par ${nPar}</div></td>
				<c:set var="topResults" value="${parResult.topResults}" />
				<c:set var="numberOfTops" value="${fn:length(topResults)}"/>
				<c:choose>
					<c:when test="${maxNumberOfTops == 0}">
						<td data-a-h="center">No results available</td>
					</c:when>
					<c:otherwise>
						<c:forEach var="topResult" items="${topResults}">
							<c:set var="topValue" value="${topResult.value}%"/>
							<td data-a-h="center">${topValue}</td>
						</c:forEach>
						<c:forEach begin="${numberOfTops+1}" end="${maxNumberOfTops}" var="t">
							<td data-a-h="center">-</td>
							<c:set var="t" value="${t+1}"/>
						</c:forEach>
						<td data-a-h="center"><fmt:formatNumber type="number" maxFractionDigits="2" value="${parResult.mean}"/>%</td>
					</c:otherwise>
				</c:choose>
			</tr>
			<c:set var="nPar" value="${nPar+1}"/>
		</c:forEach>
	</tbody>
</table>