package cn.ptp.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.ptp.service.MessageService;

@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/message")
public class MessageController
{
	private final MessageService service;
	private ModelAndView mv = new ModelAndView();
	
	@RequestMapping("/paged")
	public Page<?> index(@PageableDefault(size = 5) Pageable pageable) {
		//mv.setViewName("index");
    	//mv.addObject("name", "this is Hello! + ");  
		// return mv;
		return service.paged(pageable);
	}

	@RequestMapping("/")
    public ModelAndView index() {
        mv.setViewName("message/index");
		mv.addObject("items", service.findAll());
        return mv;
    }
	
}
