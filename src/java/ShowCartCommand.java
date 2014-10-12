import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import model.CartBeans;

public class ShowCartCommand extends FrontCommand {

    @Override
    public void process() {
        createCart(request);
        RequestDispatcher dispatcher = context.getRequestDispatcher("/cartView.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(HomeCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void createCart(HttpServletRequest request) {
        CartBeans cart = new CartBeans();
        cart.initialize(null);
        if (request.getSession().getAttribute("Cart") == null)
            request.getSession().setAttribute("Cart", cart);
    }
}
