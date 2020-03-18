package bit.com.a.login;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import bit.com.a.model.MemberDto;
import bit.com.a.service.MemberService;

@Controller
public class MemberController {

	private static Logger logger = LoggerFactory.getLogger(MemberController.class);

	@Autowired	//-> 의존성 Dependency Injection(DI) 스프링프레임 워크가 알아서 찾아줌, 자바는 다중상속 안됨
	MemberService memberService;
	
	@RequestMapping(value = "test.do", method=RequestMethod.GET)
	public String test() {
		return "test";
	}
	
	@RequestMapping(value = "login.do", method=RequestMethod.GET)
	public String login(Model model) {
		logger.info("MemberController login " + new Date());			
		
		return "login.tiles";
	}
	
	
	@RequestMapping(value = "regi.do", method=RequestMethod.GET)
	public String regi() {
		
		return "regi.tiles";
	}

	@ResponseBody
	@RequestMapping(value = "idCheck.do", method = RequestMethod.POST)
	public String idcheck(String id) {
		
		int isS = memberService.getId(id);
		System.out.println("idcheck.do isS : " + isS);
		
		if(isS==0){	// id가 없음
			return "N";	
		}else{			// id가 있음
			return "Y";
		}
		
		
	}
	
	
//	@PostMapping(value = "regiAf.do")
	@ResponseBody
	@RequestMapping(value = "regiAf.do", method = RequestMethod.POST)
	public String regiAf(MemberDto dto) {
			
		boolean b = memberService.addmember(dto);
		if(b) {
			return "Y";
		} else {	
			return "N";
		}		
	}
	
	@RequestMapping(value = "loginAf.do",  method = RequestMethod.POST)
	public String loginAf(MemberDto dto, HttpServletRequest req) {
		MemberDto login = memberService.login(dto);
		
		if(login != null && !login.getId().equals("")) {
			// session
			req.getSession().setAttribute("login", login);
			req.getSession().setMaxInactiveInterval(60 * 60 * 2);
			
			return "redirect:/bbslist.do";
		} else {
			return "redirect:/login.do";
		}
		
		 
	}
	
	@RequestMapping(value = "sessionOut.do",  method = RequestMethod.GET)
	public String sessionOut() {
		return "sessionOut.tiles";
	}
	
}





