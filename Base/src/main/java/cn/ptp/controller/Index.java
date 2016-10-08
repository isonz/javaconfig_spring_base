package cn.ptp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Index 
{
	 @RequestMapping("/")
	 public String index() {
		 return "Index Page...";
	 }

    @RequestMapping("/hello")
    public String hello() {
        return "Hello World !";
    }
	
}
