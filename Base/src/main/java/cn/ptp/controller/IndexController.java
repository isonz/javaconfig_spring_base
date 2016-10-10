package cn.ptp.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView; 

@RestController
public class IndexController
{
	private ModelAndView mv = new ModelAndView();
	
	@RequestMapping("/")
	public ModelAndView index() {
		mv.setViewName("index");
		return mv;
	}

    @RequestMapping(value="/hello1", method=RequestMethod.GET)
    public ModelAndView hello1(@RequestParam(value="pageNum", defaultValue="1") int pageNum) 	// hello1/?pageNum=3
    {
    	mv.setViewName("test/hello"); 
    	mv.addObject("name", "this is Hello! + " + pageNum);  
        return mv;
    }
    
    @RequestMapping(value="/hello2/{id}", method=RequestMethod.GET)		// hello2/4
    public ModelAndView hello2(@PathVariable int id) 
    {
    	mv.setViewName("test/hello"); 
    	mv.addObject("name", "this is freemaker test!!! + " + id);  
        return mv;
    }

}
