<%--
File: listAdmins.jsp
Author: Joshua Forester
Date: 2009/08/24
Description: List admins page.
--%>



<%@ taglib prefix="c" uri="/tags/jstl-c" %>

<div class="rdb_panel">

<h3>Admins</h3>

<table>
<tr>
<td>Admin</td>
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
<c:forEach var="admin" items="${admins}">
<tr>
<td>${admin.id} (<a href="<c:url value="editAdmin.htm?admin_id=${admin.id}" />">Edit</a> | <a href="<c:url value="deleteAdmin.htm?admin_id=${admin.id}" />">Delete</a>)</td>
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
</c:forEach>
</table>
<br>
<br>
<center><a href="<c:url value="addAdmin.htm" />">Add Admin</a></center>

</div>