<%--
File: listLeaderboardsFull.jsp
Author: Joshua Forester
Date: 2009/08/27
Description: Default page.
--%>


<%@ taglib prefix="c" uri="/tags/jstl-c" %>
<%@ taglib prefix="fn" uri="/tags/jstl-fn" %>
<%@ taglib prefix="spring" uri="/tags/spring" %>
<%@ taglib prefix="tiles" uri="/tags/tiles-jsp" %>
<%@ taglib prefix="log" uri="/tags/taglibs-log" %>
<%@ taglib prefix="sec" uri="/tags/security" %>
<%@ taglib prefix="rdb" uri="/tags/rdb" %>

<%@ page import="java.util.Hashtable" %>
<%@ page import="com.myrunning.leaderboard.global.NumberPadder" %>
<%@ page import="com.myrunning.leaderboard.model.Team" %>
<%@ page import="com.myrunning.leaderboard.model.ControlPoint" %>
<%@ page import="com.myrunning.leaderboard.model.CpVisit" %>


<spring:message var="downarrowImage" code="images.generic.downarrow" />

<div id="tabs">
<ul>
<li><a href="#fragment-1"><span>Leaderboard</span></a></li>
<c:forEach var="leaderboard" items="${leaderboards}" varStatus="leaderboardCounter">
<li><a href="<c:url value="/d/courses/${leaderboard.course.id}/description.htm" />"><span>Course Description</span></a></li>
</c:forEach>
<li><a href="<c:url value="/d/leaderboardLegend.htm" />"><span>Legend</span></a></li>
</ul>

<div id="fragment-1">

<c:forEach var="leaderboard" items="${leaderboards}">
<c:set var="numTeams" scope="page" value="${fn:length(leaderboard.teamList)}" />
</c:forEach>

<div id="expdlbdheight" class="jsvar">${(numTeams * 26) + 625}</div>

<div id="lbd">
<div class="h3menubar">

<h3>Leaderboard - Full</h3>

<div id="lbd_resize" class="h3menu">
<div class="buttons">
<img src="<c:url value="${downarrowImage}" />" alt="<spring:message code="images.generic.downarrow.alt.expand" />" title="<spring:message code="images.generic.downarrow.title.expand" />">
Resize
</div>
</div>

</div>

<div id="lbd_ctn">

<spring:message var="clearImage" code="images.generic.clear" />
<spring:message var="editImage" code="images.generic.edit" />
<spring:message var="addImage" code="images.generic.add" />
<spring:message var="viewImage" code="images.generic.view" />

<c:forEach var="leaderboard" items="${leaderboards}" varStatus="leaderboardCounter">

<ul class="clearfix sldr">
<li class="sldr tblhdr">
  <div id="row1_col1" class="sldr row1 col1 expdtblhdr">
    <div class="vertglance">
      <sec:authorize ifAnyGranted="ROLE_ROOT,ROLE_PROMOTER">
      <a href="<c:url value="/d/courses/${leaderboard.course.id}" />"><img src="<c:url value="${viewImage}" />" alt="<spring:message code="images.generic.view.alt" />" title="<spring:message code="images.generic.view.title" />"></a>
      <a href="<c:url value="/d/editCourse.htm?course_id=${leaderboard.course.id}" />"><img src="<c:url value="${editImage}" />" alt="<spring:message code="images.generic.edit.alt" />" title="<spring:message code="images.generic.edit.title" />"></a>
      </sec:authorize>
      <img src="<c:url value="${clearImage}" />" alt="" style="border: 0px solid #000000;">
    </div>
    <div class="details">
      <p class="d_header">
        ${event.name}<br>
	<span class="d_subheader">${leaderboard.course.name}</span>
      </p>
      <p>
	<span class="attval"><span class="att" title="<spring:message code="att.course.start.title" />">Start:</span> ${rdb:fmtTs(event.startTime,'yyyy-MM-dd HH:mm')}</span>
	<span class="attval"><span class="att" title="<spring:message code="att.course.end.title" />">End:</span> ${rdb:fmtTs(event.endTime,'yyyy-MM-dd HH:mm')}</span>
	<span class="attval"><span class="att" title="<spring:message code="att.course.lastmodified.title" />">Last Modified:</span> ${rdb:fmtLclTs(leaderboard.lastModified,'HH:mm')}</span>
<%--
	<span class="attval"><span class="att" title="Discuss the race and recieve updates.">Race Forum:</span> <a href="http://trailblazerar.com/forum/viewtopic.php?t=6931" onclick="window.open(this.href); return false;">Here</a></span>
--%>
      </p>

      <p class="d_footer">
        <a href="http://maps.google.com/maps?q=${event.city},+${event.region},+${event.country}+(${event.name})&amp;t=p" onclick="window.open(this.href); return false;"><img src="<spring:message code="images.3p.gmaps" />" alt="<spring:message code="images.3p.gmaps.alt" />" title="<spring:message code="images.3p.gmaps.title" />"></a>
      </p>
    </div>
  </div>
</li>
<c:forEach var="cp" items="${leaderboard.cpList}" varStatus="colCounter">
  <li class="sldr colhdr">
    <div id="row1_col${colCounter.count+1}" class="sldr row1 col${colCounter.count+1} expdcolhdr<c:if test="${ colCounter.count == 1 }"> expdcol</c:if>">
      <div class="vertglance">
        <img src="<spring:message code="images.discipline.${cp.fromDiscipline}" />" alt="<spring:message code="images.discipline.${cp.fromDiscipline}.alt" />" title="<spring:message code="images.discipline.${cp.fromDiscipline}.title" />">
        <img src="<spring:message code="images.discipline.${cp.toDiscipline}" />" alt="<spring:message code="images.discipline.${cp.toDiscipline}.alt" />" title="<spring:message code="images.discipline.${cp.toDiscipline}.title" />">
        <span class="cp">
<%--
<jsp:scriptlet><![CDATA[
   ControlPoint cp = (ControlPoint) pageContext.findAttribute("cp");
   char[] cpOrderChars = NumberPadder.zeroPad(cp.getCpOrder(), 2).toCharArray();
   String cpOrderVert = new String("");
   for (int i = 0; i < cpOrderChars.length; i++) {
       cpOrderVert += cpOrderChars[i] + "<br>";
   }
   pageContext.setAttribute("cpOrderVert", cpOrderVert, PageContext.PAGE_SCOPE);   
]]></jsp:scriptlet>

      	  C<br>P<br>${cpOrderVert}
--%>
<jsp:scriptlet><![CDATA[
   ControlPoint cp = (ControlPoint) pageContext.findAttribute("cp");
   String cpName = cp.getName();
   int maxLength = 4;
   String addendum = new String("");
   for (int i = 0; i < maxLength - cpName.length(); i++) {
     addendum += " ";
   }
   cpName += addendum;
   char[] cpOrderChars = cpName.toCharArray();
   String cpOrderVert = new String("");
   for (int i = 0; i < cpOrderChars.length; i++) {
     if (cpOrderChars[i] == ' ') {
        cpOrderVert += "&nbsp;<br>";
     } else {
        cpOrderVert += cpOrderChars[i] + "<br>";
     }
   }
   pageContext.setAttribute("cpOrderVert", cpOrderVert, PageContext.PAGE_SCOPE);   
]]></jsp:scriptlet>
      	  ${cpOrderVert}
        </span>
        <sec:authorize ifAnyGranted="ROLE_ROOT,ROLE_PROMOTER">
        <a href="<c:url value="/d/cps/${cp.id}/cpvisits" />"><img src="<c:url value="${viewImage}" />" alt="<spring:message code="images.generic.view.alt" />" title="<spring:message code="images.generic.view.title.cpvisitbatchbycp" />"></a>
        <a href="<c:url value="/d/editCpVisitBatch.htm?cp_id=${cp.id}" />"><img src="<c:url value="${editImage}" />" alt="<spring:message code="images.generic.edit.alt" />" title="<spring:message code="images.generic.edit.title.cpvisitbatchbycp" />"></a>
        </sec:authorize>
	<c:if test="${ cp.isMandatory == 'yes' }">
          <img src="<spring:message code="images.cp.mandatory" />" alt="<spring:message code="images.cp.mandatory.alt" />" title="<spring:message code="images.cp.mandatory.title" />">
	</c:if>
	<c:if test="${ cp.cutoff != null && cp.cutoff.time != 0 }">
          <img src="<spring:message code="images.cp.cutoff" />" alt="<spring:message code="images.cp.cutoff.alt" />" title="<spring:message code="images.cp.cutoff.title" />">
	</c:if>
      </div>
      <div class="details">
        <p class="d_header">
          ${cp.name}
	  <c:if test="${ cp.fromDiscipline != cp.toDiscipline }">
	   | <span class="d_subheader">TA: ${cp.taName}</span>
	  </c:if>
	   | <span class="d_subheader">Order: ${cp.cpOrder}</span>
	</p>
        <p>
	  <span class="attval"><span class="att" title="<spring:message code="att.cp.utm.title" />">UTM:</span> ${cp.zoneNumber}${cp.zoneChar} ${cp.easternly}E,${cp.northernly}N</span>
	  <c:if test="${ cp.altitude != 0 }">
	    <span class="attval"><span class="att" title="<spring:message code="att.cp.altitude.title" />">Altitude:</span> ${cp.altitude}</span>
	  </c:if>
	  <span class="attval"><span class="att" title="<spring:message code="att.cp.weight.title" />">Weight:</span> ${cp.weight}pts</span>
	  <span class="attval"><span class="att" title="<spring:message code="att.cp.hint.title" />">Hint:</span> ${cp.hint}</span>
	  <c:if test="${ cp.cutoff != null && cp.cutoff.time != 0 }">
	    <span class="attval"><span class="att" title="<spring:message code="att.cp.cutoff.title" />">Cutoff:</span> ${rdb:fmtTs(cp.cutoff,'yyyy-MM-dd HH:mm')}</span>
	  </c:if>
	</p>
        <p class="d_footer">
	  <div class="d_footer_item"><a href="<c:url value="/d/courses/${leaderboard.course.id}/cps/gmap.htm?center=${cp.latLonPoint.latitude},${cp.latLonPoint.longitude}" />"><img src="<spring:message code="images.3p.gmaps" />" alt="<spring:message code="images.3p.gmaps.alt" />" title="<spring:message code="images.3p.gmaps.title" />"></a></div>
	  <div class="d_footer_item"><a href="http://www.heywhatsthat.com/?view=${cp.heyWhatsThatId}" onclick="window.open(this.href); return false;"><img src="<spring:message code="images.3p.heywhatsthat" />" alt="<spring:message code="images.3p.heywhatsthat.alt" />" title="<spring:message code="images.3p.heywhatsthat.title" />"></a></div>
        </p>
      </div>
    </div>
  </li>
</c:forEach>
</ul>

<c:forEach var="team" items="${leaderboard.teamList}" varStatus="rowCounter">

<c:choose>
  <c:when test="${rowCounter.count % 2 == 0}">
    <c:set var="rowStyle" scope="page" value="odd" />
  </c:when>
  <c:otherwise>
    <c:set var="rowStyle" scope="page" value="even"/>
  </c:otherwise>
</c:choose>

<ul class="clearfix sldr">

  <c:forEach var="rank" items="${team.rankList}">
    <!-- ${rank.teamId}/${rank.time}:${rank.divisionPlace} (${rank.divisionPlaceChange}), ${rank.coursePlace} (${rank.coursePlaceChange}) -->
  </c:forEach>

  <li class="sldr rowhdr">
    <div id="row${rowCounter.count+1}_col1" class="sldr row${rowCounter.count+1} col1 expdrowhdr<c:if test="${ rowCounter.count == 1 }"> expdrow</c:if>">
      <div class="vertglance">
        <c:choose>
          <c:when test="${team.didNotStart}">
    	    <c:set var="status" scope="page" value="didnotstart" />
  	  </c:when>
          <c:when test="${team.disqualified}">
    	    <c:set var="status" scope="page" value="disqualified" />
  	  </c:when>
          <c:when test="${team.withdrawn}">
    	    <c:set var="status" scope="page" value="withdrawn" />
  	  </c:when>
          <c:when test="${team.unofficial}">
    	    <c:set var="status" scope="page" value="unofficial" />
  	  </c:when>
          <c:when test="${team.incomplete}">
    	    <c:set var="status" scope="page" value="incomplete" />
  	  </c:when>
          <c:when test="${team.missedCutoff}">
    	    <c:set var="status" scope="page" value="missedcutoff" />
  	  </c:when>
  	  <c:otherwise>
    	    <c:set var="status" scope="page" value="official"/>
  	  </c:otherwise>
	</c:choose>
      
        <img src="<spring:message code="images.status.${status}" />" alt="<spring:message code="images.status.${status}.alt" />" title="<spring:message code="images.status.${status}.title" />">
        <sec:authorize ifAnyGranted="ROLE_ROOT,ROLE_PROMOTER">
        <a href="<c:url value="/d/teams/${team.id}/cpvisits" />"><img src="<c:url value="${viewImage}" />" alt="<spring:message code="images.generic.view.alt" />" title="<spring:message code="images.generic.view.title.cpvisitbatchbyteam" />"></a>
        <a href="<c:url value="/d/editCpVisitBatch.htm?team_id=${team.id}" />"><img src="<c:url value="${editImage}" />" alt="<spring:message code="images.generic.edit.alt" />" title="<spring:message code="images.generic.edit.title.cpvisitbatchbyteam" />"></a>
        </sec:authorize>
      </div>
      <div class="details">
        <p class="d_header team_header">
<jsp:scriptlet><![CDATA[
   Team team = (Team) pageContext.findAttribute("team");
   String teamNumber = NumberPadder.zeroPad(team.getNumber(), 2);
   pageContext.setAttribute("teamNumber", teamNumber, PageContext.PAGE_SCOPE);   
]]></jsp:scriptlet>

T${teamNumber} | <span class="d_subheader">${team.name}</span>
	</p>
        <p>
  <span class="attval">
  <c:choose>
    <c:when test="${team.unofficial}">
      <span class="att" title="<spring:message code="images.status.unofficial.title" />">Unofficial at CP${team.unofficialControlPoint.cpOrder}</span>
    </c:when>
    <c:when test="${team.withdrawn}">
      <span class="att" title="<spring:message code="images.status.withdrawn.title" />">Withdrawn at CP${team.withdrawnControlPoint.cpOrder}</span>
    </c:when>
    <c:when test="${team.incomplete}">
      <span class="att" title="<spring:message code="images.status.incomplete.title" />">Incomplete at CP${team.incompleteControlPoint.cpOrder}</span>
    </c:when>
    <c:when test="${team.missedCutoff}">
      <span class="att" title="<spring:message code="images.status.missedcutoff.title" />">Missed Cutoff at CP${team.missedCutoffControlPoint.cpOrder}</span>
    </c:when>
    <c:when test="${team.didNotStart}">
      <span class="att" title="<spring:message code="images.status.didnotstart.title" />">Did Not Start</span>
    </c:when>
    <c:when test="${team.disqualified}">
      <span class="att" title="<spring:message code="images.status.disqualified.title" />">Disqualified at CP${team.disqualifiedControlPoint.cpOrder}</span>
    </c:when>
    <c:otherwise>
      <span class="att" title="<spring:message code="images.status.official.title" />">Official through CP${team.lastVisitedControlPoint.cpOrder}</span>
    </c:otherwise>
  </c:choose>
  </span>
<c:if test="${team.lastVisitedControlPoint != null}">
  <span class="attval"><span class="att" title="<spring:message code="att.team.lastcp.title" />">Last CP:</span> ${team.lastVisitedControlPoint.name}</span>
</c:if>
  <c:choose>
    <c:when test="${team.lastVisitedCpArrival != null && team.lastVisitedCpArrival.time != 0}">
      <span class="attval"><span class="att" title="<spring:message code="att.team.lastcparrival.title" />">Last CP Arrival:</span> ${rdb:fmtTs(team.lastVisitedCpArrival,'HH:mm')}</span>
      <c:if test="${team.lastVisitedCpDeparture.time != 0}">
        <span class="attval"><span class="att" title="<spring:message code="att.team.lastcpdeparture.title" />">Last CP Departure:</span>  ${rdb:fmtTs(team.lastVisitedCpDeparture,'HH:mm')}</span>
        <span class="attval"><span class="att" title="<spring:message code="att.team.lastcptransition.title" />">Last CP Transition:</span>  ${rdb:fmtTp(team.lastVisitedCpTransition,'HH:mm')}</span>
      </c:if>
    </c:when>
    <c:otherwise>
      <c:if test="${team.lastVisitedCpDeparture != null && team.lastVisitedCpDeparture.time != 0}">
        <span class="attval"><span class="att" title="<spring:message code="att.team.lastcpdeparture.title" />">Last CP Departure:</span>  ${rdb:fmtTs(team.lastVisitedCpDeparture,'HH:mm')}</span>
      </c:if>
    </c:otherwise>
  </c:choose>
<c:if test="${ team.timeBonuses.time != 0 }">
  <span class="attval"><span class="att" title="<spring:message code="att.team.timebonuses.title" />">Time Bonuses:</span>  ${rdb:fmtTp(team.timeBonuses,'HH:mm')}</span>
</c:if>
<c:if test="${ team.timePenalties.time != 0 }">
  <span class="attval"><span class="att" title="<spring:message code="att.team.timepenalties.title" />">Time Penalties:</span>  ${rdb:fmtTp(team.timePenalties,'HH:mm')}</span>
</c:if>
<c:if test="${ team.cpBonuses != 0 }">
  <span class="attval"><span class="att" title="<spring:message code="att.team.cpbonuses.title" />">CP Bonuses:</span> ${team.cpBonuses}</span>
</c:if>
<c:if test="${ team.cpPenalties != 0 }">
  <span class="attval"><span class="att" title="<spring:message code="att.team.cppenalties.title" />">CP Penalties:</span> ${team.cpPenalties}</span>
</c:if>
<c:if test="${ team.cpWeightBonuses != 0 }">
  <span class="attval"><span class="att" title="<spring:message code="att.team.weightbonuses.title" />">CP Weight Bonuses:</span> ${team.cpWeightBonuses}</span>
</c:if>
<c:if test="${ team.cpWeightPenalties != 0 }">
  <span class="attval"><span class="att" title="<spring:message code="att.team.weightpenalties.title" />">CP Weight Penalties:</span> ${team.cpWeightPenalties}</span>
</c:if>
<span class="attval"><span class="att" title="<spring:message code="att.team.mandatorycps.title" />">Mandatory CPs:</span> ${team.mandatoryCps}</span>
<span class="attval"><span class="att" title="<spring:message code="att.team.optionalcps.title" />">Optional CPs:</span> ${team.optionalCps}</span>
<span class="attval"><span class="att" title="<spring:message code="att.team.cpweight.title" />">CP Weight:</span> ${team.cpWeight}</span>
<span class="attval"><span class="att" title="<spring:message code="att.team.skippedmandatorycps.title" />">Skipped Mandatory CPs:</span> ${team.skippedMandatoryCps}</span>
<span class="attval"><span class="att" title="<spring:message code="att.team.skippedoptionalcps.title" />">Skipped Optional CPs:</span> ${team.skippedOptionalCps}</span>
<span class="attval"><span class="att" title="<spring:message code="att.team.skippedweight.title" />">Skipped CP Weight:</span> ${team.skippedCpWeight}</span>

<%-- <c:if test="${ ! empty team.captain }">${team.captain}</c:if><c:if test="${ ! empty team.member2 }">, ${team.member2}</c:if><c:if test="${ ! empty team.member3 }">, ${team.member3}</c:if><c:if test="${ ! empty team.member4 }">, ${team.member4}</c:if> ${team.location}(GPS: ${team.locationLongitude},${team.locationLatitude}) --%>
	</p>
        <p class="d_footer">
	  &nbsp;
        </p>
      </div>
    </div>
  </li>

<c:forEach var="cp" items="${leaderboard.cpList}" varStatus="colCounter">
<jsp:scriptlet><![CDATA[
   long cpId = ((ControlPoint) pageContext.findAttribute("cp")).getId();
   Hashtable<Long, CpVisit> ht = ((Team) pageContext.findAttribute("team")).getCpVisitTable();
   CpVisit cpVisit = ht.get(cpId);
   pageContext.setAttribute("cpVisit", cpVisit, PageContext.PAGE_SCOPE);
]]></jsp:scriptlet>

  <li class="sldr ${rowStyle}">
    <div id="row${rowCounter.count+1}_col${colCounter.count+1}" class="sldr row${rowCounter.count+1} col${colCounter.count+1}<c:if test="${ rowCounter.count == 1 }"> expdrow</c:if><c:if test="${ colCounter.count == 1 }"> expdcol</c:if>">
      <div class="vertglance">
        <c:choose>
          <c:when test="${cpVisit.departure != null && cpVisit.departure.time != 0}">
	    <img src="<spring:message code="images.status.departed" />" alt="<spring:message code="images.status.departed.alt" />" title="<spring:message code="images.status.departed.title" />">
  	  </c:when>
          <c:when test="${cpVisit.arrival != null && cpVisit.arrival.time != 0}">
	    <img src="<spring:message code="images.status.arrived" />" alt="<spring:message code="images.status.arrived.alt" />" title="<spring:message code="images.status.arrived.title" />">
  	  </c:when>
	  <c:when test="${cpVisit.isAcquired == 'yes'}">
	    <img src="<spring:message code="images.status.acquired" />" alt="<spring:message code="images.status.acquired.alt" />" title="<spring:message code="images.status.acquired.title" />">	  
	  </c:when>
	  <c:when test="${cpVisit.isSkipped == 'yes'}">
	    <img src="<spring:message code="images.status.skipped" />" alt="<spring:message code="images.status.skipped.alt" />" title="<spring:message code="images.status.skipped.title" />">
	  </c:when>
  	  <c:otherwise>
	    <img src="<c:url value="${clearImage}" />" alt="" style="border: 0px solid #000000;">
  	  </c:otherwise>
	</c:choose>

        <sec:authorize ifAnyGranted="ROLE_ROOT,ROLE_PROMOTER,ROLE_EVENTADMIN">
	<c:choose>
	  <c:when test="${cpVisit != null}">
          <a href="<c:url value="/d/cpvisits/${cpVisit.cpId}/${cpVisit.teamId}" />"><img src="<c:url value="${viewImage}" />" alt="<spring:message code="images.generic.view.alt" />" title="<spring:message code="images.generic.view.title.cpvisit" />"></a>
          <a href="<c:url value="/d/editCpVisit.htm?team_id=${cpVisit.teamId}&cp_id=${cpVisit.cpId}" />"><img src="<c:url value="${editImage}" />" alt="<spring:message code="images.generic.edit.alt" />" title="<spring:message code="images.generic.edit.title.cpvisit" />"></a>
	  </c:when>
	  <c:when test="${cpVisit == null}">
          <a href="<c:url value="/d/addCpVisit.htm?team_id=${team.id}&cp_id=${cp.id}" />"><img src="<c:url value="${addImage}" />" alt="<spring:message code="images.generic.add.alt" />" title="<spring:message code="images.generic.add.title.cpvisit" />"></a>
	  </c:when>
	</c:choose>
        </sec:authorize>

        <c:if test="${cpVisit.isDisqualified == 'yes'}">
          <img src="<spring:message code="images.status.disqualified" />" alt="<spring:message code="images.status.disqualified.alt" />" title="<spring:message code="images.status.disqualified.title" />">
  	</c:if>
        <c:if test="${cpVisit.isWithdrawn == 'yes'}">
          <img src="<spring:message code="images.status.withdrawn" />" alt="<spring:message code="images.status.withdrawn.alt" />" title="<spring:message code="images.status.withdrawn.title" />">
  	</c:if>
        <c:if test="${cpVisit.isUnofficial == 'yes'}">
          <img src="<spring:message code="images.status.unofficial" />" alt="<spring:message code="images.status.unofficial.alt" />" title="<spring:message code="images.status.unofficial.title" />">
  	</c:if>
        <c:if test="${cpVisit.isIncomplete == 'yes'}">
          <img src="<spring:message code="images.status.incomplete" />" alt="<spring:message code="images.status.incomplete.alt" />" title="<spring:message code="images.status.incomplete.title" />">
  	</c:if>
        <c:if test="${cpVisit.isMissedCutoff == 'yes'}">
          <img src="<spring:message code="images.status.missedcutoff" />" alt="<spring:message code="images.status.missedcutoff.alt" />" title="<spring:message code="images.status.missedcutoff.title" />">
  	</c:if>

      </div>
      <div class="details">
        <c:choose>
          <c:when test="${cpVisit.departure != null && cpVisit.departure.time != 0}">
            <p class="d_header">
            Departed: <span class="d_subheader">${rdb:fmtTs(cpVisit.departure,'HH:mm')}</span>
            </p>
          </c:when>
          <c:when test="${cpVisit.arrival != null && cpVisit.arrival.time != 0}"> 
            <p class="d_header">
            Arrived: <span class="d_subheader">${rdb:fmtTs(cpVisit.arrival,'HH:mm')}</span>
	    </p>
          </c:when>
          <c:when test="${cpVisit.isAcquired == 'yes'}">
            <p class="d_header">
	    Acquired
	    </p>
	  </c:when>
          <c:when test="${cpVisit.isSkipped == 'yes'}">
            <p class="d_header">
	    Skipped
	    </p>
	  </c:when>
        </c:choose>
        <p>
          <c:choose>
            <c:when test="${cpVisit.arrival != null && cpVisit.arrival.time != 0}">
              <span class="attval"><span class="att" title="<spring:message code="att.cpvisit.arrival.title" />">Arrival:</span> ${rdb:fmtTs(cpVisit.arrival,'HH:mm')}</span>
              <c:if test="${cpVisit.departure.time != 0}">
                <span class="attval"><span class="att" title="<spring:message code="att.cpvisit.departure.title" />">Departure:</span> ${rdb:fmtTs(cpVisit.departure,'HH:mm')}</span>
                <span class="attval"><span class="att" title="<spring:message code="att.cpvisit.transition.title" />">Transition:</span> ${rdb:fmtTp(cpVisit.transition,'HH:mm')}</span>
              </c:if>
            </c:when>
	    <c:when test="${cpVisit.isAcquired == 'yes'}">
	      <span class="attval" title="<spring:message code="images.status.acquired.title" />"><span class="att">Acquired</span></span>
	    </c:when>
	    <c:when test="${cpVisit.isSkipped == 'yes'}">
	      <span class="attval" title="<spring:message code="images.status.skipped.title" />"><span class="att">Skipped</span></span>
	    </c:when>
            <c:otherwise>
              <c:if test="${cpVisit.departure != null && cpVisit.departure.time != 0}">
                <span class="attval"><span class="att" title="<spring:message code="att.cpvisit.departure.title" />">Departure:</span> ${rdb:fmtTs(cpVisit.departure,'HH:mm')}</span>
              </c:if>
            </c:otherwise>
          </c:choose>

	  <c:if test="${ cpVisit != null && cpVisit.divisionPlace != 0 }">
	    <span class="attval"><span class="att" title="<spring:message code="att.cpvisit.divisionplace.title" />">Division Rank:</span> ${cpVisit.divisionPlace}</span>
	  </c:if>
	  <c:if test="${ cpVisit != null && cpVisit.coursePlace != 0 }">
	    <span class="attval"><span class="att" title="<spring:message code="att.cpvisit.courseplace.title" />">Course Rank:</span> ${cpVisit.coursePlace}</span>
	  </c:if>
          <c:if test="${ cpVisit.timeBonusAssessed != null && cpVisit.timeBonusAssessed.time != 0 }">
            <span class="attval"><span class="att" title="<spring:message code="att.cpvisit.timebonus.title" />">Time Bonus:</span> ${rdb:fmtTp(cpVisit.timeBonusAssessed,'HH:mm')}</span>
          </c:if>
          <c:if test="${ cpVisit.timePenaltyAssessed != null && cpVisit.timePenaltyAssessed.time != 0 }">
            <span class="attval"><span class="att" title="<spring:message code="att.cpvisit.timepenalty.title" />">Time Penalty:</span> ${rdb:fmtTp(cpVisit.timePenaltyAssessed,'HH:mm')}</span>
          </c:if>
          <c:if test="${ cpVisit != null && cpVisit.cpBonusAssessed != 0 }">
            <span class="attval"><span class="att" title="<spring:message code="att.cpvisit.cpbonus.title" />">CP Bonus:</span> ${cpVisit.cpBonusAssessed}</span>
          </c:if>
          <c:if test="${ cpVisit != null && cpVisit.cpPenaltyAssessed != 0 }">
            <span class="attval"><span class="att" title="<spring:message code="att.cpvisit.cppenalty.title" />">CP Penalty:</span> ${cpVisit.cpPenaltyAssessed}</span>
          </c:if>
          <c:if test="${ cpVisit != null && cpVisit.weightBonusAssessed != 0 }">
            <span class="attval"><span class="att" title="<spring:message code="att.cpvisit.weightbonus.title" />">Weight Bonus:</span> ${cpVisit.cpWeightBonusAssessed}</span>
          </c:if>
          <c:if test="${ cpVisit != null && cpVisit.weightPenaltyAssessed != 0 }">
            <span class="attval"><span class="att" title="<spring:message code="att.cpvisit.weightpenalty.title" />">Weight Penalty:</span> ${cpVisit.cpWeightPenaltyAssessed}</span>
          </c:if>
	</p>
        <p class="d_footer">
	  &nbsp;
        </p>
      </div>
    </div>
  </li>


<c:if test="${colCounter.last == 'true'}">

  <li class="sldr rowhdr">
    <div id="row${rowCounter.count+1}_col${colCounter.count+1}" class="sldr row${rowCounter.count+1} col${colCounter.count+1} expdrowhdr<c:if test="${ rowCounter.count == 1 }"> expdrow</c:if>">
      <div class="vertglance">
        <c:choose>
          <c:when test="${team.didNotStart}">
    	    <c:set var="status" scope="page" value="didnotstart" />
  	  </c:when>
          <c:when test="${team.disqualified}">
    	    <c:set var="status" scope="page" value="disqualified" />
  	  </c:when>
          <c:when test="${team.withdrawn}">
    	    <c:set var="status" scope="page" value="withdrawn" />
  	  </c:when>
          <c:when test="${team.unofficial}">
    	    <c:set var="status" scope="page" value="unofficial" />
  	  </c:when>
          <c:when test="${team.incomplete}">
    	    <c:set var="status" scope="page" value="incomplete" />
  	  </c:when>
          <c:when test="${team.missedCutoff}">
    	    <c:set var="status" scope="page" value="missedcutoff" />
  	  </c:when>
  	  <c:otherwise>
    	    <c:set var="status" scope="page" value="official"/>
  	  </c:otherwise>
	</c:choose>
      
        <img src="<spring:message code="images.status.${status}" />" alt="<spring:message code="images.status.${status}.alt" />" title="<spring:message code="images.status.${status}.title" />">
        <sec:authorize ifAnyGranted="ROLE_ROOT,ROLE_PROMOTER">
        <a href="<c:url value="/d/teams/${team.id}/cpvisits" />"><img src="<c:url value="${viewImage}" />" alt="<spring:message code="images.generic.view.alt" />" title="<spring:message code="images.generic.view.title.cpvisitbatchbyteam" />"></a>
        <a href="<c:url value="/d/editCpVisitBatch.htm?team_id=${team.id}" />"><img src="<c:url value="${editImage}" />" alt="<spring:message code="images.generic.edit.alt" />" title="<spring:message code="images.generic.edit.title.cpvisitbatchbyteam" />"></a>
        </sec:authorize>
      </div>
      <div class="details">
        <p class="d_header team_header">
T${teamNumber} | <span class="d_subheader">${team.name}</span>
	</p>
        <p>
  <span class="attval">
  <c:choose>
    <c:when test="${team.unofficial}">
      <span class="att" title="<spring:message code="images.status.unofficial.title" />">Unofficial at CP${team.unofficialControlPoint.cpOrder}</span>
    </c:when>
    <c:when test="${team.withdrawn}">
      <span class="att" title="<spring:message code="images.status.withdrawn.title" />">Withdrawn at CP${team.withdrawnControlPoint.cpOrder}</span>
    </c:when>
    <c:when test="${team.incomplete}">
      <span class="att" title="<spring:message code="images.status.incomplete.title" />">Incomplete at CP${team.incompleteControlPoint.cpOrder}</span>
    </c:when>
    <c:when test="${team.missedCutoff}">
      <span class="att" title="<spring:message code="images.status.missedcutoff.title" />">Missed Cutoff at CP${team.missedCutoffControlPoint.cpOrder}</span>
    </c:when>
    <c:when test="${team.didNotStart}">
      <span class="att" title="<spring:message code="images.status.didnotstart.title" />">Did Not Start</span>
    </c:when>
    <c:when test="${team.disqualified}">
      <span class="att" title="<spring:message code="images.status.disqualified.title" />">Disqualified at CP${team.disqualifiedControlPoint.cpOrder}</span>
    </c:when>
    <c:otherwise>
      <span class="att" title="<spring:message code="images.status.official.title" />">Official through CP${team.lastVisitedControlPoint.cpOrder}</span>
    </c:otherwise>
  </c:choose>
  </span>
<c:if test="${team.lastVisitedControlPoint != null}">
  <span class="attval"><span class="att" title="<spring:message code="att.team.lastcp.title" />">Last CP:</span> ${team.lastVisitedControlPoint.name}</span>
</c:if>
  <c:choose>
    <c:when test="${team.lastVisitedCpArrival != null && team.lastVisitedCpArrival.time != 0}">
      <span class="attval"><span class="att" title="<spring:message code="att.team.lastcparrival.title" />">Last CP Arrival:</span> ${rdb:fmtTs(team.lastVisitedCpArrival,'HH:mm')}</span>
      <c:if test="${team.lastVisitedCpDeparture.time != 0}">
        <span class="attval"><span class="att" title="<spring:message code="att.team.lastcpdeparture.title" />">Last CP Departure:</span>  ${rdb:fmtTs(team.lastVisitedCpDeparture,'HH:mm')}</span>
        <span class="attval"><span class="att" title="<spring:message code="att.team.lastcptransition.title" />">Last CP Transition:</span>  ${rdb:fmtTp(team.lastVisitedCpTransition,'HH:mm')}</span>
      </c:if>
    </c:when>
    <c:otherwise>
      <c:if test="${team.lastVisitedCpDeparture != null && team.lastVisitedCpDeparture.time != 0}">
        <span class="attval"><span class="att" title="<spring:message code="att.team.lastcpdeparture.title" />">Last CP Departure:</span>  ${rdb:fmtTs(team.lastVisitedCpDeparture,'HH:mm')}</span>
      </c:if>
    </c:otherwise>
  </c:choose>
<c:if test="${ team.timeBonuses.time != 0 }">
  <span class="attval"><span class="att" title="<spring:message code="att.team.timebonuses.title" />">Time Bonuses:</span>  ${rdb:fmtTp(team.timeBonuses,'HH:mm')}</span>
</c:if>
<c:if test="${ team.timePenalties.time != 0 }">
  <span class="attval"><span class="att" title="<spring:message code="att.team.timepenalties.title" />">Time Penalties:</span>  ${rdb:fmtTp(team.timePenalties,'HH:mm')}</span>
</c:if>
<c:if test="${ team.cpBonuses != 0 }">
  <span class="attval"><span class="att" title="<spring:message code="att.team.cpbonuses.title" />">CP Bonuses:</span> ${team.cpBonuses}</span>
</c:if>
<c:if test="${ team.cpPenalties != 0 }">
  <span class="attval"><span class="att" title="<spring:message code="att.team.cppenalties.title" />">CP Penalties:</span> ${team.cpPenalties}</span>
</c:if>
<c:if test="${ team.cpWeightBonuses != 0 }">
  <span class="attval"><span class="att" title="<spring:message code="att.team.weightbonuses.title" />">CP Weight Bonuses:</span> ${team.cpWeightBonuses}</span>
</c:if>
<c:if test="${ team.cpWeightPenalties != 0 }">
  <span class="attval"><span class="att" title="<spring:message code="att.team.weightpenalties.title" />">CP Weight Penalties:</span> ${team.cpWeightPenalties}</span>
</c:if>
<span class="attval"><span class="att" title="<spring:message code="att.team.mandatorycps.title" />">Mandatory CPs:</span> ${team.mandatoryCps}</span>
<span class="attval"><span class="att" title="<spring:message code="att.team.optionalcps.title" />">Optional CPs:</span> ${team.optionalCps}</span>
<span class="attval"><span class="att" title="<spring:message code="att.team.cpweight.title" />">CP Weight:</span> ${team.cpWeight}</span>
<span class="attval"><span class="att" title="<spring:message code="att.team.skippedmandatorycps.title" />">Skipped Mandatory CPs:</span> ${team.skippedMandatoryCps}</span>
<span class="attval"><span class="att" title="<spring:message code="att.team.skippedoptionalcps.title" />">Skipped Optional CPs:</span> ${team.skippedOptionalCps}</span>
<span class="attval"><span class="att" title="<spring:message code="att.team.skippedweight.title" />">Skipped CP Weight:</span> ${team.skippedCpWeight}</span>

<%-- <c:if test="${ ! empty team.captain }">${team.captain}</c:if><c:if test="${ ! empty team.member2 }">, ${team.member2}</c:if><c:if test="${ ! empty team.member3 }">, ${team.member3}</c:if><c:if test="${ ! empty team.member4 }">, ${team.member4}</c:if> ${team.location}(GPS: ${team.locationLongitude},${team.locationLatitude}) --%>
	</p>
        <p class="d_footer">
	  &nbsp;
        </p>
      </div>
    </div>
  </li>

</c:if>

</c:forEach>

</ul>


<c:if test="${rowCounter.last == 'true'}">

<ul class="clearfix sldr">
<li class="sldr tblhdr">
  <div id="row${rowCounter.count+1}_col1" class="sldr row${rowCounter.count+1} col1 expdtblhdr">
    <div class="vertglance">
      <sec:authorize ifAnyGranted="ROLE_ROOT,ROLE_PROMOTER">
      <a href="<c:url value="/d/courses/${leaderboard.course.id}" />"><img src="<c:url value="${viewImage}" />" alt="<spring:message code="images.generic.view.alt" />" title="<spring:message code="images.generic.view.title" />"></a>
      <a href="<c:url value="/d/editCourse.htm?course_id=${leaderboard.course.id}" />"><img src="<c:url value="${editImage}" />" alt="<spring:message code="images.generic.edit.alt" />" title="<spring:message code="images.generic.edit.title" />"></a>
      </sec:authorize>
      <img src="<c:url value="${clearImage}" />" alt="" style="border: 0px solid #000000;">
    </div>
    <div class="details">
      <p class="d_header">
        ${event.name}<br>
	<span class="d_subheader">${leaderboard.course.name}</span>
      </p>
      <p>
	<span class="attval"><span class="att" title="<spring:message code="att.course.start.title" />">Start:</span> ${rdb:fmtTs(event.startTime,'yyyy-MM-dd HH:mm')}</span>
	<span class="attval"><span class="att" title="<spring:message code="att.course.end.title" />">End:</span> ${rdb:fmtTs(event.endTime,'yyyy-MM-dd HH:mm')}</span>
	<span class="attval"><span class="att" title="<spring:message code="att.course.lastmodified.title" />">Last Modified:</span> ${rdb:fmtLclTs(leaderboard.lastModified,'HH:mm')}</span>
<%--
	<span class="attval"><span class="att" title="Discuss the race and recieve updates.">Race Forum:</span> <a href="http://trailblazerar.com/forum/viewtopic.php?t=6931" onclick="window.open(this.href); return false;">Here</a></span>
--%>
      </p>

      <p class="d_footer">
        <a href="http://maps.google.com/maps?q=${event.city},+${event.region},+${event.country}+(${event.name})&amp;t=p" onclick="window.open(this.href); return false;"><img src="<spring:message code="images.3p.gmaps" />" alt="<spring:message code="images.3p.gmaps.alt" />" title="<spring:message code="images.3p.gmaps.title" />"></a>
      </p>
    </div>
  </div>
</li>
<c:forEach var="cp" items="${leaderboard.cpList}" varStatus="colCounter">
  <li class="sldr colhdr">
    <div id="row${rowCounter.count+1}_col${colCounter.count+1}" class="sldr row${rowCounter.count+1} col${colCounter.count+1} expdcolhdr<c:if test="${ colCounter.count == 1 }"> expdcol</c:if>">
      <div class="vertglance">
        <img src="<spring:message code="images.discipline.${cp.fromDiscipline}" />" alt="<spring:message code="images.discipline.${cp.fromDiscipline}.alt" />" title="<spring:message code="images.discipline.${cp.fromDiscipline}.title" />">
        <img src="<spring:message code="images.discipline.${cp.toDiscipline}" />" alt="<spring:message code="images.discipline.${cp.toDiscipline}.alt" />" title="<spring:message code="images.discipline.${cp.toDiscipline}.title" />">
        <span class="cp">
<%--
<jsp:scriptlet><![CDATA[
   ControlPoint cp = (ControlPoint) pageContext.findAttribute("cp");
   char[] cpOrderChars = NumberPadder.zeroPad(cp.getCpOrder(), 2).toCharArray();
   String cpOrderVert = new String("");
   for (int i = 0; i < cpOrderChars.length; i++) {
       cpOrderVert += cpOrderChars[i] + "<br>";
   }
   pageContext.setAttribute("cpOrderVert", cpOrderVert, PageContext.PAGE_SCOPE);   
]]></jsp:scriptlet>

      	  C<br>P<br>${cpOrderVert}
--%>
<jsp:scriptlet><![CDATA[
   ControlPoint cp = (ControlPoint) pageContext.findAttribute("cp");
   String cpName = cp.getName();
   int maxLength = 4;
   String addendum = new String("");
   for (int i = 0; i < maxLength - cpName.length(); i++) {
     addendum += " ";
   }
   cpName += addendum;
   char[] cpOrderChars = cpName.toCharArray();
   String cpOrderVert = new String("");
   for (int i = 0; i < cpOrderChars.length; i++) {
     if (cpOrderChars[i] == ' ') {
        cpOrderVert += "&nbsp;<br>";
     } else {
        cpOrderVert += cpOrderChars[i] + "<br>";
     }
   }
   pageContext.setAttribute("cpOrderVert", cpOrderVert, PageContext.PAGE_SCOPE);   
]]></jsp:scriptlet>
      	  ${cpOrderVert}
        </span>
        <sec:authorize ifAnyGranted="ROLE_ROOT,ROLE_PROMOTER">
        <a href="<c:url value="/d/cps/${cp.id}/cpvisits" />"><img src="<c:url value="${viewImage}" />" alt="<spring:message code="images.generic.view.alt" />" title="<spring:message code="images.generic.view.title.cpvisitbatchbycp" />"></a>
        <a href="<c:url value="/d/editCpVisitBatch.htm?cp_id=${cp.id}" />"><img src="<c:url value="${editImage}" />" alt="<spring:message code="images.generic.edit.alt" />" title="<spring:message code="images.generic.edit.title.cpvisitbatchbycp" />"></a>
        </sec:authorize>
	<c:if test="${ cp.isMandatory == 'yes' }">
          <img src="<spring:message code="images.cp.mandatory" />" alt="<spring:message code="images.cp.mandatory.alt" />" title="<spring:message code="images.cp.mandatory.title" />">
	</c:if>
	<c:if test="${ cp.cutoff != null && cp.cutoff.time != 0 }">
          <img src="<spring:message code="images.cp.cutoff" />" alt="<spring:message code="images.cp.cutoff.alt" />" title="<spring:message code="images.cp.cutoff.title" />">
	</c:if>
      </div>
      <div class="details">
        <p class="d_header">
          ${cp.name}
	  <c:if test="${ cp.fromDiscipline != cp.toDiscipline }">
	   | <span class="d_subheader">TA: ${cp.taName}</span>
	  </c:if>
	   | <span class="d_subheader">Order: ${cp.cpOrder}</span>
	</p>
        <p>
	  <span class="attval"><span class="att" title="<spring:message code="att.cp.utm.title" />">UTM:</span> ${cp.zoneNumber}${cp.zoneChar} ${cp.easternly}E,${cp.northernly}N</span>
	  <c:if test="${ cp.altitude != 0 }">
	    <span class="attval"><span class="att" title="<spring:message code="att.cp.altitude.title" />">Altitude:</span> ${cp.altitude}</span>
	  </c:if>
	  <span class="attval"><span class="att" title="<spring:message code="att.cp.weight.title" />">Weight:</span> ${cp.weight}pts</span>
	  <span class="attval"><span class="att" title="<spring:message code="att.cp.hint.title" />">Hint:</span> ${cp.hint}</span>
	  <c:if test="${ cp.cutoff != null && cp.cutoff.time != 0 }">
	    <span class="attval"><span class="att" title="<spring:message code="att.cp.cutoff.title" />">Cutoff:</span> ${rdb:fmtTs(cp.cutoff,'yyyy-MM-dd HH:mm')}</span>
	  </c:if>
	</p>
        <p class="d_footer">
	  <div class="d_footer_item"><a href="<c:url value="/d/courses/${leaderboard.course.id}/cps/gmap.htm?center=${cp.latLonPoint.latitude},${cp.latLonPoint.longitude}" />"><img src="<spring:message code="images.3p.gmaps" />" alt="<spring:message code="images.3p.gmaps.alt" />" title="<spring:message code="images.3p.gmaps.title" />"></a></div>
	  <div class="d_footer_item"><a href="http://www.heywhatsthat.com/?view=${cp.heyWhatsThatId}" onclick="window.open(this.href); return false;"><img src="<spring:message code="images.3p.heywhatsthat" />" alt="<spring:message code="images.3p.heywhatsthat.alt" />" title="<spring:message code="images.3p.heywhatsthat.title" />"></a></div>
        </p>
      </div>
    </div>
  </li>
</c:forEach>
</ul>

</c:if>

</c:forEach>

<c:if test="${leaderboardCounter.last != 'true'}">
<br>
<br>
</c:if>

</c:forEach>

</div>
</div>

</div>

</div>

