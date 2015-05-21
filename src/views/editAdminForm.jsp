<%--
File: editAdminForm.jsp
Author: Joshua Forester
Date: 2009/09/15
Description: Edit admin page.
--%>


<%@ taglib prefix="c" uri="/tags/jstl-c" %>
<%@ taglib prefix="spring" uri="/tags/spring" %>
<%@ taglib prefix="form" uri="/tags/spring-form" %>
<%@ taglib prefix="tiles" uri="/tags/tiles-jsp" %>


<tiles:importAttribute name="pageTitle" />
<tiles:importAttribute name="submitUrl" />

<div class="rdb_panel">

${pageTitle}:<br/>
<form:errors path="admin" /><br/>
<form:form commandName="admin" action="${simpleUrl}" method="post">
<form:hidden path="id" /><form:errors path="id" />
<table>
<td>First Name:</td>
<td><form:input path="firstName" /><form:errors path="firstName" /></td>
</tr>
<tr>
<td>Last Name:</td>
<td><form:input path="lastName" /><form:errors path="lastName" /></td>
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
<td>Email:</td>
<td><form:input path="email" /><form:errors path="email" /></td>
</tr>
<tr>
<td>Gender:</td>
<td>
<form:radiobutton path="gender" value="male" label="Male" /><br>
<form:radiobutton path="gender" value="female" label="Female" /></<form:errors path="gender" />
</td>
</tr>
<tr>
<td>Birthday (YYYY-MM-DD):</td>
<td><form:input path="birthday" /><form:errors path="birthday" /></td>
</tr>
<tr>
<td>Home Phone:</td>
<td><form:input path="homePhone" /><form:errors path="homePhone" /></td>
</tr>
<tr>
<td>Cell Phone:</td>
<td><form:input path="cellPhone" /><form:errors path="cellPhone" /></td>
</tr>
<tr>
<td>Work Phone:</td>
<td><form:input path="workPhone" /><form:errors path="workPhone" /></td>
</tr>
<tr>
<td>Emergency Contact First Name:</td>
<td><form:input path="defaultEmergencyContactFirstName" /><form:errors path="defaultEmergencyContactFirstName" /></td>
</tr>
<tr>
<td>Emergency Contact Last Name:</td>
<td><form:input path="defaultEmergencyContactLastName" /><form:errors path="defaultEmergencyContactLastName" /></td>
</tr>
<tr>
<td>Emergency Contact Relation:</td>
<td>
<form:select path="defaultEmergencyContactRelation">
<c:forEach var="emergencyContactRelation" items="${emergencyContactRelations}">
<spring:message var="emergencyContactRelationText" code="text.person.emergencycontactrelation.${emergencyContactRelation}" /><br>
<form:option value="${emergencyContactRelation}" label="${emergencyContactRelationText}" /> 
</c:forEach>
</form:select>
<form:errors path="defaultEmergencyContactRelation" />
</td>
</tr>
<tr>
<td>Emergency Contact Phone:</td>
<td><form:input path="defaultEmergencyPhone" /><form:errors path="defaultEmergencyPhone" /></td>
</tr>
<%--

MORE STUFF HERE

--%>
<tr>
<td>Website:</td>
<td><form:input path="website" /><form:errors path="website" /></td>
</tr>
<tr>
<td>Username:</td>
<td><form:input path="username" /><form:errors path="username" /></td>
</tr>
<tr>
<td>Password:</td>
<td><form:password path="password" /><form:errors path="password" /></td>
</tr>
<tr>
<td>Confirm Password:</td>
<td><form:password path="confirmPassword" /><form:errors path="confirmPassword" /></td>
</tr>
<tr>
<td>Authorities:</td>
<td>
<form:select path="authorities">
<c:forEach var="authority" items="${authorities}">
<spring:message var="authorityText" code="text.person.authority.${authority}" /><br>
<form:option value="${authority}" label="${authorityText}" /> 
</c:forEach>
</form:select>
<form:errors path="authorities" /></td>
</tr>
<tr>
<td>&nbsp;</td>
<td><input type="submit" value="${pageTitle}"></input></td>
</tr>
</table>
</form:form>

</div>