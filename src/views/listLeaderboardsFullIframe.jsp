<%--
File: listLeaderboardsFullIframe.jsp
Author: Joshua Forester
Date: 2010/01/01
Description: Default page.
--%>


<%@ taglib prefix="c" uri="/tags/jstl-c" %>


<div class="rdb_panel_ifr">

<iframe id="lbd" src="<c:url value="/d/listLeaderboards.htm?event_id=${eventId}&course_id=${courseId}&style=full&layout=raw" />" frameborder="0">
<p>Your browser does not support iframes.</p>
</iframe>

</div>