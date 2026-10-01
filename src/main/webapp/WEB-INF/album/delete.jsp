<%
    int id = (int) request.getAttribute("id");
    String title = (String) request.getAttribute("title");
%>

<%@include file="/WEB-INF/include/header.jsp" %>

<form action="<%= request.getContextPath() %>/album" method="POST">
    <h1>Delete album</h1>
    <p>Are you sure to delete album named "<%= title %>"</p>
    <input type="hidden" name="action" value="delete"/>
    <input type="hidden" name="id" value="<%= id %>"/>
    <button type="submit" class="btn btn-danger" >Delete</button>
    <a class="btn bg-secondary text-white" href="<%= request.getContextPath() %>/album">Back</a>
</form>

<%@include file="/WEB-INF/include/footer.jsp" %>