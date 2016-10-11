package cn.ptp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView; 

@RestController
@RequestMapping("/message")
public class MessageController
{
	private ModelAndView mv = new ModelAndView();
	
	@RequestMapping("/")
	public ModelAndView index() {
		mv.setViewName("index");
        return mv;
	}

}
