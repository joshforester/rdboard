<%--
File: editCpVisitBatchFormMobile.jsp
Author: Joshua Forester
Date: 2009/09/18
Description: Edit cpVisitBatch page, plain jane html form.
--%>



<%@ taglib prefix="c" uri="/tags/jstl-c" %>
<%@ taglib prefix="form" uri="/tags/spring-form" %>
<%@ taglib prefix="tiles" uri="/tags/tiles-jsp" %>


<tiles:importAttribute name="pageTitle" />
<tiles:importAttribute name="submitUrl" />

<div class="rdb_panel">

<%--

Can't figure out how to based on the parameters, get the
team and cp attributes in which I'm interested.  For now, 
set the disabled="true" on them, but need prettier long term
solution, which involves getting them using the id.

<c:choose>
<c:when test="${pageScope.cpId != 0}">
<c:forEach var="controlPoint" items="${controlPoints}">
<c:if test='${controlPoint.id == cpId}'>
<c:set var="cpOrder" scope="page" value="${controlPoint.cpOrder}" />
<c:set var="name" scope="page" value="${controlPoint.name}" />
<c:set var="taName" scope="page" value="${controlPoint.taName}" />
</c:if>
</c:forEach>
<b>cp_id</b><br>
</c:when>

<c:when test="${pageScope.teamId != 0}">
<c:forEach var="team" items="${teams}">
<c:if test='${team.id == teamId}'>
<c:set var="teamName" scope="page" value="${team.name}" />
</c:if>
</c:forEach>
<b>team_id</b><br>
</c:when>

<c:otherwise>

<b>otherwise</b><br>
</c:otherwise>
</c:choose>

--%>




${pageTitle}:<br/>
<form:errors path="cpVisitBatch" /><br/>
<c:url var="fullSubmitUrl" value="${submitUrl}" />
<form:form commandName="cpVisitBatch" action="${fullSubmitUrl}?cp_id=${cpId}&team_id=${teamId}" method="post">

<c:forEach var="cpVisit" varStatus="iter" items="${cpVisitBatch.batch}">

<table border="1">
<tr>
<td>Team:</td>
<td>
<form:hidden path="batch[${iter.index}].teamId" />
<form:select path="batch[${iter.index}].teamId" disabled="true">
<c:forEach var="team" items="${teams}">
<form:option value="${team.id}" label="${team.number}: ${team.name}" />
</c:forEach>
</form:select>
<form:errors path="batch[${iter.index}].teamId" />
</td>
</tr>
<tr>
<td>CP:</td>
<td>
<form:hidden path="batch[${iter.index}].cpId" />
<form:select path="batch[${iter.index}].cpId" disabled="true">
<c:forEach var="controlPoint" items="${controlPoints}">
<form:option value="${controlPoint.id}" label="${controlPoint.cpOrder}: ${controlPoint.name}/${controlPoint.taName}" />
</c:forEach>
</form:select>
<form:errors path="batch[${iter.index}].cpId" />
</td>
</tr>
<tr>
<td>Arrival:</td>
<td><form:input path="batch[${iter.index}].arrival" /><form:errors path="batch[${iter.index}].arrival" /></td>
</tr>
<tr>
<td>Departure:</td>
<td><form:input path="batch[${iter.index}].departure" /><form:errors path="batch[${iter.index}].departure" /></td>
</tr>
<tr>
<td>Time Bonus Assessed:</td>
<td><form:input path="batch[${iter.index}].timeBonusAssessed" /><form:errors path="batch[${iter.index}].timeBonusAssessed" /></td>
</tr>
<tr>
<td>Time Penalty Assessed:</td>
<td><form:input path="batch[${iter.index}].timePenaltyAssessed" /><form:errors path="batch[${iter.index}].timePenaltyAssessed" /></td>
</tr>
<tr>
<td>CP Bonus Assessed:</td>
<td><form:input path="batch[${iter.index}].cpBonusAssessed" /><form:errors path="batch[${iter.index}].cpBonusAssessed" /></td>
</tr>
<tr>
<td>CP Penalty Assessed:</td>
<td><form:input path="batch[${iter.index}].cpPenaltyAssessed" /><form:errors path="batch[${iter.index}].cpPenaltyAssessed" /></td>
</tr>
<tr>
<td>Weight Bonus Assessed:</td>
<td><form:input path="batch[${iter.index}].weightBonusAssessed" /><form:errors path="batch[${iter.index}].weightBonusAssessed" /></td>
</tr>
<tr>
<td>Weight Penalty Assessed:</td>
<td><form:input path="batch[${iter.index}].weightPenaltyAssessed" /><form:errors path="batch[${iter.index}].weightPenaltyAssessed" /></td>
</tr>
<tr>
<td>Bonus Assessed Reason:</td>
<td><form:input path="batch[${iter.index}].bonusAssessedReason" /><form:errors path="batch[${iter.index}].bonusAssessedReason" /></td>
</tr>
<tr>
<td>Penalty Assessed Reason:</td>
<td><form:input path="batch[${iter.index}].penaltyAssessedReason" /><form:errors path="batch[${iter.index}].penaltyAssessedReason" /></td>
</tr>
<tr>
<td>Team Acquired CP?:</td>
<td>
<form:checkbox path="batch[${iter.index}].isAcquired" value="yes" /><form:errors path="batch[${iter.index}].isAcquired" />
</td>
</tr>
<tr>
<td>Team Skipped CP?:</td>
<td>
<form:checkbox path="batch[${iter.index}].isSkipped" value="yes" /><form:errors path="batch[${iter.index}].isSkipped" />
</td>
</tr>
<tr>
<td>Unofficial Team?:</td>
<td>
<form:checkbox path="batch[${iter.index}].isUnofficial" value="yes" /><form:errors path="batch[${iter.index}].isUnofficial" />
</td>
</tr>
<tr>
<td>Incomplete Team?:</td>
<td>
<form:checkbox path="batch[${iter.index}].isIncomplete" value="yes" /><form:errors path="batch[${iter.index}].isIncomplete" />
</td>
</tr>
<tr>
<td>Withdrawn Team?:</td>
<td>
<form:checkbox path="batch[${iter.index}].isWithdrawn" value="yes" /><form:errors path="batch[${iter.index}].isWithdrawn" />
</td>
</tr>
<tr>
<td>Disqualified Team?:</td>
<td>
<form:checkbox path="batch[${iter.index}].isDisqualified" value="yes" /><form:errors path="batch[${iter.index}].isDisqualified" />
</td>
</tr>
</table>

</c:forEach>

<table>
<tr>
<td>&nbsp;</td>
<td><input type="submit" value="${pageTitle}"></input></td>
</tr>
</table>
</form:form>

</div>