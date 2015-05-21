<%--
File: listCpVisits.jsp
Author: Joshua Forester
Date: 2009/08/18
Description: List cpVisits page.
--%>



<%@ taglib prefix="c" uri="/tags/jstl-c" %>
<%@ taglib prefix="sec" uri="/tags/security" %>
<%@ taglib prefix="rdb" uri="/tags/rdb" %>


<div class="rdb_panel">

<h3>CP Visits</h3>

<table>
<tr>
<td>Team/CP</td>
<td>Last Modified</td>
<td>Arrival</td>
<td>Departure</td>
<td>Time Bonus Assessed</td>
<td>Time Penalty Assessed</td>
<td>CP Bonus Assessed</td>
<td>CP Penalty Assessed</td>
<td>Weight Bonus Assessed</td>
<td>Weight Penalty Assessed</td>
<td>Bonus Assessed Reason</td>
<td>Penalty Assessed Reason</td>
<td>Acquired?</td>
<td>Skipped?</td>
<td>Missed Cutoff?</td>
<td>Unofficial?</td>
<td>Incomplete?</td>
<td>Withdrawn?</td>
<td>Disqualified?</td>
</tr>
<c:forEach var="cpVisit" items="${cpVisits}">
<tr>
<td>${cpVisit.teamId}/${cpVisit.cpId} <sec:authorize ifAnyGranted="ROLE_ROOT,ROLE_PROMOTER,ROLE_EVENTADMIN">(<a href="<c:url value="/d/editCpVisit.htm?team_id=${cpVisit.teamId}&cp_id=${cpVisit.cpId}" />">Edit</a> | <a href="<c:url value="/d/deleteCpVisit.htm?team_id=${cpVisit.teamId}&cp_id=${cpVisit.cpId}" />">Delete</a>)</sec:authorize></td>
<td>${cpVisit.lastModified}</td>
<td>${cpVisit.arrival}</td>
<td>${cpVisit.departure}</td>
<td>${cpVisit.timeBonusAssessed}</td>
<td>${cpVisit.timePenaltyAssessed}</td>
<td>${cpVisit.cpBonusAssessed}</td>
<td>${cpVisit.cpPenaltyAssessed}</td>
<td>${cpVisit.weightBonusAssessed}</td>
<td>${cpVisit.weightPenaltyAssessed}</td>
<td>${cpVisit.bonusAssessedReason}</td>
<td>${cpVisit.penaltyAssessedReason}</td>
<td>${cpVisit.isAcquired}</td>
<td>${cpVisit.isSkipped}</td>
<td>${cpVisit.isMissedCutoff}</td>
<td>${cpVisit.isUnofficial}</td>
<td>${cpVisit.isIncomplete}</td>
<td>${cpVisit.isWithdrawn}</td>
<td>${cpVisit.isDisqualified}</td>
</tr>
</c:forEach>
</table>
<br>
<br>
<center><sec:authorize ifAnyGranted="ROLE_ROOT,ROLE_PROMOTER,ROLE_EVENTADMIN"><a href="<c:url value="/d/addCpVisit.htm?course_id=${courseId}" />">Add CP Visit</a> |</sec:authorize> <sec:authorize ifAnyGranted="ROLE_ROOT,ROLE_PROMOTER,ROLE_EVENTADMIN"><a href="<c:url value="/d/editCpVisitBatch.htm?cp_id=${cpId}&team_id=${teamId}" />">Edit CP Visit Batch</a> |</sec:authorize> <a href="<c:url value="${rdb:cpvUrl(cpId, teamId, courseId,'order=cporder')}" />">Sort By CP Order</a> | <a href="<c:url value="${rdb:cpvUrl(cpId, teamId, courseId,'order=teamnumber')}" />">Sort By Team Number</a> | <a href="<c:url value="${rdb:cpvUrl(cpId, teamId, courseId,'order=departure')}" />">Sort By Departure</a></center>

</div>