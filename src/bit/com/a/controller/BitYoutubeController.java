package bit.com.a.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import bit.com.a.model.Youtube;
import bit.com.a.service.BitYoutubeService;
import bit.com.a.util.YoutubeParser;

@Controller
public class BitYoutubeController {

	@Autowired
	private YoutubeParser youtubeParser;		// component 로 연결
	
	@Autowired
	private BitYoutubeService service;
	
	@RequestMapping(value = "yutube.do", method= {RequestMethod.GET, RequestMethod.POST})
	public String yutube(String s_keyword, Model model) {
		model.addAttribute("doc_title", "YouTube");
		
		if(s_keyword != null && !s_keyword.equals("")) {
			
			ArrayList<Youtube> getTitles = youtubeParser.getTitles(s_keyword);
			
			model.addAttribute("yulist", getTitles);
			model.addAttribute("s_keyword", s_keyword);
		}
		
		return "yutube.tiles";
	}
	
	
}
