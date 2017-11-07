package utils;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Test {

	@org.junit.Test
	public void test() {
	long s=timeop("20161101", "yyyyMMdd")-timeop("20161001", "yyyyMMdd");
	System.out.println(s);
	}

	 public static long timeop(String t, String pattern)
	 {
	  // 传入的参数要与yyyyMMddHH的格式相同 "yyyyMMddHH"
	  SimpleDateFormat simpledateformat = new SimpleDateFormat(pattern, Locale.SIMPLIFIED_CHINESE);
	  Date date2 = null;
	  try
	  {
	   date2 = simpledateformat.parse(t);// 将参数按照给定的格式解析参数
	  } catch (ParseException e)
	  {
	   e.printStackTrace();
	  }
	  System.out.println(date2.getTime());
	  return date2.getTime();
	 } 
}
