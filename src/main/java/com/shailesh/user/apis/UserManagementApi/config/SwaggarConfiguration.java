package com.shailesh.user.apis.UserManagementApi.config;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * Swagger2 configuration class
 *
 * @author Shailesh
 */
@Configuration
@EnableSwagger2
public class SwaggarConfiguration {
	
	public static final Contact DEFAULT_CONTACT = new Contact("Shailesh Kumar",
			"http://shaileshkumar.ind.in", "shailesh.atn@gmail.com");
	
	public static final ApiInfo DEFUALT_API_INFO = new ApiInfo("User management API",
			"User management API", "1.0", "urn:tos",
			DEFAULT_CONTACT, "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0");

	private static final Set<String> PRODUCES_CONSUMES_INFO = new HashSet<String>(
			Arrays.asList("application/json"));

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(DEFUALT_API_INFO)
				.produces(PRODUCES_CONSUMES_INFO)
				.consumes(PRODUCES_CONSUMES_INFO);
	}
	
}
