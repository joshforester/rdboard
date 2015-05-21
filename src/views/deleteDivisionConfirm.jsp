<%--
File: deleteDivisionConfirm.jsp
Author: Joshua Forester
Date: 2009/08/19
Description: Delete division confirm page.
--%>



<%@ taglib prefix="c" uri="/tags/jstl-c" %>
<%@ taglib prefix="form" uri="/tags/spring-form" %>


<div class="rdb_panel">

Are you sure you want to delete the following division?
<table>
<tr>
<td>Division ID</td>
<td>Event ID</td>
<td>Name</td>
<td>Elite?</td>
</tr>
<tr>
<td>${division.id}</td>
<td>${division.eventId}</td>
<td>${division.name}</td>
<td>${division.isElite}</td>
<td>
</tr>
</table>
<br>
<br>
If you do, the following Teams must be deleted or moved to another division first:
<b>Show teams associated with this division.</b>
<br>
<br>
<table>
<tr>
<td>
<c:url var="actionUrl" value="/d/deleteDivisionConfirmed.htm" />
<form:form commandName="division" action="${actionUrl}" method="post">
<input id="id" name="id" type="hidden" value="${division.id}" />
<input type="submit" value="Confirm Delete"></input>
</form:form>
</td>
<td>
<c:url var="returnUrl" value="/d/events/${division.eventId}/divisions" />
<form:form action="${returnUrl}" method="get">
<input type="submit" value="Cancel"></input>
</form:form>
</td>
</tr>
</table>

</div>