<%--
File: editControlPointForm.jsp
Author: Joshua Forester
Date: 2009/08/20
Description: Edit ControlPoint page.
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
<form:form commandName="controlPoint" action="${fullSubmitUrl}" method="post">
<form:hidden path="id" /><form:errors path="id" />
<form:hidden path="cpOrder" /><form:errors path="cpOrder" />
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
<td>Name:</td>
<td><form:input path="name" /><form:errors path="name" /></td>
</tr>
<tr>
<td>TA Name:</td>
<td><form:input path="taName" /><form:errors path="taName" /></td>
</tr>
<tr>
<td>Zone Number:</td>
<td><form:input path="zoneNumber" /><form:errors path="zoneNumber" /></td>
</tr>
<tr>
<td>Zone Letter:</td>
<td>
<form:select path="zoneChar">
<option value="" selected="selected">-- Select Zone Letter --</option> 
<c:forEach var="zoneChar" items="${zoneChars}">
<form:option value="${zoneChar}" label="${zoneChar}" /> 
</c:forEach>
</form:select>
<form:errors path="zoneChar" />
</td>
</tr>
<tr>
<td>Easternly:</td>
<td><form:input path="easternly" /><form:errors path="easternly" /></td>
</tr>
<tr>
<td>Northernly:</td>
<td><form:input path="northernly" /><form:errors path="northernly" /></td>
</tr>
<tr>
<td>Hint:</td>
<td><form:textarea path="hint" rows="5" cols="30" /><form:errors path="hint" /></td>
</tr>
<tr>
<td>Cutoff:</td>
<td><form:input path="cutoff" /><form:errors path="cutoff" /></td>
</tr>
<tr>
<td>Mandatory?:</td>
<td>
<form:checkbox path="isMandatory" value="yes" /><form:errors path="isMandatory" />
</td>
</tr>
<tr>
<td>Weight:</td>
<td><form:input path="weight" /><form:errors path="weight" /></td>
</tr>
<tr>
<td>From Discipline:</td>
<td>
<form:select path="fromDiscipline">
<option value="" selected="selected">-- Select Discipline --</option> 
<c:forEach var="discipline" items="${disciplines}">
<spring:message var="disciplineText" code="text.controlpoint.discipline.${discipline}" /><br>
<form:option value="${discipline}" label="${disciplineText}" /> 
</c:forEach>
</form:select>
<form:errors path="fromDiscipline" />
</td>
</tr>
<tr>
<td>To Discipline:</td>
<td>
<form:select path="toDiscipline">
<option value="" selected="selected">-- Select Discipline --</option> 
<c:forEach var="discipline" items="${disciplines}">
<spring:message var="disciplineText" code="text.controlpoint.discipline.${discipline}" /><br>
<form:option value="${discipline}" label="${disciplineText}" /> 
</c:forEach>
</form:select>
<form:errors path="toDiscipline" />
</td>
</tr>
<tr>
<td>&nbsp;</td>
<td><input type="submit" value="${pageTitle}"></input></td>
</tr>
</table>
</form:form>

</div>