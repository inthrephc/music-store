<%@include file="/WEB-INF/include/header.jsp" %>

<div class="text-center w-50 mx-auto">
    <div class="h1 page-subtitle">Login page</div>

    <form action="<%= request.getContextPath()%>/login" method="POST">
        <h3 class="fw-normal mb-3 text-start">Please sign in</h3>

        <div>
            <input type="text" 
                   class="form-control" 
                   id="username" 
                   name="username" 
                   placeholder="Username" 
                   required>
        </div>
        <div>
            <input type="password" 
                   class="form-control" 
                   id="password" 
                   name="password" 
                   placeholder="Password" 
                   required>
        </div>
        <br>
        <button class="w-100 btn btn-primary btn-signin" type="submit">Sign in</button>
    </form>
</div>

<%@include file="/WEB-INF/include/footer.jsp" %>