
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

public class ShoppingCartCommand extends FrontCommand {
    
    @Override
    public void process() {
        request.setAttribute("Producto", "Musica X");
        
        RequestDispatcher dispatcher = context.getRequestDispatcher("/cartView.jsp");
        try {
            dispatcher.forward(request,response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(HomeCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
