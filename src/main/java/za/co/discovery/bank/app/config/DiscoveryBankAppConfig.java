package za.co.discovery.bank.app.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/**
 * Application configuration class
 * @author Torti Ama-Njoku @ Discovery
 */
@EnableJpaRepositories(basePackages = {"za.co.discovery.bank.app.dao"})
@EntityScan(basePackages = {"za.co.discovery.bank.app.data.model"})
@ComponentScan(basePackages = {"za.co.discovery.bank.app"})
@Configuration
public class DiscoveryBankAppConfig {

    /**
     * Password encoder bean for password encryption
     * @return {@link PasswordEncoder} singleton bean
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder;
    }
    
    /**
     * Declare message source singleton bean
     * @return {@link MessageSource} singleton bean
     */
    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames("classpath:/label_key_messages", "classpath:/webservice_validation_messages");
        messageSource.setUseCodeAsDefaultMessage(true);
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setCacheSeconds(-1);
        return messageSource;
    }

    @Bean
    public LocalValidatorFactoryBean getValidator() {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource());
        return bean;
    }
}
