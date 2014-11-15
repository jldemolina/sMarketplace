import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import model.CartBeans;
import model.ProductList;

public class RemoveFromCartCommand extends FrontCommand {

    @Override
    public void process() {
        removeFromCart(request);
        RequestDispatcher dispatcher = context.getRequestDispatcher("/cartView.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(ShowInvoiceCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void removeFromCart(HttpServletRequest request) {
        if (request.getSession().getAttribute("Cart") != null)
            ((CartBeans) request.getSession().getAttribute("Cart"))
                    .removeProduct(ProductList.getIntance()
                            .searchById(request.getParameter("ProductId")));
    }
}
