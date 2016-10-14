package cn.ptp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"cn.ptp"})
public class SpringDataApplication
{

	public static void main(String[] args) {
		SpringApplication.run(SpringDataApplication.class, args);
	}

}
