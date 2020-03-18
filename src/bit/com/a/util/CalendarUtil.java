package bit.com.a.util;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import bit.com.a.model.CalendarDto;

public class CalendarUtil {
	
	// 1 -> 01 로 변환
	public static String two(int tt) {
		return (tt+"").length()>1?(tt+""):"0"+tt;
	}
	
	// 20203 -> 202003 으로 설정
	public static String yyyymm(int year, int month) {
		return "" + year + two(month);		
	}
	
	// 202032 -> 20200302 로 설정
	public static String yyyymmdd(int year, int month, int day) {
		return "" + year + two(month) + two(day);		
	}
	
	// 03(String) -> 3(int)
	public static int toOne(String tt) {
		if(tt.charAt(0) == '0') {
			return Integer.parseInt( "" + tt.charAt(1) );
		} else {
			return Integer.parseInt( tt );
		}
	}
	
	// 날짜를 클릭하면, 그날의 일정이 모두 보이게 하는 callist.jsp로 이동하는 함수
	public static String callist(int year, int month, int day){
		String str = "";
		
		str += String.format("&nbsp;<a href='%s?year=%d&month=%d&day=%d'>", 
								"callist.do", year, month, day);
		str += String.format("%2d", day); 					// <a href="">날짜</a>
		str += "</a>";
			// <a href='callist.jsp?year=2020&month=02&day=05'>_5</a>
		return str;
	}
	
	// 일정을 기입하기 위해서 pen이미지를 클릭하면, calwrite.jsp로 이동하는 함수
	public static String showPen(int year, int month, int day){
		String str = "";
		
		String image = "<img src='./image/pen.png' width='18px' height='18px'>";	
		str = String.format("<a href='%s?year=%d&month=%d&day=%d'>%s</a>", 
								"calwrite.do", year, month, day, image);

		return str;
	}
	
	// 1 -> 01	2020/01/02
	public static String two(String msg){
		return msg.trim().length()<2?"0"+msg.trim():msg.trim();
	}
	
	
	public static String makeTable(int year, int month, int day, List<CalendarDto> list){
		String str = "";
		
		// 2020 2 5 -> 20200205
		String dates = (year + "") + two(month + "") + two(day + "");
		
		str += "<table>";
		str += "<col width='98'>";
		
		for(CalendarDto dto : list){
			if(dto.getRdate().substring(0, 8).equals(dates)){
				str += "<tr bgcolor='#ff0'>";
				str += "<td style='border:hidden'>";
				str += "<a href='caldetail.do?seq=" + dto.getSeq() + "'>";
				str += "<font style='font-size:8px;color:red'>";
				str += dot3(dto.getTitle());
				str += "</font>";
				str += "</a>";
				str += "</td>";
				str += "</tr>";
			}		
		}	
		str += "</table>";	
		return str;
	}
	
	// 일정 제목이 너무 길 때 ...으로 처리함수
	public static String dot3(String msg){
		String str = "";
		if(msg.length() >= 6){
			str = msg.substring(0, 6);
			str += "..."; 
		}else{
			str = msg.trim();	
		}
		return str;
	}
	
	public static String yyyymmddhhmm(int year, int month, int day,	int hour, int min){
		return yyyymmdd(year,month,day)+two(hour)+two(min);
	}	
	
	// 2020-03-17 문자열을 java.lang.Date로 변경
	public static Date toDate(int year, int month, int day) {
		String s = year + "-" + two(month + "") + "-" + two(day + "");
		Date d = Date.valueOf(s);
		return d;
	}
	
	// 투표의 종료를 판별
	public static String pollState(java.util.Date d) {
		String s1 = "<div style='color:RED'>[종료]</div>";
		String s2 = "<div style='color:BLUE'>[진행중]</div>";
		return isEnd(d)?s1:s2;
		
	}
		
	// 연월일만으로 비교 	오늘 > 종료일 -> 투표마감
	public static boolean isEnd(java.util.Date d) {
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		Calendar now = Calendar.getInstance();
		
		// 오늘 날짜가 마감일 보다 큰지 비교		true면 마감 됨.
		boolean b = Integer.parseInt( StringCal(now) ) > Integer.parseInt( StringCal(c) );
		return b;
	}
	
	
	// 달력의 날짜를 20200317 형식으로 만듦
	public static String StringCal(Calendar dd) {
		String s = "";
		
		int year = dd.get(Calendar.YEAR);
		int month = dd.get(Calendar.MONTH) +1;
		int day = dd.get(Calendar.DATE);
		
		s = year+"" + two(month+"") + two(day+"");
		
		return s;
	}
	
	
	
	
	
	
	
}
