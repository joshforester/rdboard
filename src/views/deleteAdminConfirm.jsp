<%--
File: deleteAdminConfirm.jsp
Author: Joshua Forester
Date: 2009/09/15
Description: Delete admin confirm page.
--%>



<%@ taglib prefix="c" uri="/tags/jstl-c" %>
<%@ taglib prefix="form" uri="/tags/spring-form" %>


<div class="rdb_panel">

Are you sure you want to delete the following Admin?
<table>
<tr>
<td>Admin ID</td>
<td>Identity ID</td>
<td>Name</td>
<td>Address</td>
<td>Email</td>
<td>Gender</td>
<td>Birthday</td>
<td>Home Phone</td>
<td>Cell Phone</td>
<td>Work Phone</td>
<td>Emergency Contact</td>
<td>Emergency Contact Relation</td>
<td>Emergency Contact Phone</td>
<td>Website</td>
<td>Username</td>
<td>Authorities</td>
</tr>
<tr>
<td>${admin.id}</td>
<td>${admin.identityId}</td>
<td>${admin.firstName} ${admin.lastName}</td>
<td>${admin.city}, ${admin.region}, ${admin.country}  ${admin.zip}</td>
<td>${admin.email}</td>
<td>${admin.gender}</td>
<td>${admin.birthday}</td>
<td>${admin.homePhone}</td>
<td>${admin.cellPhone}</td>
<td>${admin.workPhone}</td>
<td>${admin.defaultEmergencyContactFirstName} ${admin.defaultEmergencyContactLastName}</td>
<td>${admin.defaultEmergencyContactRelation}</td>
<td>${admin.defaultEmergencyPhone}</td>
<td>${admin.website}</td>
<td>${admin.username}</td>
<td>
<c:forEach var="authority" items="${admin.authorities}">
${authority}<br />
</c:forEach>
</td>
</tr>
</table>
<table>
<tr>
<td>
<form:form commandName="admin" action="deleteAdminConfirmed.htm" method="post">
<input id="id" name="id" type="hidden" value="${admin.id}" />
<input type="submit" value="Confirm Delete"></input>
</form:form>
</td>
<td>
<form:form action="listAdmins.htm" method="get">
<input type="submit" value="Cancel"></input>
</form:form>
</td>
</tr>
</table>

</div>