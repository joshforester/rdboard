<%--
File: addRegistrationEditCompetitors.jsp
Author: Joshua Forester
Date: 2009/09/01
Description: Edit competitor portion of addRegistrationFlow
--%>


<%@ taglib prefix="c" uri="/tags/jstl-c" %>
<%@ taglib prefix="spring" uri="/tags/spring" %>
<%@ taglib prefix="form" uri="/tags/spring-form" %>

<div class="rdb_panel">

Edit Competitors.

<form:form modelAttribute="registration" action="${flowExecutionUrl}" method="post">

<c:forTokens var="memberId" items="1,2,3,4" delims=",">

<c:if test="${ memberId eq '1' }">
<c:set var="currentMemberId" value="${registration.member1Id}" />
</c:if>
<c:if test="${ memberId eq '2' }">
<c:set var="currentMemberId" value="${registration.member2Id}" />
</c:if>
<c:if test="${ memberId eq '3' }">
<c:set var="currentMemberId" value="${registration.member3Id}" />
</c:if>
<c:if test="${ memberId eq '4' }">
<c:set var="currentMemberId" value="${registration.member4Id}" />
</c:if>

<c:if test="${ currentMemberId != 0 }">

<br>
<br>
<table>
<th>
<td colspan="2">Member${memberId}</td>
</th>
<tr>
<td>Emergency Contact First Name:</td>
<td><form:input path="member${memberId}EmergencyContactFirstName" /><form:errors path="member${memberId}EmergencyContactFirstName" /></td>
</tr>
<tr>
<td>Emergency Contact Last Name:</td>
<td><form:input path="member${memberId}EmergencyContactLastName" /><form:errors path="member${memberId}EmergencyContactLastName" /></td>
</tr>
<tr>
<td>Emergency Contact Relation:</td>
<td>
<form:select path="member${memberId}EmergencyContactRelation">
<c:forEach var="emergencyContactRelation" items="${emergencyContactRelations}">
<spring:message var="emergencyContactRelationText" code="text.person.emergencycontactrelation.${emergencyContactRelation}" /><br>
<form:option value="${emergencyContactRelation}" label="${emergencyContactRelationText}" />
</c:forEach>
</form:select>
<form:errors path="member${memberId}EmergencyContactRelation" />
</td>
</tr>
<tr>
<td>Emergency Contact Phone:</td>
<td><form:input path="member${memberId}EmergencyPhone" /><form:errors path="member${memberId}EmergencyPhone" /></td>
</tr>
<tr>
<td>Shirt Size:</td>
<td>
<form:select path="member${memberId}ShirtSize">
<c:forEach var="shirtSize" items="${shirtSizes}">
<spring:message var="shirtSizeText" code="text.person.shirtsize.${shirtSize}" /><br>
<form:option value="${shirtSize}" label="${shirtSizeText}" />
</c:forEach>
</form:select>
<form:errors path="member${memberId}ShirtSize" />
</td>
</tr>
<tr>
<td>Shoe Size:</td>
<td><form:input path="member${memberId}ShoeSize" /><form:errors path="member${memberId}ShoeSize" /></td>
</tr>
</table>

</c:if>

</c:forTokens>

<input type="submit" name="_eventId_submit" value="Next" />
<input type="submit" name="_eventId_cancel" value="Cancel" />

</form:form>

</div>