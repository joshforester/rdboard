<%--
File: editDivisionForm.jsp
Author: Joshua Forester
Date: 2009/08/18
Description: Edit division page.
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
<form:form commandName="division" action="${fullSubmitUrl}" method="post">
<form:hidden path="id" /><form:errors path="id" />
<table>
<tr>
<td>Event:</td>
<td>
<form:select path="eventId">
<c:forEach var="event" items="${events}">
<form:option value="${event.id}" label="${event.name}" />
</c:forEach>
</form:select>
<form:errors path="eventId" />
</td>
</tr>
<tr>
<td>Name:</td>
<td><form:input path="name" /><form:errors path="name" /></td>
</tr>
<tr>
<td>Member Count:</td>
<td>
<form:radiobutton path="memberCount" value="1" label="1" /><br>
<form:radiobutton path="memberCount" value="2" label="2" /><br>
<form:radiobutton path="memberCount" value="3" label="3" /><br>
<form:radiobutton path="memberCount" value="4" label="4" /></<form:errors path="memberCount" />
</td>
</tr>
<tr>
<td>Consistency:</td>
<td>
<form:select path="consistency">
<option value="" selected="selected">-- Select Consistency --</option> 
<c:forEach var="consistency" items="${consistencies}">
<spring:message var="consistencyText" code="text.division.consistency.${consistency}" /><br>
<form:option value="${consistency}" label="${consistencyText}" /> 
</c:forEach>
</form:select>
<form:errors path="consistency" />
</td>
</tr>
<tr>
<td>Elite?:</td>
<td>
<form:checkbox path="isElite" value="yes" /><form:errors path="isElite" />
</td>
</tr>
<tr>
<td>&nbsp;</td>
<td><input type="submit" value="${pageTitle}"></input></td>
</tr>
</table>
</form:form>

</div>