package com.demo.socialwebapplication.application;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Set;

import static com.google.common.collect.Sets.newHashSet;
import static springfox.documentation.service.ApiInfo.DEFAULT_CONTACT;
import static springfox.documentation.spi.DocumentationType.SWAGGER_2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    public static final ApiInfo API_INFO = new ApiInfo("Social Web Messaging API", "Social Web Messaging API", "1.0", "urn:tos",
            DEFAULT_CONTACT, "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0", new ArrayList<VendorExtension>());
    private static final Set<String> DEFAULT_PRODUCE_AND_CONSUME = newHashSet("application/json");


    @Bean
    public Docket api() {
        return new Docket(SWAGGER_2)
                .apiInfo(API_INFO)
                .produces(DEFAULT_PRODUCE_AND_CONSUME)
                .consumes(DEFAULT_PRODUCE_AND_CONSUME);
    }
}
