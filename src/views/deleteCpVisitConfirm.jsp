<%--
File: deleteCpVisitConfirm.jsp
Author: Joshua Forester
Date: 2009/08/25
Description: Delete cpVisit confirm page.
--%>



<%@ taglib prefix="c" uri="/tags/jstl-c" %>
<%@ taglib prefix="form" uri="/tags/spring-form" %>


<div class="rdb_panel">

Are you sure you want to delete the following CpVisit?
<table>
<tr>
<td>Team ID/CP ID</td>
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
<td>Skipped?</td>
<td>Missed Cutoff?</td>
<td>Unofficial?</td>
<td>Incomplete?</td>
<td>Withdrawn?</td>
<td>Disqualified?</td>
</tr>
<tr>
<td>${cpVisit.teamId}/${cpVisit.cpId}</td>
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
<td>${cpVisit.isSkipped}</td>
<td>${cpVisit.isMissedCutoff}</td>
<td>${cpVisit.isUnofficial}</td>
<td>${cpVisit.isIncomplete}</td>
<td>${cpVisit.isWithdrawn}</td>
<td>${cpVisit.isDisqualified}</td>
</tr>
</table>
<table>
<tr>
<td>
<form:form commandName="cpVisit" action="deleteCpVisitConfirmed.htm" method="post">
<input id="teamId" name="teamId" type="hidden" value="${cpVisit.teamId}" />
<input id="cpId" name="cpId" type="hidden" value="${cpVisit.cpId}" />
<input type="submit" value="Confirm Delete"></input>
</form:form>
</td>
<%-- currently returns to team CPVisit list but not to CP CPVisit list --%>
<td>
<form:form action="listCpVisits.htm" method="get">
<input id="team_id" name="team_id" type="hidden" value="${cpVisit.teamId}" />
<input type="submit" value="Cancel"></input>
</form:form>
</td>
</tr>
</table>

</div>