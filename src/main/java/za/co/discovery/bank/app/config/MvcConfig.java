package za.co.discovery.bank.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * This class defines the MVC configuration for spring boot
 * @author Torti Ama-Njoku @ Discovery
 */
@Configuration
@EnableSwagger2
public class MvcConfig implements WebMvcConfigurer {

    /**
     * Swagger documentation bean instantiation
     * @return {@link Docket} singleton bean
     */
    @Bean
    public Docket userApi() {
        // the api documentation is available at
        // {application URL}/swagger-ui.html
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(path -> path.startsWith("/api/"))
                .build();
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/", "/swagger-ui/index.html");
    }
}
