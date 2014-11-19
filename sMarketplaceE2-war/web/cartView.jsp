
<%@page import="javax.ejb.EJB"%>
<%@page import="javax.ejb.embeddable.EJBContainer"%>
<%@page import="javax.naming.Context"%>
<%@page import="ejb.ShoppingCart"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="model.User"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="persistence.FileProductListLoader"%>
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
            <div class="col-md-7 col-sm-12 text-left">
                <ul>
                    <li class="row list-inline columnCaptions">
                        <span>IN-CART ITEMS</span>
                    </li>

                    <%  
                        ShoppingCart cart = (ShoppingCart) new InitialContext().lookup("java:app/sMarketplaceE2-war/CartBean");
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
                            <input type="hidden" name="Page" value="cart">
                            <input type="hidden" name="ProductId" value=<% out.println(entry.getKey().getId()); %>>
                            <span class="addbtn"><input type="submit" class="btn btn-default"value="+"></span>
                        </form>
                        <span class="price"> <% out.println(entry.getKey().getPrice() + "€");
                                if (entry.getKey().getPriceWithDiscount() != entry.getKey().getPrice()) out.print(" > " + entry.getKey().getPriceWithDiscount() + "€"); %></span>
                    </li>
                    <% }%>
                    <li>
                        <span class="itemName">Total:</span>
                        <span class="price"> <% out.println(cart.getProductsPriceWithDiscount());%></span>
                    </li>
                    <br><br>
                    <li>
                        <span class="itemName">Total with In-Cart discounts:</span>
                        <span class="price"> <% out.println(cart.getPriceWithDiscount());%></span>
                    </li>
                    <br><br><br>
                     <li>
                         <div align="right"><span class="order" data-toggle="modal" data-target="#myModal" > <a class="text-center">ORDER</a></span></div>
                     </li>
                </ul>
            </div>

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