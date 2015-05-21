<%--
File: liveCoverageSponsor.jsp
Author: Joshua Forester
Date: 2010/04/30
Description: Live coverage sponsor.
--%>



<%@ taglib prefix="c" uri="/tags/jstl-c" %>
<%@ taglib prefix="spring" uri="/tags/spring" %>
<%@ taglib prefix="sec" uri="/tags/security" %>
<%@ taglib prefix="rdb" uri="/tags/rdb" %>


<c:if test="${not empty event}">
<c:if test="${not empty event.sponsorDescription || event.sponsorUrl != 'default'}">

<div class="rdb_panel">

<h3>Live Coverage Sponsor</h3>

<c:if test="${event.sponsorUrl != 'default'}">
<a <c:if test="${event.sponsorWebsite != 'default'}">target="_blank" </c:if>href="<c:choose><c:when test="${event.sponsorWebsite != 'default'}">${event.sponsorWebsite}</c:when><c:otherwise>javascript:void();</c:otherwise></c:choose>"><img style="border-width: 0px; width: 100%;" src="${event.sponsorUrl}" alt="<c:if test="${event.sponsorTitle != 'default'}">${event.sponsorTitle}</c:if>" title="<c:if test="${event.sponsorCaption != 'default'}">${event.sponsorCaption}</c:if>"></a>
</c:if>

<c:if test="${not empty event.sponsorDescription}">
${event.sponsorDescription}
</c:if>

</div>

</c:if>
</c:if>