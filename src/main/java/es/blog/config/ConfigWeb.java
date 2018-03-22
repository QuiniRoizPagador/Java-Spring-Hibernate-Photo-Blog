package es.blog.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 *
 * @author Quini
 */
public class ConfigWeb extends AbstractAnnotationConfigDispatcherServletInitializer {

    /**
     *
     * @return String Servlet Rute
     */
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    /**
     *
     * @return Class<?> Config Root
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{RootConfig.class};
    }

    /**
     *
     * @return Class<?> Servlet Config classes
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{WebConfig.class};
    }
}
