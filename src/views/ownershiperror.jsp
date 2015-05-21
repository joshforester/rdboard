<%--
File: ownershiperror.jsp
Author: Joshua Forester
Date: 2009/10/21
Description: Ownership error page.  Blackhole page for data access problems.
--%>


<%@ taglib prefix="spring" uri="/tags/spring" %>


<div class="rdb_panel">

If you are seeing this error it is because you:
<br>
<ol>
<li>Do not own the data you are attempting to edit or delete.</li>
<li>Do not own the data associated with the data you are adding or editing.</li>
</ol>
<br>
If you believe that do not fall under these circumstances, please verify that you are logged in.
<br>
Otherwise, please feel free to contact us about the error.

</div>