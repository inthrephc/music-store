<%@page import="java.util.List"%>
<%@page import="model.Artist"%>
<%@include file="/WEB-INF/include/header.jsp" %>

<div class="d-flex justify-content-end mb-3">
    <form action="<%= request.getContextPath()%>/artist" method="GET" class="d-inline">
        <button type="submit" name="view" value="create" class="btn btn-success">Add artist</button>
    </form>
</div>
<h1>Artist list</h1>
<table class="table table-hover">
    <tr>
        <th>ID</th>
        <th>Artist Name</th>
        <th>Actions</th>
    </tr>
    <%
        List<Artist> artistList = (List<Artist>) request.getAttribute("list");
    %>
    <% for (Artist ar : artistList) {%>
    <tr>
        <td><%= ar.getId()%></td>
        <td><%= ar.getName()%></td>
        <td>
            <a class="btn btn-primary btn-sm" href="<%= request.getContextPath()%>/artist?view=edit&id=<%= ar.getId()%>">Edit</a>
            <a class="btn btn-danger btn-sm" href="<%= request.getContextPath()%>/artist?view=delete&id=<%= ar.getId()%>">Delete</a>
        </td>
    </tr>
    <%
        }
    %>
</table>
<% if ((int) request.getAttribute("totalPages") > 1) { %>
<nav aria-label="Artist Pagination">
    <ul class="pagination justify-content-center">
        <li class="page-item <% if ((int) request.getAttribute("currentPage") <= 1) { %>disabled<% } %>">
            <a class="page-link" href="${pageContext.request.contextPath}/artist?page=${currentPage - 1}&size=${pageSize}">Prev</a>
        </li>

        <% for (int i : (List<Integer>) request.getAttribute("pages")) { %>
            <% if (i == -1) { %>
                <li class="page-item disabled">
                    <span class="page-link">...</span>
                </li>
            <% } else if (i == (int) request.getAttribute("currentPage")) {%> 
                <li class="page-item active">
                    <a class="page-link" href="${pageContext.request.contextPath}/artist?page=<%= i%>&size=${pageSize}">
                        <%= i%>
                    </a>
                </li>
            <%} else {%>
                <li class="page-item">
                    <a class="page-link" href="${pageContext.request.contextPath}/artist?page=<%= i%>&size=${pageSize}">
                        <%= i%>
                    </a>
                </li>
            <%}%>
        <%}%>

        <li class="page-item <% if ((int) request.getAttribute("currentPage") >= (int) request.getAttribute("totalPages")) { %>disabled<% } %>">
            <a class="page-link" href="${pageContext.request.contextPath}/artist?page=${currentPage + 1}&size=${pageSize}">Next</a>
        </li>
    </ul>
</nav>
<%}%>

<%@include file="/WEB-INF/include/footer.jsp" %>
