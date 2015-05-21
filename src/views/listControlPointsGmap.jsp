<%--
File: listControlPointsGmap.jsp
Author: Joshua Forester
Date: 2010/01/11
Description: List Control Points on a google map.
--%>



<%@ taglib prefix="c" uri="/tags/jstl-c" %>
<%@ taglib prefix="spring" uri="/tags/spring" %>
<%@ taglib prefix="sec" uri="/tags/security" %>
<%@ taglib prefix="rdb" uri="/tags/rdb" %>


<%@ page import="com.myrunning.leaderboard.global.NumberPadder" %>
<%@ page import="com.myrunning.leaderboard.model.Leaderboard" %>
<%@ page import="com.myrunning.leaderboard.model.Team" %>
<%@ page import="com.myrunning.leaderboard.model.CpVisit" %>


<spring:message var="clearImage" code="images.generic.clear" />
<c:url var="clearImageUrl" value="${clearImage}" scope="page" />


<div id="tabs">
<ul>
<li><a href="#fragment-1"><span>Course Map</span></a></li>
<li><a href="<c:url value="/d/courses/${leaderboard.course.id}/description.htm" />"><span>Course Description</span></a></li>
<li><a href="<c:url value="/d/leaderboardLegend.htm" />"><span>Legend</span></a></li>
</ul>

<div id="fragment-1">

<h3>Course Map</h3>

<div style="display:none;">
<c:forEach var="cp" varStatus="iter" items="${leaderboard.cpList}">


<div id="m${iter.index}t1" class="gmap_ctn">

<div id="m${iter.index}t1_hdr" class="row">
<div id="m${iter.index}t1_cp_hdr">

<div class="cp_hdr">
<span class="attval">
${cp.name}
<c:if test="${ cp.fromDiscipline != cp.toDiscipline }">
 | ${cp.taName} 
</c:if>
</span>
</div>

</div>
<div id="m${iter.index}t1_cp_icons">

<div class="cp_icons">
<img src="<spring:message code="images.discipline.${cp.fromDiscipline}" />" alt="<spring:message code="images.discipline.${cp.fromDiscipline}.alt" />" title="<spring:message code="images.discipline.${cp.fromDiscipline}.title" />">
<img src="<spring:message code="images.discipline.${cp.toDiscipline}" />" alt="<spring:message code="images.discipline.${cp.toDiscipline}.alt" />" title="<spring:message code="images.discipline.${cp.toDiscipline}.title" />">
<c:choose>
  <c:when test="${ cp.isMandatory == 'yes' }">
    <img src="<spring:message code="images.cp.mandatory" />" alt="<spring:message code="images.cp.mandatory.alt" />" title="<spring:message code="images.cp.mandatory.title" />">
  </c:when>
  <c:otherwise>
    <img src="<c:url value="${clearImage}" />" alt="" style="border: 0px solid #000000;">
  </c:otherwise>
</c:choose>
<c:choose>
  <c:when test="${ cp.cutoff != null && cp.cutoff.time != 0 }">
    <img src="<spring:message code="images.cp.cutoff" />" alt="<spring:message code="images.cp.cutoff.alt" />" title="<spring:message code="images.cp.cutoff.title" />">
  </c:when>
  <c:otherwise>
    <img src="<c:url value="${clearImage}" />" alt="" style="border: 0px solid #000000;">
  </c:otherwise>
</c:choose>
<img src="<c:url value="${clearImage}" />" alt="" style="border: 0px solid #000000;">
</div>

</div>
</div>

<span class="attval"><span class="att">Order</span> ${cp.cpOrder}</span>
<span class="attval"><span class="att">UTM</span> ${cp.zoneNumber}${cp.zoneChar} ${cp.easternly}E,${cp.northernly}N</span>
<span class="attval"><span class="att">Weight</span> ${cp.weight}</span>
<span class="attval"><span class="att">Hint</span> ${cp.hint}</span>
<c:if test="${ cp.cutoff != null && cp.cutoff.time != 0 }">
  <span class="attval"><span class="att" title="<spring:message code="att.cp.cutoff.title" />">Cutoff:</span> ${rdb:fmtTs(cp.cutoff,'yyyy-MM-dd HH:mm')}</span>
</c:if>

</div>

<div id="m${iter.index}t2" class="gmap_ctn">
<c:forEach var="cpVisit" items="${cp.cpVisitList}">
<c:if test="${ cpVisit.isSkipped != 'yes' }">
<jsp:scriptlet><![CDATA[
   Leaderboard l = (Leaderboard) pageContext.findAttribute("leaderboard");
   CpVisit cpv = (CpVisit) pageContext.findAttribute("cpVisit");
   Team t = (Team) l.getTeamTable().get(cpv.getTeamId());
   String teamNumber = NumberPadder.zeroPad(t.getNumber(), 2);
   pageContext.setAttribute("teamNumber", teamNumber, PageContext.PAGE_SCOPE);   
]]></jsp:scriptlet>
<span class="attval"><span class="att">T${teamNumber}</span> ${leaderboard.teamTable[cpVisit.teamId].name}</span>
</c:if>
</c:forEach>
</div>

<div id="m${iter.index}t3" class="gmap_ctn">
<c:forEach var="cpVisit" items="${cp.cpVisitList}">
<c:if test="${ cpVisit.isSkipped == 'yes' }">
<jsp:scriptlet><![CDATA[
   Leaderboard l = (Leaderboard) pageContext.findAttribute("leaderboard");
   CpVisit cpv = (CpVisit) pageContext.findAttribute("cpVisit");
   Team t = (Team) l.getTeamTable().get(cpv.getTeamId());
   String teamNumber = NumberPadder.zeroPad(t.getNumber(), 2);
   pageContext.setAttribute("teamNumber", teamNumber, PageContext.PAGE_SCOPE);   
]]></jsp:scriptlet>
<span class="attval"><span class="att">T${teamNumber}</span> ${leaderboard.teamTable[cpVisit.teamId].name}</span>
</c:if>
</c:forEach>
</div>


</c:forEach>
</div>


<script type="text/javascript">
$(document).ready(function() {
 function showPoint(marker, title, i){
   map.panTo(marker.getPoint());
   marker.openInfoWindowTabsHtml([new GInfoWindowTab(title+' Details',document.getElementById('m'+i+'t1')), new GInfoWindowTab('Acquired',document.getElementById('m'+i+'t2')), new GInfoWindowTab('Skipped',document.getElementById('m'+i+'t3'))]);
 }

 function makePoint(lat, lng, title, tacq, tskp, i){
  var pt = new GLatLng(lat, lng);
  bounds.extend(pt);
  var marker = new GMarker(pt,{title : title});
  marker.bindInfoWindowTabsHtml([new GInfoWindowTab(title+' Details',document.getElementById('m'+i+'t1')), new GInfoWindowTab('Acquired',document.getElementById('m'+i+'t2')), new GInfoWindowTab('Skipped',document.getElementById('m'+i+'t3'))]);
  map.addOverlay(marker);
  GEvent.addListener(marker, "click", function(){
    showPoint(marker, title, i);
  });
  $("<li />").addClass("cp_item").html('<div class="row cp_item">'+$('#m'+i+'t1_cp_hdr').html()+$('#m'+i+'t1_cp_icons').html()+'<div class="cp_att"><span class="attval"><span class="att" title="<spring:message code="att.cp.stats.teamsskipped" />">'+tskp+'</span></span></div><div class="cp_att"><span class="attval"><span class="att" title="<spring:message code="att.cp.stats.teamsacquired" />">'+tacq+'</span></span></div></div>').click(function(){
    showPoint(marker, title, i);
  }).appendTo("#cp_list");
 }

 if (GBrowserIsCompatible()) {
  var map = new GMap2(document.getElementById('map'));
  map.setCenter(new GLatLng(37.7,-97.3),4);
  map.addControl(new GLargeMapControl3D());
  map.addControl(new GMapTypeControl());
  map.addMapType(G_PHYSICAL_MAP);
  map.addMapType(MYTOPO_MAP);
  map.addMapType(G_SATELLITE_3D_MAP);
  map.setMapType(G_PHYSICAL_MAP);
  var bounds = new GLatLngBounds();
<c:forEach var="cp" varStatus="iter" items="${leaderboard.cpList}">

  makePoint(${cp.latLonPoint.latitude}, ${cp.latLonPoint.longitude}, "${cp.name}", "${cp.teamsAcquired}", "${cp.teamsSkipped}", ${iter.index});

</c:forEach>
<c:choose>
<c:when test="${ ! empty center }">
  var pt = new GLatLng(${center.latitude},${center.longitude});
  bounds.extend(pt);	
  map.setCenter(pt, map.getBoundsZoomLevel(bounds));
</c:when>
<c:otherwise>
  map.setCenter(bounds.getCenter(), map.getBoundsZoomLevel(bounds));
</c:otherwise>
</c:choose>
 }
});
</script>

<div id="map" style="width:100%; height:515px"></div>

</div>

</div>

