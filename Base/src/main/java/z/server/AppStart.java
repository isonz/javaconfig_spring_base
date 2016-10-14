package z.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableAutoConfiguration  
//@ConfigurationProperties(locations = "classpath:application.properties")  
@SpringBootApplication(scanBasePackages = {"cn.ptp"})
//@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
//@ComponentScan(basePackages = "cn.ptp")
@EnableJpaRepositories("cn.ptp.repository")  
@EnableTransactionManagement
public class AppStart implements EmbeddedServletContainerCustomizer 
{
	private static final Logger logger = LoggerFactory.getLogger(AppStart.class);
	private static int port = 8080;
	
	public static void main(String[] args) 
	{
		getPort(args);
		SpringApplication.run(AppStart.class, args);
		//System.exit(SpringApplication.exit(SpringApplication.run(AppStart.class, args)));
	}

	@Override
	public void customize(ConfigurableEmbeddedServletContainer container) 
	{
		container.setPort(port);  
	}
	
	private static int getPort(String[] args) 
	{
		if (args.length > 0) {
			try {
				port = Integer.valueOf(args[0]);
			} catch (NumberFormatException ignore) {}
		}
		logger.debug("server port : {}", port);
		return port;
	}
	
}
