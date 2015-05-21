<%--
File: addRegistrationSelectEvent.jsp
Author: Joshua Forester
Date: 2009/09/01
Description: Select event portion of addRegistrationFlow
--%>


<%@ taglib prefix="c" uri="/tags/jstl-c" %>
<%@ taglib prefix="form" uri="/tags/spring-form" %>

<div class="rdb_panel">

Select event.<br>

<form:form modelAttribute="registration" action="${flowExecutionUrl}" method="post">
<form:select path="eventId">
<c:forEach var="event" items="${events}">
<form:option value="${event.id}" label="${event.name}" />
</c:forEach>
</form:select>
<input type="submit" name="_eventId_submit" value="Next" />
<input type="submit" name="_eventId_cancel" value="Cancel" />
</form:form>

</div>