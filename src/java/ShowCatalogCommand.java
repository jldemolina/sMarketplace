
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import model.CartBeans;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Seruk
 */
public class ShowCatalogCommand extends FrontCommand {

    @Override
    public void process() {
        createCart(request);
        RequestDispatcher dispatcher = context.getRequestDispatcher("/catalogView.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(ShowInvoiceCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void createCart(HttpServletRequest request) {
        CartBeans cart = new CartBeans();
        cart.initialize(null);
        if (request.getSession().getAttribute("Cart") == null)
            request.getSession().setAttribute("Cart", cart);
    }
}