<%@page import="java.util.List"%>
<%@page import="model.Artist"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/header.jsp" %>

<h1>Add new artist</h1>
<form action="http://localhost:8080/music-store/artist" method="POST">
    <input type="hidden" name="action" value="create"/>
    <label id="input" class="form-label">Name: </label>
    <input type="text" class="form-control" name="name" id="input"/>
    <button type="submit" class="btn btn-success">Submit</button>
</form>

<%@include file="/WEB-INF/include/footer.jsp" %>