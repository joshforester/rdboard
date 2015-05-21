<%--
File: Script.jsp
Author: Joshua Forester
Date: 2009/08/05
Description: The common SCRIPT section of the website.
--%>


<%@ taglib prefix="c" uri="/tags/jstl-c" %>
<%@ taglib prefix="tiles" uri="/tags/tiles-jsp" %>


<%--<script src="http://cdn.jquerytools.org/1.1.2/full/jquery.tools.min.js" type="text/javascript"></script> --%>
<%--
<script src="<c:url value="/js/jquery-1.3.2.min.js" />" type="text/javascript"></script>
<script src="<c:url value="/js/jquery.debug.js" />" type="text/javascript"></script>
--%>

<tiles:useAttribute id="list" name="jsfilesList" classname="java.util.List" />
<c:forEach var="jsfileName" items="${list}">
    <script src="<c:url value="${jsfileName}" />" type="text/javascript"></script>
</c:forEach>