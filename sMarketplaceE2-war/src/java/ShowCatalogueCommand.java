import ejb.Catalogue;
import ejb.CatalogueBean;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import persistence.FileProductListLoader;
import persistence.FileProductPercentageDiscountLoader;

public class ShowCatalogueCommand extends FrontCommand {

    @Override
    public void process() {
        checkAttribute();
        updateParameters();
        initCatalogue();

        RequestDispatcher dispatcher = context.getRequestDispatcher("/catalogueView.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(ShowInvoiceCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void initCatalogue() {
        Catalogue catalogue;
        try {
            catalogue = (Catalogue) new InitialContext().lookup("java:app/sMarketplaceE2-ejb/CatalogueBean");
            new FileProductListLoader("V:/Proyectos/Espacio de trabajo personal/NetBeans/sMarketplace/Entrega 2/sMarketplace/data/products.txt",
                    (Integer) request.getSession().getAttribute("firstItemNumber"),
                    (Integer) request.getSession().getAttribute("lastItemNumber"),
                    catalogue).load();
            new FileProductPercentageDiscountLoader("V:/Proyectos/Espacio de trabajo personal/NetBeans/sMarketplace/Entrega 2/sMarketplace/data/productsPercentageDiscounts.txt", catalogue).load();
        } catch (NamingException ex) {
            Logger.getLogger(AddToCartCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void checkAttribute() {
        if (request.getSession().getAttribute("firstItemNumber") == null) {
            request.getSession().setAttribute("firstItemNumber", 0);
        }
        if (request.getSession().getAttribute("lastItemNumber") == null) {
            request.getSession().setAttribute("lastItemNumber", 5);
        }
    }

    private void updateParameters() {
        if (request.getParameter("increase") != null) {
            increasePage();
        }
        if (request.getParameter("decrease") != null) {
            decreasePage();
        }
    }

    private boolean canToBeUpdated() {
        Catalogue checkerCatalogue = new CatalogueBean();
        new FileProductListLoader("V:/Proyectos/Espacio de trabajo personal/NetBeans/sMarketplace/Entrega 2/sMarketplace/data/products.txt",
                (Integer) request.getSession().getAttribute("firstItemNumber") + 5,
                (Integer) request.getSession().getAttribute("lastItemNumber") + 5,
                checkerCatalogue).load();
        if (checkerCatalogue.getProducts().size() > 0) {
            return true;
        }
        return false;
    }

    private void increasePage() {
        if (!canToBeUpdated()) {
            return;
        }
        request.getSession().setAttribute("firstItemNumber", (Integer) request.getSession().getAttribute("firstItemNumber") + 5);
        request.getSession().setAttribute("lastItemNumber", (Integer) request.getSession().getAttribute("lastItemNumber") + 5);
    }

    private void decreasePage() {
        if (!canToBeUpdated()) {
            return;
        }
        request.getSession().setAttribute("firstItemNumber", (Integer) request.getSession().getAttribute("firstItemNumber") - 5);
        request.getSession().setAttribute("lastItemNumber", (Integer) request.getSession().getAttribute("lastItemNumber") - 5);
    }
}
