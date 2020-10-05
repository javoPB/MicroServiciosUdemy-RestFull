package com.in28minutes.rest.webservices.restfulwebservices.helloworld;

import java.util.Locale;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
	
	@Autowired
	private MessageSource messageSource; 

	//@RequestMapping(method = RequestMethod.GET, path = "/hello-world")
	@GetMapping(path = "/hello-world")
	public String getHelloWorld() {
		return "Hello World.";
	}
	
	/**
	 * Si en la clase HelloWorldBean no se define el getter del atributo de clase donde se almacena el valor 
	 * que se pasa como parámetro en el constructor, de genera un error. 
	 * @return
	 */
	@GetMapping(path = "/hello-world-bean")
	public HelloWorldBean getHelloWorldBean() {
		return new HelloWorldBean("Hello World.");
	}

	@GetMapping(path = "/hello-world/path-variable/{name}")
	public HelloWorldBean getHelloWorldBean(@PathVariable String name) {
		return new HelloWorldBean(String.format("Hello World %s", name) );
	}


	/**
	 * Para el manejo de la internacionalización. (i18n)
	 * @RequestHeader para indicar que el parámetro se encuentra en el header del request.
	 *  y por consiguiente, el lenguaje se pasa como parámetro.
	 * @return
	 */
//	@GetMapping(path = "/hello-world-internationalized")
//	public String getHelloWorldInternationalized(@RequestHeader(name="Accept-Language", required = false) Locale locale) {
//		return messageSource.getMessage("good.morning.message", null, locale);
//	}

	//Optimizando la configuración se la internacionalización. 
	@GetMapping(path = "/hello-world-internationalized")
	public String getHelloWorldInternationalized() {
		return messageSource.getMessage("good.morning.message", null, LocaleContextHolder.getLocale());
	}
	
}
