package org.rahul.lattice.jerseyJetty;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;

//import for websocket
import org.json.JSONArray;
import org.json.JSONObject;
import org.rahul.lattice.jerseyJetty.dao.manager.DatabaseManager;
import org.rahul.lattice.jerseyJetty.websocket.Message;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;



public class Main {

    public static void main(String[] args) {

    	
//code for websocket
        
        final DatabaseManager dbManager = new DatabaseManager();
		System.out.println("-----------1-----------");
        Configuration configuration = new Configuration();
        System.out.println("-----------2-----------");
        configuration.setHostname("0.0.0.0");
        System.out.println("-----------3-----------");
        configuration.setPort(8080);
        
        
        System.out.println("-----------4-----------");
 
        final SocketIOServer socketServer = new SocketIOServer(configuration);
 
        System.out.println("-----------5-----------");
        
        
        
      //code for jetty
        Server server = new Server(8888);

        ServletContextHandler ctx = 
                new ServletContextHandler(ServletContextHandler.NO_SESSIONS);
                
        ctx.setContextPath("/");
        server.setHandler(ctx);

        ServletHolder serHol = ctx.addServlet(ServletContainer.class, "/rest/*");
        serHol.setInitOrder(1);
        serHol.setInitParameter("jersey.config.server.provider.packages", 
                "org.rahul.lattice.jerseyJetty");
        
        
        
        
        socketServer.addConnectListener(new ConnectListener() {
			
			@Override
			public void onConnect(SocketIOClient client) {
				System.out.println("-----------gagdg4-----------");
				JSONArray jsonArray = dbManager.showAllTask();
					
				socketServer.getBroadcastOperations().sendEvent("add", jsonArray.toString());
				
				
			}
		});
        
      //
        socketServer.addEventListener("addTodo", Message.class,
                new DataListener<Message>() {

					@Override
					public void onData(SocketIOClient client, Message json, AckRequest ackSender) throws Exception {
						
						
						System.out.println("-----------6-----------");
						String str = "[{\"message\":\"hello\"}]";
						JSONArray jsonArray = dbManager.showAllTask();
						
						
						JSONObject j = new JSONObject();
						j.put("message", "hello");
						socketServer.getBroadcastOperations().sendEvent("addTodo", jsonArray.toString());
						
						
						System.out.println("-----------jsonArray-----------"+jsonArray);
						System.out.println("-----------7-----------"+json.getMessage());
						
						
					}
                });
        
        
       
        
        
        System.out.println("-----------8-----------");
        socketServer.start();
        try {
            server.start();
            System.out.println("-----------10-----------");
			Thread.sleep(Integer.MAX_VALUE);
			System.out.println("-----------11-----------");
            server.join();
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            server.destroy();
            socketServer.stop();
        } 
      
        
        
    

        
        
        
        
    }
}