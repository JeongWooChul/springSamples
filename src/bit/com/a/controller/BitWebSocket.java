package bit.com.a.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BitWebSocket {

	@RequestMapping(value = "chatting.do", method= {RequestMethod.GET, RequestMethod.POST})
	public String chating() {
		return "chatting.tiles";
	}
	
}
