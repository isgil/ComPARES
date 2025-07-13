<%@ include file="../views/html/include.jsp"%>
<%@ tag body-content="empty"%>
<%@ attribute name="id" required="true" type="java.lang.String"%>
<%@ attribute name="source1" required="true" type="java.lang.String"%>
<%@ attribute name="source2" required="true" type="java.lang.String"%>
<%@ attribute name="testResult" required="true" type="es.um.fcd.web.model.TestResult"%>

<table id="${id}" class="basic-results-table highlight centered">
	<thead>
		<tr class="top-header">
			<th class="top-id">Par</th>
			<th class="top-id">Order Index</th>
			<th class="top-id">Absence Index</th>
			<th class="top-id">Combined Index</th>
		</tr>
	</thead>
	<tbody>
		<c:set var="nPar" value="1"/>
		<c:forEach var="parResult" items="${testResult.parResults}">
			<tr>
				<c:set var="par" value="${parResult.par}"/>
				<c:set var="numTitlesSource1" value="${parResult.par.getNumTitlesSource1()}"/>
				<c:set var="numTitlesSource2" value="${parResult.par.getNumTitlesSource2()}"/>
				<td>
					<div id="${par.id}-${source1}-${source2}">
						<div class="chip blue lighten-4">${par.testFileSource1.fileName} (${numTitlesSource1} docs)</div><br/>
						<div class="chip blue lighten-4">${par.testFileSource2.fileName} (${numTitlesSource2} docs)</div>
					</div>
				</td>
				<td>
					<fmt:formatNumber value="${parResult.orderIndex}" type="number" maxFractionDigits="4" />
					<br/>
					<c:choose>
						<c:when test="${parResult.orderIndex >= 0 && parResult.orderIndex < 0.05}">
							<i>Almost identical order</i>
						</c:when>
						<c:when test="${parResult.orderIndex >= 0.05 && parResult.orderIndex < 0.10}">
							<i>Small changes to the order</i>
						</c:when>
						<c:when test="${parResult.orderIndex >= 0.10 && parResult.orderIndex < 0.20}">
							<i>Noticeably different order</i>
						</c:when>
						<c:when test="${parResult.orderIndex >= 0.20 && parResult.orderIndex < 0.30}">
							<i>Moderate order changes</i>
						</c:when>
						<c:when test="${parResult.orderIndex >= 0.30 && parResult.orderIndex < 0.50}">
							<i>Strong realignments</i>
						</c:when>
						<c:when test="${parResult.orderIndex >= 0.50}">
							<i>Very different order (almost random)</i>
						</c:when>
					</c:choose>
				</td>	
				<td>
					<fmt:formatNumber value="${parResult.absenceIndex}" type="number" maxFractionDigits="4" />
					<br/>
					<c:choose>
						<c:when test="${parResult.absenceIndex >= 0 && parResult.absenceIndex < 0.05}">
							<i>Almost no absence</i>
						</c:when>
						<c:when test="${parResult.absenceIndex >= 0.05 && parResult.absenceIndex < 0.10}">
							<i>Very low one-off absences</i>
						</c:when>
						<c:when test="${parResult.absenceIndex >= 0.10 && parResult.absenceIndex < 0.20}">
							<i>Low but present absences</i>
						</c:when>
						<c:when test="${parResult.absenceIndex >= 0.20 && parResult.absenceIndex < 0.30}">
							<i>Moderate absences</i>
						</c:when>
						<c:when test="${parResult.absenceIndex >= 0.30 && parResult.absenceIndex < 0.50}">
							<i>High absences</i>
						</c:when>
						<c:when test="${parResult.absenceIndex >= 0.50}">
							<i>Very high absences</i>
						</c:when>
					</c:choose>
				</td>
				<td><fmt:formatNumber value="${parResult.combinedIndex}" type="number" maxFractionDigits="4" /></td>
			</tr>
			<c:set var="nPar" value="${nPar+1}"/>
		</c:forEach>
		<c:if test="${testResult.getParResults().size() > 1}">
			<tr>
				<td><b>Mean</b></td>
				<td><fmt:formatNumber value="${testResult.getOrderIndexMean()}" type="number" maxFractionDigits="4" /></td>
				<td><fmt:formatNumber value="${testResult.getAbsenceIndexMean()}" type="number" maxFractionDigits="4" /></td>
				<td><fmt:formatNumber value="${testResult.getCombinedIndexMean()}" type="number" maxFractionDigits="4" /></td>
			</tr>
		</c:if>
	</tbody>
</table>