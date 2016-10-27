package cn.ptp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class AppStart {

	public static void main(String[] args)
	{
		ApplicationContext ctx = SpringApplication.run(AppStart.class, args);
		/*
		System.out.println("Spring 注入的 Beans :");
		String[] beanNames = ctx.getBeanDefinitionNames();
		Arrays.sort(beanNames);
		for (String beanName : beanNames) {
			System.out.println(beanName);
		}
		*/
	}
}
