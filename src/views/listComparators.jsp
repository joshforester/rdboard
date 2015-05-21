<%--
File: listComparators.jsp
Author: Joshua Forester
Date: 2009/08/26
Description: List Comparators page.
--%>



<%@ taglib prefix="c" uri="/tags/jstl-c" %>
<%@ taglib prefix='sec' uri="/tags/security" %>


<div class="rdb_panel">

<h3>Comparators</h3>

<table>
<tr>
<td>Comparator</td>
<td>Course ID</td>
<td>Type</td>
<td>Tie Action</td>
<td>Order</td>
</tr>
<c:forEach var="comparator" items="${comparators}">
<tr>
<td><sec:authorize ifAnyGranted="ROLE_ROOT">${comparator.id} </sec:authorize>(<a href="<c:url value="editComparator.htm?comparator_id=${comparator.id}" />">Edit</a> | <a href="<c:url value="deleteComparator.htm?comparator_id=${comparator.id}" />">Delete</a>)</td>
<td>${comparator.courseId}</td>
<td>${comparator.type}</td>
<td>${comparator.tieAction}</td>
<td>${comparator.comparatorOrder}</td>
</tr>
</c:forEach>
</table>
<br>
<br>
<center><a href="<c:url value="addComparator.htm" />">Add Comparator</a></center>

</div>