import ejb.CartBeans;
import ejb.ShoppingCart;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import model.ProductList;

public class AddToCartCommand extends FrontCommand {
    
    @EJB
    private ShoppingCart cart;

    @Override
    public void process() {
        initCart();
        addToCart(request);
        RequestDispatcher dispatcher = context.getRequestDispatcher("/" +  
                request.getParameter("Page") + 
                "View.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(ShowInvoiceCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void addToCart(HttpServletRequest request) {
      cart.addProduct(ProductList.getIntance().searchById(request.getParameter("ProductId")));
    }

    private void initCart() {
        try {
            cart = (ShoppingCart) new InitialContext().lookup("java:app/sMarketplaceE2-war/CartBeans");
        } catch (NamingException ex) {
            Logger.getLogger(AddToCartCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
