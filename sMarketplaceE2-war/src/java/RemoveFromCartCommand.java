import ejb.CartBean;
import ejb.Catalogue;
import ejb.ShoppingCart;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import persistence.FileCartPercentageDiscountLoader;

public class RemoveFromCartCommand extends FrontCommand {

    @EJB
    private ShoppingCart cart;
    @EJB
    private Catalogue catalogue;
    
    @Override
    public void process() {
        initCart();
        initCatalogue();
        deleteFromCart(request);
        new FileCartPercentageDiscountLoader("V:/Proyectos/Espacio de trabajo personal/NetBeans/sMarketplace/Entrega 2/sMarketplace/data/cartPercentageDiscounts.txt").load();

        RequestDispatcher dispatcher = context.getRequestDispatcher("/cartView.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(ShowInvoiceCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     private void deleteFromCart(HttpServletRequest request) {
        cart.removeProduct(catalogue.searchById(request.getParameter("ProductId")));
    }

    private void initCart() {
        try {
            cart = (ShoppingCart) new InitialContext().lookup("java:app/sMarketplaceE2-war/CartBean");
        } catch (NamingException ex) {
            Logger.getLogger(AddToCartCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void initCatalogue() {
        try {
            catalogue = (Catalogue) new InitialContext().lookup("java:app/sMarketplaceE2-war/CatalogueBean");
        } catch (NamingException ex) {
            Logger.getLogger(AddToCartCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
