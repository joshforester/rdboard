<%--
File: listEvents.jsp
Author: Joshua Forester
Date: 2009/08/18
Description: List events page.
--%>



<%@ taglib prefix="c" uri="/tags/jstl-c" %>
<%@ taglib prefix='sec' uri="/tags/security" %>


<div class="rdb_panel">

<h3>Events</h3>

<table>
<tr>
<sec:authorize ifAnyGranted="ROLE_ROOT,ROLE_PROMOTER">
<td>Event</td>
</sec:authorize>
<td>Name</td>
<td>Start Time</td>
<td>End Time</td>
<td>City</td>
<td>Region</td>
<td>Country</td>
<td>Zip</td>
<sec:authorize ifAnyGranted="ROLE_ROOT,ROLE_PROMOTER">
<td>Event Image URL</td>
<td>Event Image Caption</td>
<td>Sponsor Website URL</td>
<td>Sponsor Image URL</td>
<td>Sponosr Image Title</td>
<td>Sponosr Image Caption</td>
</sec:authorize>
<td>Courses</td>
<td>Divisions</td>
<%-- 
<td>Updates</td> 
--%>
</tr>
<c:forEach var="event" items="${events}">
<tr>
<sec:authorize ifAnyGranted="ROLE_ROOT,ROLE_PROMOTER"><td><sec:authorize ifAnyGranted="ROLE_ROOT">${event.id} </sec:authorize>(<a href="<c:url value="/d/editEvent.htm?event_id=${event.id}" />">Edit</a> | <a href="<c:url value="/d/deleteEvent.htm?event_id=${event.id}" />">Delete</a>)</td></sec:authorize>
<td>${event.name}</td>
<td>${event.startTime}</td>
<td>${event.endTime}</td>
<td>${event.city}</td>
<td>${event.region}</td>
<td>${event.country}</td>
<td>${event.zip}</td>
<sec:authorize ifAnyGranted="ROLE_ROOT,ROLE_PROMOTER">
<td>${event.eventUrl}</td>
<td>${event.eventCaption}</td>
<td>${event.sponsorWebsite}</td>
<td>${event.sponsorUrl}</td>
<td>${event.sponsorTitle}</td>
<td>${event.sponsorCaption}</td>
</sec:authorize>
<td><a href="<c:url value="/d/events/${event.id}/courses" />">View</a></td>
<td><a href="<c:url value="/d/events/${event.id}/divisions" />">View</a></td>
<%-- 
<td><a href="<c:url value="/d/events/${event.id}/updates" />">View</a></td> 
--%>
</tr>
</c:forEach>
</table>
<br>
<br>
<center><sec:authorize ifAnyGranted="ROLE_ROOT,ROLE_PROMOTER"><a href="<c:url value="/d/addEvent.htm" />">Add Event</a> |</sec:authorize> <a href="<c:url value="/d/events?order=name" />">Sort By Name</a> | <a href="<c:url value="/d/events?order=city" />">Sort By City</a> | <a href="<c:url value="/d/events?order=region" />">Sort By Region</a> | <a href="<c:url value="/d/events?order=country" />">Sort By Country</a> | <a href="<c:url value="/d/events?order=starttime" />">Sort By Start Time</a></center>

</div>