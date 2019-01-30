package org.rahul.lattice.jerseyJetty;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class App {
    public static void main(String[] args) throws Exception {
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        System.out.println("----------context-----------"+context);
        context.setContextPath("/");

        System.out.println("----------1-----------");
        Server jettyServer = new Server(8080);
        System.out.println("-----------2----------");
        jettyServer.setHandler(context);
        System.out.println("-----------3----------");

        ServletHolder jerseyServlet = context.addServlet(
             org.glassfish.jersey.servlet.ServletContainer.class, "/*");
        
        System.out.println("---------jerseyServlet------------"+jerseyServlet);
        jerseyServlet.setInitOrder(0);
        System.out.println("-----------5----------");

        // Tells the Jersey Servlet which REST service/class to load.
        jerseyServlet.setInitParameter(
           "jersey.config.server.provider.classnames",
           MyResource.class.getCanonicalName());
        System.out.println("-----------6----------");

        try {
        	System.out.println("--------7-------------");
            jettyServer.start();
            System.out.println("----------8-----------");
            jettyServer.join();
            System.out.println("-----------9----------");
        } catch (Exception e){
            System.out.println("error during server starting  --- "+e);
            jettyServer.stop();
            jettyServer.destroy();
        }
    }
}