package data.checks;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class Transaction {
	
	public static void main(String[] args) {
		java.util.Date currentDate = getCurrentDate();
		System.out.println(currentDate);
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date();
		System.out.println(dateFormat.format(date)); //2016/11/16 12:08:43
	}
	
	
	public static Date getCurrentDate(){
	    Calendar cal = java.util.Calendar.getInstance();
	    //cal.set(Calendar.HOUR_OF_DAY, 0);
	    //cal.set(Calendar.MINUTE, 0);
	    //cal.set(Calendar.SECOND, 0);
	    //cal.set(Calendar.MILLISECOND, 0);
	    java.util.Date date = cal.getTime();
	    return date;
	  }

}
