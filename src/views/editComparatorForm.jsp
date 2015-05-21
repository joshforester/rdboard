<%--
File: editComparatorForm.jsp
Author: Joshua Forester
Date: 2009/08/26
Description: Edit Comparator page.
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
<form:form commandName="comparator" action="${fullSubmitUrl}" method="post">
<form:hidden path="id" /><form:errors path="id" />
<form:hidden path="comparatorOrder" /><form:errors path="comparatorOrder" />
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
<td>Type:</td>
<td>
<form:select path="type">
<option value="" selected="selected">-- Select Type --</option> 
<c:forEach var="type" items="${types}">
<spring:message var="typeText" code="text.comparator.type.${type}" /><br>
<form:option value="${type}" label="${typeText}" /> 
</c:forEach>
</form:select>
<form:errors path="type" />
</td>
</tr>
<tr>
<td>Tie Action:</td>
<td>
<c:forEach var="tieAction" items="${tieActions}">
<spring:message var="tieActionText" code="text.comparator.tieaction.${tieAction}" /><br>
<form:radiobutton path="tieAction" value="${tieAction}" label="${tieActionText}" /><br>
</c:forEach>
<form:errors path="tieAction" />
</td>
</tr>
<tr>
<td>&nbsp;</td>
<td><input type="submit" value="${pageTitle}"></input></td>
</tr>
</table>
</form:form>

</div>