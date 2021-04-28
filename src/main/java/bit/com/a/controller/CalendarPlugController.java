package bit.com.a.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import bit.com.a.dto.CalendarPlugDto;
import bit.com.a.dto.MemberDto;
import bit.com.a.service.CalendarPlugService;

@Controller
public class CalendarPlugController {

	@Autowired
	CalendarPlugService service;
//TODO 리트스 출력
	@RequestMapping(value = "calendarpluglist.do", method = RequestMethod.GET)
	public String calendarpluglist(Model model, HttpSession session) {
		model.addAttribute("doc_title", "일정목록plug");

		MemberDto dto = (MemberDto) session.getAttribute("login");
		CalendarPlugDto cal = new CalendarPlugDto();
		cal.setId(dto.getId());

		List<CalendarPlugDto> list = service.getCalendarPlugList(cal);

		model.addAttribute("callist", list);

		return "calendarpluglist.tiles";

	}
	
//TODO 작성	
	@RequestMapping(value = "writeCalAf.do", method = RequestMethod.GET) 
	public String writeCalAf(CalendarPlugDto dto, HttpSession session) {
		  
	  MemberDto mem = (MemberDto)session.getAttribute("login");
	  dto.setId(mem.getId());  

	  boolean b = service.writeCalAf(dto);
		  
	  if(b) 
		  System.out.println("writeCal Success"); 
	  else
		  System.out.println("writeCal fail");
	  
	  return "redirect:/calendarpluglist.do";
	  
	  }

//TODO 수정
	@RequestMapping(value = "updateCal.do", method = RequestMethod.GET) 
	public String updateCal(CalendarPlugDto dto) {

	  boolean b = service.updateCal(dto);

	  if(b) 
		  System.out.println("updateCal Success"); 
	  else
		  System.out.println("updateCal fail");
	  
	  return "redirect:/calendarpluglist.do";
	  
	  }
	
//TODO 삭제
	@RequestMapping(value = "delCal.do", method = RequestMethod.GET) 
	public String delCal(int seq) {
		
		 boolean b = service.delCal(seq); 
		 if(b)
			 System.out.println("delCal Success"); 
		 else 
			 System.out.println("delCal fail");
		 
		return "redirect:/calendarpluglist.do";
	}
	
	
}
