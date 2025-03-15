package com.davis.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Initializes the Spring web application by configuring the root application context,
 * the dispatcher servlet context, and the servlet mappings.
 * This class extends {@link AbstractAnnotationConfigDispatcherServletInitializer} to
 * provide a programmatic alternative to the traditional `web.xml` configuration.
 * @author CYPRIAN DAVIS
 */
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    /**
     * Specifies the root configuration classes for the application.
     * These classes define the beans that are shared across the entire application context.
     *
     * @return An array of root configuration classes.
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[] {
            AppConfig.class // Root configuration class for the application
        };
    }

    /**
     * Specifies the servlet configuration classes for the application.
     * These classes define the beans that are specific to the dispatcher servlet context.
     *
     * @return An array of servlet configuration classes.
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] {
            WebConfig.class // Servlet configuration class for Spring MVC
        };
    }

    /**
     * Specifies the servlet mappings for the dispatcher servlet.
     * The mappings determine which URLs are handled by the dispatcher servlet.
     *
     * @return An array of URL patterns for the dispatcher servlet.
     */
    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" }; // Maps all requests to the dispatcher servlet
    }
}