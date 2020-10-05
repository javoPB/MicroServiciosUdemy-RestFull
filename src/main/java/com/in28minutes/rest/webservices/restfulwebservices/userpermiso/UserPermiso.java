package com.in28minutes.rest.webservices.restfulwebservices.userpermiso;

import java.util.List;


import com.in28minutes.rest.webservices.restfulwebservices.permiso.Permiso;
import com.in28minutes.rest.webservices.restfulwebservices.user.User;

public class UserPermiso {
	private Integer id;
	private User user;
	private List<Permiso> permisos;
	
	public UserPermiso(Integer id, User user, List<Permiso> permisos) {
		super();
		this.id = id;
		this.user = user;
		this.permisos = permisos;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Permiso> getPermisos() {
		return permisos;
	}

	public void setPermisos(List<Permiso> permisos) {
		this.permisos = permisos;
	}
	
	
}
