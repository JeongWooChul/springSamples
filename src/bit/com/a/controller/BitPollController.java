package bit.com.a.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import bit.com.a.model.MemberDto;
import bit.com.a.model.PollBean;
import bit.com.a.model.PollDto;
import bit.com.a.model.PollSubDto;
import bit.com.a.service.BitPollService;

/*
	투표항목	: 1.좋아하는 과일은?	투표를 한 질문인지 yes/no -> 재투표 확인	2.좋아하는 배우는?
	투표보기	: [1]1.사과  2.귤  3.배								[2]1.손예진  2.수지
	투표자	: 누가, 어떤질문, 어떤보기를 선택했는가
 */

@Controller
public class BitPollController {
	
	@Autowired
	BitPollService pollService;
	
	@RequestMapping(value = "polllist.do", method= {RequestMethod.GET, RequestMethod.POST})
	public String polllist(Model model, HttpServletRequest req) {
		model.addAttribute("doc_title", "투표 목록");
		
		String id = ((MemberDto)req.getSession().getAttribute("login")).getId();
		
		List<PollDto> list = pollService.getPollAllList(id);
		model.addAttribute("plists", list);
		
		return "polllist.tiles";
		
	}
	
	@RequestMapping(value = "pollmake.do", method= {RequestMethod.GET, RequestMethod.POST})
	public String pollmake(Model model) {
		model.addAttribute("doc_title", "투표만들기");
		
		return "pollmake.tiles";
		
	}
	
	@RequestMapping(value = "pollmakeAf.do", method= {RequestMethod.GET, RequestMethod.POST})
	public String pollmakeAf(PollBean pbean) {
		
		System.out.println(pbean.toString());
		pollService.makePoll(pbean);
		
		return "redirect:/polllist.do";
	}
	
	@RequestMapping(value = "polldetail.do", method= {RequestMethod.GET, RequestMethod.POST})
	public String polldetail(PollDto poll, Model model) {
		model.addAttribute("doc_title", "투표 내용");
		
		PollDto dto = pollService.getPoll(poll);
		List<PollSubDto> list = pollService.getPollSubList(poll);
		
		model.addAttribute("poll", dto);	// 질문
		model.addAttribute("pollsublist", list);
		
		return "polldetail.tiles";
	}
	
	
	
	
	
	
}
