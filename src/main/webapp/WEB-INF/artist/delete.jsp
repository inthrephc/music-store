<%
    int artistId = (int) request.getAttribute("artistId");
    String artistName = (String) request.getAttribute("artistName");
%>

<%@include file="/WEB-INF/include/header.jsp" %>

<form action="<%= request.getContextPath() %>/artist" method="POST">
    <h1>Delete artist</h1>
    <p>Are you sure to delete artist <%= artistName %> with id <%= artistId %></p>
    <input type="hidden" name="action" value="delete"/>
    <input type="hidden" name="id" value="<%= artistId %>"/>
    <button type="submit" class="btn btn-danger" >Delete</button>
    <a class="btn bg-secondary text-white" href="<%= request.getContextPath() %>/artist">Back</a>
</form>

<%@include file="/WEB-INF/include/footer.jsp" %>