<%--
File: MainSlideshow.jsp
Author: Joshua Forester
Date: 2010/02/12
Description: The 
--%>


<%@ taglib prefix="c" uri="/tags/jstl-c" %>
<%@ taglib prefix="spring" uri="/tags/spring" %>


<div id="slider">

<c:if test="${not empty event}">

 <c:if test="${event.eventUrl != 'default'}">
  <div class="slide">
    <img src="${event.eventUrl}" alt="<c:if test="${event.eventCaption != 'default'}">${event.eventCaption}</c:if>" title="<c:if test="${event.eventCaption != 'default'}">${event.eventCaption}</c:if>">
    <span class="top"><strong>Event</strong><br /><c:if test="${event.eventCaption != 'default'}">${event.eventCaption}</c:if></span>
  </div>
 </c:if>

 <c:if test="${event.sponsorUrl != 'default'}">
  <div class="slide">
    <img src="${event.sponsorUrl}" alt="<c:if test="${event.sponsorTitle != 'default'}">${event.sponsorTitle}</c:if>" title="<c:if test="${event.sponsorCaption != 'default'}">${event.sponsorCaption}</c:if>">
    <span class="top"><strong><c:choose><c:when test="${event.sponsorTitle != 'default'}">${event.sponsorTitle}</c:when><c:otherwise>Event Sponsor</c:otherwise></c:choose></strong><br /><c:if test="${event.sponsorCaption != 'default'}">${event.sponsorCaption}</c:if></span>
  </div>
 </c:if>

</c:if>
  
  <div class="slide">
    <img src="<spring:message code="images.sponsors.4lph41337adventures" />" alt="<spring:message code="images.sponsors.4lph41337adventures.alt" />" title="<spring:message code="images.sponsors.4lph41337adventures.title" />">
    <span class="top"><strong><spring:message code="images.sponsors.4lph41337adventures.alt" /></strong><br /><spring:message code="images.sponsors.4lph41337adventures.caption" /></span>
  </div>
</div>
