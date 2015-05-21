<%--
File: addRegistrationSelectCourseDivision.jsp
Author: Joshua Forester
Date: 2009/09/01
Description: Select course/division portion of addRegistrationFlow
--%>


<%@ taglib prefix="c" uri="/tags/jstl-c" %>
<%@ taglib prefix="form" uri="/tags/spring-form" %>

<div class="rdb_panel">

Select course/division.

<form:form modelAttribute="registration" action="${flowExecutionUrl}" method="post">
<form:select path="courseId">
<c:forEach var="course" items="${courses}">
<form:option value="${course.id}" label="${course.name}" />
</c:forEach>
</form:select>
<form:select path="divisionId">
<c:forEach var="division" items="${divisions}">
<form:option value="${division.id}" label="${division.name}" />
</c:forEach>
</form:select>
<table>
<tr>
<td>Name:</td>
<td><form:input path="teamName" /><form:errors path="teamName" /></td>
</tr>
</table>
<input type="submit" name="_eventId_submit" value="Next" />
<input type="submit" name="_eventId_cancel" value="Cancel" />
</form:form>

</div>