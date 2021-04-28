package bit.com.a.controller;

import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import bit.com.a.dto.CalendarDto;
import bit.com.a.dto.CalendarParam;
import bit.com.a.dto.MemberDto;
import bit.com.a.service.CalendarService;
import bit.com.a.util.UtilEx;

@Controller
public class CalendarController {

	@Autowired
	CalendarService service;
	
	@RequestMapping(value = "calList.do", method = RequestMethod.GET)
	public String calendarlist(Model model, CalendarParam param, HttpSession session) {		
		model.addAttribute("doc_title", "일정목록");
		
		Calendar cal = Calendar.getInstance();
		
		int year = param.getYear();
		int month = param.getMonth();
		int day = param.getDay();
		
		if(month == 0) {
			year--;
			month = 12;
		}
		else if(month == 13) {
			year++;
			month = 1;
		}
		else if(month < 0) {	// 처음 들어온 경우 여기로 들어 와서 연월일을 현재 날짜로 셋팅한다 
			year = cal.get(Calendar.YEAR);
			month = cal.get(Calendar.MONTH) + 1;
			if(day < 0) {
				day = cal.get(Calendar.DATE);
			}
		}		 
		cal.set(year, month - 1, 1);	// 요일을 구하기 위한 설정
				
		// 요일
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		// 마지막 날짜
		int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		
		// 셋팅된 날짜를 다시 넘겨주기 위한 set
		param.setYear(year);
		param.setMonth(month);
		param.setDay(day);
		param.setDayOfWeek(dayOfWeek);
		param.setLastDay(lastDay);
				
		// 로그인 정보
		String id = ((MemberDto)session.getAttribute("login")).getId();
		// 날짜 취득
		String yyyymm = UtilEx.yyyymm(param.getYear(), param.getMonth());
		
		// DB에서 그달의 일정을 모두 취득하기 위한 Dto
		CalendarDto fcal = new CalendarDto();
		fcal.setId(id);
		fcal.setRdate(yyyymm);
		
		// Db로부터 일정들을 취득한다
		List<CalendarDto> list = service.getCalendarList(fcal);
		
		// 짐싸!
		model.addAttribute("list", list);	// 일정목록을 포장
		model.addAttribute("cal", param);	// 설정된 날짜를 포장
		
		return "callist.tiles";
	}
	

	
//TODO 일정추가	
	@RequestMapping(value = "calWrite.do", method = RequestMethod.GET)
	public String calWrite(CalendarParam param, Model model) {		
		Calendar cal = Calendar.getInstance();
		
		//큰 메뉴에서 일정추가버튼을 눌렀다면 오늘날짜로 셋팅해주기
		if(param.getYear() == -1) {
			param.setYear(cal.get(Calendar.YEAR));
			param.setMonth(cal.get(Calendar.MONTH) + 1);
			param.setDay(cal.get(Calendar.DATE));
		}
		
		model.addAttribute("param", param);
		return "calWrite.tiles";	
	}
	
}






