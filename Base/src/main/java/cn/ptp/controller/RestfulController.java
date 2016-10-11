package cn.ptp.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import test.resource.RestfulResource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam; 

@RestController
//@RequestMapping("/greeting")
public class RestfulController
{
	private static final String TEMPLATE = "Hello, %s!";

    @RequestMapping("/rest")
    public HttpEntity<RestfulResource> demo(
            @RequestParam(value = "name", required = false, defaultValue = "World") String name) {

    	RestfulResource greetingResource = new RestfulResource(String.format(TEMPLATE, name));
    	greetingResource.add(linkTo(methodOn(RestfulController.class).demo(name)).withSelfRel());

        return new ResponseEntity<RestfulResource>(greetingResource, HttpStatus.OK);
    }

}
