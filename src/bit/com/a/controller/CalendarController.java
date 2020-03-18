package bit.com.a.controller;

import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import bit.com.a.model.CalendarDto;
import bit.com.a.model.CalendarParam;
import bit.com.a.model.CalDateParam;
import bit.com.a.model.MemberDto;
import bit.com.a.service.CalendarService;
import bit.com.a.util.CalendarUtil;

@Controller
public class CalendarController {
	
	@Autowired
	CalendarService service;
	
	@RequestMapping(value = "calendarlist.do", method= {RequestMethod.GET, RequestMethod.POST})
	public String calendarlist(Model model, CalDateParam jcal, HttpSession session) {
		model.addAttribute("doc_title", "일정");
		
		jcal.calculate(); //오늘 날짜 세팅
		
		
		// id를 취득하여 해당 id의 일정만 확인
		String id = ((MemberDto)session.getAttribute("login")).getId();
		
		// 1 -> 01 -> 2020112 날짜 취득
		String yyyymm = CalendarUtil.yyyymm(jcal.getYear(), jcal.getMonth()); 
		
		CalendarDto fcal = new CalendarDto();
		fcal.setId(id);
		fcal.setRdate(yyyymm);
		
		List<CalendarDto> list = service.getCalendarList(fcal);
		
		model.addAttribute("flist", list);
		model.addAttribute("jcal", jcal);
		
		return "calendar.tiles";
		
	}
	
	@RequestMapping(value = "calwrite.do", method=RequestMethod.GET)
	public String calwrite(Model model, CalDateParam jcal) {
		
		System.out.println(jcal.toString());
		jcal.calculate();
		System.out.println(jcal.toString());
		
		model.addAttribute("jcal",jcal);
		
		//model.addAttribute("year", jcal.getYear());
		//model.addAttribute("month", jcal.getMonth());
		//model.addAttribute("day", jcal.getDay());
		return "calwrite.tiles";
	}
	
	@ResponseBody	
	@RequestMapping(value = "calWriteAf.do", method=RequestMethod.POST)
	public String calWriteAf(CalDateParam param, CalendarDto dto) {
		
		String yyyyMMddhhmm = ""+param.getYear()+CalendarUtil.two(param.getMonth())+CalendarUtil.two(param.getDay())
								+param.getHour()+param.getMin();
		
		//System.out.println(yyyyMMddhhmm);
		dto.setRdate(yyyyMMddhhmm);
		
		boolean b = service.addCalendar(dto);
		
		if(b) {
			return "Y";
		} else {
			return "N";
		}
		
	}
	
	@RequestMapping(value = "caldetail.do", method=RequestMethod.GET)
	public String caldetail(CalendarDto fcal, Model model) {
		model.addAttribute("doc_title", "일정");
		System.out.println("fcal.getSeq() : " + fcal.getSeq());
		
		CalendarDto dto = service.getDetail(fcal);
		String wdate = dto.getWdate();
		
		System.out.println("wdate : " + wdate);
		
		String year = wdate.substring(0, 4);
		String month = CalendarUtil.toOne(wdate.substring(5,7)) + "";
		String urls = String.format("%s?year=%s&month=%s", "calendarlist.do", year, month);
		
		System.out.println("urls : " + urls);
		
		model.addAttribute("cal", dto);
		model.addAttribute("urls", urls);
		
		return "caldetail.tiles";
	}
	
	@RequestMapping(value = "callist.do", method=RequestMethod.GET)
	public String calDayList(HttpSession session, CalDateParam calDateParam, Model model, CalendarDto fcal) {
		model.addAttribute("doc_title", "달력 일별 일정");
		
		System.out.println("calDateParam : " +calDateParam.toString());
		String id = ((MemberDto)session.getAttribute("login")).getId();
		String yyyyMMdd = CalendarUtil.yyyymmdd(calDateParam.getYear(), calDateParam.getMonth(), calDateParam.getDay());
		
		System.out.println("id : " + id);
		System.out.println("yyyyMMdd : " + yyyyMMdd);
		
		System.out.println("CalendarDto : " + fcal.toString());
		
		fcal.setId(id);
		fcal.setRdate(yyyyMMdd);
		
		List<CalendarDto> flist = service.getDayList(fcal);
		
		model.addAttribute("callist",flist);
		
		return "callist.tiles";
	}
	
	@RequestMapping(value = "calendarMonth.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String callistmonth(HttpServletRequest request, Model model, int year, int month) {
		model.addAttribute("doc_title", "월별 일정 목록");
		
		MemberDto dto = (MemberDto)request.getSession().getAttribute("login");
		
		String id = dto.getId();
		
		Calendar cal = Calendar.getInstance();
		
		if(year == 0 && month == 0){
			year = cal.get(Calendar.YEAR);
			month = cal.get(Calendar.MONTH) + 1;
		}
			
		String rdate = Integer.toString(year) + CalendarUtil.two(month);
		
		CalendarDto caldto = new CalendarDto();
		
		caldto.setId(id);
		caldto.setRdate(rdate);;
		
		List<CalendarDto> callistmonth = service.getCallistmonth(caldto);
		
		model.addAttribute("month", month);
		model.addAttribute("year", year);
		model.addAttribute("callistmonth", callistmonth);		
		
		return "calendarMonth.tiles";
	}
	
	
	
	
	
}
