package budgetaryinfoXXYN;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SQLConnect {
    /**
    * Connect to a sample database
    */
   public Connection connect() {
       Connection conn = null;
       try {
           // db parameters
           String url = "jdbc:sqlite:budgetInfoSubmissions.db";
           // create a connection to the database
           conn = DriverManager.getConnection(url);
           
           System.out.println("Connection to SQLite has been established.");
           
       } catch (SQLException e) {
           System.out.println(e.getMessage());
       } finally {
           try {
               if (conn == null) {
                   conn.close();
               }
           } catch (SQLException ex) {
               System.out.println(ex.getMessage());
           }
       }
       return conn;
   }
   
   public void createNewTable() throws SQLException {
	 
       // SQL statement for creating a new table
       String sql = "CREATE TABLE IF NOT EXISTS BudgetaryData (\n"
               + "	uniqueID text PRIMARY KEY,\n"
               + "	firstName text NOT NULL,\n"
               + "	debt integer,\n"
               + "	income integer,\n"
               + "	age integer,\n"
               + "	carBrand text NOT NULL,\n"
               + "	kids boolean,\n"
               + "	eatingOut text NOT NULL,\n"
               + "	city text NOT NULL,\n"
               + "	state text NOT NULL,\n"
               + "	date text\n"
               + ")";
       
	   Connection conn = this.connect();
	   PreparedStatement stmnt = conn.prepareStatement(sql);
	   try {
		stmnt.execute();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

   }
   
   public void insert(String uniqueID, String firstName, int debt, int income, int age, String carBrand, 
		   boolean kids, int eatingOut, String city, String state, String date ) {
       String sql = "INSERT INTO BudgetaryData(uniqueID,firstName,debt,income,age,carBrand,kids,eatingOut,city,state,date) VALUES(?,?,?,?,?,?,?,?,?,?,?)";

       try (Connection conn = this.connect();
               PreparedStatement pstmt = conn.prepareStatement(sql)) {
           pstmt.setString(1, uniqueID);
           pstmt.setString(2, firstName);
           pstmt.setInt(3, debt);
           pstmt.setInt(4, income);
           pstmt.setInt(5, age);
           pstmt.setString(6, carBrand);
           pstmt.setBoolean(7, kids);
           pstmt.setInt(8, eatingOut);
           pstmt.setString(9, city);
           pstmt.setString(10, state);
           pstmt.setString(11, date);
           pstmt.executeUpdate();
       } catch (SQLException e) {
           System.out.println(e.getMessage());
       }
   }
   
   public void selectAll() throws SQLException{
	   String sql = "select rowid, * from BudgetData";
	   Connection conn = this.connect();
	   PreparedStatement stmnt = conn.prepareStatement(sql);
	   ResultSet rs = stmnt.executeQuery();
	   if (rs.next()) {
		   System.out.println("Found"); 
	   }
	   else {
		   System.out.println("Not found");
	   }

   }
   
   
   
	Map<Object, Object> queryHM = new HashMap<>();
	
public String getPrimary(String uniqueIdentifier) throws SQLException {
	
	// trying for 5350i53M535S
	  
	String sql = "SELECT * FROM BudgetaryData WHERE uniqueID = " + "'" + uniqueIdentifier + "'" + ";";
		
    try (Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
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
}




	

  /*
   * 
   * 		public static void main(String[] args) throws SQLException {
			   SQLConnect sQLConn = new SQLConnect();
			   sQLConn.createNewTable();
			   
			   
		   }
   * 
   */


Map<Object, Object> queryState = new HashMap<>();
	public String getStateInfo(String stateAt) throws SQLException {
		
		
		String sqlOne = "SELECT * FROM CostOfLiving WHERE State = " + "'" + stateAt + "'" + ";";
		
	    try (Connection conn = this.connect();
	            PreparedStatement pstmt = conn.prepareStatement(sqlOne)) {
		   PreparedStatement stmnt = conn.prepareStatement(sqlOne);
		   ResultSet rsOne = stmnt.executeQuery();
		   while (rsOne.next()) {
					 			 String statee = rsOne.getString("state");
	                             int CostIndex = rsOne.getInt("CostIndex");
	                             int LivingWage = rsOne.getInt("LivingWage");
	                           
	                             queryState.put("State", statee);                    	    
	                             queryState.put("CostOfLiving", CostIndex);
	                             queryState.put("LivingWage", LivingWage);
           	     
	                             
	                    		   queryState.forEach((k, v) -> {
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
	     
		   System.out.println(stateAt);
	       System.out.println(queryState.toString());
	       
	       return queryState.toString();
	}

	}
	
	
	
	Map<Object, Object> queryBrand = new HashMap<>();
	public String isLux(String carBr) throws SQLException {
		
		
		String sqlOne = "SELECT * FROM carInfo WHERE brandCar = " + "'" + carBr + "'" + ";";
		
	    try (Connection conn = this.connect();
	       PreparedStatement pstmt = conn.prepareStatement(sqlOne)) {
		   PreparedStatement stmnt = conn.prepareStatement(sqlOne);
		   ResultSet rsOne = stmnt.executeQuery();
		   while (rsOne.next()) {

					 			 int isLux = rsOne.getInt("isLux");

	                             queryBrand.put("brandCar", isLux);                    	    
           	     
	                             
	                    		   queryBrand.forEach((k, v) -> {
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
	     
	       System.out.println(queryBrand.toString());
	       
	       return queryBrand.toString();
	}

	}
	
	public String[] yourBudgetScore() {
		
		String personalizedState = (String) queryHM.get("state");
		int personalizedCostOfLiving = (int) queryState.get("CostOfLiving");
		int personalizedAgeInfo = (int) queryHM.get("age");
		int personalizedLivingWage = (int) queryState.get("LivingWage");
		int personalizedDebtInfo = (int) queryHM.get("debt");
		int personalizedIncomeInfo = (int) queryHM.get("income");
		String personalizedEatingOutInfo = (String) queryHM.get("eatingOut");
		boolean personalizedKidsInfo = (boolean) queryHM.get("hasKids");
		String personalizedCarInfo = (String) queryHM.get("carBrand");
		String personalizedName = (String) queryHM.get("firstName");
		int personalizedLuxury = (int) queryBrand.get("brandCar");
		
		// debt to income ratio
		
		personalizedLivingWage = personalizedLivingWage *1000;
		double personalizedDebtInfoD = personalizedDebtInfo;
		double personalizedIncomeInfoD = personalizedIncomeInfo;
		double debtToIncome = ((personalizedDebtInfoD/personalizedIncomeInfoD)*100);
		
		System.out.println(debtToIncome);
		double debtToIncomeScore = 0.0;
		
		if (debtToIncome >= 50) {
			debtToIncomeScore = 1;
		}
		if (debtToIncome <50 && (debtToIncome > 35)) {
			debtToIncomeScore = 2;
		}
		if (debtToIncome <35 && (debtToIncome > 28)) {
			debtToIncomeScore = 2.5;
		}
		if (debtToIncome < 28 && (debtToIncome > 15)) {
			debtToIncomeScore = 3;
		}
		if (debtToIncome < 15 && (debtToIncome > 5)) {
			debtToIncomeScore = 4;
		}
		
		if (debtToIncome < 5 && (debtToIncome > 0)) {
			debtToIncomeScore = 4.5;
		}
		
		if (debtToIncome <= 0) {
			debtToIncomeScore = 5;		
		}
		
		
		// poverty habits
		int eatingOutInfo = Integer.parseInt(personalizedEatingOutInfo);
		double povScore = 0.00;

		if (personalizedIncomeInfo < personalizedLivingWage && eatingOutInfo >= 15 && personalizedLuxury == 1) {
			povScore = 1;
		}
		if ((personalizedIncomeInfo < personalizedLivingWage) && (eatingOutInfo < 15 &&  eatingOutInfo > 10) && (personalizedLuxury == 1)) {
			povScore = 1.5;
		}
				if ((personalizedIncomeInfo < personalizedLivingWage) && (eatingOutInfo <= 10 &&  eatingOutInfo > 5) && (personalizedLuxury == 1)) {
			povScore = 1.5;
		}
		if (personalizedIncomeInfo < personalizedLivingWage && (eatingOutInfo < 5 && personalizedLuxury == 1)) {
			povScore = 2;
		}
				if (personalizedIncomeInfo < personalizedLivingWage && eatingOutInfo >= 15 && personalizedLuxury == 0) {
			povScore = 2.25;
		}
		if ((personalizedIncomeInfo < personalizedLivingWage) && (eatingOutInfo < 15 &&  eatingOutInfo > 10) && (personalizedLuxury == 0)) {
			povScore = 3;
		}
				if ((personalizedIncomeInfo < personalizedLivingWage) && (eatingOutInfo <= 10 &&  eatingOutInfo > 5) && (personalizedLuxury == 0)) {
			povScore = 3.25;
		}
		if (personalizedIncomeInfo < personalizedLivingWage && (eatingOutInfo < 5 && personalizedLuxury == 0)) {
			povScore = 3.30;
		}
		
		if (personalizedIncomeInfo >= personalizedLivingWage && personalizedIncomeInfo < personalizedLivingWage*1.5 && (eatingOutInfo <= 30 &&  eatingOutInfo > 20) && personalizedLuxury == 1) {
			povScore = 3.35;
		}	
		
		if (personalizedIncomeInfo >= personalizedLivingWage && personalizedIncomeInfo < personalizedLivingWage*1.5 && (eatingOutInfo <= 20 &&  eatingOutInfo > 10) && personalizedLuxury == 1) {
			povScore = 3.4;
		}	
		
				
		if (personalizedIncomeInfo >= personalizedLivingWage && personalizedIncomeInfo < personalizedLivingWage*1.5 && (eatingOutInfo <= 10 &&  eatingOutInfo > 5) && personalizedLuxury == 1) {
			povScore = 3.5;
		}	
		
		if (personalizedIncomeInfo >= personalizedLivingWage && personalizedIncomeInfo < personalizedLivingWage*1.5 && (eatingOutInfo < 5) && personalizedLuxury == 1) {
			povScore = 3.6;
		}	
		
		if (personalizedIncomeInfo >= personalizedLivingWage && personalizedIncomeInfo < personalizedLivingWage*1.5 && (eatingOutInfo <= 30 &&  eatingOutInfo > 20) && personalizedLuxury == 0) {
			povScore = 3.75;
		}	
		
		
		if (personalizedIncomeInfo >= personalizedLivingWage && personalizedIncomeInfo < personalizedLivingWage*1.5 && (eatingOutInfo <= 20 &&  eatingOutInfo > 10) && personalizedLuxury == 0) {
			povScore = 3.80;
		}	
		
				
		if (personalizedIncomeInfo >= personalizedLivingWage && personalizedIncomeInfo < personalizedLivingWage*1.5 && (eatingOutInfo <= 10 &&  eatingOutInfo > 5) && personalizedLuxury == 0) {
			povScore = 3.85;
		}	
		
		if (personalizedIncomeInfo >= personalizedLivingWage && personalizedIncomeInfo < personalizedLivingWage*1.5 && (eatingOutInfo < 5) || personalizedLuxury == 0) {
			povScore = 3.9;
		}	
		
		
		if (personalizedIncomeInfo >= personalizedLivingWage*1.5 && personalizedIncomeInfo < personalizedLivingWage*2 && (eatingOutInfo <= 30 &&  eatingOutInfo > 20) && personalizedLuxury == 1) {
			povScore = 3.9;
		}	
		
		if (personalizedIncomeInfo >= personalizedLivingWage*1.5 && personalizedIncomeInfo < personalizedLivingWage*2 && (eatingOutInfo <= 20 &&  eatingOutInfo > 10) && personalizedLuxury == 1) {
			povScore = 4.0;
		}	
		
				
		if (personalizedIncomeInfo >= personalizedLivingWage*1.5 && personalizedIncomeInfo < personalizedLivingWage*2 && (eatingOutInfo <= 10 &&  eatingOutInfo > 5) && personalizedLuxury == 1) {
			povScore = 4.1;
		}	
		
		if (personalizedIncomeInfo >= personalizedLivingWage*1.5 && personalizedIncomeInfo < personalizedLivingWage*2 && (eatingOutInfo < 5) && personalizedLuxury == 1) {
			povScore = 4.2;
		}	
		
		if (personalizedIncomeInfo >= personalizedLivingWage*1.5 && personalizedIncomeInfo < personalizedLivingWage*2 && (eatingOutInfo <= 30 &&  eatingOutInfo > 20) && personalizedLuxury == 0) {
			povScore = 4.3;
		}	
		
		
		if (personalizedIncomeInfo >= personalizedLivingWage*1.5 && personalizedIncomeInfo < personalizedLivingWage*2 && (eatingOutInfo <= 20 &&  eatingOutInfo > 10) && personalizedLuxury == 0) {
			povScore = 4.4;
		}	
		
				
		if (personalizedIncomeInfo >= personalizedLivingWage*1.5 && personalizedIncomeInfo < personalizedLivingWage*2 && (eatingOutInfo <= 10 &&  eatingOutInfo > 5) && personalizedLuxury == 0) {
			povScore = 4.5;
		}	
		
		if (personalizedIncomeInfo >= personalizedLivingWage*1.5 && personalizedIncomeInfo < personalizedLivingWage*2 && (eatingOutInfo < 5) && personalizedLuxury == 0) {
			povScore = 4.55;
		}	
		
			
		if (personalizedIncomeInfo >= personalizedLivingWage*2 && personalizedIncomeInfo < personalizedLivingWage*3) {
		    povScore = 4.7;
		}
				if (personalizedIncomeInfo >= personalizedLivingWage*3 && personalizedIncomeInfo < personalizedLivingWage*4) {
		    povScore = 4.8;
		}
		
				if (personalizedIncomeInfo >= personalizedLivingWage*4) {
		    povScore = 5;
		}
		
	
		
		// cost of living
		double cOLScore = 0;
		if	(personalizedCostOfLiving > 120) {
			cOLScore = 1;
		}
		if (personalizedCostOfLiving <= 120 && personalizedCostOfLiving > 100) {
			cOLScore = 2;
		}
		
		if (personalizedCostOfLiving <= 100 && personalizedCostOfLiving > 95) {
			cOLScore = 3;
		}
		if (personalizedCostOfLiving <= 95 && personalizedCostOfLiving > 90) {
			cOLScore = 4;
		}
		if (personalizedCostOfLiving <= 90) {
			cOLScore = 5;
		}
		
		// num of Kids
		double kidsScore = 0;
		
		if (personalizedKidsInfo && (personalizedIncomeInfo*1.5) < personalizedLivingWage) {
			kidsScore = 1;
		}
		
		if (personalizedKidsInfo && personalizedIncomeInfo < personalizedLivingWage) {
			kidsScore = 2;
		}
		
		if (personalizedKidsInfo && (personalizedIncomeInfo >= personalizedLivingWage && personalizedIncomeInfo < personalizedLivingWage*1.5)) {
			kidsScore = 3;
		}
		
		if (personalizedKidsInfo && (personalizedIncomeInfo >= personalizedLivingWage*1.5 && personalizedIncomeInfo < personalizedLivingWage*2)) {
			kidsScore = 3.9;
		}
		
		if (personalizedKidsInfo && (personalizedIncomeInfo >= personalizedLivingWage*2 && personalizedIncomeInfo < personalizedLivingWage*3)) {
			kidsScore = 4.2;
		}
		
		if (personalizedKidsInfo && (personalizedIncomeInfo >= personalizedLivingWage*3)) {
			kidsScore = 4.75;
		}
		
		if (!personalizedKidsInfo && (personalizedIncomeInfo < personalizedLivingWage)) {
			kidsScore = 2;
		}
		
		if (!personalizedKidsInfo && (personalizedIncomeInfo >= personalizedLivingWage && personalizedIncomeInfo < personalizedLivingWage*1.5)) {
			kidsScore = 3.5;
		}
		
		if (!personalizedKidsInfo && (personalizedIncomeInfo >= personalizedLivingWage*1.5 && personalizedIncomeInfo < personalizedLivingWage*2)) {
			kidsScore = 4.0;
		}
		
		if (!personalizedKidsInfo && (personalizedIncomeInfo >= personalizedLivingWage*2 && personalizedIncomeInfo < personalizedLivingWage*3)) {
			kidsScore = 4.5;
		}
		
		if (!personalizedKidsInfo && (personalizedIncomeInfo >= personalizedLivingWage*3)) {
			kidsScore = 5;
		}
		
		
		// bonus points
		double bonusPoints = 0;
		if (personalizedAgeInfo < 26 && personalizedIncomeInfo > 100000) {
			bonusPoints = 4;
		}
		
		if (personalizedAgeInfo < 35 && (personalizedIncomeInfo >= 150000 && personalizedIncomeInfo < 250000)) {
			bonusPoints = 5;
		}
		
		if (personalizedAgeInfo < 35 && personalizedIncomeInfo >= 250000) {
			bonusPoints = 6;
		}
		double scoring = (((debtToIncomeScore*0.50) + (povScore*0.15) + (kidsScore*0.20) + (cOLScore*0.15))/5)*100;
		
		System.out.println(debtToIncomeScore);
		System.out.println(povScore);
		System.out.println(kidsScore);
		System.out.println(cOLScore);	
		
		String[] reportContents = new String[15];
		reportContents[0] = String.valueOf((debtToIncomeScore/5)*100);
		reportContents[1] = String.valueOf((povScore/5)*100);
		reportContents[2] = String.valueOf((kidsScore/5)*100);
		reportContents[3] = String.valueOf((cOLScore/5)*100);
		reportContents[4] = String.valueOf(bonusPoints);
		reportContents[5] = String.valueOf(personalizedIncomeInfoD);
		reportContents[6] = String.valueOf(personalizedDebtInfoD);
		reportContents[7] = personalizedName;
		reportContents[8] = personalizedCarInfo;
		reportContents[9] = personalizedEatingOutInfo;
		reportContents[10] = String.valueOf(scoring);
		reportContents[11] = personalizedState;
		reportContents[12] = String.valueOf(personalizedLivingWage);
		reportContents[13] = String.valueOf(personalizedCostOfLiving);
		reportContents[14] = String.valueOf(debtToIncome);
	

		// 		double finalTally = scoring + bonusPoints;
		return reportContents;
	}
	

}