package bit.com.a.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import bit.com.a.model.BbsDto;
import bit.com.a.model.BbsParam;
import bit.com.a.service.BbsService;

@Controller
public class BbsController {
	
	private static Logger logger = LoggerFactory.getLogger(BbsController.class);
	
	@Autowired
	BbsService bbsService;
	/*
	@RequestMapping(value = "bbslist.do", method=RequestMethod.GET)
	public String bbsList(Model model) {
		model.addAttribute("doc_title","글목록");
		
		List<BbsDto> list = bbsService.getBbsList();
		model.addAttribute("bbslist", list);		
		return "bbslist.tiles";		
	}
	*/
	@RequestMapping(value = "bbslist.do", method= {RequestMethod.GET, RequestMethod.POST})
	public String bbsList(Model model, BbsParam param) {
		model.addAttribute("doc_title","글목록");
		
		// paging 처리
		int sn = param.getPageNumber(); // 0 1 2 현재페이지   최초 : 0
		int start = sn * param.getRecordCountPerPage() + 1; //  1, 11, 21
		int end = (sn + 1) * param.getRecordCountPerPage();	// 10, 20, 30
		
		param.setStart(start);
		param.setEnd(end);
		
		List<BbsDto> list = bbsService.getBbsList(param);
		
		// 글의 총수
		int totalRecordCount = bbsService.getBbsCount(param);
		
		model.addAttribute("bbslist", list);
		model.addAttribute("pageNumber", sn);	/* 현재 페이지 */
		model.addAttribute("pageCountPerScreen", 10);	/*화면당 페이지 갯수 = 10 */
		model.addAttribute("recordCountPerPage", param.getRecordCountPerPage());	/* 한 페이지에 나타낼 글 갯수 */
		model.addAttribute("totalRecordCount", totalRecordCount); /* 모든 글의 수 */
		
		model.addAttribute("category", param.getS_category());
		model.addAttribute("keyword", param.getS_keyword());
		
		return "bbslist.tiles";		
	}
	
	
	
	
	@RequestMapping(value = "bbsdetail.do", method=RequestMethod.GET)
	public String bbsdetail(int seq, Model model) {
		// 조회수 추가
		bbsService.readCount(seq);
		// 상세내용 호출
		BbsDto dto = bbsService.getBbs(seq);
		model.addAttribute("getBbs", dto);
		return "bbsdetail.tiles";
	}
	
	@RequestMapping(value = "bbswrite.do", method=RequestMethod.GET)
	public String bbswrite() {
		return "bbswrite.tiles";
	}
	
	@ResponseBody
	@RequestMapping(value = "writeAf.do", method=RequestMethod.POST)
	public String bbswriteAf(BbsDto dto) {
		boolean b = bbsService.write(dto);		
		// insert한 내용의 seq 호출
		String seq = Integer.toString(dto.getSeq());
		
		if(b) {
			return seq;
		} else {
			return "N";
		}
	}
	
	@RequestMapping(value = "bbsdelete.do", method=RequestMethod.GET)
	public String bbsDelete(int seq) {
		boolean b = bbsService.setDelete(seq);
		if(b) {
			return "redirect:/bbslist.do";
		} else {
			return "bbsDetail";
		}
	}
	
	@RequestMapping(value = "bbsupdate.do", method=RequestMethod.GET)
	public String bbsUpdate(int seq, Model model) {
		BbsDto dto = bbsService.getBbs(seq);
		model.addAttribute("getBbs", dto);
		
		return "bbsupdate.tiles";
		
	}
	
	@ResponseBody
	@RequestMapping(value = "bbsupdateAf.do", method=RequestMethod.GET)
	public String bbsUpdateAf(BbsDto dto) {
		//System.out.println(dto.getTitle());
		//System.out.println(dto.getContent());
		//System.out.println(dto.getSeq());
		
		boolean b = bbsService.setUpdate(dto);
		String seq = Integer.toString(dto.getSeq());
		
		if(b) {
			return seq;
		} else {
			return "N";
		}
	}
	
	@RequestMapping(value = "answer.do", method = RequestMethod.GET)
	public String answer(int seq, Model model) throws Exception {
		BbsDto bbs = bbsService.getBbs(seq);
		model.addAttribute("_bbs", bbs);
		return "answer.tiles";
	}
	
	@ResponseBody
	@RequestMapping(value = "answerAf.do", method = RequestMethod.GET)
	public String answerAf(BbsDto dto, Model model) throws Exception {
		boolean b = bbsService.replyBbsInsert(dto);		
		
		if(b) {
			return "Y";
		} else {
			return "N";
		}
	}
}
