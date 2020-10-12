package com.in28minutes.rest.webservices.restfulwebservices;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

@Configuration
@EnableSwagger2WebMvc
public class SwaggerConfig {
	  
	public static final Contact DEFAULT_CONTACT
      = new Contact(
      "Javier P. B.",
      "http://www.misitiooficial.com",
      "pbjavouam@gmail.com");
	  
	public static final ApiInfo DEFAULT_API_INFO
      = new ApiInfo(
      "Api Documentation - Curso Udemy micro servicios con spring boot",
      "Api Documentation - Curso Udemy micro servicios con spring boot",
      "1.0",
      "urn:tos",
      DEFAULT_CONTACT,
      "Apache 2.0",
      "http://www.apache.org/licenses/LICENSE-2.0",
      new ArrayList<>());

	private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES = new HashSet<String>(Arrays.asList("application/json", "application/xml"));


//	@Bean
//	public Docket api() { 
//		return new Docket(DocumentationType.SWAGGER_2)
//				  .select()                                  
//		          .apis(RequestHandlerSelectors.any())              
//		          .paths(PathSelectors.any())                          
//		          .build();//En esta línea puede dejar solo el constructor o agregar las extensiones. (.select().apis(...).paths(...).build())
//	}
	
//	//Personalizando algunas secciones de la documentación de los servicios.
//	@Bean
//	public Docket api() { 
//		return new Docket(DocumentationType.SWAGGER_2).apiInfo(DEFAULT_API_INFO);
//	}
	
	//Personalizando algunas secciones de la documentación de los servicios e indicando que los servicios aceptan tanto JSON como XML.
	@Bean
	public Docket api() { 
		return new Docket(DocumentationType.SWAGGER_2)
					.apiInfo(DEFAULT_API_INFO)
					.produces(DEFAULT_PRODUCES_AND_CONSUMES)
					.consumes(DEFAULT_PRODUCES_AND_CONSUMES);
	}
}
