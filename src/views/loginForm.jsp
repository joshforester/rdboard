<%--
File: loginForm.jsp
Author: Joshua Forester
Date: 2009/10/23
Description: Login page.
--%>


<%@ taglib prefix="c" uri="/tags/jstl-c" %>
<%@ taglib prefix="tiles" uri="/tags/tiles-jsp" %>

<%@ page import="org.springframework.security.web.authentication.AbstractProcessingFilter" %>
<%@ page import="org.springframework.security.web.authentication.AuthenticationProcessingFilter" %>
<%@ page import="org.springframework.security.core.AuthenticationException" %>

<tiles:importAttribute name="pageTitle" />
<tiles:importAttribute name="submitUrl" />


<div class="rdb_panel">

    <h3>${pageTitle}:</h3>

    <%-- this form-login-page form is also used as the
         form-error-page to ask for a login again.
         --%>
    <c:if test="${not empty param.login_error}">
      <font color="red">
        Your login attempt was not successful, try again.<br/><br/>
        Reason: ${SPRING_SECURITY_LAST_EXCEPTION.message}.
      </font>
    </c:if>

    <form name="f" action="<c:url value="${submitUrl}" />" method="POST">
      <table>
        <tr>
		<td>User:</td>
		<td><input type='text' name='j_username' value='<c:if test="${not empty param.login_error}">${SPRING_SECURITY_LAST_USERNAME}</c:if>'/></td>
	</tr>
        <tr>
		<td>Password:</td>
		<td><input type='password' name='j_password'></td>
	</tr>
        <tr>
		<td><input type="checkbox" name="_spring_security_remember_me"></td>
		<td>Remember me</td>
	</tr>

        <tr>
		<td><input name="submit" value="Login" type="submit"></td>
		<td><input name="reset" value="Reset" type="reset"></td>
	</tr>
      </table>

    </form>

</div>
