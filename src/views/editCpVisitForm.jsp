<%--
File: editCpVisitForm.jsp
Author: Joshua Forester
Date: 2009/08/25
Description: Edit cpVisit page.
--%>



<%@ taglib prefix="c" uri="/tags/jstl-c" %>
<%@ taglib prefix="form" uri="/tags/spring-form" %>
<%@ taglib prefix="tiles" uri="/tags/tiles-jsp" %>


<tiles:importAttribute name="pageTitle" />
<tiles:importAttribute name="submitUrl" />

<div class="rdb_panel">

${pageTitle}:<br/>
<form:errors path="cpVisit" /><br/>
<c:url var="fullSubmitUrl" value="${submitUrl}" />
<form:form commandName="cpVisit" action="${fullSubmitUrl}?course_id=${courseId}&cp_id=${cpId}&team_id=${teamId}" method="post">
<%--
<c:url var="fullSubmitUrl" value="/d/cpvisits/${cpId}/${teamId}/save.json" />" />
<form:form commandName="cpVisit" action="${fullSubmitUrl}" method="post">
--%>
<table>
<tr>
<td>Team:</td>
<td>
<form:select path="teamId">
<c:forEach var="team" items="${teams}">
<form:option value="${team.id}" label="${team.number}: ${team.name}" />
</c:forEach>
</form:select>
<form:errors path="teamId" />
</td>
</tr>
<tr>
<td>CP:</td>
<td>
<form:select path="cpId">
<c:forEach var="controlPoint" items="${controlPoints}">
<form:option value="${controlPoint.id}" label="${controlPoint.cpOrder}: ${controlPoint.name}/${controlPoint.taName}" />
</c:forEach>
</form:select>
<form:errors path="cpId" />
</td>
</tr>
<tr>
<td>Arrival:</td>
<td><form:input path="arrival" /><form:errors path="arrival" /></td>
</tr>
<tr>
<td>Departure:</td>
<td><form:input path="departure" /><form:errors path="departure" /></td>
</tr>
<tr>
<td>Time Bonus Assessed:</td>
<td><form:input path="timeBonusAssessed" /><form:errors path="timeBonusAssessed" /></td>
</tr>
<tr>
<td>Time Penalty Assessed:</td>
<td><form:input path="timePenaltyAssessed" /><form:errors path="timePenaltyAssessed" /></td>
</tr>
<tr>
<td>CP Bonus Assessed:</td>
<td><form:input path="cpBonusAssessed" /><form:errors path="cpBonusAssessed" /></td>
</tr>
<tr>
<td>CP Penalty Assessed:</td>
<td><form:input path="cpPenaltyAssessed" /><form:errors path="cpPenaltyAssessed" /></td>
</tr>
<tr>
<td>Weight Bonus Assessed:</td>
<td><form:input path="weightBonusAssessed" /><form:errors path="weightBonusAssessed" /></td>
</tr>
<tr>
<td>Weight Penalty Assessed:</td>
<td><form:input path="weightPenaltyAssessed" /><form:errors path="weightPenaltyAssessed" /></td>
</tr>
<tr>
<td>Bonus Assessed Reason:</td>
<td><form:input path="bonusAssessedReason" /><form:errors path="bonusAssessedReason" /></td>
</tr>
<tr>
<td>Penalty Assessed Reason:</td>
<td><form:input path="penaltyAssessedReason" /><form:errors path="penaltyAssessedReason" /></td>
</tr>
<tr>
<td>Team Acquired CP?:</td>
<td>
<form:checkbox path="isAcquired" value="yes" /><form:errors path="isAcquired" />
</td>
</tr>
<tr>
<td>Team Skipped CP?:</td>
<td>
<form:checkbox path="isSkipped" value="yes" /><form:errors path="isSkipped" />
</td>
</tr>
<tr>
<td>Unofficial Team?:</td>
<td>
<form:checkbox path="isUnofficial" value="yes" /><form:errors path="isUnofficial" />
</td>
</tr>
<tr>
<td>Incomplete Team?:</td>
<td>
<form:checkbox path="isIncomplete" value="yes" /><form:errors path="isIncomplete" />
</td>
</tr>
<tr>
<td>Withdrawn Team?:</td>
<td>
<form:checkbox path="isWithdrawn" value="yes" /><form:errors path="isWithdrawn" />
</td>
</tr>
<tr>
<td>Disqualified Team?:</td>
<td>
<form:checkbox path="isDisqualified" value="yes" /><form:errors path="isDisqualified" />
</td>
</tr>
<tr>
<td>&nbsp;</td>
<td><input type="submit" value="${pageTitle}"></input></td>
</tr>
</table>
</form:form>

</div>