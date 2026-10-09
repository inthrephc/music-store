<%@page import="model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    // Trong JSP, ta khong can phai lay session tu request.getSession() nhu Servlet
    // Vi session la mot trong cac implicit object (doi tuong duoc xay dung san cho JSP)
    User user = (User) session.getAttribute("loggedInUser");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>FPT Music Store</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
    </head>
    <body>
        <nav class="navbar navbar-expand-lg bg-body-tertiary">
            <div class="container-fluid">
                <% if (user != null) {%>
                <a class="navbar-brand" href="<%= request.getContextPath()%>/artist">FPT Music Store</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNavDropdown">
                    <ul class="navbar-nav me-auto">
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                Artists
                            </a>
                            <ul class="dropdown-menu">
                                <li><a class="dropdown-item" href="<%= request.getContextPath()%>/artist?view=list">View artists list</a></li>
                                <li><a class="dropdown-item" href="<%= request.getContextPath()%>/artist?view=create">Add new artist</a></li>
                            </ul>
                        </li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                Albums
                            </a>
                            <ul class="dropdown-menu">
                                <li><a class="dropdown-item" href="<%= request.getContextPath()%>/album?view=list">View album list</a></li>
                                <li><a class="dropdown-item" href="<%= request.getContextPath()%>/album?view=create">Add new album</a></li>
                            </ul>
                        </li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                Genres
                            </a>
                            <ul class="dropdown-menu">
                                <li><a class="dropdown-item" href="<%= request.getContextPath()%>/genre?view=list">View genre list</a></li>
                                <li><a class="dropdown-item" href="<%= request.getContextPath()%>/genre?view=create">Add new genre</a></li>
                            </ul>
                        </li>
                    </ul>
                <% } %>

                    <ul class="navbar-nav ms-auto">
                        <li class="nav-item">
                            <a href="#" class="nav-link">Theme: ${cookie["theme"].value}</a>
                        </li>
                        <% if (user == null) {%>
                        <li class="nav-item">
                            <a href="<%= request.getContextPath()%>/login" class="nav-link">Login</a>
                        </li>
                        <% } else {%>
                        <li class="nav-item">
                            <a href="<%= request.getContextPath()%>/logout" class="nav-link">
                                <%-- Co the dung Hello <%= user.getUsername() %> --%>

                                <%-- Duoi day su dung EL (Expression Language) - Ngon ngu bieu thuc --%>
                                <%-- Hello ${loggedInUser.username}, Logout --%>
                                
                                <%-- Duoi day su dung cookie --%>
                                Hello ${cookie["tenDangNhap"].value}, Logout
                            </a>
                        </li>
                        <% }%>
                    </ul>
                </div>
            </div>
        </nav>
        <div class="container mt-3">