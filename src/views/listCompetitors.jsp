<%--
File: listCompetitors.jsp
Author: Joshua Forester
Date: 2009/08/24
Description: List competitors page.
--%>


<%@ taglib prefix="c" uri="/tags/jstl-c" %>
<%@ taglib prefix='sec' uri="/tags/security" %>


<div class="rdb_panel">

<h3>Competitors</h3>

<table>
<tr>
<%--
<td>Competitor</td>
<td>Person ID</td>
--%>
<td>Name</td>
<%--
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
<td>Shirt Size</td>
<td>Shoe Size</td>
--%>
<td>Website</td>
</tr>
<c:forEach var="competitor" items="${competitors}">
<tr>
<%--
<td><sec:authorize ifAnyGranted="ROLE_ROOT">${competitor.id} </sec:authorize>(<a href="<c:url value="editCompetitor.htm?competitor_id=${competitor.id}" />">Edit</a> | <a href="<c:url value="deleteCompetitor.htm?competitor_id=${competitor.id}" />">Delete</a>)</td>
<td>${competitor.personId}</td>
--%>
<td>${competitor.firstName} ${competitor.lastName}</td>
<%--
<td>${competitor.city}, ${competitor.region}, ${competitor.country}  ${competitor.zip}</td>
<td>${competitor.email}</td>
<td>${competitor.gender}</td>
<td>${competitor.birthday}</td>
<td>${competitor.homePhone}</td>
<td>${competitor.cellPhone}</td>
<td>${competitor.workPhone}</td>
<td>${competitor.emergencyContactFirstName} ${competitor.emergencyContactLastName}</td>
<td>${competitor.emergencyContactRelation}</td>
<td>${competitor.emergencyPhone}</td>
<td>${competitor.shirtSize}</td>
<td>${competitor.shoeSize}</td>
--%>
<td>${competitor.website}</td>
</tr>
</c:forEach>
</table>
<%--
<br>
<br>
<center><a href="<c:url value="addCompetitor.htm" />">Add Competitor</a></center>
--%>

</div>