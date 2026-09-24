<%@page import="java.util.List"%>
<%@page import="model.Artist"%>
<%@include file="/WEB-INF/include/header.jsp" %>
<% 
// <jsp:include page="/WEB-INF/include/header.jsp"/> Cach 2
%>

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
    <% for (Artist ar : artistList) { %>
    <tr>
        <td><% out.print(ar.getId()); %></td>
        <td><% out.print(ar.getName()); %></td>
        <td>
            <button class="btn btn-primary btn-sm">Edit</button>
            <button class="btn btn-danger btn-sm">Delete</button>
        </td>
    </tr>
    <%
        }
    %>
</table>

<%@include file="/WEB-INF/include/footer.jsp" %>
