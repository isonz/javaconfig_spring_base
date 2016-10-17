package cn.ptp;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/message")
public class MessageController
{
	private final MessageService messageService;
	private ModelAndView mv = new ModelAndView();
	
	@RequestMapping("/paged")
	public ModelAndView paged(@PageableDefault(size = 5) Pageable pageable) {
		mv.setViewName("paged");
		mv.addObject("lists",  messageService.findAll(pageable));
		return mv;
	}

    @RequestMapping("/")
    public ModelAndView index() {
        mv.setViewName("index");
		mv.addObject("name", "ison.zhang");
        return mv;
    }
}
