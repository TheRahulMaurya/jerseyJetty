package org.rahul.lattice.jerseyJetty.websocket;



import org.json.JSONArray;
import org.json.JSONObject;
import org.rahul.lattice.jerseyJetty.dao.manager.DatabaseManager;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;


public class SocketImpl {
	
	public static void main(String[] args) {
		
		final DatabaseManager dbManager = new DatabaseManager();
		System.out.println("-----------1-----------");
        Configuration configuration = new Configuration();
        System.out.println("-----------2-----------");
        configuration.setHostname("0.0.0.0");
        System.out.println("-----------3-----------");
        configuration.setPort(8080);
        
        
        System.out.println("-----------4-----------");
 
        final SocketIOServer server = new SocketIOServer(configuration);
 
        System.out.println("-----------5-----------");
        
       
        server.addEventListener("addTodo", Message.class,
                new DataListener<Message>() {
        	

					@Override
					public void onData(SocketIOClient client, Message json, AckRequest ackSender) throws Exception {
						
						
						System.out.println("-----------6-----------");
						String str = "[{\"message\":\"hello\"}]";
						JSONArray jsonArray = dbManager.showAllTask();
						
						
						JSONObject j = new JSONObject();
						j.put("message", "hello");
						server.getBroadcastOperations().sendEvent("addTodo", jsonArray.toString());
						
						
						System.out.println("-----------jsonArray-----------"+jsonArray);
						System.out.println("-----------7-----------"+json.getMessage());
						
						
					}
                });
        
        server.addConnectListener(new ConnectListener() {
			
			@Override
			public void onConnect(SocketIOClient client) {
				System.out.println("-----------gagdg4-----------");
				
			}
		});
      
        
        
        
       
        
        
        System.out.println("-----------8-----------");
        server.start();
        System.out.println("-----------9-----------");
        
        try {
        	System.out.println("-----------10-----------");
			Thread.sleep(Integer.MAX_VALUE);
			System.out.println("-----------11-----------");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println("-----------12-----------");
        server.stop();
        System.out.println("-----------13-----------");
        
//        @OnEvent(value = "event1")
//        private static void onEvent() {
//    		// TODO Auto-generated method stub
//    		
//    	}
        
    }

	

	
}
