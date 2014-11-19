import ejb.CartBean;
import ejb.Catalogue;
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
import ejb.CatalogueBean;
import persistence.FileCartPercentageDiscountLoader;

public class AddToCartCommand extends FrontCommand {
    
    @EJB
    private ShoppingCart cart;
    @EJB
    private Catalogue catalogue;

    @Override
    public void process() {
        initCart();
        initCatalogue();
        addToCart(request);
        new FileCartPercentageDiscountLoader("V:/Proyectos/Espacio de trabajo personal/NetBeans/sMarketplace/Entrega 2/sMarketplace/data/cartPercentageDiscounts.txt", cart).load();

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
      cart.addProduct(catalogue.searchById(request.getParameter("ProductId")));
    }

    private void initCart() {
        try {
            cart = (ShoppingCart) new InitialContext().lookup("java:app/sMarketplaceE2-ejb/CartBean");
        } catch (NamingException ex) {
            Logger.getLogger(AddToCartCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void initCatalogue() {
        try {
            catalogue = (Catalogue) new InitialContext().lookup("java:app/sMarketplaceE2-ejb/CatalogueBean");
        } catch (NamingException ex) {
            Logger.getLogger(AddToCartCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
