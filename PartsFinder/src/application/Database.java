package application;

import java.sql.*;
import java.util.ArrayList;


public class Database {
	
	 private Connection con;
 
	//method for automatically connecting the database 
	//quality of life (avoid manual connection every time a query is needed)
	 public void connectSQL() throws ClassNotFoundException, SQLException {
		 Class.forName("com.mysql.cj.jdbc.Driver");

	       //Credentials
	       String url = "jdbc:mysql://localhost:3310/vernon136652";
	       String username = "vernon136652";
	       String password = "136652";

	       //Establish the connection
	      this.con = DriverManager.getConnection(url, username, password);
	 
	 }
	 
	  	//takes an SQL query as an input and store it into a 2D array list 
	    public ArrayList<ArrayList<String>> query(String query) throws ClassNotFoundException, SQLException {

	        ArrayList<ArrayList<String>> arr = new ArrayList<ArrayList<String>>();

	        try{

	            connectSQL();

	            java.sql.Statement st = this.con.createStatement();

	            ResultSet rs = ((java.sql.Statement) st).executeQuery(query);

	            //Store data to array
	            while (rs.next()) {
	                arr.add(new ArrayList<String>());

	                for(int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
	                    arr.get(arr.size() - 1).add(rs.getString(i));
	                }
	            }
	            
	            for(int i = 1; i <= arr.size(); i++) {
		        	for (int j = 0; j <= i; j++) {
		        		
		        		System.out.print( arr.get(j) + "||");
		        	}
		        	System.out.println(" ");
		        }

	            rs.close();
	            con.close();

	        }catch(Exception e) {
	            System.out.println(e);
	        }

	       
	        return arr;
	    }
	    
	    
	  //takes an SQL query as an input and store it into a 2D array list 
	    //shortcut for displaying the information of parts in the GUI
	    public void getDisplayInfoQuery() throws ClassNotFoundException, SQLException {

	    	String query = " SELECT `image`, `parts_name`, `parts_stock`, `parts_srp`, `retailer_ID`  FROM `parts` "; //query to give the necessary columns/information
	    	
	    	
	        ArrayList<ArrayList<String>> arr = new ArrayList<ArrayList<String>>();
	        
	        Parts part = null;
	        

	        try{

	            connectSQL();

	            java.sql.Statement st = this.con.createStatement();

	            ResultSet rs = ((java.sql.Statement) st).executeQuery(query);
	            
	            while(rs.next()) {
	            	
	            	System.out.println(rs.getString("parts_name"));
	 	            System.out.println(rs.getInt("parts_stock"));
	 	            System.out.println(rs.getInt("parts_srp"));
	 	            System.out.println(rs.getString("retailer_ID"));
	 	            System.out.println("\n");    
	            	
	            	part = new Parts(rs.getBlob(query), rs.getString("parts_name"), rs.getInt("parts_stock"), rs.getInt("parts_srp"), rs.getString("retailer_ID"));
	           
	   
	            }
	            
	            
	      

	            rs.close();
	            con.close();

	        }catch(Exception e) {
	            System.out.println(e);
	        }

	   
	    }

	    
	    
}
	 
	 
		 
	      
	