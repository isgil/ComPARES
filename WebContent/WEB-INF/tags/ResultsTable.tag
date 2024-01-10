<%@ include file="../views/html/include.jsp"%>
<%@ tag body-content="empty"%>
<%@ attribute name="id" required="true" type="java.lang.Integer"%>
<%@ attribute name="result" required="true" type="es.um.fcd.web.model.TestResults"%>
<%@ attribute name="maxNumberOfTops" required="true" type="java.lang.Integer"%>
<%@ attribute name="maxTopResults" required="true" type="java.util.LinkedHashMap"%>
<%@ attribute name="vertical" required="true" type="java.lang.Boolean"%>

<table id="${id}" class="results-table highlight centered">
	<thead>
		<tr>
			<th></th>
			<c:forEach var="topResults" items="${maxTopResults}">
				<c:set var="top" value="${topResults.key}"/>
				<th>${top}</th>
			</c:forEach>
		</tr>
	</thead>
	<tbody>
		<c:set var="nPar" value="1"/>
		<c:forEach var="parResults" items="${result.testResults}">
			<tr>
				<c:set var="par" value="${parResults.key}"/>
				<!--<td><div class="chip blue lighten-4">${par.fileName1}</div><br/><div class="chip blue lighten-4">${par.fileName2}</div></td>-->
				<td title="${par.fileName1} || ${par.fileName2}"><div class="chip blue lighten-4">Par ${nPar}</div></td>
				<c:set var="numberOfTops" value="${fn:length(parResults.value)}"/>
				<c:forEach var="topResults" items="${parResults.value}">
					<c:set var="topValue" value="${topResults.value}"/>
					<td>${topValue}</td>
				</c:forEach>
				<c:forEach begin="${numberOfTops+1}" end="${maxNumberOfTops}" var="t">
					<td>-</td>
					<c:set var="t" value="${t+1}"/>
				</c:forEach>
			</tr>
			<c:set var="nPar" value="${nPar+1}"/>
		</c:forEach>
	</tbody>
</table>