<%--
File: deleteEventConfirm.jsp
Author: Joshua Forester
Date: 2009/08/20
Description: Delete event confirm page.
--%>



<%@ taglib prefix="c" uri="/tags/jstl-c" %>
<%@ taglib prefix="form" uri="/tags/spring-form" %>


<div class="rdb_panel">

Are you sure you want to delete the following event?
<table>
<tr>
<td>Event ID</td>
<td>Name</td>
<td>Start Time</td>
<td>End Time</td>
<td>City</td>
<td>Region</td>
<td>Country</td>
<td>Zip</td>
</tr>
<tr>
<td>${event.id}</td>
<td>${event.name}</td>
<td>${event.startTime}</td>
<td>${event.endTime}</td>
<td>${event.city}</td>
<td>${event.region}</td>
<td>${event.country}</td>
<td>${event.zip}</td>
<td>
</tr>
</table>
<br>
<br>
If you do, the following Courses and Divisions must be deleted first:
<b>Show courses and divisions associated with this Event.</b>
<br>
<br>
<table>
<tr>
<td>
<c:url var="actionUrl" value="/d/deleteEventConfirmed.htm" />
<form:form commandName="event" action="${actionUrl}" method="post">
<input id="id" name="id" type="hidden" value="${event.id}" />
<input type="submit" value="Confirm Delete"></input>
</form:form>
</td>
<td>
<c:url var="returnUrl" value="/d/events" />
<form:form action="${returnUrl}" method="get">
<input type="submit" value="Cancel"></input>
</form:form>
</td>
</tr>
</table>

</div>