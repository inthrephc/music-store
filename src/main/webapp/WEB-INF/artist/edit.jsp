<%
    int artistId = (int) request.getAttribute("artistId");
    String artistName = (String) request.getAttribute("artistName");
%>

<%@include file="/WEB-INF/include/header.jsp" %>
<% 
// <jsp:include page="/WEB-INF/include/header.jsp"/> Cach 2
%>

<h1>Edit artist</h1>
<form action="http://localhost:8080/music-store/artist" method="POST">
    <input type="hidden" name="action" value="edit"/>
    <label id="input" class="form-label" >Name: </label>
    <input type="text" class="form-control" name="name" id="input" value="<%= artistName %>"/>
    <button type="submit" class="btn btn-success">Submit</button>
</form>

<%@include file="/WEB-INF/include/footer.jsp" %>