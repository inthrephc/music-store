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
    
    <% if (genreList.size() == 0) { %>
    <tr>
        <td colspan="3">No genre</td>
    </tr>
 </table>
    <% } else { %>
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
<% if ((int) request.getAttribute("totalPages") > 1) { %>
<nav aria-label="Genre Pagination">
    <ul class="pagination justify-content-center">
        <li class="page-item <% if ((int) request.getAttribute("currentPage") <= 1) { %>disabled<% } %>">
            <a class="page-link" href="${pageContext.request.contextPath}/genre?page=${currentPage - 1}&size=${pageSize}">Prev</a>
        </li>

        <% for (int i : (List<Integer>) request.getAttribute("pages")) { %>
        <% if (i == -1) { %>
        <li class="page-item disabled">
            <span class="page-link">...</span>
        </li>
        <% } else if (i == (int) request.getAttribute("currentPage")) {%> 
        <li class="page-item active">
            <a class="page-link" href="${pageContext.request.contextPath}/genre?page=<%= i%>&size=${pageSize}">
                <%= i%>
            </a>
        </li>
        <%} else {%>
        <li class="page-item">
            <a class="page-link" href="${pageContext.request.contextPath}/genre?page=<%= i%>&size=${pageSize}">
                <%= i%>
            </a>
        </li>
        <%}%>
        <%}%>

        <li class="page-item <% if ((int) request.getAttribute("currentPage") >= (int) request.getAttribute("totalPages")) { %>disabled<% } %>">
            <a class="page-link" href="${pageContext.request.contextPath}/genre?page=${currentPage + 1}&size=${pageSize}">Next</a>
        </li>
    </ul>
</nav>
<%}%>
<%}%>

<%@include file="/WEB-INF/include/footer.jsp" %>
