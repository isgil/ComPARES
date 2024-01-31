<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/js/library.jspf"%>

<body>
	<div id="library" class="content">
		<!-- CONTENT -->
		<div class="row">
			<div id="test-list" class="test-list col s12 m12 l4">
				<c:choose>
					<c:when test="${tests.size() == 0}">
						<div class="item col s12 m12 l12 grey lighten-4 center"><span><b>There are no tests in the system</b></span></div>
					</c:when>
					<c:otherwise>
						<c:forEach items="${tests}" var="test">
							<div class="test row">
								<div class="item col s12 m12 l12 grey lighten-4">
									<input id="check${test.id}" type="checkbox" class="custom-checkbox" value="${test.id}" name="test" /> 
									<label for="check${test.id}" class="custom-checkbox"></label>
									<div class="chip test-name white lighten-4">
									<div class="chip test-id grey lighten-2">${test.id}</div> 
									<c:choose>
										<c:when test="${!empty test.name}">${test.name}</c:when>
										<c:otherwise>[Sin título]</c:otherwise>
									</c:choose>
									</div>
									<div class="sources">
										<div class="chip orange lighten-4">${test.source1.name}</div>
										<div class="chip orange lighten-4">${test.source2.name}</div>
									</div>
								</div>
							</div>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</div>
			<div id="results-wrapper" class="results-wrapper col s12 m12 l8">
				<div id="results" class="item results row center grey lighten-4">
					<b><span>No results to display</span></b>
				</div>
			</div>
		</div>
	</div>
</body>