import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class FrontCommand {
    protected ServletContext context; 
    protected HttpServletRequest request; 
    protected HttpServletResponse response;
            
    public void initialize(ServletContext context, HttpServletRequest request, HttpServletResponse response) {
        this.context = context;
        this.request = request;
        this.response = response;
    } 
   
    public abstract void process();
    
}
