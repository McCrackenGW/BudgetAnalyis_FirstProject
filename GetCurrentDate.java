package budgetaryinfoXXYN;

import java.text.SimpleDateFormat;
import java.util.Date;


public class GetCurrentDate {
	
	public static String getToday() {
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		String currentDate  = formatter.format(date);
		return currentDate;
	}


}

