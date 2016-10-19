package cn.ptp.demo.controller;

import cn.ptp.demo.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
//@RequestMapping("/")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MessageController
{
    private final MessageService service;
	private ModelAndView mv = new ModelAndView();

    @RequestMapping("/")
    public ModelAndView index() {
        mv.setViewName("index");

		mv.addObject("items", service.findAll());
        return mv;
    }
}