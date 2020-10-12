package com.in28minutes.rest.webservices.restfulwebservices.filtering;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value = {"valor2"})
public class SomeBean {
	private String valor1;
	private String valor2;
	private String valor3;
	
	public SomeBean(String valor1, String valor2, String valor3) {
		super();
		this.valor1 = valor1;
		this.valor2 = valor2;
		this.valor3 = valor3;
	}

	public String getValor1() {
		return valor1;
	}

	public void setValor1(String valor1) {
		this.valor1 = valor1;
	}

	public String getValor2() {
		return valor2;
	}

	public void setValor2(String valor2) {
		this.valor2 = valor2;
	}

	public String getValor3() {
		return valor3;
	}

	public void setValor3(String valor3) {
		this.valor3 = valor3;
	}
	
	
	
}
