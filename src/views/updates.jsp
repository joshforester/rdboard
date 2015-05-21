<%--
File: updates.jsp
Author: Joshua Forester
Date: 2010/02/24
Description: The feedburner content of the website.
--%>

<%@ taglib prefix="c" uri="/tags/jstl-c" %>


<div id="tabs">
<ul>
<c:if test="${course.feedburnerAllId ne 'default'}">
<li><a href="#fragment-1"><span>All Feeds</span></a></li>
</c:if>
<c:if test="${course.feedburnerBlogsId ne 'default'}">
<li><a href="#fragment-2"><span>Blogs</span></a></li>
</c:if>
<c:if test="${course.feedburnerPhotosId ne 'default'}">
<li><a href="#fragment-3"><span>Photos</span></a></li>
</c:if>
<c:if test="${course.feedburnerVideosId ne 'default'}">
<li><a href="#fragment-4"><span>Videos</span></a></li>
</c:if>
<c:if test="${course.feedburnerAudioId ne 'default'}">
<li><a href="#fragment-5"><span>Audio</span></a></li>
</c:if>
<li><a href="<c:url value="/d/courses/${courseId}/lbdupdates.htm" />"><span>Leaderboard</span></a></li>
</ul>


<c:if test="${course.feedburnerAllId ne 'default'}">
<div id="fragment-1">

<h3>All Feeds</h3>

<script src="http://feeds.feedburner.com/${course.feedburnerAllId}?format=sigpro&nItems=0&displayTitle=false&displayExcerpts=true&excerptFormat=full&excerptLength=0&displayDate=true&dateLocation=above&dateFormat=MMMM d, yyyy&displayEnclosures=true&openLinks=new" type="text/javascript" ></script><noscript><p>Subscribe to RSS headline updates from: <a href="http://feeds.feedburner.com/${course.feedburnerAllId}"></a><br/>Powered by FeedBurner</p> </noscript>

</div>
</c:if>


<c:if test="${course.feedburnerBlogsId ne 'default'}">
<div id="fragment-2">

<h3>Blog Feed</h3>

<script src="http://feeds.feedburner.com/${course.feedburnerBlogsId}?format=sigpro&nItems=0&displayTitle=false&displayExcerpts=true&excerptFormat=full&excerptLength=0&displayDate=true&dateLocation=above&dateFormat=MMMM d, yyyy&displayEnclosures=true&openLinks=new" type="text/javascript" ></script><noscript><p>Subscribe to RSS headline updates from: <a href="http://feeds.feedburner.com/${course.feedburnerBlogsId}"></a><br/>Powered by FeedBurner</p> </noscript>

</div>
</c:if>


<c:if test="${course.feedburnerPhotosId ne 'default'}">
<div id="fragment-3">

<h3>Photo Feed</h3>

<script src="http://feeds.feedburner.com/${course.feedburnerPhotosId}?format=sigpro&nItems=0&displayTitle=false&displayExcerpts=true&excerptFormat=full&excerptLength=0&displayDate=true&dateLocation=above&dateFormat=MMMM d, yyyy&displayEnclosures=true&openLinks=new" type="text/javascript" ></script><noscript><p>Subscribe to RSS headline updates from: <a href="http://feeds.feedburner.com/${course.feedburnerPhotosId}"></a><br/>Powered by FeedBurner</p> </noscript>

</div>
</c:if>


<c:if test="${course.feedburnerVideosId ne 'default'}">
<div id="fragment-4">

<h3>Video Feed</h3>

<script src="http://feeds.feedburner.com/${course.feedburnerVideosId}?format=sigpro&nItems=0&displayTitle=false&displayExcerpts=true&excerptFormat=full&excerptLength=0&displayDate=true&dateLocation=above&dateFormat=MMMM d, yyyy&displayEnclosures=true&openLinks=new" type="text/javascript" ></script><noscript><p>Subscribe to RSS headline updates from: <a href="http://feeds.feedburner.com/${course.feedburnerVideosId}"></a><br/>Powered by FeedBurner</p> </noscript>

</div>
</c:if>


<c:if test="${course.feedburnerAudioId ne 'default'}">
<div id="fragment-5">

<h3>Audio Feed</h3>

<script src="http://feeds.feedburner.com/${course.feedburnerAudioId}?format=sigpro&nItems=0&displayTitle=false&displayExcerpts=true&excerptFormat=full&excerptLength=0&displayDate=true&dateLocation=above&dateFormat=MMMM d, yyyy&displayEnclosures=true&openLinks=new" type="text/javascript" ></script><noscript><p>Subscribe to RSS headline updates from: <a href="http://feeds.feedburner.com/${course.feedburnerAudioId}"></a><br/>Powered by FeedBurner</p> </noscript>

</div>
</c:if>


</div>

