<%-- 
    Document   : HomeView
    Created on : 03-oct-2014, 13:32:49
    Author     : usuario
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Esto es el HOME</h1>
        <%
            String p = (String) request.getAttribute("Producto");
            
            out.println(p);
        %>
        
    </body>
</html>
