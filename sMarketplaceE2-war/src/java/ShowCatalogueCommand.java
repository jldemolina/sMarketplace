
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import ejb.CartBean;
import persistence.FileProductListLoader;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Seruk
 */
public class ShowCatalogueCommand extends FrontCommand {

    @Override
    public void process() {
        new FileProductListLoader("V:/Proyectos/Espacio de trabajo personal/NetBeans/sMarketplace/Entrega 2/sMarketplace/data/products.txt").load();
       
        RequestDispatcher dispatcher = context.getRequestDispatcher("/catalogueView.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(ShowInvoiceCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}