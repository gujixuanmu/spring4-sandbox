package com.hantsylabs.example.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.web.config.SpringDataWebConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;

@Configuration
@EnableWebMvc
@ComponentScan(
    basePackages= {"com.hantsylabs.example.spring"},
    useDefaultFilters = false,
    includeFilters = {
        @Filter(
            type = FilterType.ANNOTATION,
            value = {
                Controller.class,
                RestController.class,
                ControllerAdvice.class
            })
    }
)
public class WebConfig extends SpringDataWebConfiguration {

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
    
    

    @Override
	public void addViewControllers(ViewControllerRegistry registry) {
    	registry.addRedirectViewController("/", "/tasks");
//		registry
//			.addViewController("/add")
//			.setViewName("add");
//		registry
//			.addViewController("/edit")
//			.setViewName("edit");
		
	}



	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		registry
			.tiles();
//			.prefix("WEB-INF/views")
//			.suffix(".jspx");
	}
	
	@Bean
	public TilesConfigurer tilesConfiguer() {
		TilesConfigurer config=new TilesConfigurer();
		config.setDefinitions("/WEB-INF/tiles/definitions.xml");
		return config;	
	}



	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry
			.addResourceHandler("webjars/**")
	        .addResourceLocations("classpath:META-INF/resources/webjars/");
	}



	@Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.favorParameter(false);
        configurer.favorPathExtension(false);
    }

}
