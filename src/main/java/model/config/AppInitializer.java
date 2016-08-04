package model.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    protected Class[] getRootConfigClasses() {
        return new Class[] { WallapopConfig.class };
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    protected Class[] getServletConfigClasses() {
        return null;
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }
}