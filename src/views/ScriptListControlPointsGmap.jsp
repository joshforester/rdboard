<%--
File: ScriptListControlPointsGmap.jsp
Author: Joshua Forester
Date: 2010/01/11
Description: The common SCRIPT section for the list control points gmap app.
--%>


<%@ taglib prefix="c" uri="/tags/jstl-c" %>
<%@ taglib prefix="tiles" uri="/tags/tiles-jsp" %>

<%@ page import="com.myrunning.leaderboard.global.Constants" %>


<jsp:scriptlet><![CDATA[
   pageContext.setAttribute("gmapsApiKey", Constants.GMAPS_API_KEY, PageContext.PAGE_SCOPE);   
]]></jsp:scriptlet>

<script src="http://maps.google.com/maps?file=api&amp;v=2.x&amp;key=${gmapsApiKey}&amp;hl=&amp;sensor=false" type="text/javascript"></script>

<tiles:useAttribute id="list" name="jsfilesList" classname="java.util.List" />
<c:forEach var="jsfileName" items="${list}">
    <script src="<c:url value="${jsfileName}" />" type="text/javascript"></script>
</c:forEach>