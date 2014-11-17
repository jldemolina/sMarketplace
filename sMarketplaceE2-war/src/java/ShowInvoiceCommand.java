
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import ejb.CartBeans;
import model.Customer;
import model.Invoice;
import model.User;

public class ShowInvoiceCommand extends FrontCommand {
    
    @Override
    public void process() {
        RequestDispatcher dispatcher = context.getRequestDispatcher("/invoiceView.jsp");
        User user = new Customer((String) request.getParameter("name"), (String) request.getParameter("email"));
        user.setPaymentMethod(request.getParameter("paymentMethod"));
        Invoice invoice = new Invoice( user, ((CartBeans)(request.getSession().getAttribute("Cart"))).getProducts());
        try {
            request.getSession().setAttribute("Invoice", invoice);
            dispatcher.forward(request,response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(ShowInvoiceCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
