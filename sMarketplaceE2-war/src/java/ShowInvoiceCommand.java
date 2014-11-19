import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import ejb.ShoppingCart;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import model.Customer;
import model.Invoice;
import model.User;
import persistence.FileProductTaxPriceIncrementLoader;

public class ShowInvoiceCommand extends FrontCommand {

    @Override
    public void process() {
        try {
            RequestDispatcher dispatcher = context.getRequestDispatcher("/invoiceView.jsp");
            
            User user = new Customer((String) request.getParameter("name"), (String) request.getParameter("email"), (String) request.getParameter("ubication"), (String) request.getParameter("paymentMethod"));
            
            new FileProductTaxPriceIncrementLoader("V:/Proyectos/Espacio de trabajo personal/NetBeans/sMarketplace/Entrega 2/sMarketplace/data/taxCartIncrements.txt", (ShoppingCart) new InitialContext().lookup("java:app/sMarketplaceE2-ejb/CartBean"), (String) request.getParameter("ubication")).load();

            Invoice invoice = new Invoice(user, ((ShoppingCart) new InitialContext().lookup("java:app/sMarketplaceE2-ejb/CartBean")));
            request.getSession().setAttribute("Invoice", invoice);
            dispatcher.forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(ShowInvoiceCommand.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(ShowInvoiceCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
