
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

public class ShowCatalogueCommand extends FrontCommand {

    @Override
    public void process() {
        RequestDispatcher dispatcher = context.getRequestDispatcher("/catalogueView.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(ShowInvoiceCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}