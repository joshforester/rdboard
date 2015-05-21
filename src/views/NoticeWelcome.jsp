<%--
File: NoticeWelcome.jsp
Author: Joshua Forester
Date: 2009/09/15
Description: The Welcome notice section of the website.
--%>


<%@ taglib prefix="c" uri="/tags/jstl-c" %>


<c:if test='${currentAdmin != null}'>

<div class="rdb_panel">
Welcome ${currentAdmin.firstName} ${currentAdmin.lastName}!
</div>

</c:if>