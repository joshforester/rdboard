<%--
File: deleteComparatorConfirm.jsp
Author: Joshua Forester
Date: 2009/08/26
Description: Delete comparator confirm page.
--%>



<%@ taglib prefix="c" uri="/tags/jstl-c" %>
<%@ taglib prefix="form" uri="/tags/spring-form" %>


<div class="rdb_panel">

Are you sure you want to delete the following Comparator?
<table>
<tr>
<td>Comparator ID</td>
<td>Course ID</td>
<td>Type</td>
<td>Tie Action</td>
<td>Order</td>
</tr>
<tr>
<td>${comparator.id}</td>
<td>${comparator.courseId}</td>
<td>${comparator.type}</td>
<td>${comparator.tieAction}</td>
<td>${comparator.comparatorOrder}</td>
</tr>
</table>
<table>
<tr>
<td>
<form:form commandName="comparator" action="deleteComparatorConfirmed.htm" method="post">
<input id="id" name="id" type="hidden" value="${comparator.id}" />
<input type="submit" value="Confirm Delete"></input>
</form:form>
</td>
<td>
<form:form action="listComparators.htm" method="get">
<input id="course_id" name="course_id" type="hidden" value="${comparator.courseId}" />
<input type="submit" value="Cancel"></input>
</form:form>
</td>
</tr>
</table>

</div>