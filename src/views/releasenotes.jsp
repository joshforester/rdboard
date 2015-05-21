<%--
File: releasenotes.jsp
Author: Joshua Forester
Date: 2009/08/11
Description: Release notes page.
--%>


<%@ taglib prefix="spring" uri="/tags/spring" %>

<div class="rdb_panel">

<i><b>Current Release:</b></i><br />
<i>Leaderboard <spring:message code="text.global.releaseversion" /></i><br />
<ul>
<li>Added customizable event and sponsor images, captions, and descriptions.</li>
<li>Streamlined the CpVisit data entry process.</li>
<li>User can expand/shrink leaderboard.</li>
<li>Added headers on all sides of leaderboard.</li>
<li>Update tabs now appear only if configured.</li>
<li>Update CpVisit service now available.</li>
<li>Minor bug fixes.</li>
</ul>

<hr style="width: 70%; height: 2px;">

<i><b>Previous Releases:</b></i><br />

<i>Leaderboard 0.9</i><br />
<ul>
<li>Integrated secure login/logout functionality with Spring Security.</li>
<li>Added url, content, and domain object security controls utilizing role-based and uid/gid-based access control.</li>
<li>Added Leaderboard sorting algorithm.</li>
<li>Made all domain objects sortable.</li>
<li>Lots of code refactoring.</li>
<li>Input validation methods for all domain objects.</li>
<li>Restructured validators to be reusable.</li>
<li>Combined TEAM and Team_Competing_In_Rel objects.</li>
<li>Added notion of identity.</li>
<li>Converted URLs over to RESTful notation.</li>
<li>Added support for XML and JSON object views.</li>
<li>Major performance increases.</li>
<li>Bugfixes and UI improvements for edit cp visit batch module.</li>
<li>Upgrade to Spring 3.0.</li>
<li>Migrated codebase to SOA.</li>
<li>Created Google Maps CP view.</li>
</ul>

<i>Leaderboard 0.8</i><br />
<ul>
<li>Added login/logout functionality.</li>
<li>Added notice section to layout.</li>
<li>Added CP Visit batch edit by team and by CP.</li>
</ul>

<i>Leaderboard 0.7</i><br />
<ul>
<li>Added Leaderboard Display with proper data structures</li>
<li>Added Support for Team_Competing_In_Rel and Team_Instance_Rel</li>
<li>Changed Competitor Is-A relationship to Person to Has-A relationship.</li>
<li>Added Add/Edit Registration Webflow (to include validation)</li>
</ul>

<i>Leaderboard 0.6</i><br />
<ul>
<li>Added Comparators</li>
<li>Fixed ordered object edit bug.</li>
</ul>

<i>Leaderboard 0.5</i><br />
<ul>
<li>Added Persons, Competitors, Teams, CP_Visits</li>
<li>Fixed global insert on update problem.</li>
</ul>

<i>Leaderboard 0.4</i><br />
<ul>
<li>Added Events, Courses, Divisions, CPs</li>
<li>Added create and update SimpleFormControllers.</li>
<li>Added list and delete annotated controllers.</li>
<li>Added validation support and util classes to assist.</li>
</ul>

<i>Leaderboard 0.3</i><br />
<ul>
<li>Added form input controllers.</li>
<li>Added validation support.</li>
<li>Added event & course forms.</li>
<li>Added dynamic form field generation.</li>
<li>Added CustomTimestampEditor for initBinder</li>
</ul>

<i>Leaderboard 0.2</i><br />
<ul>
<li>Added support for iBatis.</li>
</ul>

<i>Leaderboard 0.1</i><br />
<ul>
<li>Added support for Annotations, primarily to be used for rapid ActionForward-style Controllers.</li>
<li>Added support for internationalization via ResourceBundles.</li>
<li>Added support for log4j, using MDC (session-specific).</li>
<li>Added support for Tiles 2 views.
<li>Fixed bug in configuration for loading applicationContext.xml.</li>
</ul>

</div>