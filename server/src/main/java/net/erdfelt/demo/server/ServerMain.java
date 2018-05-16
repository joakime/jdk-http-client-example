package net.erdfelt.demo.server;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;

public class ServerMain
{
    public static void main(String[] args) throws Exception
    {
        Server server = new Server(9090);
    
        ServletContextHandler context = new ServletContextHandler();
        context.setContextPath("/");
        context.addServlet(HelloServlet.class, "/hello");
        
        server.setHandler(context);
        server.start();
        System.out.println("Server started on " + server.getURI());
        server.join();
    }
    
    public static class HelloServlet extends HttpServlet
    {
        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException
        {
            resp.setContentType("text/html");
            resp.setCharacterEncoding("utf-8");
            
            resp.getWriter().println("<html><body><h1>Heading</h1><p>Some Text</p></body></html>");
        }
    }
}
