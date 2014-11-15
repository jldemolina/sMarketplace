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

        <div class="container-fluid breadcrumbBox text-center">
            <ol class="breadcrumb">
                <li><a href="#">Review</a></li>
                <li class="active"><a href="#">Order</a></li>
                <li><a href="#">Payment</a></li>
            </ol>
        </div>

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
                            <input type="hidden" name="ProductId" value=<% out.println(product.getId()); %>>
                            <span class="itemName"> <% out.println(product.getName()); %> </span>
                            <span class="addbtn"><input type="submit" class="btn btn-default"value="+"></span>
                            <span class="price"> <% out.println(product.getPrice()); %> </span>
                        </form> 
                    </li>
                    <% } %>

                </ul>
            </div>

            <div class="col-md-7 col-sm-12 text-left">
                <ul>
                    <li class="row list-inline columnCaptions">
                        <span>IN-CART ITEMS</span>
                    </li>

                    <%  CartBeans cart = (CartBeans) request.getSession().getAttribute("Cart");
                        for (Map.Entry<Product, Integer> entry : cart.getProducts().entrySet()) { %>
                    <li class="row">
                        <span class="quantity"> <% out.println(entry.getValue()); %></span>
                        <span class="itemName"> <% out.println(entry.getKey().getName()); %> </span>
                        <form action="FrontController" method="GET">
                            <input type="hidden" name="command" value="RemoveFromCart">
                            <input type="hidden" name="ProductId" value=<% out.println(entry.getKey().getId()); %>>
                            <span class="deletebtn"><input type="submit" class="btn btn-default"value="-"></span>
                        </form>
                        <form action="FrontController" method="GET">
                            <input type="hidden" name="command" value="AddToCart">
                            <input type="hidden" name="ProductId" value=<% out.println(entry.getKey().getId()); %>>
                            <span class="addbtn"><input type="submit" class="btn btn-default"value="+"></span>
                        </form>
                        <span class="price"> <% out.println(entry.getKey().getPrice() + "€"); %></span>
                    </li>
                    <% }%>
                    <li class="row totals">
                        <span class="itemName">Total:</span>
                        <span class="price"> <% out.println(((CartBeans) request.getSession().getAttribute("Cart")).getTotalPrice() + "€");%></span>
                        <span class="order" data-toggle="modal" data-target="#myModal" > <a class="text-center">ORDER</a></span>
                    </li>


                </ul>
            </div>

        </div>

        <!-- The popover content -->

        <div id="popover" style="display: none">
            <form action="FrontController" method="GET">
                <input type="submit" class="btn btn-default"value="+">
                <input type="hidden" name="command" value="AddToCart">
                <input type="hidden" name="ProductId">
            </form>
            <form action="FrontController" method="GET">
                <input type="submit" class="btn btn-default"value="-">
                <input type="hidden" name="command" value="DeleteFromCart">
                <input type="hidden" name="ProductId">
            </form>
        </div>

        <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                        <h4 class="modal-title" id="myModalLabel">Datos de compra</h4>
                    </div>
                    <form action="FrontController" method="GET">

                        <div class="modal-body">
                            <br>
                            Full name:
                            <input name="name" type="text" class="form-control">
                            <br>
                            Email address:
                            <input name="email" type="text" class="form-control">
                            <br>
                            Current residence:
                            <div class="radio">
                                <label><input type="radio" name="ubication" value="Canary Islands">Canary Islands</label>
                            </div>
                            <div class="radio">
                                <label><input type="radio" name="ubication" value="Iberian Peninsula">Iberian Peninsula</label>
                            </div>
                            <div class="radio disabled">
                                <label><input type="radio" name="ubication" value="Out of Spain" disabled>Out of Spain</label>
                            </div>                        
                        </div>
                        <div class="modal-footer">
                            <input type="hidden" name="command" value="ShowInvoice">
                            <button id="processButton" type="submit" name="paymentMethod" value="Paypal" class="btn btn-primary">Paypal</button>
                            <button id="processButton" type="submit" name="paymentMethod" value="Credit Card" class="btn btn-primary">Credit Card</button>
                            <button id="processButton" type="submit" name="paymentMethod" value="PaySafeCard" class="btn btn-primary">PaySafeCard</button>
                        </div>
                    </form> 

                </div>
            </div>
        </div>
    </div>
    <!-- JavaScript includes -->

    <script src="http://code.jquery.com/jquery-1.11.0.min.js"></script> 
    <script src="assets/js/bootstrap.min.js"></script>
    <script src="assets/js/customjs.js"></script>


</body>
</html>