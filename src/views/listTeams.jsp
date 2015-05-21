<%--
File: listTeams.jsp
Author: Joshua Forester
Date: 2009/08/24
Description: List teams page.
--%>



<%@ taglib prefix="c" uri="/tags/jstl-c" %>
<%@ taglib prefix="sec" uri="/tags/security" %>


<div class="rdb_panel">

<h3>Teams</h3>

<table>
<tr>
<sec:authorize ifAnyGranted="ROLE_ROOT,ROLE_PROMOTER">
<td>Team</td>
</sec:authorize>
<td>Name</td>
<td>Course ID</td>
<td>Division ID</td>
<td>Number</td>
<td>Competitors</td>
<td>CP Visits</td>
<td>Ranks</td>
</tr>
<c:forEach var="team" items="${teams}">
<tr>
<sec:authorize ifAnyGranted="ROLE_ROOT,ROLE_PROMOTER"><td><sec:authorize ifAnyGranted="ROLE_ROOT">${team.id} </sec:authorize>(<a href="<c:url value="/d/editTeam.htm?team_id=${team.id}" />">Edit</a> | <a href="<c:url value="/d/deleteTeam.htm?team_id=${team.id}" />">Delete</a>)</td></sec:authorize>
<td>${team.name}</td>
<td>${team.courseId}</td>
<td>${team.divisionId}</td>
<td>${team.number}</td>
<td><a href="<c:url value="/d/teams/${team.id}/competitors" />">View</a></td>
<td><a href="<c:url value="/d/teams/${team.id}/cpvisits" />">View</a></td>
<td><a href="<c:url value="/d/teams/${team.id}/ranks" />">View</a></td>
</tr>
</c:forEach>
</table>
<br>
<br>
<center><sec:authorize ifAnyGranted="ROLE_ROOT,ROLE_PROMOTER"><a href="<c:url value="addTeam.htm?course_id=${courseId}&division_id=${divisionId}" />">Add Team</a> |</sec:authorize> <a href="<c:url value="listTeams.htm?course_id=${courseId}&division_id=${divisionId}&team_id=${team_id}&order=name" />">Sort By Name</a> | <a href="<c:url value="listTeams.htm?course_id=${courseId}&division_id=${divisionId}&team_id=${team_id}&order=number" />">Sort By Number</a></center>

</div>