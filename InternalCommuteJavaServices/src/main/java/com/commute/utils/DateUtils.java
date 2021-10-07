package com.commute.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateUtils {
	
	public static String getCurrentDateTime() {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return formatter.format(calendar.getTime());
	}

}
