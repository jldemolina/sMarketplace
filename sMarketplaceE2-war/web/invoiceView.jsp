<%-- 
    Document   : invoiceView
    Created on : 15-nov-2014, 0:09:30
    Author     : Seruk
--%>

<%@page import="model.Customer"%>
<%@page import="ejb.CartBean"%>
<%@page import="java.util.Map"%>
<%@page import="model.Product"%>
<%@page import="model.Invoice"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<% Invoice invoice = (Invoice) request.getSession().getAttribute("Invoice"); %>

<html lang="en">
  <head>
    <meta charset="UTF-8">
    <title>Invoice Viewer</title>
    <link rel="stylesheet" href="assets/css/bootstrap.css">
    <style>
      @import url(http://fonts.googleapis.com/css?family=Bree+Serif);
      body, h1, h2, h3, h4, h5, h6{
      font-family: 'Bree Serif', serif;
      }
    </style>
  </head>
  
  <body>
    <div class="container">
      <div class="row">
        <div class="col-xs-6">
          <h1>
             sMarketplace
          </h1>
        </div>
        <div class="col-xs-6 text-right">
          <h1>INVOICE</h1>
          <h1><small>Invoice #001</small></h1>
        </div>
      </div>
      <div class="row">
        <div class="col-xs-5">
          <div class="panel panel-default">
            <div class="panel-heading">
              <h4>From: <a href="#">sMarketplace</a></h4>
            </div>
            <div class="panel-body">
              <p>
                Canary Island <br>
                Tafira <br>
                Escuela de Ingeniería Informática <br>
              </p>
            </div>
          </div>
        </div>
        <div class="col-xs-5 col-xs-offset-2 text-right">
          <div class="panel panel-default">
            <div class="panel-heading">
              <h4>To : <a href="#"><% out.print(invoice.getUser().getUsername()); %></a></h4>
            </div>
            <div class="panel-body">
              <p>
                <% out.print(invoice.getUser().getEmail()); %> <br>
              </p>
            </div>
          </div>
        </div>
      </div>
      <!-- / end client details section -->
      <table class="table table-bordered">
        <thead>
          <tr>
            <th>
              <h4>Product</h4>
            </th>
            <th>
              <h4>Description</h4>
            </th>
            <th>
              <h4>Quantity</h4>
            </th>
            <th>
              <h4>Rate/Price</h4>
            </th>
            <th>
              <h4>Sub Total</h4>
            </th>
          </tr>
        </thead>
        <tbody>
            <% for (Map.Entry<Product, Integer> entry : invoice.getProducts().entrySet()) { %>
            <tr>
                <td>  <% out.print(entry.getKey().getName());  %> </td>
                <td><a href="#">  <% out.print(entry.getKey().getDescription());  %> </a></td>
                <td class="text-right">  <% out.print(entry.getValue());  %> </td>
                <td class="text-right">  <% out.print(entry.getKey().getPrice());  %> </td>
                <td class="text-right">  <% out.print(entry.getKey().getPrice() * entry.getValue()); %> </td>
            </tr>>
            <% } %>
        </tbody>
      </table>
      <div class="row text-right">
        <div class="col-xs-2 col-xs-offset-8">
          <p>
            <strong>
            Sub Total : <br>
            TAX : <br>
            Total : <br>
            </strong>
          </p>
        </div>
        <div class="col-xs-2">
          <strong>
          <% out.print(invoice.getTotal()); %> <br>
          0% <br>
          <% out.print(invoice.getTotal()); %> <br>
          </strong>
        </div>
      </div>
      <div class="row">
        <div class="col-xs-5">
          <div class="panel panel-info">
            <div class="panel-heading">
              <h4>Detalles Bancarios</h4>
            </div>
            <div class="panel-body">
              <p>sMarketplace Limited</p>
              <p>BBVA</p>
              <p>Payment method : <% out.print(invoice.getUser().getPaymentMethod()); %><p>
            </div>
          </div>
        </div>
        <div class="col-xs-7">
          <div class="span7">
            <div class="panel panel-info">
              <div class="panel-heading">
                <h4>Detalles de contacto</h4>
              </div>
              <div class="panel-body">
                  <p>Email : contacto@sMarketplace.com</p>
                  <p>Mobile : 687788778</p>
                  <p>Twitter : <a href="https://twitter.com/">@sMarketplace</a></p>
                </p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </body>
</html>