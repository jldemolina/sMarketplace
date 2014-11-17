
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

public class HomeCommand extends FrontCommand {
    
    @Override
    public void process() {        
        RequestDispatcher dispatcher = context.getRequestDispatcher("/homeView.jsp");
        try {
            dispatcher.forward(request,response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(HomeCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
