package application;


import java.sql.*;
import java.util.*;



public class Database {
	
	 private Connection con;
 
	//method for automatically connecting the database 
	//quality of life (avoid manual connection every time a query is needed)
	 public void connectSQL() throws ClassNotFoundException, SQLException {
		 Class.forName("com.mysql.cj.jdbc.Driver");

	       //Credentials
	       String url = "jdbc:mysql://localhost:3306/toyotainventorysystem";
	       String username = "root";
	       String password = "";

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
    
    public  ArrayList<Parts> createPartsClassesforDisplay() throws ClassNotFoundException, SQLException{
    	
    	ArrayList<Parts> partsArr = new ArrayList<Parts>();
    	ResultSet rs = RSquery("SELECT `image`, `parts_name`, `parts_srp`, `parts_stock`, `location` FROM `parts`");
    	
    	 while (rs.next()) {
    		Parts part = new Parts();
			part.setImage(rs.getBinaryStream("image"));
			part.setName(rs.getString("parts_name"));
			part.setLocation(rs.getString("location"));
			part.setSrp(rs.getInt("parts_srp"));
			part.setStock(rs.getInt("parts_stock"));
			partsArr.add(part);
         }
    	
		return partsArr;
    	
    }
    
    public  ArrayList<Parts> createPartsClassesforDisplay(String choice) throws ClassNotFoundException, SQLException{
    	
    	ArrayList<Parts> partsArr = new ArrayList<Parts>();
    	ResultSet rs = RSquery(" SELECT `image`, `parts_name`, `parts_srp`, `parts_stock`, `location` FROM `parts` WHERE location =" + "\"" + choice + "\"");
    	
    	 while (rs.next()) {
    		Parts part = new Parts();
			part.setImage(rs.getBinaryStream("image"));
			part.setName(rs.getString("parts_name"));
			part.setLocation(rs.getString("location"));
			part.setSrp(rs.getInt("parts_srp"));
			part.setStock(rs.getInt("parts_stock"));
			partsArr.add(part);
         }
    	
		return partsArr;
    	
    }
    
    //P E N D I N G
    public  ArrayList<Parts> partsForSortedDisplay(String column, String search) throws ClassNotFoundException, SQLException{
    	
    	ArrayList<Parts> partsArr = new ArrayList<Parts>();
    	ResultSet rs = RSquery(" SELECT * FROM `parts` WHERE "  + column  + " LIKE " +  "\"" + "%"  + search + "%" + "\"" );
    	
    	 while (rs.next()) {
    		Parts part = new Parts();
			part.setImage(rs.getBinaryStream("image"));
			part.setName(rs.getString("parts_name"));
			part.setLocation(rs.getString("location"));
			part.setSrp(rs.getInt("parts_srp"));
			part.setStock(rs.getInt("parts_stock"));
			partsArr.add(part);
         }
    	
		return partsArr;
    	
    }
    
    public  ArrayList<Parts> generalSearchSortedDisplay(String partName,String location, int upperPrice, int lowerPrice ) throws ClassNotFoundException, SQLException{
    	
    	ArrayList<Parts> partsArr = new ArrayList<Parts>();
    	
    	ResultSet rs = RSquery(" SELECT * FROM `parts` WHERE location LIKE " +  "\"" + "%"  + location + "%" + "\"" + " AND parts_name LIKE " + "\"" + "%"  + partName + "%" + "\"" + " AND parts_srp BETWEEN " +  lowerPrice + " AND " + upperPrice  );
    	
    	 while (rs.next()) {
    		Parts part = new Parts();
			part.setImage(rs.getBinaryStream("image"));
			part.setName(rs.getString("parts_name"));
			part.setLocation(rs.getString("location"));
			part.setSrp(rs.getInt("parts_srp"));
			part.setStock(rs.getInt("parts_stock"));
			partsArr.add(part);
         }
    	
		return partsArr;
       
    }
    
    public  ArrayList<Parts> sortPrice(int lowerPrice, int upperPrice) throws ClassNotFoundException, SQLException {
        
        ArrayList<Parts> partsArr = new ArrayList<Parts>();
        ResultSet rs;
        
        rs = RSquery(" SELECT * FROM `parts` WHERE parts_srp BETWEEN " + lowerPrice + " AND " + upperPrice);
        
        while (rs.next()) {
       Parts part = new Parts();
    part.setImage(rs.getBinaryStream("image"));
    part.setName(rs.getString("parts_name"));
    part.setLocation(rs.getString("location"));
    part.setSrp(rs.getInt("parts_srp"));
    part.setStock(rs.getInt("parts_stock"));
    partsArr.add(part);
        }
       
        return partsArr;
        }
    
    public  ArrayList<Parts> sortUpperPriceOnly(int upperPrice) throws ClassNotFoundException, SQLException {
        
        ArrayList<Parts> partsArr = new ArrayList<Parts>();
        ResultSet rs;
        
        rs = RSquery(" SELECT * FROM `parts` WHERE `parts_srp` BETWEEN 0  AND " + upperPrice);
        
            while (rs.next()) {
               Parts part = new Parts();
                part.setImage(rs.getBinaryStream("image"));
                part.setName(rs.getString("parts_name"));
                part.setLocation(rs.getString("location"));
                part.setSrp(rs.getInt("parts_srp"));
                part.setStock(rs.getInt("parts_stock"));
                partsArr.add(part);
                }
           
        return partsArr;
    }
    
    public  ArrayList<Parts> sortLowerPriceOnly(int lowerPrice) throws ClassNotFoundException, SQLException {
        
        ArrayList<Parts> partsArr = new ArrayList<Parts>();
        ResultSet rs;
     
        rs = RSquery(" SELECT * FROM `parts` WHERE `parts_srp` BETWEEN " + lowerPrice +  " AND 99999 " );
        
            while (rs.next()) {
               Parts part = new Parts();
                part.setImage(rs.getBinaryStream("image"));
                part.setName(rs.getString("parts_name"));
                part.setLocation(rs.getString("location"));
                part.setSrp(rs.getInt("parts_srp"));
                part.setStock(rs.getInt("parts_stock"));
                partsArr.add(part);
                }
           
        return partsArr;
    }
    
    //WILL BE USED FOR A CHOICE BOX SORT LIST
    //PENDING
    public  ArrayList<Parts> SortedDisplay(String choice, GraphRepresentation graph, String location) throws ClassNotFoundException, SQLException{
    	
    	ArrayList<Parts> partsArr = new ArrayList<Parts>();
    	ResultSet rs;
    	
    	switch(choice) {
    	
    	case("Name"):
    		
    		 rs = RSquery(" SELECT * FROM `parts` ORDER BY `parts_name` ASC ");
    	
		   	 while (rs.next()) {
		   		Parts part = new Parts();
					part.setImage(rs.getBinaryStream("image"));
					part.setName(rs.getString("parts_name"));
					part.setLocation(rs.getString("location"));
					part.setSrp(rs.getInt("parts_srp"));
					part.setStock(rs.getInt("parts_stock"));
					partsArr.add(part);
		        }
		   	
				break;
    	
    	case("Location"):	
    		
    		ArrayList <String> locations = new ArrayList<String>();
    		ArrayList <Integer> distance = new ArrayList<Integer>();

    		
    		List<Map.Entry<String, Integer>> sortedVertices = graph.getSortedVerticesWithDistances(location);
	        for (Map.Entry<String, Integer> entry : sortedVertices) {
	        	locations.add(entry.getKey());
	        	distance.add(entry.getValue());
	            //System.out.println("Vertex: " + entry.getKey() + ", Distance: " + entry.getValue());
	        }
    		
    		rs = RSquery("SELECT * FROM parts ORDER BY FIELD (location, " +  "\"" + locations.get(0) + "\"" + ", " +  "\"" + locations.get(1) + "\"" + ", "+  "\"" + locations.get(2) + "\"" + ")");
//    		rs = RSquery("SELECT * FROM parts ORDER BY FIELD (location, " +  "\"" + locations.get(0) + "\"" + ", " +  "\"" + locations.get(1) + "\"" + ", "+  "\"" + locations.get(2) + "\"" +", " +  "\"" + locations.get(3) + "\"" + ", " +  "\"" + locations.get(4) + "\"" + ", " +  "\"" + locations.get(5) + "\"" + ", " +  "\"" + locations.get(6) + "\"");
    		  
	   	 	while (rs.next()) {
		   		Parts part = new Parts();
					part.setImage(rs.getBinaryStream("image"));
					part.setName(rs.getString("parts_name"));
					part.setLocation(rs.getString("location"));
					part.setSrp(rs.getInt("parts_srp"));
					part.setStock(rs.getInt("parts_stock"));
					partsArr.add(part);
	   	 		}
		   	
	   	 		break;
			
			
    	case("Price"):	
    		 
    		rs = RSquery(" SELECT * FROM `parts` ORDER BY `parts_srp` ASC ");
    	
	   	 	while (rs.next()) {
		   		Parts part = new Parts();
					part.setImage(rs.getBinaryStream("image"));
					part.setName(rs.getString("parts_name"));
					part.setLocation(rs.getString("location"));
					part.setSrp(rs.getInt("parts_srp"));
					part.setStock(rs.getInt("parts_stock"));
					partsArr.add(part);
	   	 		}
		   	
	   	 		break;
    	}
    	
		return partsArr;
    	
    }
    
    
//    public  ArrayList<Parts> TEST( ) throws ClassNotFoundException, SQLException{
//    	
//    	ArrayList<Parts> partsArr = new ArrayList<Parts>();
//    	
//    	ResultSet rs = RSquery(" SELECT * FROM `parts` ORDER BY `parts_name` ASC ");
//    	
//    	 while (rs.next()) {
//    		Parts part = new Parts();
//			part.setImage(rs.getBinaryStream("image"));
//			part.setName(rs.getString("parts_name"));
//			part.setLocation(rs.getString("location"));
//			part.setSrp(rs.getInt("parts_srp"));
//			part.setStock(rs.getInt("parts_stock"));
//			partsArr.add(part);
//         }
//    	
//		return partsArr;
//       
//    }
    
    
    
    
    
    
    
    
    
    
    
    
}
	 
	 
		 
	      
	