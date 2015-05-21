<%--
File: editCpVisitBatchForm.jsp
Author: Joshua Forester
Date: 2009/09/18
Description: Edit cpVisitBatch page.
--%>



<%@ taglib prefix="c" uri="/tags/jstl-c" %>
<%@ taglib prefix="spring" uri="/tags/spring" %>
<%@ taglib prefix="form" uri="/tags/spring-form" %>
<%@ taglib prefix="tiles" uri="/tags/tiles-jsp" %>
<%@ taglib prefix="rdb" uri="/tags/rdb" %>

<%@ page import="javax.servlet.jsp.jstl.core.LoopTagStatus" %>
<%@ page import="org.springframework.validation.Errors" %>


<tiles:importAttribute name="pageTitle" />
<tiles:importAttribute name="submitUrl" />

<div class="rdb_panel">
<div class="clear">

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

<spring:message var="editImage" code="images.generic.edit" />
<spring:message var="downarrowImage" code="images.generic.downarrow" />


<h3 class="ftr">${pageTitle}:</h3>

<spring:hasBindErrors name="cpVisitBatch">
<table width="100%">
<tbody>
<tr>
<td width="1%" align="left">

<c:choose>
<c:when test="${errors.globalErrorCount != 0}">
<rdb:rdberrs path="cpVisitBatch" element="div" cssClass="tooltip" code="images.generic.error.big" />
</td>
<td align=left">
<h4 class="err">You have errors in your input!</h3>
<form:errors path="cpVisitBatch" />
</c:when>
<c:when test="${errors.globalErrorCount == 0}">
<img src="<spring:message code="images.generic.error.big" />" alt="You have errors in your input!" title="You have errors in your input!">
</td>
<td align=left">
<h4 class="err">You have errors in your input!</h3>
</c:when>
</c:choose>

</td>
</tr>
</tbody>
</table>

</spring:hasBindErrors>

<spring:hasBindErrors name="cpVisitBatch">

<script type="text/javascript">
$(document).ready(function(){

<c:forEach var="cpVisit" varStatus="iter" items="${cpVisitBatch.batch}">

<jsp:scriptlet><![CDATA[
   String b = "batch";
   int i = ((LoopTagStatus) pageContext.findAttribute("iter")).getIndex();

   if ((errors.hasFieldErrors(b + "[" + i + "].timeBonusAssessed")) ||
       (errors.hasFieldErrors(b + "[" + i + "].timePenaltyAssessed")) ||
       (errors.hasFieldErrors(b + "[" + i + "].cpBonusAssessed")) ||
       (errors.hasFieldErrors(b + "[" + i + "].cpPenaltyAssessed")) ||
       (errors.hasFieldErrors(b + "[" + i + "].weightBonusAssessed")) ||
       (errors.hasFieldErrors(b + "[" + i + "].weightPenaltyAssessed")) ||
       (errors.hasFieldErrors(b + "[" + i + "].bonusAssessedReason")) ||
       (errors.hasFieldErrors(b + "[" + i + "].penaltyAssessedReason")) ||
       (errors.hasFieldErrors(b + "[" + i + "].isUnofficial")) ||
       (errors.hasFieldErrors(b + "[" + i + "].isIncomplete")) ||
       (errors.hasFieldErrors(b + "[" + i + "].isWithdrawn")) ||
       (errors.hasFieldErrors(b + "[" + i + "].isDisqualified"))) {
       pageContext.setAttribute("hasFieldErrors", 
                                "true", 
                                PageContext.PAGE_SCOPE);   
   } else {
       pageContext.setAttribute("hasFieldErrors", 
                                "false", 
                                PageContext.PAGE_SCOPE);   
   }
]]></jsp:scriptlet>

<c:if test="${ hasFieldErrors }">
  $("div#af${iter.index}").slideToggle(100);
  $("div#at${iter.index}").toggleClass('clicked');
</c:if>

</c:forEach>

});
</script>

</spring:hasBindErrors>


<c:url var="fullSubmitUrl" value="${submitUrl}" />
<form:form commandName="cpVisitBatch" action="${fullSubmitUrl}?cp_id=${cpId}&team_id=${teamId}" method="post">

<div class="form_list">

<c:forEach var="cpVisit" varStatus="iter" items="${cpVisitBatch.batch}">

<div class="basic_form">

<div class="row cpv_row">
<div class="cpv_hdr">
Team/CP:
<form:hidden path="batch[${iter.index}].teamId" /> 
<rdb:rdberrs path="batch[${iter.index}].teamId" element="div" cssClass="tooltip" code="images.generic.error" />
<form:select path="batch[${iter.index}].teamId" id="batch${iter.index}.teamIdSelect" cssErrorClass="fld_err" disabled="true">
<c:forEach var="team" items="${teams}">
<form:option value="${team.id}" label="${team.number}: ${team.name}" />
</c:forEach>
</form:select>
<form:hidden path="batch[${iter.index}].cpId" /> 
<rdb:rdberrs path="batch[${iter.index}].cpId" element="div" cssClass="tooltip" code="images.generic.error" />
<form:select path="batch[${iter.index}].cpId" id="batch${iter.index}.cpIdSelect" cssErrorClass="fld_err" disabled="true">
<c:forEach var="controlPoint" items="${controlPoints}">
<form:option value="${controlPoint.id}" label="${controlPoint.cpOrder}: ${controlPoint.name}/${controlPoint.taName}" />
</c:forEach>
</form:select>
</div>
<div id="at${iter.index}" class="adv_tab">
<div class="buttons">
<img src="<c:url value="${downarrowImage}" />" alt="<spring:message code="images.generic.downarrow.alt.expand" />" title="<spring:message code="images.generic.downarrow.title.expand" />">
Advanced
</div>
</div>
</div>

<div class="row cpv_row">
<div class="qtr_fld out_cb vert_fld">
<rdb:rdberrs path="batch[${iter.index}].isAcquired" element="div" cssClass="tooltip" code="images.generic.error" />
<img src="<spring:message code="images.status.acquired" />" alt="<spring:message code="images.status.acquired.alt" />" title="<spring:message code="images.status.acquired.title" />">
Acquired CP?
<form:checkbox path="batch[${iter.index}].isAcquired" cssErrorClass="fld_err" cssClass="isAcquired" value="yes" />
</div>
<div class="qtr_fld md_ts vert_fld">
<rdb:rdberrs path="batch[${iter.index}].arrival" element="div" cssClass="tooltip" code="images.generic.error" />
Arrival:
<form:input path="batch[${iter.index}].arrival" cssErrorClass="fld_err" size="16" maxlength="16" />
</div>
<div class="qtr_fld md_ts vert_fld">
<rdb:rdberrs path="batch[${iter.index}].departure" element="div" cssClass="tooltip" code="images.generic.error" />
Departure:
<form:input path="batch[${iter.index}].departure" cssErrorClass="fld_err" size="16" maxlength="16" />
</div>
<div class="qtr_fld out_cb vert_fld">
<rdb:rdberrs path="batch[${iter.index}].isSkipped" element="div" cssClass="tooltip" code="images.generic.error" />
<img src="<spring:message code="images.status.skipped" />" alt="<spring:message code="images.status.skipped.alt" />" title="<spring:message code="images.status.skipped.title" />">
Skipped CP?
<form:checkbox path="batch[${iter.index}].isSkipped" cssErrorClass="fld_err" cssClass="isSkipped" value="yes" />
</div>
</div>
<div class="clear"></div>

</div>


<div id="af${iter.index}" class="adv_form">

<div class="half_hdr positive">
Bonuses
</div>
<div class="half_hdr negative">
Penalties
</div>
<div class="clear"></div>

<div class="row cpv_row clear">
<div class="qtr_hdr positive">
<rdb:rdberrs path="batch[${iter.index}].timeBonusAssessed" element="div" cssClass="tooltip" code="images.generic.error" />
Time Bonus Assessed:
</div>
<div class="qtr_fld positive">
<form:input path="batch[${iter.index}].timeBonusAssessed" cssErrorClass="fld_err" size="5" maxlength="5" />
</div>
<div class="qtr_hdr negative">
<rdb:rdberrs path="batch[${iter.index}].timePenaltyAssessed" element="div" cssClass="tooltip" code="images.generic.error" />
Time Penalty Assessed:
</div>
<div class="qtr_fld negative">
<form:input path="batch[${iter.index}].timePenaltyAssessed" cssErrorClass="fld_err" size="5" maxlength="5" />
</div>
</div>

<div class="row cpv_row">
<div class="qtr_hdr positive">
<rdb:rdberrs path="batch[${iter.index}].cpBonusAssessed" element="div" cssClass="tooltip" code="images.generic.error" />
CP Bonus Assessed:
</div>
<div class="qtr_fld positive">
<form:input path="batch[${iter.index}].cpBonusAssessed" cssErrorClass="fld_err" size="2" maxlength="2" />
</div>
<div class="qtr_hdr negative">
<rdb:rdberrs path="batch[${iter.index}].cpPenaltyAssessed" element="div" cssClass="tooltip" code="images.generic.error" />
CP Penalty Assessed:
</div>
<div class="qtr_fld negative">
<form:input path="batch[${iter.index}].cpPenaltyAssessed" cssErrorClass="fld_err" size="2" maxlength="2" />
</div>
</div>

<div class="row cpv_row">
<div class="qtr_hdr positive">
<rdb:rdberrs path="batch[${iter.index}].weightBonusAssessed" element="div" cssClass="tooltip" code="images.generic.error" />
Weight Bonus Assessed:
</div>
<div class="qtr_fld positive">
<form:input path="batch[${iter.index}].weightBonusAssessed" cssErrorClass="fld_err" size="4" maxlength="4" />
</div>
<div class="qtr_hdr negative">
<rdb:rdberrs path="batch[${iter.index}].weightPenaltyAssessed" element="div" cssClass="tooltip" code="images.generic.error" />
Weight Penalty Assessed:
</div>
<div class="qtr_fld negative">
<form:input path="batch[${iter.index}].weightPenaltyAssessed" cssErrorClass="fld_err" size="4" maxlength="4" />
</div>
</div>

<div class="row cpv_row">
<div class="qtr_hdr positive">
<rdb:rdberrs path="batch[${iter.index}].bonusAssessedReason" element="div" cssClass="tooltip" code="images.generic.error" />
Bonus Assessed Reason:
</div>
<div class="qtr_fld positive">
<form:textarea path="batch[${iter.index}].bonusAssessedReason" cssErrorClass="fld_err" rows="3" cols="16" />
</div>
<div class="qtr_hdr negative">
<rdb:rdberrs path="batch[${iter.index}].penaltyAssessedReason" element="div" cssClass="tooltip" code="images.generic.error" />
Penalty Assessed Reason:
</div>
<div class="qtr_fld negative">
<form:textarea path="batch[${iter.index}].penaltyAssessedReason" cssErrorClass="fld_err" rows="3" cols="16" />
</div>
</div>

<div class="row cpv_row">
<div class="full_hdr">
Status Updates
</div>
</div>

<div class="row cpv_row">

<div class="qtr_fld vert_fld">
<rdb:rdberrs path="batch[${iter.index}].isUnofficial" element="div" cssClass="tooltip" code="images.generic.error" />
<img src="<spring:message code="images.status.unofficial" />" alt="<spring:message code="images.status.unofficial.alt" />" title="<spring:message code="images.status.unofficial.title" />">
Unofficial Team?:
<form:checkbox path="batch[${iter.index}].isUnofficial" cssErrorClass="fld_err" value="yes" />
</div>

<div class="qtr_fld vert_fld">
<rdb:rdberrs path="batch[${iter.index}].isIncomplete" element="div" cssClass="tooltip" code="images.generic.error" />
<img src="<spring:message code="images.status.incomplete" />" alt="<spring:message code="images.status.incomplete.alt" />" title="<spring:message code="images.status.incomplete.title" />">
Incomplete Team?:
<form:checkbox path="batch[${iter.index}].isIncomplete" cssErrorClass="fld_err" value="yes" />
</div>

<div class="qtr_fld vert_fld">
<rdb:rdberrs path="batch[${iter.index}].isWithdrawn" element="div" cssClass="tooltip" code="images.generic.error" />
<img src="<spring:message code="images.status.withdrawn" />" alt="<spring:message code="images.status.withdrawn.alt" />" title="<spring:message code="images.status.withdrawn.title" />">
Withdrawn Team?:
<form:checkbox path="batch[${iter.index}].isWithdrawn" cssErrorClass="fld_err" value="yes" />
</div>

<div class="qtr_fld vert_fld">
<rdb:rdberrs path="batch[${iter.index}].isDisqualified" element="div" cssClass="tooltip" code="images.generic.error" />
<img src="<spring:message code="images.status.disqualified" />" alt="<spring:message code="images.status.disqualified.alt" />" title="<spring:message code="images.status.disqualified.title" />">
Disqualified Team?:	
<form:checkbox path="batch[${iter.index}].isDisqualified" cssErrorClass="fld_err" value="yes" />
</div>

</div>


</div>

</c:forEach>

</div>

<div class="form_actions">

<div id="submit" class="buttons form_action">
<button type="submit">
<img src="<c:url value="${editImage}" />" alt="<spring:message code="images.generic.edit.title.cpvisitbatch"/>" title="<spring:message code="images.generic.edit.title.cpvisitbatch" />">
${pageTitle}
</button>
</div>

</div>

</form:form>

</div>
</div>