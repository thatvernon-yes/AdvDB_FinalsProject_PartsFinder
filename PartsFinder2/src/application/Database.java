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
	 
	 
	 public ResultSet RSquery(String query) throws ClassNotFoundException, SQLException {
		 
		 connectSQL();

         java.sql.Statement st = this.con.createStatement();

         ResultSet rs = ((java.sql.Statement) st).executeQuery(query);
         
         return rs;
		 
	 }
  	//takes an SQL query as an input and store it into a 2D array list 
	//returns a 2D array  list
    public ArrayList<ArrayList<String>> query(String query) throws ClassNotFoundException, SQLException {

        ArrayList<ArrayList<String>> arr = new ArrayList<ArrayList<String>>(); //2D array list

        try{

            connectSQL();

            java.sql.Statement st = this.con.createStatement();

            ResultSet rs = ((java.sql.Statement) st).executeQuery(query);

            //Store data to array list
            while (rs.next()) {
                arr.add(new ArrayList<String>());

                for(int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    arr.get(arr.size() - 1).add(rs.getString(i));
                }
            }
            
            //prints data
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
    
 
    //kulang pa ng location
    public  ArrayList<Parts> createPartsClassesforDisplay() throws ClassNotFoundException, SQLException{
    	
    	ArrayList<Parts> partsArr = new ArrayList<Parts>();
    	ResultSet rs = RSquery("SELECT `image`, `parts_name`, `parts_srp`, `parts_stock` FROM `parts`");
    	
    	 while (rs.next()) {
    		Parts part = new Parts();
			part.setImage(rs.getBinaryStream("image"));
			part.setName(rs.getString("parts_name"));
			part.setSrp(rs.getInt("parts_srp"));
			part.setStock(rs.getInt("parts_stock"));
			partsArr.add(part);
         }
    	
		return partsArr;
    	
    }
   
    
}
	 
	 
		 
	      
	