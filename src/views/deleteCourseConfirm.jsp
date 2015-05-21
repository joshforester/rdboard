<%--
File: deleteCourseConfirm.jsp
Author: Joshua Forester
Date: 2009/08/19
Description: Delete course confirm page.
--%>



<%@ taglib prefix="c" uri="/tags/jstl-c" %>
<%@ taglib prefix="form" uri="/tags/spring-form" %>


<div class="rdb_panel">

Are you sure you want to delete the following Course?
<table>
<tr>
<td>Course ID</td>
<td>Event ID</td>
<td>Name</td>
</tr>
<tr>
<td>${course.id}</td>
<td>${course.eventId}</td>
<td>${course.name}</td>
<td>
</tr>
</table>
<br>
<br>
If you do, the following Teams must be deleted or moved to another course first:
<b>Show teams associated with this course.</b>
<br>
<br>
Also, the following CPs (and their associated visits) will be deleted:
<b>Show CPs associated with this course.</b>
<br>
<br>
Also, the following Comparators will be deleted:
<b>Show Comparators associated with this course.</b>
<br>
<br>
<table>
<tr>
<td>
<c:url var="actionUrl" value="/d/deleteCourseConfirmed.htm" />
<form:form commandName="course" action="${actionUrl}" method="post">
<input id="id" name="id" type="hidden" value="${course.id}" />
<input type="submit" value="Confirm Delete"></input>
</form:form>
</td>
<td>
<c:url var="returnUrl" value="/d/events/${course.eventId}/courses" />
<form:form action="${returnUrl}" method="get">
<input type="submit" value="Cancel"></input>
</form:form>
</td>
</tr>
</table>

</div>