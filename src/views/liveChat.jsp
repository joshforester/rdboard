<%--
File: liveChat.jsp
Author: Joshua Forester
Date: 2010/01/15
Description: iframe to ScribbleLive.com event.
--%>


<%@ taglib prefix="c" uri="/tags/jstl-c" %>


<div class="rdb_panel">

<c:choose>
<c:when test='${ (not empty course.formattedScribbleliveId) || (not empty course.formattedCoveritliveId) }'>
<h3>Live Chat</h3>

<c:if test='${ not empty course.formattedScribbleliveId }'>
<%--
<iframe src='http://embed.scribblelive.com/${course.formattedScribbleliveId}' width='250' height='500' frameborder='0' style='border: 1px solid #000'></iframe>
--%>
<iframe src='http://embed.scribblelive.com/Embed/v5.aspx?Id=${course.scribbleliveId}' width='250' height='500' frameborder='0' style='border: 1px solid #000'></iframe>
</c:if>

<c:if test='${ not empty course.formattedCoveritliveId }'>
<iframe src="http://www.coveritlive.com/index2.php/option=com_altcaster/task=viewaltcast/altcast_code=${course.formattedCoveritliveId}/height=600/width=750" scrolling="no" height="600px" width="750px" frameBorder="0" style="border: 1px solid #000"><a href="http://www.coveritlive.com/mobile.php/option=com_mobile/task=viewaltcast/altcast_code=${course.formattedCoveritliveId}" >${course.name}</a></iframe>
</c:if>

</c:when>
<c:otherwise>
Live chat not configured for course.
</c:otherwise>
</c:choose>

</div>