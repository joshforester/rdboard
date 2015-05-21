<%--
File: addRegistrationSelectMembers.jsp
Author: Joshua Forester
Date: 2009/09/01
Description: Select members portion of addRegistrationFlow
--%>


<%@ taglib prefix="c" uri="/tags/jstl-c" %>
<%@ taglib prefix="form" uri="/tags/spring-form" %>

<div class="rdb_panel">

Select members.  1st member should be the captain.

<form:form modelAttribute="registration" action="${flowExecutionUrl}" method="post">

<c:forTokens var="memberId" items="1,2,3,4" delims=",">

<form:select path="member${memberId}Id">
<option value="0" selected="selected">-- Select Competitor --</option>
<c:forEach var="person" items="${persons}">
<form:option value="${person.id}" label="${person.lastName}, ${person.firstName}" />
</c:forEach>
</form:select>

</c:forTokens>

<center>Competitor Not in List?  <a target="_blank" href="<c:url value="addCompetitor.htm" />">Add Competitor</a>, then refresh this page.</center>

<input type="submit" name="_eventId_submit" value="Next" />
<input type="submit" name="_eventId_cancel" value="Cancel" />
</form:form>

</div>