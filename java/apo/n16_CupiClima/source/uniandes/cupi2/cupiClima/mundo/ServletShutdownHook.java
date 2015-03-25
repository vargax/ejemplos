package uniandes.cupi2.cupiClima.mundo;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

 
public class ServletShutdownHook extends HttpServlet {
    private static final long serialVersionUID = 1L;
       
     
    public ServletShutdownHook() {
        super();
        
    }

    public void init() {
    MyShutdown sh = new MyShutdown(this);
        Runtime.getRuntime().addShutdownHook(sh);
        System.out.println("Added shutdown hook");        
    }
     
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

     
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         
    }

    class MyShutdown extends Thread {
        public MyShutdown(ServletShutdownHook managedClass) {
            super();
            this.managedClass = managedClass;
        }
        private ServletShutdownHook managedClass;
        public void run() {
            System.out.println("MyShutDown Thread started");
            try {
                managedClass.freeResources();
            } catch (Exception ee) {
                ee.printStackTrace();
            }
        }
    }

    public void freeResources() {
        CupiClima.getInstance().persistir();
    }
}