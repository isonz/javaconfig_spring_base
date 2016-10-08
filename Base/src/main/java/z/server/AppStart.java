package z.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "cn.ptp")
public class AppStart 
{
	public static void main(String[] args) {
		SpringApplication.run(AppStart.class, args);
	}
}
