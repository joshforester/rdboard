<%--
File: deleteControlPointConfirm.jsp
Author: Joshua Forester
Date: 2009/08/20
Description: Delete controlPoint confirm page.
--%>



<%@ taglib prefix="c" uri="/tags/jstl-c" %>
<%@ taglib prefix="form" uri="/tags/spring-form" %>


<div class="rdb_panel">

Are you sure you want to delete the following Control Point?
<table>
<tr>
<td>Control Point ID</td>
<td>Course ID</td>
<td>Order</td>
<td>Name</td>
<td>TA Name</td>
<td>Easternly</td>
<td>Northernly</td>
<td>Hint</td>
<td>Altitude</td>
<td>Cutoff</td>
<td>Mandatory?</td>
<td>Weight</td>
<td>From Discipline</td>
<td>To Discipline</td>
</tr>
<tr>
<td>${controlPoint.id}</td>
<td>${controlPoint.courseId}</td>
<td>${controlPoint.cpOrder}</td>
<td>${controlPoint.name}</td>
<td>${controlPoint.taName}</td>
<td>${controlPoint.easternly}</td>
<td>${controlPoint.northernly}</td>
<td>${controlPoint.hint}</td>
<td>${controlPoint.altitude}</td>
<td>${controlPoint.cutoff}</td>
<td>${controlPoint.isMandatory}</td>
<td>${controlPoint.weight}</td>
<td>${controlPoint.fromDiscipline}</td>
<td>${controlPoint.toDiscipline}</td>
<td>
</tr>
</table>
Also, the following CP Visits will be deleted:
<b>Show CP_Visits associated with this Control Point.</b>
<br>
<br>
<table>
<tr>
<td>
<c:url var="actionUrl" value="/d/deleteControlPointConfirmed.htm" />
<form:form commandName="controlPoint" action="${actionUrl}" method="post">
<input id="id" name="id" type="hidden" value="${controlPoint.id}" />
<input type="submit" value="Confirm Delete"></input>
</form:form>
</td>
<td>
<c:url var="returnUrl" value="/d/courses/${controlPoint.courseId}/cps" />
<form:form action="${returnUrl}" method="get">
<input type="submit" value="Cancel"></input>
</form:form>
</td>
</tr>
</table>

</div>