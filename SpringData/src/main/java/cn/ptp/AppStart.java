package cn.ptp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"cn.ptp"})
public class AppStart {

	public static void main(String[] args)
	{
		SpringApplication.run(AppStart.class, args);
	}
}
