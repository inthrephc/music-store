<%@page import="java.util.List"%>
<%@page import="model.Artist"%>
<%@include file="/WEB-INF/include/header.jsp" %>
<%
// <jsp:include page="/WEB-INF/include/header.jsp"/> Cach 2
%>


<form action="<%= request.getContextPath()%>/artist" method="GET" class="d-inline">
    <button type="submit" name="view" value="create" class="btn btn-success">Add artist</button>
</form>
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
            <a class="btn btn-primary btn-sm" href="<%= request.getContextPath() %>/artist?view=edit&id=<%= ar.getId() %>">Edit</a>
            <button class="btn btn-danger btn-sm">Delete</button>
        </td>
    </tr>
    <%
        }
    %>
</table>

<%@include file="/WEB-INF/include/footer.jsp" %>
