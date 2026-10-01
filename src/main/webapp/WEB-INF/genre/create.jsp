<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/header.jsp" %>

<h1>Add new genre</h1>
<form action="<%= request.getContextPath() %>/genre" method="POST">
    <input type="hidden" name="action" value="create"/>
    <label id="input" class="form-label">Name: </label>
    <input type="text" class="form-control" name="name" id="input"/>
    <button type="submit" class="btn btn-success">Submit</button>
</form>

<%@include file="/WEB-INF/include/footer.jsp" %>