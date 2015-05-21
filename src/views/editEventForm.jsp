<%--
File: editEventForm.jsp
Author: Joshua Forester
Date: 2009/08/17
Description: Edit event page.
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
<form:form commandName="event" action="${fullSubmitUrl}" method="post">
<form:hidden path="id" /><form:errors path="id" />
<table>
<tr>
<td>Name:</td>
<td><form:input path="name" /><form:errors path="name" /></td>
</tr>
<tr>
<td>Start Time:</td>
<td><form:input path="startTime" /><form:errors path="startTime" /></td>
</tr>
<tr>
<td>End Time:</td>
<td><form:input path="endTime" /><form:errors path="endTime" /></td>
</tr>
<tr>
<td>City:</td>
<td><form:input path="city" /><form:errors path="city" /></td>
</tr>
<tr>
<td>Region/State:</td>
<td>
<form:select path="region">
<%-- <c:forEach var="country" items="${countries}"> --%>
<option value="" selected="selected">-- Select Region --</option>
<c:forEach var="region" items="${regions}">
<spring:message var="regionText" code="text.generic.region.US.${region}" /><br>
<form:option value="${region}" label="${regionText}" /> 
</c:forEach>
</form:select>
<form:errors path="region" /></td>
</tr>
<tr>
<td>Country:</td>
<td>
<form:select path="country">
<option value="" selected="selected">-- Select Country --</option>
<c:forEach var="country" items="${countries}">
<spring:message var="countryText" code="text.generic.country.${country}" /><br>
<form:option value="${country}" label="${countryText}" /> 
</c:forEach>
</form:select>
<form:errors path="country" /></td>
</tr>
<tr>
<td>Zip:</td>
<td><form:input path="zip" /><form:errors path="zip" /></td>
</tr>
<tr>
<td>Event Image URL:</td>
<td><form:input path="eventUrl" /><form:errors path="eventUrl" /></td>
</tr>
<tr>
<td>Event Image Caption:</td>
<td><form:input path="eventCaption" /><form:errors path="eventCaption" /></td>
</tr>
<tr>
<td>Sponsor Website URL:</td>
<td><form:input path="sponsorWebsite" /><form:errors path="sponsorWebsite" /></td>
</tr>
<tr>
<td>Sponsor Image URL:</td>
<td><form:input path="sponsorUrl" /><form:errors path="sponsorUrl" /></td>
</tr>
<tr>
<td>Sponsor Image Title:</td>
<td><form:input path="sponsorTitle" /><form:errors path="sponsorTitle" /></td>
</tr>
<tr>
<td>Sponsor Image Caption:</td>
<td><form:input path="sponsorCaption" /><form:errors path="sponsorCaption" /></td>
</tr>
<tr>
<td>Sponsor Description:</td>
<td><form:textarea cssClass="ckeditor" path="sponsorDescription" rows="5" cols="64" /><form:errors path="sponsorDescription" /></td>
</tr>
<tr>
<td>&nbsp;</td>
<td><input type="submit" value="${pageTitle}"></input></td>
</tr>
</table>
</form:form>

</div>