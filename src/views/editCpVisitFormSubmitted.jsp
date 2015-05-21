<%--
File: editCpVisitFormSubmitted.jsp
Author: Joshua Forester
Date: 2009/08/25
Description: Edit cpVisit success page.
--%>


<%@ taglib prefix="c" uri="/tags/jstl-c" %>
<%@ taglib prefix="form" uri="/tags/spring-form" %>


<div class="rdb_panel">

CP Visit submitted!
<br>
<br>
Go To:
<br>
<a href="<c:url value="/d/courses/${course.id}/leaderboards.htm?style=full" />">Leaderboard</a> | 
<a href="<c:url value="/d/editCpVisit.htm?team_id=${teamId}&cp_id=${cpId}" />">Edit Again</a> 
<br>

</div>