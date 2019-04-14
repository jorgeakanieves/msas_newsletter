package com.jene.newsletter.microservices.subscription.config;

import java.io.IOException;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;


@Configuration
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {

  private static final String SWAGGER_UI_PATH = "";

  @Override
  public void addResourceHandlers(final ResourceHandlerRegistry registry) {
    registry
      .addResourceHandler(SWAGGER_UI_PATH + "/swagger-ui.html")
      .addResourceLocations("classpath:/META-INF/resources/")
      .resourceChain(true)
      .addResolver(new PathResourceResolver() {
        @Override
        protected Resource getResource(String resourcePath, Resource location) throws IOException {
          return location.createRelative(resourcePath.substring(SWAGGER_UI_PATH.length()));
        }
      });

    registry
      .addResourceHandler(SWAGGER_UI_PATH + "/webjars/**")
      .addResourceLocations("classpath:/META-INF/resources/webjars/");

    registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
  }

  @Override
  public void addCorsMappings(final CorsRegistry registry) {
    registry.addMapping("/api/Subscriptions").allowedOrigins("*").allowedMethods("*");
  }
}
