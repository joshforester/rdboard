<%--
File: LayoutView.jsp
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

<c:set var="layout" scope="request" value="_view" />


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


<div id="rdb_hdr_pane" class="pg_hdr">

<div id="menu_bar_ctn">
<div id="main_menu_bar">
<tiles:insertAttribute name="MainMenu" />
</div>

<div id="sub_menu_bar" style="margin-bottom:7px;">
<tiles:insertAttribute name="SubMenu" /> 
</div>
</div>

<div id="slideshow">
<tiles:insertAttribute name="TopAds" />
</div>

</div>


<div id="rdb_body_pane">

<div class="rdb_top_lt_pane">
<tiles:insertAttribute name="ContentTopLt1"/>
<tiles:insertAttribute name="ContentTopLt2"/>
<tiles:insertAttribute name="ContentTopLt3"/>
<tiles:insertAttribute name="ContentTopLt4"/>
<tiles:insertAttribute name="ContentTopLt5"/>
</div>

</div>


<div class="rdb_top_lt_pane">
<tiles:insertAttribute name="Footer1"/>
<tiles:insertAttribute name="Footer2"/>
</div>



</div>

<tiles:insertAttribute name="GoogleAnalytics"/>

</body>
</html>