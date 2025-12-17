<%@ include file="../views/html/include.jsp"%>
<%@ tag body-content="empty"%>
<%@ attribute name="indexType" required="true" type="java.lang.String"%>
<%@ attribute name="indexValue" required="true" type="java.lang.Double"%>


<c:set var="levelOA" value="
    ${indexValue == 0 ? 9 :
      indexValue <= 0.125 ? 8 :
      indexValue <= 0.25 ? 7 :
      indexValue <= 0.375 ? 6 :
      indexValue <= 0.50 ? 5 :
      indexValue <= 0.625 ? 4 :
      indexValue <= 0.75 ? 3 :
      indexValue <= 0.875 ? 2 :
      indexValue <= 0.99 ? 1 :
      0}" />


<c:set var="levelGSF" value="
    ${indexValue == 0 ? 11 :
      indexValue <= 0.10 ? 10 :
      indexValue <= 0.20 ? 9 :
      indexValue <= 0.30 ? 8 :
      indexValue <= 0.40 ? 7 :
      indexValue <= 0.50 ? 6 :
      indexValue <= 0.60 ? 5 :
      indexValue <= 0.70 ? 4 :
      indexValue <= 0.80 ? 3 :
      indexValue <= 0.90 ? 2 :
      indexValue <= 0.99 ? 1 :
      0}" />

<c:if test="${indexType == 'order'}">
    <c:set var="label"
           value="${indexValue == 0 ? 'Identical order' :
                  indexValue <= 0.125 ? 'Very low order difference' :
                  indexValue <= 0.25 ? 'Low order difference' :
                  indexValue <= 0.375 ? 'Moderate-low order difference' :
                  indexValue <= 0.50 ? 'Moderate order difference' :
                  indexValue <= 0.625 ? 'Moderate-high order difference' :
                  indexValue <= 0.75 ? 'High order difference' :
                  indexValue <= 0.875 ? 'Very high order difference' :
                  indexValue <= 0.99 ? 'Extremely high order difference' :
                  'Completely different order'}" />
</c:if>


<c:if test="${indexType == 'absence'}">
    <c:set var="label"
           value="${indexValue == 0 ? 'No absence' :
                  indexValue <= 0.125 ? 'Minimal absence' :
                  indexValue <= 0.25 ? 'Very low absence' :
                  indexValue <= 0.375 ? 'Low absence' :
                  indexValue <= 0.50 ? 'Moderate-low absence' :
                  indexValue <= 0.625 ? 'Moderate absence' :
                  indexValue <= 0.75 ? 'Moderate-high absence' :
                  indexValue <= 0.875 ? 'High absence' :
                  indexValue <= 0.99 ? 'Very high absence' :
                  'None of the elements match'}" />
</c:if>


<c:if test="${indexType == 'combined' or indexType == 'GSFn'}">
    <c:set var="label"
           value="${indexValue == 0 ? 'Perfect similarity' :
                  indexValue <= 0.10 ? 'Extremely strong similarity' :
                  indexValue <= 0.20 ? 'Very strong similarity' :
                  indexValue <= 0.30 ? 'Strong similarity' :
                  indexValue <= 0.40 ? 'Moderately strong similarity' :
                  indexValue <= 0.50 ? 'Moderate similarity' :
                  indexValue <= 0.60 ? 'Moderately weak similarity' :
                  indexValue <= 0.70 ? 'Weak similarity' :
                  indexValue <= 0.80 ? 'Very weak similarity / high dissimilarity' :
                  indexValue <= 0.90 ? 'Minimal similarity' :
                  indexValue <= 0.99 ? 'Extremely weak similarity' :
                  'No similarity'}" />
</c:if>

<%--
<i>${label}</i>
 --%>

<c:if test="${indexType == 'order' or indexType == 'absence'}">
    <progress-steps segments="9" value="${levelOA}" label=""></progress-steps>
</c:if>

<c:if test="${indexType == 'combined' or indexType == 'GSFn'}">
    <progress-steps segments="11" value="${levelGSF}" label=""></progress-steps>
</c:if>