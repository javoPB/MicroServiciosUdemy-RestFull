package com.in28minutes.rest.webservices.restfulwebservices.filtering;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class DinamicFilteringController {
	
	@GetMapping("/dinamic-filtering")
	public MappingJacksonValue retriveSomeDinamicBean() {
		SomeDinamicBean someBean = new SomeDinamicBean("valor1", "valor2", "valor3");
		
		//Se definen los campos que no se quieren filtrar.		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("valor1", "valor2");
		
		//Se hace el mapeo del filter con el bean.
		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeDinamicBeanFiltering", filter);
		
		//Asocia el filtro que se debe aplicar a la información. (Es decir, se hace el filtrado de la información)
		MappingJacksonValue mapping = new MappingJacksonValue(someBean);
		mapping.setFilters(filters);
		
		
		return mapping;
	}
	
	
	@GetMapping("/dinamic-filtering-list")
	public MappingJacksonValue retriveListOfSomeDinamicBean() {
		List<SomeDinamicBean> lista = new ArrayList<SomeDinamicBean>();
		lista.add(new SomeDinamicBean("valor1", "valor2", "valor3"));
		lista.add(new SomeDinamicBean("valor11", "valor12", "valor13"));
		
		//Se definen los campos que no se quieren filtrar.		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("valor3", "valor2");
		
		//Se hace el mapeo del filter con el la clase del bean.
		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeDinamicBeanFiltering", filter);
		
		//Asocia el filtro que se debe aplicar a la información.
		MappingJacksonValue mapping = new MappingJacksonValue(lista);
		mapping.setFilters(filters);
		
		
		return mapping;
	}	
}
