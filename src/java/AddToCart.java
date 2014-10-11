/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.CartBeans;
import model.ProductList;

@WebServlet(urlPatterns = {"/AddToCart"})
public class AddToCart extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("Action");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        String action = request.getParameter("Action");

        if (action.equals("Add")) {
            addToCart(request);
        } else if (action.equals("Remove")) {
            removeFromCart(request);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    protected void removeFromCart(HttpServletRequest request) {
        HttpSession session = request.getSession();
        CartBeans cart = null;

        Object sentCart = session.getAttribute("Cart");
        if (sentCart != null) {
            cart = (CartBeans) sentCart;
        } else {
            cart = new CartBeans();
        }

        cart.removeProduct(ProductList.getIntance().search(request.getParameter("ProductName")));
    }

    protected void addToCart(HttpServletRequest request) {
        HttpSession session = request.getSession();
        CartBeans cart = null;

        Object sentCart = session.getAttribute("Cart");

        if (sentCart != null) {
            cart = (CartBeans) sentCart;
        } else {
            cart = new CartBeans();
            session.setAttribute("Cart", sentCart);
        }

        cart.addProduct(ProductList.getIntance().search(request.getParameter("productName")));
    }

}
