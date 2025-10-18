<%@ include file="../views/html/include.jsp"%>
<%@ tag body-content="empty"%>
<%@ attribute name="par" required="true" type="es.um.fcd.model.Par"%>
<%@ attribute name="source1" required="true" type="java.lang.String"%>
<%@ attribute name="source2" required="true" type="java.lang.String"%>

<table id="common-elements-table" class="highlight centered">
	<thead>
		<tr class="top-header">
			<th class="top-id">Common Titles</th>
			<th class="top-id">Non-common Titles</th>
			<th class="top-id">Titles only in ${source1}</th>
			<th class="top-id">Titles only in ${source2}</th>
		</tr>
	</thead>
	<tbody>
		<c:set var="commonTitles" value="${par.getNumCommonTitles()}" />
		<c:set var="distinctTitles" value="${par.getNumDistinctTitles()}" />
		<c:set var="titlesOnlyInSource1" value="${par.getNumTitlesOnlyInSource1()}" />
		<c:set var="titlesOnlyInSource2" value="${par.getNumTitlesOnlyInSource2()}" />
		<tr>
			<td>${commonTitles}</td>
			<td>${distinctTitles}</td>
			<td>${titlesOnlyInSource1}</td>
			<td>${titlesOnlyInSource2}</td>
		</tr>
	</tbody>
</table>