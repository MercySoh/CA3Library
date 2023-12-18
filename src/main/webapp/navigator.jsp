<%@ page import="business.Users" %>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <a class="navbar-brand" href="controller?action=dashboard">
            <img src="img/logo.png" alt="Logo" width="25" height="30" class="d-inline-block align-text-top">
            Library
        </a>

        <!-- Toggle button for small screens -->
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
                aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarNav">
            <!-- Search Bar -->
            <form class="d-flex mx-auto">
                <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
                <button class="btn btn-outline-success" type="submit">Search</button>
            </form>

            <!-- right, could be a profile / logout too -->
            <ul class="navbar-nav ms-3">
                <%
                    Users u = (Users) session.getAttribute("user");
                    if (u != null) {%>
                <li class="nav-item">
                    <a class="nav-link" href="controller?action=logout">logout</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="controller?action=show_profile">profile</a>
                </li>
                <%} else {%>
                <li class="nav-item">
                    <a class="nav-link" href="controller?action=show_login">login</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="controller?action=show_register">REGISTER</a>
                </li>
                <%}%>
            </ul>
        </div>
    </div>
</nav>
