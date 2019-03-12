<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<!-- START PAGE SOURCE -->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Error</title>
    <meta http-equiv="content-type" content="text/html; charset=utf-8" />
</head>
<body>

<div class="main">
    <div>
        <h1>Sorry, something went wrong</h1>
        <% List<String> errors = (ArrayList<String>) request.getAttribute("errors"); %>
        <% for (String error: errors) {
        %> <h3><%= error %></h3> <%
        }%>
    </div>
</div>
<hr>
<div class="navigate">
    <form action="/">
        <input type="submit" value="Start page"/>
    </form>
</div>

<div>
    <div>
        <p>Design by <a href="#">T-Watch</a></p>
        <p>Roots: <a href="#">T-Watch Telegram Bot</a></p>
    </div>
</div>

</body>
</html>
