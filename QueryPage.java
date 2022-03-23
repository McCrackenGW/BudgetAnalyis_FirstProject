package budgetaryinfoXXYN;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class QueryPage {
		Map<Object, Object> queryHM = new HashMap<>();
	
/* 	public String getPrimary(String uniqueIdentifier) throws SQLException {
		
/*
 * 
 * 		// select rowid, * from BudgetData
		Connection conn = null;
	       try {
	           // db parameters
	           String url = "jdbc:sqlite:budgetInfoSubmissions.db";
	           // create a connection to the database
	          conn = DriverManager.getConnection(url);
	           
	           System.out.println("Connection to SQLite has been established.");
	           
	       } catch (SQLException e) {
	           System.out.println(e.getMessage());
	       } 
 */
				   
/*
 * 
 * 		   String sql = "SELECT * FROM BudgetaryData WHERE uniqueID = ?;";
		   PreparedStatement stmnt = conn.prepareStatement(sql);
		   ResultSet rs = stmnt.executeQuery();
		   while (rs.next()) {
               					 String uniqueId = rs.getString("uniqueID");
               					 System.out.println(uniqueId);
                                 String firstName = rs.getString("firstName");
                                 int debt = rs.getInt("debt");
                                 int income = rs.getInt("income");
                                 int age = rs.getInt("age");
                                 String carBrand = rs.getString("carBrand");
                                 boolean kids = rs.getBoolean("kids");
                                 String eatingOut = rs.getString("eatingOut");
                                 String city = rs.getString("city");
                                 String state = rs.getString("state");
                                 String date = rs.getString("date");
                        	    
                                 queryHM.put("ID", uniqueId);
                        	     queryHM.put("firstName", firstName);
                        	     queryHM.put("debt", debt);
                        	     queryHM.put("income", income);
                        	     queryHM.put("age", age);
                        	     queryHM.put("carBrand", carBrand);
                        	     queryHM.put("hasKids", kids);
                        	     queryHM.put("eatingOut", eatingOut);
                        	     queryHM.put("city", city);
                        	     queryHM.put("state", state);
                        	     queryHM.put("submitDate", date);
                        	     
                      		   queryHM.forEach((k, v) -> {
                   	    	    if (v instanceof Integer) {
                   	    	        Integer theV = (Integer) v;
                   	    	    } else if (v instanceof Boolean) {
                   	    	        Boolean theV = (Boolean) v;
                   	    	    } else if (v instanceof String) {
                   	    	        String theV = (String) v;
                   	    	    } else {
                   	    	        throw new IllegalStateException("Unknown Type Found.");
                   	    	    }
                   	    	});

		   }
	     
		   System.out.println(uniqueIdentifier);
           System.out.println(queryHM.toString());
           
           return queryHM.toString();
	}
	
/*
 *                System.out.println(rs.getString("uniqueID") +  "\t" + 
                                  rs.getString("firstName") + "\t" +
                                  rs.getInt("debt") + "\t" +
                                  rs.getInt("income") + "\t" +
                                  rs.getInt("age") + "\t" +
                                  rs.getString("carBrand") + "\t" +
                                  rs.getBoolean("kids") + "\t" +
                                  rs.getString("eatingOut") + "\t" +
                                  rs.getString("city") + "\t" +
                                  rs.getString("state") + "\t" +
                                  rs.getString("date"));
 * 
 */
	
/*
 * 	public static void main (String[] args) {
		String uniqueIdentifier = "3833fjdlfj";
		  String sql = "SELECT * FROM BudgetaryData WHERE uniqueID = " + uniqueIdentifier + ";";
		System.out.println(sql);
	}
 * 
 */

	
}
