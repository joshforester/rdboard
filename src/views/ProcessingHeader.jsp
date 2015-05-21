<%--
File: ProcessingHeader.jsp
Author: Joshua Forester
Date: 2009/08/11
Description: The common JSP header section for the website.
--%>


<%@ taglib prefix="c" uri="/tags/jstl-c" %>
<%@ taglib prefix="log" uri="/tags/taglibs-log" %>
<%@ page import="org.apache.log4j.MDC" %>


<c:set var="sessionId" scope="session" value="${pageContext.session.id}" />
<% MDC.put("Session", request.getSession().getId()); %> 
<log:info>testing session id</log:info> 
