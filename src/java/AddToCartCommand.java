import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import model.CartBeans;
import model.ProductList;

public class AddToCartCommand extends FrontCommand {

    @Override
    public void process() {
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
        if (request.getSession().getAttribute("Cart") != null)
            ((CartBeans) request.getSession().getAttribute("Cart"))
                    .addProduct(ProductList.getIntance()
                            .searchById(request.getParameter("ProductId")));
    }
}
