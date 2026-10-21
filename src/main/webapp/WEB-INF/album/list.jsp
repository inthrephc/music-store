<%@page import="model.Album"%>
<%@page import="java.util.List"%>
<%@include file="/WEB-INF/include/header.jsp" %>

<div class="d-flex justify-content-end mb-3">
    <form action="<%= request.getContextPath()%>/album" method="GET" class="d-inline">
        <button type="submit" name="view" value="create" class="btn btn-success">Add album</button>
    </form>
</div>
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
    
    <% if (albumList.size() == 0) { %>
    <tr>
        <td colspan="4">No album</td>
    </tr>
    <% } else { %>
    <% for (Album al : albumList) {%>
    <tr>
        <td><%= al.getId()%></td>
        <td><%= al.getTitle()%></td>
        <td><%= al.getArtist().getName()%></td>
        <td>
            <a class="btn btn-primary btn-sm" href="<%= request.getContextPath()%>/album?view=edit&id=<%= al.getId()%>">Edit</a>
            <a class="btn btn-danger btn-sm" href="<%= request.getContextPath()%>/album?view=delete&id=<%= al.getId()%>">Delete</a>
        </td>
    </tr>
    <%
        }
    %>
</table>
<% if ((int) request.getAttribute("totalPages") > 1) { %>
<nav aria-label="Album Pagination">
    <ul class="pagination justify-content-center">
        <li class="page-item <% if ((int) request.getAttribute("currentPage") <= 1) { %>disabled<% } %>">
            <a class="page-link" href="${pageContext.request.contextPath}/album?page=${currentPage - 1}&size=${pageSize}">Prev</a>
        </li>

        <% for (int i : (List<Integer>) request.getAttribute("pages")) { %>
        <% if (i == -1) { %>
        <li class="page-item disabled">
            <span class="page-link">...</span>
        </li>
        <% } else if (i == (int) request.getAttribute("currentPage")) {%> 
        <li class="page-item active">
            <a class="page-link" href="${pageContext.request.contextPath}/album?page=<%= i%>&size=${pageSize}">
                <%= i%>
            </a>
        </li>
        <%} else {%>
        <li class="page-item">
            <a class="page-link" href="${pageContext.request.contextPath}/album?page=<%= i%>&size=${pageSize}">
                <%= i%>
            </a>
        </li>
        <%}%>
        <%}%>

        <li class="page-item <% if ((int) request.getAttribute("currentPage") >= (int) request.getAttribute("totalPages")) { %>disabled<% } %>">
            <a class="page-link" href="${pageContext.request.contextPath}/album?page=${currentPage + 1}&size=${pageSize}">Next</a>
        </li>
    </ul>
</nav>
<%}%>
<%}%>

<%@include file="/WEB-INF/include/footer.jsp" %>
