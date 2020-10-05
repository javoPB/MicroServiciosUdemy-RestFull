package com.in28minutes.rest.webservices.restfulwebservices.permiso;

public class Permiso {
	private Integer id;
	private String name;
	private String descripcion;
	
	public Permiso(Integer id, String name, String descripcion) {
		super();
		this.id = id;
		this.name = name;
		this.descripcion = descripcion;
	}

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
}
