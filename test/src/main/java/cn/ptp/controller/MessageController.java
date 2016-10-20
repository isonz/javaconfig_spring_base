package cn.ptp.controller;

import cn.ptp.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/message")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MessageController
{
    private final MessageService service;
	private ModelAndView mv = new ModelAndView();

    @RequestMapping("/")
    public ModelAndView index() {
        mv.setViewName("message/index");

		mv.addObject("items", service.findAll());
        return mv;
    }
}
