package z.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;

@SpringBootApplication(scanBasePackages = "cn.ptp.*")
public class AppStart {

	public static void main(String[] args)
	{
		ApplicationContext ctx = SpringApplication.run(AppStart.class, args);
		System.out.println("Spring 注入的 Beans :");
		String[] beanNames = ctx.getBeanDefinitionNames();
		Arrays.sort(beanNames);
		for (String beanName : beanNames) {
			System.out.println(beanName);
		}
	}
}
