<%-- 
    Document   : cart
    Created on : 11-oct-2014, 13:56:50
    Author     : Seruk
--%>

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
                        new FileProductListLoader("/Users/Seruk/Google Drive/Proyectos/Espacio de trabajo personal/NetBeans/sMarketplace/sMarketplace/data/products.txt").load();
                        for (Product product : ProductList.getIntance()) {
                        %> <li class="row">
                                <span class="quantity"> &infin; </span>
                                <span class="itemName"> <% out.println(product.getName()); %> </span>
                                <span class="addbtn"><a class="glyphicon glyphicon-plus-sign"></a></span>
                                <span class="price"> <% out.println(product.getPrice()); %> </span>
                            </li>
                        <% } %>
                       
                </ul>
            </div>

            <div class="col-md-7 col-sm-12 text-left">
                <ul>
                    <li class="row list-inline columnCaptions">
                        <span>IN-CART ITEMS</span>
                    </li>
                    <li class="row">
                        <span class="quantity">1</span>
                        <span class="itemName">Birthday Cake</span>
                        <span class="popbtn"><a class="arrow"></a></span>
                        <span class="price">$49.95</span>
                    </li>
                    <li class="row">
                        <span class="quantity">50</span>
                        <span class="itemName">Party Cups</span>
                        <span class="popbtn"><a class="arrow"></a></span>
                        <span class="price">$5.00</span>
                    </li>
                    <li class="row">
                        <span class="quantity">20</span>
                        <span class="itemName">Beer kegs</span>
                        <span class="popbtn"><a class="arrow"></a></span>
                        <span class="price">$919.99</span>				
                    </li>
                    <li class="row">
                        <span class="quantity">18</span>
                        <span class="itemName">Pound of beef</span>
                        <span class="popbtn"><a class="arrow"></a></span>
                        <span class="price">$269.45</span>
                    </li>
                    <li class="row">
                        <span class="quantity">1</span>
                        <span class="itemName">Bullet-proof vest</span>
                        <span class="popbtn"  data-parent="#asd" data-toggle="collapse" data-target="#demo"><a class="arrow"></a></span>
                        <span class="price">$450.00</span>				
                    </li>
                    <li class="row totals">
                        <span class="itemName">Total:</span>
                        <span class="price">$1694.43</span>
                        <span class="order"> <a class="text-center">ORDER</a></span>
                    </li>
                </ul>
            </div>

        </div>

        <!-- The popover content -->

        <div id="popover" style="display: none">
            <a href="#"><span class="glyphicon glyphicon-pencil"></span></a>
            <a href="#"><span class="glyphicon glyphicon-remove"></span></a>
        </div>

        <!-- JavaScript includes -->

        <script src="http://code.jquery.com/jquery-1.11.0.min.js"></script> 
        <script src="assets/js/bootstrap.min.js"></script>
        <script src="assets/js/customjs.js"></script>

    </body>
</html>