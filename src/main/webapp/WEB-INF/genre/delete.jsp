<%
    int id = (int) request.getAttribute("id");
    String name = (String) request.getAttribute("name");
%>

<%@include file="/WEB-INF/include/header.jsp" %>

<form action="<%= request.getContextPath() %>/genre" method="POST">
    <h1>Delete genre</h1>
    <p>Are you sure to delete genre <%= name %> with id <%= id %></p>
    <input type="hidden" name="action" value="delete"/>
    <input type="hidden" name="id" value="<%= id %>"/>
    <button type="submit" class="btn btn-danger" >Delete</button>
    <a class="btn bg-secondary text-white" href="<%= request.getContextPath() %>/genre">Back</a>
</form>

<%@include file="/WEB-INF/include/footer.jsp" %>