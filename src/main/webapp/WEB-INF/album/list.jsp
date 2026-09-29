<%@page import="model.Album"%>
<%@page import="java.util.List"%>
<%@include file="/WEB-INF/include/header.jsp" %>

<form action="<%= request.getContextPath()%>/album" method="GET" class="d-inline">
    <button type="submit" name="view" value="create" class="btn btn-success">Add album</button>
</form>
<h1>Album list</h1>
<table class="table table-hover">
    <tr>
        <th>ID</th>
        <th>Album Name</th>
        <th>Artist Name</th>
        <th>Actions</th>
    </tr>
    <%
        List<Album> albumList = (List<Album>) request.getAttribute("list");
    %>
    <% for (Album al : albumList) {%>
    <tr>
        <td><%= al.getId() %></td>
        <td><%= al.getTitle() %></td>
        <td><%= al.getArtist().getName() %></td>
        <td>
            <a class="btn btn-primary btn-sm" href="<%= request.getContextPath() %>/artist?view=edit&id=<%= al.getId() %>">Edit</a>
            <a class="btn btn-danger btn-sm" href="<%= request.getContextPath() %>/artist?view=delete&id=<%= al.getId() %>">Delete</a>
        </td>
    </tr>
    <%
        }
    %>
</table>

<%@include file="/WEB-INF/include/footer.jsp" %>
