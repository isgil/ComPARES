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
			<th class="top-id">GSF-n Index</th>
			<th class="top-id">Combined Index</th>
			<th class="top-id">Order Index</th>
			<th class="top-id">Absence Index</th>
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
					<fmt:formatNumber value="${parResult.GSFnIndex}" type="number" maxFractionDigits="2" />
					<br/>
					<tag:IndexInterpretation indexType="GSFn" indexValue="${parResult.GSFnIndex}" />
				</td>
				<td>
					<fmt:formatNumber value="${parResult.combinedIndex}" type="number" maxFractionDigits="2" />
					<br/>
					<tag:IndexInterpretation indexType="combined" indexValue="${parResult.combinedIndex}" />
				</td>
				<td>
					<fmt:formatNumber value="${parResult.orderIndex}" type="number" maxFractionDigits="2" />
					<br/>
					<tag:IndexInterpretation indexType="order" indexValue="${parResult.orderIndex}" />
				</td>	
				<td>
					<fmt:formatNumber value="${parResult.absenceIndex}" type="number" maxFractionDigits="2" />
					<br/>
					<tag:IndexInterpretation indexType="absence" indexValue="${parResult.absenceIndex}" />
				</td>
			</tr>
			<c:set var="nPar" value="${nPar+1}"/>
		</c:forEach>
		<c:if test="${testResult.getParResults().size() > 1}">
			<tr>
				<c:set var="GSFnIndexMean" value="${testResult.getCombinedIndexMean()}" />
				<c:set var="combinedIndexMean" value="${testResult.getCombinedIndexMean()}" />
				<c:set var="orderIndexMean" value="${testResult.getOrderIndexMean()}" />
				<c:set var="absenceIndexMean" value="${testResult.getAbsenceIndexMean()}" />
				<td><b>Mean</b></td>
				<td>
					<fmt:formatNumber value="${GSFnIndexMean}" type="number" maxFractionDigits="2" />
					<br/>
					<tag:IndexInterpretation indexType="GSFn" indexValue="${GSFnIndexMean}" />
				</td>
				<td>
					<fmt:formatNumber value="${combinedIndexMean}" type="number" maxFractionDigits="2" />
					<br/>
					<tag:IndexInterpretation indexType="combined" indexValue="${combinedIndexMean}" />
				</td>
				<td>
					<fmt:formatNumber value="${orderIndexMean}" type="number" maxFractionDigits="2" />
					<br/>
					<tag:IndexInterpretation indexType="order" indexValue="${orderIndexMean}" />
				</td>
				<td>
					<fmt:formatNumber value="${absenceIndexMean}" type="number" maxFractionDigits="2" />
					<br/>
					<tag:IndexInterpretation indexType="absence" indexValue="${absenceIndexMean}" />
				</td>
			</tr>
		</c:if>
	</tbody>
</table>