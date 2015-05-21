<%--
File: CompetitorMap.jsp
Author: Joshua Forester
Date: 2010/04/25
Description: The competitor map stub page for the Atomic AR.
--%>


<%@ taglib prefix="c" uri="/tags/jstl-c" %>


<div class="rdb_panel">

<h3>Competitor Map</h3>

<c:choose>
<c:when test="${ not empty course && not empty course.competitorGmapSuffix && course.competitorGmapSuffix != 'default'}">

<p>
<iframe width="500" height="450" frameborder="0" scrolling="no" marginheight="0" marginwidth="0" src="http://maps.google.com/maps/${course.competitorGmapSuffix}&output=embed"></iframe>
<br>
<a href="http://maps.google.com/maps/${course.competitorGmapSuffix}&source=embed" target="_blank">View Larger Map</a>
</p>

</c:when>
<c:otherwise>

<p>
Not configured for this course.
</p>

</c:otherwise>
</c:choose>

</div>

