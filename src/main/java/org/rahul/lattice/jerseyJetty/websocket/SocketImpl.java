package org.rahul.lattice.jerseyJetty.websocket;

import org.json.JSONObject;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.OnEvent;
import com.corundumstudio.socketio.listener.DataListener;

public class SocketImpl {
	
	public static void main(String[] args) {
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
						server.getBroadcastOperations().sendEvent("addTodo", "hello there");
						System.out.println("-----------7-----------"+json.getMsg());
						
						
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
