<%-- 
    Document   : cart
    Created on : 11-oct-2014, 13:56:50
    Author     : Seruk
--%>

<%@page import="model.User"%>
<%@page import="model.Invoice"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="model.CartBeans"%>
<%@page import="persistence.FileProductListLoader"%>
<%@page import="model.ProductList"%>
<%@page import="model.Product"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Shopping Cart</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" type="text/css" href="assets/css/bootstrap.min.css"/>
        <link rel="stylesheet" type="text/css" href="assets/css/custom.css"/>		
    </head>

    <body>

        <nav class="navbar">
            <div class="container">
                <a class="navbar-brand" href="#">Shopping Cart</a>
                <div class="navbar-right">
                    <div class="container minicart"></div>
                </div>
            </div>
        </nav>

        <br>
        <br>
        <br>

        <div class="container text">
            <div class="col-md-5 col-sm-12">
                <ul>
                    <li class="row list-inline columnCaptions">
                        <span>RECOMMENDED AND AVAILABLE ITEMS</span>
                    </li>
                    <%
                        new FileProductListLoader("V:/Proyectos/Espacio de trabajo personal/NetBeans/sMarketplace/sMarketplace/data/products.txt").load();
                        for (Product product : ProductList.getIntance()) {
                    %> 
                    <li class="row">
                        <form action="FrontController" method="GET">
                            <input type="hidden" name="command" value="AddToCart">
                            <input type="hidden" name="Page" value="catalog">
                            <input type="hidden" name="ProductId" value=<% out.println(product.getId()); %>>
                            <span class="itemName"> <% out.println(product.getName()); %> </span>
                            <span class="addbtn"><input type="submit" class="btn btn-default"value="+"></span>
                            <span class="price"> <% out.println(product.getPrice()); %> </span>
                        </form> 
                    </li>
                    <% } %>
                </ul>
                <li class="row totals">
                    <form action="FrontController" method="GET">
                        <input type="hidden" name="command" value="ShowCart">
                        <span class="itemName">Total items in cart: </span>
                        <span class="price"> <% out.println(((CartBeans) request.getSession().getAttribute("Cart")).getProducts().size());%></span>
                        <span class="order"><button class="btn btn-primary">View cart</button> </span>
                    </form> 
                </li>
            </div>
        </ul>
    </div>

</div>

<!-- JavaScript includes -->

<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script> 
<script src="assets/js/bootstrap.min.js"></script>
<script src="assets/js/customjs.js"></script>


</body>
</html>