<%@page import="model.Genre"%>
<%@page import="java.util.List"%>
<%@include file="/WEB-INF/include/header.jsp" %>

<div class="d-flex justify-content-end mb-3">
    <form action="<%= request.getContextPath()%>/genre" method="GET" class="d-inline">
        <button type="submit" name="view" value="create" class="btn btn-success">Add genre</button>
    </form>
</div>
<h1>Genre list</h1>
<table class="table table-hover">
    <tr>
        <th>ID</th>
        <th>Genre Name</th>
        <th>Actions</th>
    </tr>
    <%
        List<Genre> genreList = (List<Genre>) request.getAttribute("list");
    %>
    <% for (Genre genre : genreList) {%>
    <tr>
        <td><%= genre.getId()%></td>
        <td><%= genre.getName()%></td>
        <td>
            <a class="btn btn-primary btn-sm" href="<%= request.getContextPath()%>/genre?view=edit&id=<%= genre.getId()%>">Edit</a>
            <a class="btn btn-danger btn-sm" href="<%= request.getContextPath()%>/genre?view=delete&id=<%= genre.getId()%>">Delete</a>
        </td>
    </tr>
    <%
        }
    %>
</table>

<%@include file="/WEB-INF/include/footer.jsp" %>
