<%--
File: listCourses.jsp
Author: Joshua Forester
Date: 2009/08/18
Description: List courses page.
--%>



<%@ taglib prefix="c" uri="/tags/jstl-c" %>
<%@ taglib prefix="sec" uri="/tags/security" %>

<div class="rdb_panel">

<h3>Courses</h3>

<table>
<tr>
<sec:authorize ifAnyGranted="ROLE_ROOT,ROLE_PROMOTER">
<td>Course</td>
</sec:authorize>
<td>Event ID</td>
<td>Name</td>
<td>Type</td>
<td>Duration in Hours</td>
<td>Length in Miles</td>
<sec:authorize ifAnyGranted="ROLE_ROOT,ROLE_PROMOTER">
<td>ScribbleLive Id</td>
<td>CoverItLive Id</td>
<td>All Items - Feedburner ID</td>
<td>Blogs - Feedburner ID</td>
<td>Photos - Feedburner ID</td>
<td>Videos - Feedburner ID</td>
<td>Audio - Feedburner ID</td>
</sec:authorize>
<td>Control Points</td>
<td>Teams</td>
<sec:authorize ifAnyGranted="ROLE_ROOT,ROLE_PROMOTER"><td>Comparators</td></sec:authorize>
<td>Leaderboard</td>
<td>Updates</td>
</tr>
<c:forEach var="course" items="${courses}">
<tr>
<sec:authorize ifAnyGranted="ROLE_ROOT,ROLE_PROMOTER"><td><sec:authorize ifAnyGranted="ROLE_ROOT">${course.id} </sec:authorize>(<a href="<c:url value="/d/editCourse.htm?course_id=${course.id}" />">Edit</a> | <a href="<c:url value="/d/deleteCourse.htm?course_id=${course.id}" />">Delete</a>)</td></sec:authorize>
<td>${course.eventId}</td>
<td>${course.name}</td>
<td>${course.type}</td>
<td>${course.lengthHours}</td>
<td>${course.lengthMiles}</td>
<sec:authorize ifAnyGranted="ROLE_ROOT,ROLE_PROMOTER">
<td>${course.scribbleliveId}</td>
<td>${course.coveritliveId}</td>
<td>${course.feedburnerAllId}</td>
<td>${course.feedburnerBlogsId}</td>
<td>${course.feedburnerPhotosId}</td>
<td>${course.feedburnerVideosId}</td>
<td>${course.feedburnerAudioId}</td>
</sec:authorize>
<td><a href="<c:url value="/d/courses/${course.id}/cps" />">View</a></td>
<td><a href="<c:url value="/d/courses/${course.id}/teams" />">View</a></td>
<sec:authorize ifAnyGranted="ROLE_ROOT,ROLE_PROMOTER"><td><a href="<c:url value="/d/courses/${course.id}/comparators" />">View</a></td></sec:authorize>
<td><a href="<c:url value="/d/courses/${course.id}/leaderboards.htm?style=full" />">Full</a> | <a href="<c:url value="/d/courses/${course.id}/leaderboards.htm?style=list" />">List</a> | <a href="<c:url value="/d/courses/${course.id}/leaderboards.htm?style=short" />">Short</a></td>
<td><a href="<c:url value="/d/courses/${course.id}/updates.htm" />">View</a></td>
</tr>
</c:forEach>
</table>
<br>
<br>
<sec:authorize ifAnyGranted="ROLE_ROOT,ROLE_PROMOTER">
<center><a href="<c:url value="/d/addCourse.htm" />">Add Course</a></center>
</sec:authorize>

</div>