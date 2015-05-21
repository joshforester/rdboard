<%--
File: deleteTeamConfirm.jsp
Author: Joshua Forester
Date: 2009/08/24
Description: Delete team confirm page.
--%>



<%@ taglib prefix="c" uri="/tags/jstl-c" %>
<%@ taglib prefix="form" uri="/tags/spring-form" %>


<div class="rdb_panel">

Are you sure you want to delete the following Team?
<table>
<tr>
<td>Team ID</td>
<td>Name</td>
<td>Number</td>
<td>Course ID</td>
<td>Division ID</td>
<td>Registration Time</td>
</tr>
<tr>
<td>${team.id}</td>
<td>${team.name}</td>
<td>${team.number}</td>
<td>${team.courseId}</td>
<td>${team.divisionId}</td>
<td>${team.registrationTime}</td>
<td>
</tr>
</table>
<br>
<br>
If you do, the following Competitors will be deleted:
<b>Show competitors associated with this team.</b>
<br>
<br>
<table>
<tr>
<td>
<c:url var="actionUrl" value="/d/deleteTeamConfirmed.htm" />
<form:form commandName="team" action="${actionUrl}" method="post">
<input id="id" name="id" type="hidden" value="${team.id}" />
<input type="submit" value="Confirm Delete"></input>
</form:form>
</td>
<td>
<c:url var="returnUrl" value="/d/teams/${team.id}" />
<form:form action="${returnUrl}" method="get">
<input type="submit" value="Cancel"></input>
</form:form>
</td>
</tr>
</table>

</div>