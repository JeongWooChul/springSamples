package bit.com.a.aop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import bit.com.a.model.MemberDto;

@Aspect
public class LogAop {
	
	@Around("within(bit.com.a.controller.*) or within(bit.com.a.dao.*)")
	public Object loggerAop(ProceedingJoinPoint joinpoint) throws Throwable {
		/* 특정부분의 메소드가 호출, 실행되면 자동으로 실행 */
		String signatureStr = joinpoint.getSignature().toShortString();
		
		// session check 
		
		HttpServletRequest request
			= ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
		
		if(request != null) {
			HttpSession session = request.getSession();
			MemberDto login = (MemberDto)session.getAttribute("login");
			if(login == null) {
				return "redirect:/sessionOut.do";
						
			}
		}
		
		
		Object obj = joinpoint.proceed();	//	기능 실행
		System.out.println("loggerAOP : " + signatureStr + " 메소드 호출");
		
		return obj;
	}
}
