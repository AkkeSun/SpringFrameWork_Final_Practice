package bit.com.a.aop;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import bit.com.a.dto.MemberDto;

@Aspect
public class LogAop {
	
	//controller안에 있는 method에서 다음의 것을 모두 실행하라
	@Around("within(bit.com.a.controller.*)")
	public Object loggerAop(ProceedingJoinPoint joinpoint)throws Throwable {
		
		String signatureStr = joinpoint.getSignature().toShortString();
		
		// session check (survlet에 담겨있는거 다 가져오기)
		// MemberController는 다른 패키지를 사용해야한다!! (안그럼 로그인 안돼)
		HttpServletRequest request 
			= ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest(); 
				
		if(request != null) {
			HttpSession session = request.getSession();
			MemberDto login = (MemberDto)session.getAttribute("login");			
			if(login == null) {
				return "redirect:/sessionOut.do";
			}
		}	
		
		
		
		
		
		//실행 전
		System.out.println(signatureStr + "시작");
		
		try {
			//실행 시 
			Object obj = joinpoint.proceed();	
			System.out.println("loggerAOP:"+signatureStr + "메소드 실행"+new Date());
			return obj;
			
		}finally {
			//객채 실행 후 처리
			System.out.println("실행 후:" + System.currentTimeMillis());
			System.out.println(signatureStr + "종료");	
		}		
	}
}