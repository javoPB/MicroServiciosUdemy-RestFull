package com.in28minutes.rest.webservices.restfulwebservices.permiso;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class PermisoDaoService {
	private static List<Permiso> permisos = new ArrayList<Permiso>();
	private static int permisosCounter = 0;
	
	static {
		permisos.add(new Permiso(1, "Administrador", "Con permisos de root. (CRUD)"));
		permisos.add(new Permiso(2, "User", "Con permisos de usuario restringido. (CRU)"));
		permisos.add(new Permiso(3, "Invitado", "Con permisos de solo lectura. (R)"));
		
		permisosCounter = permisos.size();
	}
	
	public List<Permiso> findAll() {
		return permisos;
	}
	
	public Permiso findOne(Integer id) {
		for(Permiso permiso:permisos) {
			if( permiso.getId() == id ) 
				return permiso;
		}
		
		return null;
	}
	
	public Permiso save(Permiso permiso) {
		if( permiso.getId() == null ) {
			permiso.setId(++permisosCounter);
		}
		
		permisos.add(permiso);
		
		return permiso;
	}
	
	public Permiso deletePermiso(Integer id) {
		for( Permiso permiso:permisos ) {
			if( permiso.getId() == id ) {
				permisos.remove(permiso);
				
				return permiso;
			}
		}
		
		return null;
	}
}
