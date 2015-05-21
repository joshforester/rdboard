<%--
File: leaderboardLegend.jsp
Author: Joshua Forester
Date: 2009/12/02
Description: Symbol Legend for leaderboard.
--%>


<%@ taglib prefix="c" uri="/tags/jstl-c" %>
<%@ taglib prefix="spring" uri="/tags/spring" %>
<%@ taglib prefix="tiles" uri="/tags/tiles-jsp" %>
<%@ taglib prefix="log" uri="/tags/taglibs-log" %>


<div id="focal" class="rdb_panel">

<div class="clear">

<h3>Legend</h3>

<div style="float: left; width: 50%;">

<div class="listhdr">Discipline</div>

<ul class="simple">
<li>
<span class="def"><img src="<spring:message code="images.discipline.running" />" alt="<spring:message code="images.discipline.running.alt" />" title="<spring:message code="images.discipline.running.alt" />"> <spring:message code="images.discipline.running.title" /></span>
</li>
<li>
<span class="def"><img src="<spring:message code="images.discipline.trekking" />" alt="<spring:message code="images.discipline.trekking.alt" />" title="<spring:message code="images.discipline.trekking.alt" />"> <spring:message code="images.discipline.trekking.title" /></span>
</li>
<li>
<span class="def"><img src="<spring:message code="images.discipline.mountaineering" />" alt="<spring:message code="images.discipline.mountaineering.alt" />" title="<spring:message code="images.discipline.mountaineering.alt" />"> <spring:message code="images.discipline.mountaineering.title" /></span>
</li>
<li>
<span class="def"><img src="<spring:message code="images.discipline.coasteering" />" alt="<spring:message code="images.discipline.coasteering.alt" />" title="<spring:message code="images.discipline.coasteering.alt" />"> <spring:message code="images.discipline.coasteering.title" /></span>
</li>
<li>
<span class="def"><img src="<spring:message code="images.discipline.biking" />" alt="<spring:message code="images.discipline.biking.alt" />" title="<spring:message code="images.discipline.biking.alt" />"> <spring:message code="images.discipline.biking.title" /></span>
</li>
<li>
<span class="def"><img src="<spring:message code="images.discipline.orienteering" />" alt="<spring:message code="images.discipline.orienteering.alt" />" title="<spring:message code="images.discipline.orienteering.alt" />"> <spring:message code="images.discipline.orienteering.title" /></span>
</li>
<li>
<span class="def"><img src="<spring:message code="images.discipline.paddling" />" alt="<spring:message code="images.discipline.paddling.alt" />" title="<spring:message code="images.discipline.paddling.alt" />"> <spring:message code="images.discipline.paddling.title" /></span>
</li>
<li>
<span class="def"><img src="<spring:message code="images.discipline.swimming" />" alt="<spring:message code="images.discipline.swimming.alt" />" title="<spring:message code="images.discipline.swimming.alt" />"> <spring:message code="images.discipline.swimming.title" /></span>
</li>
<li>
<span class="def"><img src="<spring:message code="images.discipline.ropes" />" alt="<spring:message code="images.discipline.ropes.alt" />" title="<spring:message code="images.discipline.ropes.alt" />"> <spring:message code="images.discipline.ropes.title" /></span>
</li>
<li>
<span class="def"><img src="<spring:message code="images.discipline.riverboarding" />" alt="<spring:message code="images.discipline.riverboarding.alt" />" title="<spring:message code="images.discipline.riverboarding.alt" />"> <spring:message code="images.discipline.riverboarding.title" /></span>
</li>
<li>
<span class="def"><img src="<spring:message code="images.discipline.inlineskating" />" alt="<spring:message code="images.discipline.inlineskating.alt" />" title="<spring:message code="images.discipline.inlineskating.alt" />"> <spring:message code="images.discipline.inlineskating.title" /></span>
</li>
<li>
<span class="def"><img src="<spring:message code="images.discipline.scootering" />" alt="<spring:message code="images.discipline.scootering.alt" />" title="<spring:message code="images.discipline.scootering.alt" />"> <spring:message code="images.discipline.scootering.title" /></span>
</li>
<li>
<span class="def"><img src="<spring:message code="images.discipline.specialchallenge" />" alt="<spring:message code="images.discipline.specialchallenge.alt" />" title="<spring:message code="images.discipline.specialchallenge.alt" />"> <spring:message code="images.discipline.specialchallenge.title" /></span>
</li>
</ul>
</div>

<div style="float: right; width: 50%">

<div class="listhdr">Control Point</div>

<ul class="simple">
<li>
<span class="def"><img src="<spring:message code="images.cp.mandatory" />" alt="<spring:message code="images.cp.mandatory.alt" />" title="<spring:message code="images.cp.mandatory.alt" />"> <spring:message code="images.cp.mandatory.title" /></span>
</li>
<li>
<span class="def"><img src="<spring:message code="images.cp.cutoff" />" alt="<spring:message code="images.cp.cutoff.alt" />" title="<spring:message code="images.cp.cutoff.alt" />"> <spring:message code="images.cp.cutoff.title" /></span>
</li>
<li>
<span class="def"><img src="<spring:message code="images.status.arrived" />" alt="<spring:message code="images.status.arrived.alt" />" title="<spring:message code="images.status.arrived.alt" />"> <spring:message code="images.status.arrived.title" /></span>
</li>
<li>
<span class="def"><img src="<spring:message code="images.status.departed" />" alt="<spring:message code="images.status.departed.alt" />" title="<spring:message code="images.status.departed.alt" />"> <spring:message code="images.status.departed.title" /></span>
</li>
<li>
<span class="def"><img src="<spring:message code="images.status.skipped" />" alt="<spring:message code="images.status.skipped.alt" />" title="<spring:message code="images.status.skipped.alt" />"> <spring:message code="images.status.skipped.title" /></span>
</li>
</ul>

<div class="listhdr">Status</div>
<ul class="simple">
<li>
<span class="def"><img src="<spring:message code="images.status.official" />" alt="<spring:message code="images.status.official.alt" />" title="<spring:message code="images.status.official.alt" />"> <spring:message code="images.status.official.title" /></span>
</li>
<li>
<span class="def"><img src="<spring:message code="images.status.unofficial" />" alt="<spring:message code="images.status.unofficial.alt" />" title="<spring:message code="images.status.unofficial.alt" />"> <spring:message code="images.status.unofficial.title" /></span>
</li>
<li>
<span class="def"><img src="<spring:message code="images.status.missedcutoff" />" alt="<spring:message code="images.status.missedcutoff.alt" />" title="<spring:message code="images.status.missedcutoff.alt" />"> <spring:message code="images.status.missedcutoff.title" /></span>
</li>
<li>
<span class="def"><img src="<spring:message code="images.status.incomplete" />" alt="<spring:message code="images.status.incomplete.alt" />" title="<spring:message code="images.status.incomplete.alt" />"> <spring:message code="images.status.incomplete.title" /></span>
</li>
<li>
<span class="def"><img src="<spring:message code="images.status.withdrawn" />" alt="<spring:message code="images.status.withdrawn.alt" />" title="<spring:message code="images.status.withdrawn.alt" />"> <spring:message code="images.status.withdrawn.title" /></span>
</li>
<li>
<span class="def"><img src="<spring:message code="images.status.disqualified" />" alt="<spring:message code="images.status.disqualified.alt" />" title="<spring:message code="images.status.disqualified.alt" />"> <spring:message code="images.status.disqualified.title" /></span>
</li>
<li>
<span class="def"><img src="<spring:message code="images.status.didnotstart" />" alt="<spring:message code="images.status.didnotstart.alt" />" title="<spring:message code="images.status.didnotstart.alt" />"> <spring:message code="images.status.didnotstart.title" /></span>
</li>
</ul>


</div>

</div>

</div>
