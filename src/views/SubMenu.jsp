<%--
File: SubMenu.jsp
Author: Joshua Forester
Date: 2009/08/13
Description: The home sub menu for the website.
--%>


<%@ taglib prefix="c" uri="/tags/jstl-c" %>
<%@ taglib prefix="sec" uri="/tags/security" %>


<div class="menuitems">

<div class="buttons">
<a href="<c:url value="/d/events" />">
<img src="<c:url value="/images/icons/arrow_down.gif" />" alt=""> 
Events
</a>
<c:if test="${not empty courseId}">
<a href="<c:url value="/d/courses/${courseId}/leaderboards.htm?style=full" />">
<img src="<c:url value="/images/icons/arrow_down.gif" />" alt=""> 
Leaderboard
</a>
<a href="<c:url value="/d/courses/${courseId}/cps/gmap.htm" />">
<img src="<c:url value="/images/icons/arrow_down.gif" />" alt=""> 
Course Map
</a>
<a href="<c:url value="/d/courses/${courseId}/updates.htm" />">
<img src="<c:url value="/images/icons/arrow_down.gif" />" alt=""> 
Updates
</a>
<c:if test="${not empty courseId}">
<a href="<c:url value="/d/courses/${courseId}/competitors/map.htm" />">
<img src="<c:url value="/images/icons/arrow_down.gif" />" alt=""> 
Competitor Map
</a>
</c:if>
</c:if>
</div>

</div>


<%--
<div class="buttons" style="float:right">
<a href="<c:url value="/d/login.htm" />" class="positive">
<img src="<c:url value="/images/icons/arrow_down.gif" />" alt=""> 
Login
</a>
</div>
--%>

