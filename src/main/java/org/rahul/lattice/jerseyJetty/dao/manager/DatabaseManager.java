package org.rahul.lattice.jerseyJetty.dao.manager;


import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONArray;
import org.json.JSONObject;


public class DatabaseManager {

	    

	
	
	public static JSONArray getJSONFromResultSet(ResultSet rs) {
  
		JSONArray jsonArray = new JSONArray();
		if(rs!=null)
		{
		  try {
		      ResultSetMetaData metaData = rs.getMetaData();
		      while(rs.next())
		      {
		          JSONObject jsonObject = new JSONObject();
		          for(int columnIndex=1;columnIndex<=metaData.getColumnCount();columnIndex++)
		          {
		              if(rs.getString(metaData.getColumnName(columnIndex))!=null)
		            	  jsonObject.put(metaData.getColumnLabel(columnIndex),     rs.getString(metaData.getColumnName(columnIndex)));
		              else
		            	  jsonObject.put(metaData.getColumnLabel(columnIndex), "");
		          }
		          jsonArray.put(jsonObject);
		      }
		  } catch (SQLException e) {
		      e.printStackTrace();
		  }
		}
		return jsonArray;
	}


	    public void saveTask(String title) throws Exception
	    {

//	        int id = jsonObject.getInt("id");


	        final String INSERT_QUERY = "insert into todo (title) values('"+title+"');"; 
	        

	        try {
	            Connection conn = DBConnectionManager.getInstance().getConnection();
	            System.out.println("-----------123-----------");
	            conn.setAutoCommit(false);
	           System.out.println("----111-------");
	            Statement stmt = conn.createStatement();
	            System.out.println("--------222----");
//	             System.out.println("------dbexist----"+isDBExists("S:/sqlite/rahul.db"));
//	                System.out.println("------tableexist----"+isTableExists("todo"));
	           stmt.executeUpdate(INSERT_QUERY);
	           System.out.println("------333----");
	           stmt.close();
	           conn.commit();
	           conn.close();
	         
	        } catch (SQLException ex) {
	            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
	        }
	        
	    }
	    
	    public JSONArray showAllTask()
	    {
	        ResultSet resultSet = null;
	        JSONArray jsonArray = null;
	        final String SELECT_QUERY = "select * from todo;";
	                try {
	            Connection conn = DBConnectionManager.getInstance().getConnection();
	            conn.setAutoCommit(false);
	            Statement stmt = conn.createStatement();
	            System.out.println("--------------1----------------");
	         
	           resultSet = stmt.executeQuery( SELECT_QUERY );
	           
	           System.out.println("--------------1----------------");
	      
	           jsonArray = getJSONFromResultSet(resultSet);
	           
	           System.out.println("--------------jsonArray----------------"+jsonArray);
	           resultSet.close();
	           stmt.close();
	           conn.commit();
	           conn.close();
	           
	         
	        } catch (SQLException ex) {
	            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
	        }
	               
	        return jsonArray;
	    }
	    
	    public void updateTask(int taskId , String title)
	    {
	    	final String UPDATE_QUERY = "update todo set title = '"+title+"' where id = "+taskId+";";
	    	
	    	try {
	            Connection conn = DBConnectionManager.getInstance().getConnection();
	            conn.setAutoCommit(false);
	            Statement stmt = conn.createStatement();
	            System.out.println("--------------1----------------");
	         
	           stmt.executeUpdate( UPDATE_QUERY );
	           
	           System.out.println("--------------2----------------");

	           stmt.close();
	           conn.commit();
	           conn.close();
	           
	         
	        } catch (SQLException ex) {
	            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
	        }
	    
	    }
	    
	    public void deleteTask(int taskId)
	    {
	    	final String DELETE_QUERY = "delete from todo where id = "+taskId+";";
	    	
	    	try {
	            Connection conn = DBConnectionManager.getInstance().getConnection();
	            conn.setAutoCommit(false);
	            Statement stmt = conn.createStatement();
	            System.out.println("--------------1----------------");
	         
	           stmt.executeUpdate( DELETE_QUERY );
	           
	           System.out.println("--------------2----------------");

	           stmt.close();
	           conn.commit();
	           conn.close();
	           
	         
	        } catch (SQLException ex) {
	            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
	        }
	    
	    }
	    
	    
	

}
