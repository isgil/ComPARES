<%@ include file="../views/html/include.jsp"%>
<%@ tag body-content="empty"%>
<%@ attribute name="indexType" required="true" type="java.lang.String"%>
<%@ attribute name="indexValue" required="true" type="java.lang.Double"%>

<c:choose>
	<c:when test="${indexValue == 0}">
		<c:choose>
			<c:when test="${indexType == 'order'}">
				<i>[Identical order]</i>
			</c:when>
			<c:when test="${indexType == 'absence'}">
				<i>[No absence]</i>
			</c:when>
			<c:when test="${indexType == 'combined'}">
				<i>[Perfect correlation, identical lists]</i>
			</c:when>
		</c:choose>
	</c:when>
	<c:when test="${indexValue > 0 && indexValue <= 0.10}">
		<c:choose>
			<c:when test="${indexType == 'order'}">
				<i>[Very low order difference]</i>
			</c:when>
			<c:when test="${indexType == 'absence'}">
				<i>[Very low absence]</i>
			</c:when>
			<c:when test="${indexType == 'combined'}">
				<i>[Very high correlation]</i>
			</c:when>
		</c:choose>
	</c:when>
	<c:when test="${indexValue > 0.10 && indexValue <= 0.20}">
		<c:choose>
			<c:when test="${indexType == 'order'}">
				<i>[Low order difference]</i>
			</c:when>
			<c:when test="${indexType == 'absence'}">
				<i>[Low absence]</i>
			</c:when>
			<c:when test="${indexType == 'combined'}">
				<i>[High correlation]</i>
			</c:when>
		</c:choose>
	</c:when>
	<c:when test="${indexValue > 0.20 && indexValue <= 0.30}">
		<c:choose>
			<c:when test="${indexType == 'order'}">
				<i>[Moderate order difference]</i>
			</c:when>
			<c:when test="${indexType == 'absence'}">
				<i>[Moderate absence]</i>
			</c:when>
			<c:when test="${indexType == 'combined'}">
				<i>[Moderate correlation]</i>
			</c:when>
		</c:choose>
	</c:when>
	<c:when test="${indexValue > 0.30 && indexValue <= 0.40}">
		<c:choose>
			<c:when test="${indexType == 'order'}">
				<i>[High order difference]</i>
			</c:when>
			<c:when test="${indexType == 'absence'}">
				<i>[High absence]</i>
			</c:when>
			<c:when test="${indexType == 'combined'}">
				<i>[Low correlation]</i>
			</c:when>
		</c:choose>
	</c:when>
	<c:when test="${indexValue > 0.40 && indexValue <= 0.50}">
		<c:choose>
			<c:when test="${indexType == 'order'}">
				<i>[Very high order difference]</i>
			</c:when>
			<c:when test="${indexType == 'absence'}">
				<i>[Very high absence]</i>
			</c:when>
			<c:when test="${indexType == 'combined'}">
				<i>[Very low correlation]</i>
			</c:when>
		</c:choose>
	</c:when>
	<c:when test="${indexValue > 0.50 && indexValue <= 0.60}">
		<c:choose>
			<c:when test="${indexType == 'order'}">
				<i>[Extremely high order difference]</i>
			</c:when>
			<c:when test="${indexType == 'absence'}">
				<i>[Extremely high absence]</i>
			</c:when>
			<c:when test="${indexType == 'combined'}">
				<i>[Extremely low correlation]</i>
			</c:when>
		</c:choose>
	</c:when>
	<c:when test="${indexValue > 0.60 && indexValue <= 0.70}">
		<c:choose>
			<c:when test="${indexType == 'order'}">
				<i>[Near-random order]</i>
			</c:when>
			<c:when test="${indexType == 'absence'}">
				<i>[Near-complete absence]</i>
			</c:when>
			<c:when test="${indexType == 'combined'}">
				<i>[Near-zero correlation]</i>
			</c:when>
		</c:choose>
	</c:when>
	<c:when test="${indexValue > 0.70 && indexValue <= 0.99}">
		<c:choose>
			<c:when test="${indexType == 'order'}">
				<i>[Almost random order]</i>
			</c:when>
			<c:when test="${indexType == 'absence'}">
				<i>[Almost total absence]</i>
			</c:when>
			<c:when test="${indexType == 'combined'}">
				<i>[No correlation]</i>
			</c:when>
		</c:choose>
	</c:when>
	<c:when test="${indexValue == 1}">
		<c:choose>
			<c:when test="${indexType == 'order'}">
				<i>[Completely random order]</i>
			</c:when>
			<c:when test="${indexType == 'absence'}">
				<i>[Total absence]</i>
			</c:when>
			<c:when test="${indexType == 'combined'}">
				<i>[Completely absent correlation]</i>
			</c:when>
		</c:choose>
	</c:when>
</c:choose>