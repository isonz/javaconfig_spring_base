package cn.ptp;

import cn.ptp.mapper.MessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
//public class AppStart implements CommandLineRunner
public class AppStart
{
	@Autowired
	private MessageMapper messageMapper;

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

	/*
	@Override
	public void run(String... args) throws Exception
	{
		System.out.println(this.messageMapper.findByName("abc"));
	}
	*/

}
