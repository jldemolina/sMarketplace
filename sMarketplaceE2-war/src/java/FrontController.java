import ejb.Catalogue;
import ejb.ShoppingCart;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import persistence.FileProductListLoader;
import persistence.FileProductPercentageDiscountLoader;

@WebServlet(urlPatterns = {"/FrontController"})
public class FrontController extends HttpServlet {
    
    @EJB
    private Catalogue catalogue;
    
    public FrontController() {
        initCatalogue();
        new FileProductListLoader("V:/Proyectos/Espacio de trabajo personal/NetBeans/sMarketplace/Entrega 2/sMarketplace/data/products.txt").load();
        new FileProductPercentageDiscountLoader("V:/Proyectos/Espacio de trabajo personal/NetBeans/sMarketplace/Entrega 2/sMarketplace/data/productsPercentageDiscounts.txt", catalogue).load();
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, InstantiationException {
        try {
            FrontCommand command = (FrontCommand) Class.forName(request.getParameter("command") + "Command").newInstance();
            command.initialize(getServletContext(), request, response);
            command.process();
        } catch (IllegalAccessException ex) {
            Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
    
    private void initCatalogue() {
        try {
            catalogue = (Catalogue) new InitialContext().lookup("java:app/sMarketplaceE2-war/CatalogueBean");
        } catch (NamingException ex) {
            Logger.getLogger(AddToCartCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
}
