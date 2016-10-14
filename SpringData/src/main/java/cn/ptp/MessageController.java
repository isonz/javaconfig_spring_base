package cn.ptp;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/message")
public class MessageController
{
	private final MessageService messageService;
	//private ModelAndView mv = new ModelAndView();
	
	@ModelAttribute("messages")
	@RequestMapping("/")
	public Page<?> index(@PageableDefault(size = 5) Pageable pageable) {
		//mv.setViewName("index");
    	//mv.addObject("name", "this is Hello! + ");  
		// return mv;
		return messageService.findAll(pageable);
	}

}
