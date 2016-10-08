package z.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "cn.ptp")
public class AppStart implements EmbeddedServletContainerCustomizer 
{
	public static void main(String[] args) 
	{
		SpringApplication.run(AppStart.class, args);
	}

	@Override
	public void customize(ConfigurableEmbeddedServletContainer container) 
	{
		container.setPort(8082);  
	}
}
