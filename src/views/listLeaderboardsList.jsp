<%--
File: listLeaderboardsList.jsp
Author: Joshua Forester
Date: 2009/09/25
Description: Default page.
--%>


<%@ taglib prefix="c" uri="/tags/jstl-c" %>
<%@ taglib prefix="spring" uri="/tags/spring" %>
<%@ taglib prefix="tiles" uri="/tags/tiles-jsp" %>
<%@ taglib prefix="log" uri="/tags/taglibs-log" %>

<%@ page import="com.myrunning.leaderboard.model.Team" %>
<%@ page import="com.myrunning.leaderboard.model.ControlPoint" %>
<%@ page import="com.myrunning.leaderboard.model.CpVisit" %>


<div class="rdb_panel">

<h3>Leaderboard - List</h3>

<c:forEach var="leaderboard" items="${leaderboards}">

${leaderboard.course.name}<br>
<table border="1" cellspacing="0" cellpadding="0">
<th>
<td>Rank</td>
<td>Division Rank</td>
<td>Last CP</td>
<td>Arrival</td>
<td>Departure</td>
<td>Status</td>
</th>
<c:forEach var="team" items="${leaderboard.teamList}" varStatus="rowCounter">
<c:choose>
  <c:when test="${rowCounter.count % 2 == 0}">
    <c:set var="rowStyle" scope="page" value="odd" />
  </c:when>
  <c:otherwise>
    <c:set var="rowStyle" scope="page" value="even"/>
  </c:otherwise>
</c:choose>
<tr class="${rowStyle}">
<td>${team.number}:<a href="<c:url value="listTeams.htm?team_id=${team.id}" />">${team.name}</a></td>
<td>${team.coursePlace}</td>
<td>${team.divisionPlace}</td>
<td>${team.lastVisitedControlPoint.cpOrder}</td>
<td>${team.lastVisitedCpArrival}</td>
<td>${team.lastVisitedCpDeparture}</td>
<td>
<c:choose>
  <c:when test="${team.missedCutoff}">Missed Cutoff @ CP ${team.missedCutoffControlPoint.cpOrder}</c:when>
  <c:when test="${team.unofficial}">Unofficial @ CP ${team.unofficialControlPoint.cpOrder}</c:when>
  <c:when test="${team.incomplete}">Incomplete @ CP ${team.incompleteControlPoint.cpOrder}</c:when>
  <c:when test="${team.withdrawn}">Withdrawn @ CP ${team.withdrawnControlPoint.cpOrder}</c:when>
  <c:when test="${team.disqualified}">Disqualified @ CP ${team.disqualifiedControlPoint.cpOrder}</c:when>
  <c:when test="${team.didNotStart}">DNS</c:when>
  <c:otherwise>Official</c:otherwise>
</c:choose>
</td>
</tr>
</c:forEach>
</table>

</c:forEach>

</div>