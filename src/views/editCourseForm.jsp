<%--
File: editCourseForm.jsp
Author: Joshua Forester
Date: 2009/08/18
Description: Edit course page.
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
<form:form commandName="course" action="${fullSubmitUrl}" method="post">
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
<td>Type:</td>
<td>
<form:select path="type">
<option value="" selected="selected">-- Select Type --</option> 
<c:forEach var="type" items="${types}">
<spring:message var="typeText" code="text.course.type.${type}" /><br>
<form:option value="${type}" label="${typeText}" /> 
</c:forEach>
</form:select>
<form:errors path="type" />
</td>
</tr>
<tr>
<td>Duration in Hours:</td>
<td><form:input path="lengthHours" /><form:errors path="lengthHours" /></td>
</tr>
<tr>
<td>Length in Miles:</td>
<td><form:input path="lengthMiles" /><form:errors path="lengthMiles" /></td>
</tr>
<tr>
<td>Description:</td>
<td><form:textarea cssClass="ckeditor" path="description" rows="5" cols="64" /><form:errors path="description" /></td>
</tr>
<tr>
<td>ScribbleLive Id:</td>
<td><form:input path="scribbleliveId" /><form:errors path="scribbleliveId" /></td>
</tr>
<tr>
<td>CoverItLive Id:</td>
<td><form:input path="coveritliveId" /><form:errors path="coveritliveId" /></td>
</tr>
<tr>
<td>All Items - Feedburner ID:</td>
<td>http://feeds.feedburner.com/<form:input path="feedburnerAllId" /><form:errors path="feedburnerAllId" /></td>
</tr>
<tr>
<td>Blogs - Feedburner ID:</td>
<td>http://feeds.feedburner.com/<form:input path="feedburnerBlogsId" /><form:errors path="feedburnerBlogsId" /></td>
</tr>
<tr>
<td>Photos - Feedburner ID:</td>
<td>http://feeds.feedburner.com/<form:input path="feedburnerPhotosId" /><form:errors path="feedburnerPhotosId" /></td>
</tr>
<tr>
<td>Videos - Feedburner ID:</td>
<td>http://feeds.feedburner.com/<form:input path="feedburnerVideosId" /><form:errors path="feedburnerVideosId" /></td>
</tr>
<tr>
<td>Audio - Feedburner ID:</td>
<td>http://feeds.feedburner.com/<form:input path="feedburnerAudioId" /><form:errors path="feedburnerAudioId" /></td>
</tr>
<tr>
<td>Competitor Google Maps Suffix:</td>
<td>http://maps.google.com/maps/<form:input path="competitorGmapSuffix" /><form:errors path="competitorGmapSuffix" /></td>
</tr>
<tr>
<td>&nbsp;</td>
<td><input type="submit" value="${pageTitle}"></input></td>
</tr>
</table>
</form:form>

</div>