package com.davis.config;

import java.util.List;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuration class for setting up Spring Web MVC.
 * This class enables Spring MVC, configures message converters for JSON and XML,
 * and scans the specified package for Spring components.
 * @author CYPRIAN DAVIS
 */
@Configuration // Indicates that this class contains Spring configuration definitions.
@EnableWebMvc // Enables Spring MVC and registers default configurations.
@ComponentScan(basePackages = "com") // Scans the specified package for Spring components (e.g., @Controller, @Service).
public class WebConfig implements WebMvcConfigurer {

    /**
     * Configures the message converters for the application.
     * This method adds support for JSON and XML message conversion.
     *
     * @param converters - A list of {@link HttpMessageConverter} instances to be configured.
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        // Adds a converter for JSON using Jackson
        converters.add(new MappingJackson2HttpMessageConverter()); // Supports JSON
        // Adds a converter for XML using JAXB
        converters.add(new Jaxb2RootElementHttpMessageConverter()); // Supports XML
    }
}