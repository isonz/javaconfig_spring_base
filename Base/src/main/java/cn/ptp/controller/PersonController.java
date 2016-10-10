package cn.ptp.controller;

import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import cn.ptp.resource.PersonResource; 

@RestController
@RequestMapping("/people")
public class PersonController
{
	
	@RequestMapping(method = RequestMethod.GET)
	public HttpEntity<PersonResource> showAll() 
	{
		return null;
	}

	@RequestMapping(value = "/{person}", method = RequestMethod.GET)
	public HttpEntity<PersonResource> show(@PathVariable Long person) 
	{
		return null;
    }

}
