package com.zjn.network2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.http.CacheControl;
import org.springframework.web.filter.ShallowEtagHeaderFilter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import java.util.concurrent.TimeUnit;

@EnableCaching
@SpringBootApplication
public class Network2Application {

	public static void main(String[] args) {
		SpringApplication.run(Network2Application.class, args);
	}

	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/templates/**")
				.addResourceLocations("classpath:/templates/")
				.setCacheControl(
						CacheControl.maxAge(0, TimeUnit.SECONDS)
								.cachePublic());
	}

	@Bean
	public FilterRegistrationBean filterRegistrationBean () {
		ShallowEtagHeaderFilter eTagFilter = new ShallowEtagHeaderFilter();
		//设置为weakETag，默认为false
		//eTagFilter.setWriteWeakETag(true);
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(eTagFilter);
		registration.addUrlPatterns("/*");
		return registration;
	}
}

