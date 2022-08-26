package com.code.bidder;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTimeDifference {

	public static void main(String[] args) 
	{
		/*DateTimeDifference dtd=new DateTimeDifference();
		String a=dtd.getCurrentDate();
		System.out.println("C Date "+a);
		*/
	
		DateTimeDifference dtd= new DateTimeDifference();
		String c_date_time=dtd.getCurrentDate();
		
		long diff_time1=dtd.getDateDifference("2021-05-13",c_date_time);
		long diff_time2=dtd.getDateDifference(c_date_time, "2021-05-19");
		System.out.println("C Date "+diff_time1);
		System.out.println("C Date "+diff_time2);
	}

	public String getCurrentDateTimevalue(String cdatetime)
	{
		String newTime="";
		try {
			

		String myTime = cdatetime;
		 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		 Date d = df.parse(myTime); 
		 Calendar cal = Calendar.getInstance();
		 cal.setTime(d);
		 cal.add(Calendar.MINUTE, 15);  //Add minit incremt By
		 newTime = df.format(cal.getTime());

		} catch (Exception e) 
		{
			System.out.println("Exc "+e);
		}
		 return newTime;
	}
	
	public String getCurrentDate()
	{
		DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
		Date dateobj = new Date();
		String regdate=df1.format(dateobj);
	//	System.out.println("Registration Date "+df1.format(dateobj));
		
	String myTime = df1.format(dateobj);
	
	return myTime;
	}

	public long getDateDifference(String stdate,String lsdate) 
	{
		
		//System.out.println("st date "+stdate);
		//System.out.println("ls date "+lsdate);
		long diff=0;
		//HH converts hour in 24 hours format (0-23), day calculation
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		Date d1 = null;
		Date d2 = null;
		long diffDays=0;
		long diffMinutes=0;
		

		try 
		{
			d1 = format.parse(stdate);
			d2 = format.parse(lsdate);

			//in milliseconds
			diff = d2.getTime() - d1.getTime();

			long diffSeconds = diff / 1000 % 60;
			diffMinutes = diff / (60 * 1000) % 60;
			long diffHours = diff / (60 * 60 * 1000) % 24;
			diffDays = diff / (24 * 60 * 60 * 1000);

			//System.out.println(diffDays + " days, ");
			//System.out.print(diffHours + " hours, ");
			//System.out.print(diffMinutes + " minutes, ");
			//System.out.print(diffSeconds + " seconds.");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return diffDays;
	}
	
}
