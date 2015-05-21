<%--
File: MainMenu.jsp
Author: Joshua Forester
Date: 2010/02/11
Description: The home main menu for the website.
--%>


<%@ taglib prefix="c" uri="/tags/jstl-c" %>
<%@ taglib prefix="sec" uri="/tags/security" %>


<div class="menuitems">

<div class="buttons">
<sec:authorize ifAnyGranted="ROLE_ROOT,ROLE_PROMOTER">
<a href="<c:url value="/d/addRegistrationFlow.htm" />">
<img src="<c:url value="/images/icons/arrow_down.gif" />" alt=""> 
Register
</a>
</sec:authorize>
<%--
<sec:authorize ifAnyGranted="ROLE_ROOT,ROLE_PROMOTER">
<a href="<c:url value="/d/listCompetitors.htm" />">
<img src="<c:url value="/images/icons/arrow_down.gif" />" alt=""> 
Competitors
</a>
</sec:authorize>
--%>
<sec:authorize ifAnyGranted="ROLE_ROOT,ROLE_PROMOTER">
<a href="<c:url value="/d/addTeam.htm" />">
<img src="<c:url value="/images/icons/arrow_down.gif" />" alt=""> 
Teams
</a>
</sec:authorize>
<sec:authorize ifAnyGranted="ROLE_ROOT">
<a href="<c:url value="/d/listAdmins.htm" />">
<img src="<c:url value="/images/icons/arrow_down.gif" />" alt=""> 
Admins
</a>
</sec:authorize>
<sec:authorize ifAnyGranted="ROLE_ROOT,ROLE_PROMOTER">
<a href="<c:url value="/d/addCourse.htm" />">
<img src="<c:url value="/images/icons/arrow_down.gif" />" alt=""> 
Courses
</a>
<a href="<c:url value="/d/addDivision.htm" />">
<img src="<c:url value="/images/icons/arrow_down.gif" />" alt=""> 
Divisions
</a>
<a href="<c:url value="/d/addControlPoint.htm" />">
<img src="<c:url value="/images/icons/arrow_down.gif" />" alt=""> 
CPs
</a>
</sec:authorize>
</div>

</div>

<%--
<c:if test='${currentAdmin == null}'>
<form name="f" action="<c:url value="/j_spring_security_check" />" method="POST">
<div style="float:right;color:#000000;display:block;">
User:<input type='text' name='j_username' value=''/>
Password:<input type='password' name='j_password'>
</div>
</c:if>
--%>
<div class="buttons" style="float:right">
<c:choose>
<c:when test='${currentAdmin == null}'>
<%--
<div class="form_actions">

<div id="submit" class="buttons form_action">
<button type="submit">
<img src="<c:url value="/images/icons/arrow_down.gif" />" alt="">
Login
</button>
</div>

</div>

</form>

--%>
<a href="<c:url value="/d/login.htm" />" class="positive">
<img src="<c:url value="/images/icons/arrow_down.gif" />" alt=""> 
Login
</a>
</c:when>
<c:otherwise>
<a href="<c:url value="/d/logout.htm" />" class="negative">
<img src="<c:url value="/images/icons/arrow_down.gif" />" alt=""> 
Logout
</a>
</c:otherwise>
</c:choose>
</div>
