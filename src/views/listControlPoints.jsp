<%--
File: listControlPoints.jsp
Author: Joshua Forester
Date: 2009/08/20
Description: List Control Points page.
--%>



<%@ taglib prefix="form" uri="/tags/spring-form" %>
<%@ taglib prefix="c" uri="/tags/jstl-c" %>
<%@ taglib prefix="sec" uri="/tags/security" %>


<div class="rdb_panel">

<h3>Control Points</h3>

<form:errors path="cpOrderShift" htmlEscape="false" /><br />

<table>
<tr>
<sec:authorize ifAnyGranted="ROLE_ROOT,ROLE_PROMOTER">
<td>Control Point</td>
</sec:authorize>
<td>Course ID</td>
<td>Order</td>
<td>Name</td>
<td>TA Name</td>
<td>Zone Number</td>
<td>Zone Letter</td>
<td>Easternly</td>
<td>Northernly</td>
<td>Hint</td>
<td>Altitude</td>
<td>Cutoff</td>
<td>Mandatory?</td>
<td>Weight</td>
<td>From Discipline</td>
<td>To Discipline</td>
<td>CP Visits</td>
</tr>
<c:forEach var="controlPoint" varStatus="iter" items="${controlPoints}">
<tr>
<sec:authorize ifAnyGranted="ROLE_ROOT,ROLE_PROMOTER"><td><sec:authorize ifAnyGranted="ROLE_ROOT">${controlPoint.id} </sec:authorize>(<a href="<c:url value="/d/editControlPoint.htm?cp_id=${controlPoint.id}" />">Edit</a> | <a href="<c:url value="/d/deleteControlPoint.htm?cp_id=${controlPoint.id}" />">Delete</a>)</td></sec:authorize>
<td>${controlPoint.courseId}</td>
<td>
${controlPoint.cpOrder}
<sec:authorize ifAnyGranted="ROLE_ROOT,ROLE_PROMOTER">
<c:if test="${!iter.first}">
<form:form id="d${controlPoint.id}" commandName="cpOrderShift" action="/r/d/editCpOrder.htm" method="post">
<input type="hidden" name="cpId" value="${controlPoint.id}" /><form:errors path="cpId" htmlEscape="false" />
<input type="hidden" name="change" value="decrement" /><form:errors path="change" htmlEscape="false" />
<div class="buttons">
<button type="submit" class="positive">
<img src="<c:url value="/images/icons/arrow_up.png" />" alt=""/>
</button>
</div>
</form:form>
</c:if>

<c:if test="${!iter.last}">
<form:form id="i${controlPoint.id}" commandName="cpOrderShift" action="/r/d/editCpOrder.htm" method="post">
<input type="hidden" name="cpId" value="${controlPoint.id}" /><form:errors path="cpId" htmlEscape="false" />
<input type="hidden" name="change" value="increment" /><form:errors path="change" htmlEscape="false" />
<div class="buttons">
<button type="submit" class="negative">
<img src="<c:url value="/images/icons/arrow_down.gif" />" alt=""/>
</button>
</div>
</form:form>
</c:if>
</sec:authorize>
</td>
<td>${controlPoint.name}</td>
<td>${controlPoint.taName}</td>
<td>${controlPoint.zoneNumber}</td>
<td>${controlPoint.zoneChar}</td>
<td>${controlPoint.easternly}</td>
<td>${controlPoint.northernly}</td>
<td>${controlPoint.hint}</td>
<td>${controlPoint.altitude}</td>
<td>${controlPoint.cutoff}</td>
<td>${controlPoint.isMandatory}</td>
<td>${controlPoint.weight}</td>
<td>${controlPoint.fromDiscipline}</td>
<td>${controlPoint.toDiscipline}</td>
<td><a href="<c:url value="/d/cps/${controlPoint.id}/cpvisits" />">View</a></td>
</tr>
</c:forEach>
</table>
<br>
<br>
<center><sec:authorize ifAnyGranted="ROLE_ROOT,ROLE_PROMOTER"><a href="<c:url value="/d/addControlPoint.htm" />">Add ControlPoint</a> |</sec:authorize> <a href="<c:url value="/d/courses/${courseId}/cps?order=order" />">Sort By Order</a> | <a href="<c:url value="/d/courses/${courseId}/cps?order=name" />">Sort By Name</a> | <a href="<c:url value="/d/courses/${courseId}/cps?order=mandatory" />">Sort By Mandatory</a> | <a href="<c:url value="/d/courses/${courseId}/cps?order=weight" />">Sort By Weight</a> | <a href="<c:url value="/d/courses/${courseId}/cps?order=todiscipline" />">Sort By To Discipline</a></center>

</div>