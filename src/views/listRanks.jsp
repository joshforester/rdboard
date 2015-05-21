<%--
File: listRanks.jsp
Author: Joshua Forester
Date: 2010/01/16
Description: List ranks page.
--%>



<%@ taglib prefix="c" uri="/tags/jstl-c" %>
<%@ taglib prefix="sec" uri="/tags/security" %>
<%@ taglib prefix="rdb" uri="/tags/rdb" %>


<div class="rdb_panel">

<h3>Ranks</h3>

<table>
<tr>
<td>Team ID</td>
<td>Time</td>
<td>Division Place</td>
<td>Course Place</td>
</tr>
<c:forEach var="rank" items="${ranks}">
<tr>
<td>${rank.teamId}</td>
<td>${rdb:fmtLclTs(rank.time,'yyyy-MM-dd HH:mm')}</td>
<td>${rank.divisionPlace}</td>
<td>${rank.coursePlace}</td>
</tr>
</c:forEach>
</table>
<br>
<br>
<center><a href="<c:url value="/d/teams/${teamId}/ranks"><c:param name="order" value="time" /></c:url>">Sort By Time</a> | <a href="<c:url value="/d/teams/${teamId}/ranks"><c:param name="order" value="divisionplace" /></c:url>">Sort By Division Place</a> | <a href="<c:url value="/d/teams/${teamId}/ranks"><c:param name="order" value="courseplace" /></c:url>">Sort By Course Place</a></center>

</div>