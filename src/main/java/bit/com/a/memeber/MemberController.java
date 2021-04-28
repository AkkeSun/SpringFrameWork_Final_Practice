package bit.com.a.memeber;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import bit.com.a.dto.MemberDto;
import bit.com.a.service.MemberService;

@Controller
public class MemberController {
	
	@Autowired
	MemberService memberService;
	
	
//TODO 로그인창으로 이동	
	@RequestMapping(value = "login.do", method = RequestMethod.GET)
	public String login() {
		//tiles를 사용하는경우 layouts.xml을 타고간다
		return "login.tiles";
	}

	
//TODO 회원가입	
	@RequestMapping(value = "regi.do", method = RequestMethod.GET)
	public String regi() {
		return "regi.tiles";
	}
	
	@RequestMapping(value = "regiAf.do", method = RequestMethod.POST)
	//jsp의 name명과 Dto 변수를 일치시키면 dto로 값을 받을 수 있다.
	public String addmember(MemberDto dto) {
		boolean b = memberService.addmember(dto);
		if(b) {
			System.out.println("addMember Success!");
			return "login.tiles";
		} else {
			System.out.println("addMember fail!");
			return "regi.tiles";
		}
	}


	
//TODO 회원가입 시 아이디 체크
	@ResponseBody //ajax 사용
	@RequestMapping(value = "idCheck.do", method = RequestMethod.POST)
	public String idCheck(String id) {
		int n = memberService.getId(id);
		System.out.println("n="+n);
		String msg = "YES";
		if(n > 0) {
			msg = "NO";
		}
		return msg;
	}
	

//TODO 로그인 완료
	@RequestMapping(value = "loginAf.do", method = RequestMethod.POST)
	public String loginAf(MemberDto dto, HttpServletRequest req) {
		MemberDto mem = memberService.login(dto);
	
		if(mem != null && !mem.getId().equals("")) {
			
			// session 저장
			req.getSession().setAttribute("login", mem);
			req.getSession().setMaxInactiveInterval(60*60*24); // 유지시간 지정
			
			System.out.println("login Success!");
			
			//Controller -> Controller 이동
			// 짐 없을 때 : redirect:/url 
            // 짐 있을 때 : forward:/url 
			return "redirect:/bbslist.do";		
		}
		else {				
			return "redirect:/login.do";
		}
	}
	
	
//TODO 로그아웃
	@RequestMapping(value = "logout.do", method = RequestMethod.GET)
	public String logout(HttpServletRequest req) {
		req.removeAttribute("login");
		return "redirect:/login.do";
	}	

}
