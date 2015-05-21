<%--
File: LayoutDashboard.jsp
Author: Joshua Forester
Date: 2009/11/12
Description: Default page.
--%>


<%@ taglib prefix="c" uri="/tags/jstl-c" %>
<%@ taglib prefix="tiles" uri="/tags/tiles-jsp" %>


<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<c:set var="layout" scope="request" value="_dashboard" />


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


<div id="rdb_lt_col">

<div class="rdb_top_lt_pane">
<tiles:insertAttribute name="ContentTopLt1"/>
<tiles:insertAttribute name="ContentTopLt2"/>
<tiles:insertAttribute name="ContentTopLt3"/>
<tiles:insertAttribute name="ContentTopLt4"/>
<tiles:insertAttribute name="ContentTopLt5"/>
</div>

<div id="rdb_lt_pane">
<tiles:insertAttribute name="ContentLt1"/>
<tiles:insertAttribute name="ContentLt2"/>
<tiles:insertAttribute name="ContentLt3"/>
<tiles:insertAttribute name="ContentLt4"/>
<tiles:insertAttribute name="ContentLt5"/>
</div>

<div id="rdb_md_pane">
<tiles:insertAttribute name="ContentMd1"/>
<tiles:insertAttribute name="ContentMd2"/>
<tiles:insertAttribute name="ContentMd3"/>
<tiles:insertAttribute name="ContentMd4"/>
<tiles:insertAttribute name="ContentMd5"/>
</div>

</div>


<div id="rdb_rt_col">
<tiles:insertAttribute name="ContentRt1"/>
<tiles:insertAttribute name="ContentRt2"/>
<tiles:insertAttribute name="ContentRt3"/>
<tiles:insertAttribute name="ContentRt4"/>
<tiles:insertAttribute name="ContentRt5"/>
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