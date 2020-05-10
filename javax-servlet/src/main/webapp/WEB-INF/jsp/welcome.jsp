<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
</head>
<body>
<% if (session.getAttribute("user") == null) { %>
<a href="/login">Login</a>
<% } else {%>
<form action="logout" method="POST">
    <label>Your name: ${session.getAttribute("user")} </label>
    <button type="submit">Logout</button>
</form>
<% } %>
</body>
</html>