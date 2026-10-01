<%
    int id = (int) request.getAttribute("id");
    String name = (String) request.getAttribute("name");
%>

<%@include file="/WEB-INF/include/header.jsp" %>

<h1>Edit genre</h1>
<form action="<%= request.getContextPath() %>/genre" method="POST">
    <input type="hidden" name="action" value="edit"/>
    <input type="hidden" name="id" value="<%= id %>"/>
    <label id="input" class="form-label" >Name: </label>
    <input type="text" class="form-control" name="name" id="input" value="<%= name %>"/>
    <button type="submit" class="btn btn-success">Submit</button>
    <button type="reset" class="btn bg-secondary text-white">Clear</button>
</form>

<%@include file="/WEB-INF/include/footer.jsp" %>