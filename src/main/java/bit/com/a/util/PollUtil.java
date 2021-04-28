package bit.com.a.util;


import java.util.Calendar;
import java.util.Date;

public class PollUtil {

	// 달력의 날짜를 20210426형식으로 만들기
	public static String StringCal(Calendar dd) {
		String s = "";
		int year = dd.get(Calendar.YEAR);
		int month = dd.get(Calendar.MONTH) + 1;
		int day = dd.get(Calendar.DATE);
		
		s = year + "" + UtilEx.two(month + "") + UtilEx.two(day + "");
		return s;
	}
	
	// 연월일을 비교. 투표가 끝났는지? 오늘 > 종료일 -> 투표 마감
	public static boolean isEnd(Date d) {
		//투표 종료일
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		//오늘 날짜
		Calendar now = Calendar.getInstance();
		// 오늘 날짜가 마감일보다 큰지?
		boolean b = Integer.parseInt(StringCal(now)) > Integer.parseInt( StringCal(c) );
		return b;
	}
	
	// 투표 종료의 판별
	public static String pollState(Date d) {
		String s1 = "<div style='color:RED'>[종료]</div>";
		String s2 = "<div style='color:BLUE'>[진행중]</div>";
		
		return isEnd(d)?s1:s2;
	}
	
	
	
}