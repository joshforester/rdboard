<%--
File: listLbdUpdatesVertical.jsp
Author: Joshua Forester
Date: 2010/01/18
Description: The all updates vertical toolbar.
--%>

<%@ taglib prefix="c" uri="/tags/jstl-c" %>
<%@ taglib prefix="spring" uri="/tags/spring" %>
<%@ taglib prefix="rdb" uri="/tags/rdb" %>


<%@ page import="com.myrunning.leaderboard.global.Constants" %>
<%@ page import="com.myrunning.leaderboard.global.TimeConverter" %>


<jsp:scriptlet><![CDATA[
   pageContext.setAttribute("updatesLookback", Constants.UPDATES_LOOKBACK, PageContext.PAGE_SCOPE);   
   pageContext.setAttribute("updatesLookbackMinutes", Constants.UPDATES_LOOKBACK / TimeConverter.MILLIS_IN_MINUTE, PageContext.PAGE_SCOPE);   
   pageContext.setAttribute("updatesInterval", Constants.UPDATES_INTERVAL, PageContext.PAGE_SCOPE);   
   pageContext.setAttribute("updatesTimeout", Constants.UPDATES_TIMEOUT, PageContext.PAGE_SCOPE);   
]]></jsp:scriptlet>


<spring:message var="loadingImg" code="images.generic.loading" />


<div id="loadingImg" class="jsvar"><img src="<c:url value="${loadingImg}" />"></div>
<div id="updateUrl" class="jsvar"><c:url value="${updateUrl}" /></div>
<div id="updatesLookback" class="jsvar">${updatesLookback}</div>
<div id="updatesInterval" class="jsvar">${updatesInterval}</div>
<div id="updatesTimeout" class="jsvar">${updatesTimeout}</div>


<div id="updates${layout}" class="rdb_panel">

<div id="focal">


<div id="loading_updates"></div>
<h3><a href="<c:url value="${updateUrl}" />">Leaderboard Updates</a></h3>

<c:choose>
<c:when test="${empty allLbdUpdates}">
<p id="updates_header" style="display:none;">
</c:when>
<c:otherwise>
<p id="updates_header">
</c:otherwise>
</c:choose>
<span class="updates_subheader">Time</span> | <span class="updates_subheader">Type</span> | <span class="updates_subheader">Message</span>
</p>

<p id="updates_ctn">
<c:choose>

<c:when test="${empty allLbdUpdates}">
<span id="no_updates" class="attval">No updates within the last ${updatesLookbackMinutes} minutes.</span>
</c:when>

<c:otherwise>
<c:forEach var="lbdUpdate" items="${allLbdUpdates}">
<span class="attval"><span class="att">${rdb:fmtLclTs(lbdUpdate.time,'HH:mm')}</span> <span class="att">${lbdUpdate.type}</span> ${lbdUpdate.message}</span>
</c:forEach>
<span id="no_updates" class="attval" style="display:none;">No updates within the last ${updatesLookbackMinutes} minutes.</span>
</c:otherwise>

</c:choose>
</p>

</div>


</div>