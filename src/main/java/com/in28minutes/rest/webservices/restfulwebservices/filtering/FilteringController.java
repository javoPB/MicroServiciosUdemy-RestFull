package com.in28minutes.rest.webservices.restfulwebservices.filtering;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FilteringController {
	
	@GetMapping("/filtering")
	public SomeBean retrieveSomeBean() {
		return new SomeBean("valor1", "valor2", "valor3");
	}

	@GetMapping("/filtering-list")
	public List<SomeBean> retrieveListOfSomeBean() {
		List<SomeBean> lista = new ArrayList<SomeBean>();
		
		lista.add(new SomeBean("valor1", "valor2", "valor3"));
		lista.add(new SomeBean("valor11", "valor12", "valor13"));
		
		return lista;
	}


}
