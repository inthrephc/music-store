<%@page import="java.util.List"%>
<%@page import="model.Artist"%>
<%
    List<Artist> artistList = (List<Artist>) request.getAttribute("list");
    int id = (int) request.getAttribute("id");
    String title = (String) request.getAttribute("title");
    int artistId = (int) request.getAttribute("artistId");
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/header.jsp" %>

<h1>Edit album</h1>
<form action="<%= request.getContextPath()%>/album" method="POST">
    <input type="hidden" name="action" value="edit"/>
    <input type="hidden" name="id" value="<%= id %>"/>
    <label id="title" class="form-label">Title</label>
    <input type="text" class="form-control" name="title" id="title" value="<%= title %>"/>
    <label id="artist" class="form-label">Artist</label>
    <select class="form-select" id="artist" name="artist">
        <% for (Artist artist : artistList) {%>
        <option value="<%= artist.getId()%>" <% if (artist.getId() == artistId) { %> selected <% } %> ><%= artist.getName()%></option>
        <% }%>
    </select>

    <button type="submit" class="btn btn-success">Save</button>
    <a class="btn bg-secondary text-white" href="<%= request.getContextPath()%>/album">Back</a>
</form>

<%@include file="/WEB-INF/include/footer.jsp" %>