<%--
File: deleteCompetitorConfirm.jsp
Author: Joshua Forester
Date: 2009/08/24
Description: Delete competitor confirm page.
--%>



<%@ taglib prefix="c" uri="/tags/jstl-c" %>
<%@ taglib prefix="form" uri="/tags/spring-form" %>


<div class="rdb_panel">

Are you sure you want to delete the following Competitor?
<table>
<tr>
<td>Competitor ID</td>
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
<td>Shirt Size</td>
<td>Shoe Size</td>
<td>Website</td>
</tr>
<tr>
<td>${competitor.id}</td>
<td>${competitor.firstName} ${competitor.lastName}</td>
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
<td>${competitor.website}</td>
</tr>
</table>
<br>
<br>
If you do, the following Teams may require a relacement competitor:
<b>Show teams associated with this competitor.</b>
<br>
<br>
<table>
<tr>
<td>
<form:form commandName="competitor" action="deleteCompetitorConfirmed.htm" method="post">
<input id="id" name="id" type="hidden" value="${competitor.id}" />
<input type="submit" value="Confirm Delete"></input>
</form:form>
</td>
<td>
<form:form action="listCompetitors.htm" method="get">
<input type="submit" value="Cancel"></input>
</form:form>
</td>
</tr>
</table>

</div>