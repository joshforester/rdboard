<%--
File: LayoutRaw.jsp
Author: Joshua Forester
Date: 2009/11/13
Description: Default page.
--%>


<%@ taglib prefix="c" uri="/tags/jstl-c" %>
<%@ taglib prefix="tiles" uri="/tags/tiles-jsp" %>


<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<c:set var="layout" scope="request" value="_raw" />


<tiles:insertAttribute name="ProcessingHeader" />
<tiles:insertAttribute name="DoctypeHeader" />
<html>

<head>
<title><tiles:getAsString name="title" /></title>
<tiles:insertAttribute name="Link1" />
<tiles:insertAttribute name="Link2" />
<tiles:insertAttribute name="Meta" /> 
<tiles:insertAttribute name="Script" />
</head>

<tiles:insertAttribute name="Body" />

<div id="rdb_page${layout}">


<tiles:insertAttribute name="ContentTopLt1"/>
<tiles:insertAttribute name="ContentTopLt2"/>
<tiles:insertAttribute name="ContentTopLt3"/>
<tiles:insertAttribute name="ContentTopLt4"/>
<tiles:insertAttribute name="ContentTopLt5"/>

<tiles:insertAttribute name="Footer1"/>


</div>

<tiles:insertAttribute name="GoogleAnalytics"/>

</body>
</html>