package cn.ptp.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.ptp.resource.GreetingResource; 

@RestController
//@RequestMapping("/greeting")
public class GreetingController
{
	private static final String TEMPLATE = "Hello, %s!";

    @RequestMapping("/greeting")
    public HttpEntity<GreetingResource> greeting(
            @RequestParam(value = "name", required = false, defaultValue = "World") String name) {

    	GreetingResource greetingResource = new GreetingResource(String.format(TEMPLATE, name));
    	greetingResource.add(linkTo(methodOn(GreetingController.class).greeting(name)).withSelfRel());

        return new ResponseEntity<GreetingResource>(greetingResource, HttpStatus.OK);
    }

}
