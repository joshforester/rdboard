<%--
File: listDivisions.jsp
Author: Joshua Forester
Date: 2009/08/18
Description: List divisions page.
--%>



<%@ taglib prefix="c" uri="/tags/jstl-c" %>
<%@ taglib prefix="sec" uri="/tags/security" %>


<div class="rdb_panel">

<h3>Divisions</h3>

<table>
<tr>
<sec:authorize ifAnyGranted="ROLE_ROOT,ROLE_PROMOTER">
<td>Division</td>
</sec:authorize>
<td>Event ID</td>
<td>Name</td>
<td>Member Count</td>
<td>Consistency</td>
<td>Elite?</td>
<td>Teams</td>
</tr>
<c:forEach var="division" items="${divisions}">
<tr>
<sec:authorize ifAnyGranted="ROLE_ROOT,ROLE_PROMOTER"><td><sec:authorize ifAnyGranted="ROLE_ROOT">${division.id} </sec:authorize>(<a href="<c:url value="/d/editDivision.htm?division_id=${division.id}" />">Edit</a> | <a href="<c:url value="/d/deleteDivision.htm?division_id=${division.id}" />">Delete</a>)</td></sec:authorize>
<td>${division.eventId}</td>
<td>${division.name}</td>
<td>${division.memberCount}</td>
<td>${division.consistency}</td>
<td>${division.isElite}</td>
<td><a href="<c:url value="/d/divisions/${division.id}/teams" />">View</a></td>
</tr>
</c:forEach>
</table>
<br>
<br>
<center><sec:authorize ifAnyGranted="ROLE_ROOT,ROLE_PROMOTER"><a href="<c:url value="/d/addDivision.htm" />">Add Division</a> |</sec:authorize> <a href="<c:url value="/d/events/${eventId}/divisions?order=name" />">Sort By Name</a> | <a href="<c:url value="/d/events/${eventId}/divisions?order=consistency" />">Sort By Consistency</a> | <a href="<c:url value="/d/events/${eventId}/divisions?order=membercount" />">Sort By Member Count</a></center>

</div>