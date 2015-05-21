<%--
File: editTeamForm.jsp
Author: Joshua Forester
Date: 2009/08/24
Description: Edit team page.
--%>


<%@ taglib prefix="c" uri="/tags/jstl-c" %>
<%@ taglib prefix="spring" uri="/tags/spring" %>
<%@ taglib prefix="form" uri="/tags/spring-form" %>
<%@ taglib prefix="tiles" uri="/tags/tiles-jsp" %>


<tiles:importAttribute name="pageTitle" />
<tiles:importAttribute name="submitUrl" />

<div class="rdb_panel">

${pageTitle}:</br>
<c:url var="fullSubmitUrl" value="${submitUrl}" />
<form:form commandName="team" action="${fullSubmitUrl}" method="post">
<form:hidden path="id" /><form:errors path="id" />
<table>
<tr>
<td>Event/Course:</td>
<td>
<form:select path="courseId">
<c:forEach var="eventCourse" items="${eventCourses}">
<form:option value="${eventCourse.courseId}" label="${eventCourse.eventName} - ${eventCourse.courseName}" />
</c:forEach>
</form:select>
<form:errors path="courseId" />
</td>
</tr>
<tr>
<td>Event/Division:</td>
<td>
<form:select path="divisionId">
<c:forEach var="eventDivision" items="${eventDivisions}">
<form:option value="${eventDivision.divisionId}" label="${eventDivision.eventName} - ${eventDivision.divisionName}" />
</c:forEach>
</form:select>
<form:errors path="divisionId" />
</td>
</tr>
<tr>
<td>Number:</td>
<td><form:input path="number" /><form:errors path="number" /></td>
</tr>
<tr>
<td>Name:</td>
<td><form:input path="name" /><form:errors path="name" /></td>
</tr>
<tr>
<td>&nbsp;</td>
<td><input type="submit" value="${pageTitle}"></input></td>
</tr>
</table>
</form:form>

</div>