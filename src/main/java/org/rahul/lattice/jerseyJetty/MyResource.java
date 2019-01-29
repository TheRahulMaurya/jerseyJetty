package org.rahul.lattice.jerseyJetty;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


import org.json.JSONArray;
import org.json.JSONObject;
import org.rahul.lattice.jerseyJetty.dao.manager.DatabaseManager;


@Path("myresource")
public class MyResource {
	
	DatabaseManager dbManager = new DatabaseManager() ;
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Got it!";
    }
    
    // shows all tasks
    @GET
    @Path("/all_tasks")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllTask() {
    	
    	JSONArray jsonArray = dbManager.showAllTask();
    	System.out.println("--------------jsonArray----------------"+jsonArray);
        return jsonArray.toString();
    }
    
    
    //add a new task
    @POST
    @Path("/add_task")
    @Consumes(MediaType.APPLICATION_JSON)
    public void addTask(String title) throws Exception {
    	
    	JSONObject jsonObject = new JSONObject(title);
    	
    	System.out.println("---------111--------"+title);
    	dbManager.saveTask(jsonObject.get("title").toString());
    	
    	
    }
    
    
    //update task
    @PUT
    @Path("/update_task/{taskId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateTask(@PathParam ("taskId") int taskId , String values) {
    	
    	System.out.println("---------id------"+taskId);
    	JSONObject jsonObject = new JSONObject(values);
    	System.out.println("---------title------"+jsonObject.getString("title"));
    	System.out.println("---------id------"+jsonObject.getString("id"));
    	dbManager.updateTask(taskId, jsonObject.getString("title"));
    	
    	
    }
    
    
    //delete a task
    @DELETE
    @Path("/delete-task/{taskId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void deleteTask(@PathParam ("taskId") int taskId) {
    	
    	
    	dbManager.deleteTask(taskId);
    	
    }
    
}
