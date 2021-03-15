package za.co.discovery.bank.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;

/**
 * Spring Boot Application Runner
 * @author Torti Ama-Njoku @ Discovery
 */
@SpringBootApplication
public class DiscoveryBankApplication extends SpringBootServletInitializer {
    
    /**
     * Logger for the class
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(DiscoveryBankApplication.class);

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(DiscoveryBankApplication.class);
    }

    /**
     * Runtime method executed when application is started
     * @param args command line arguments
     */
    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(DiscoveryBankApplication.class, args);

//        String[] beanNames = ctx.getBeanDefinitionNames();
//        Arrays.sort(beanNames);
//
//        StringBuilder sb = new StringBuilder("Application beans:\n");
//        for (String beanName : beanNames) {
//            sb.append(beanName).append("\n");
//        }
//        LOGGER.info(sb.toString());
    }
}
