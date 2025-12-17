<%@ include file="../views/html/include.jsp"%>
<%@ tag body-content="empty"%>
<%@ attribute name="id" required="true" type="java.lang.String"%>
<%@ attribute name="source1" required="true" type="java.lang.String"%>
<%@ attribute name="source2" required="true" type="java.lang.String"%>
<%@ attribute name="testResult" required="true" type="es.um.fcd.web.model.TestResult"%>

<c:forEach var="parTopResult" items="${testResult.parResults}">
<div class="par-result-content">
<c:set var="par" value="${parTopResult.par}"/>
<c:set var="numTitlesSource1" value="${parTopResult.par.getNumTitlesSource1()}"/>
<c:set var="numTitlesSource2" value="${parTopResult.par.getNumTitlesSource2()}"/>
<br/>
<div class="pares" id="${par.id}-${source1}-${source2}">
	<div class="chip blue lighten-4">${par.testFileSource1.fileName} (${numTitlesSource1} docs)</div>
	<div class="chip blue lighten-4">${par.testFileSource2.fileName} (${numTitlesSource2} docs)</div>
</div>
				
<table id="T${id}-P${parTopResult.par.id}" class="basic-results-table highlight centered">
	<thead>
		<tr class="top-header">
			<th class="top-id"></th>
			<c:set var="keys" value="${parTopResult.topResults.keySet()}" />
			<c:set var="values" value="${parTopResult.topResults.values()}" />
			<c:set var="numTopResults" value="${fn:length(keys)}" />

			<!-- Pintar el resto de elementos en orden, excepto el primero -->
			<c:forEach var="top" items="${keys}" varStatus="status">
        		<th class="top-id">Top ${top}</th>
			</c:forEach>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td class="top-id">GSF-n Index</td>
			<c:forEach var="topResult" items="${parTopResult.topResults.values()}">
				<td>
					<fmt:formatNumber value="${topResult.GSFnIndex}" type="number" maxFractionDigits="2" />
					<br/>
					<tag:IndexInterpretation indexType="GSFn" indexValue="${topResult.GSFnIndex}" />
				</td>
			</c:forEach>
		</tr>
		<tr>
			<td class="top-id">Combined Index</td>
			<c:forEach var="topResult" items="${parTopResult.topResults.values()}">
				<td>
					<fmt:formatNumber value="${topResult.combinedIndex}" type="number" maxFractionDigits="2" />
					<br/>
					<tag:IndexInterpretation indexType="combined" indexValue="${topResult.combinedIndex}" />
				</td>
			</c:forEach>
		</tr>
		<tr>
			<td class="top-id">Order Index</td>
			<c:forEach var="topResult" items="${parTopResult.topResults.values()}">
				<td>
					<fmt:formatNumber value="${topResult.orderIndex}" type="number" maxFractionDigits="2" />
					<br/>
					<tag:IndexInterpretation indexType="order" indexValue="${topResult.orderIndex}" />
				</td>
			</c:forEach>
		</tr>
		<tr>
			<td class="top-id">Absence Index</td>
			<c:forEach var="topResult" items="${parTopResult.topResults.values()}">
				<td>
					<fmt:formatNumber value="${topResult.absenceIndex}" type="number" maxFractionDigits="2" />
					<br/>
					<tag:IndexInterpretation indexType="absence" indexValue="${topResult.absenceIndex}" />
				</td>
			</c:forEach>
		</tr>
	</tbody>
</table>
</div>
</c:forEach>