package com.feeling.tools;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GetNowTime {
	private static final String TIME1="yyyy-MM-dd HH:mm";
	public static String getNowTime1()
	{
		Date now = new Date(); 
		SimpleDateFormat dateFormat = new SimpleDateFormat(TIME1);
		String hehe = dateFormat.format( now );
		return hehe;
	}

}
