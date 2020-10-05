package com.in28minutes.rest.webservices.restfulwebservices.permiso;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class PermisoController {
	
	@Autowired
	private PermisoDaoService permisoDaoService;
	
	@GetMapping("/permisos")
	public ResponseEntity<?> retrievePermisos(){
		List<Permiso> permisos = permisoDaoService.findAll();
		
		return new ResponseEntity(permisos, HttpStatus.OK);
	}
	
	
	@GetMapping("/permisos/{id}")
	public ResponseEntity<?> retrievePermiso(@PathVariable int id){
		Permiso permiso = permisoDaoService.findOne(id);
		
		if( permiso == null )
			throw new PermisoNotFoundException("No existe el permiso id-"+id);
		//URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(permiso.getId()).toUri();
		
		return new ResponseEntity(permiso, HttpStatus.OK);
	}
	
	@PostMapping("/permisos")
	public ResponseEntity createPermiso(@RequestBody Permiso permiso) {
		Permiso savedPermiso = permisoDaoService.save(permiso);
		
		if( savedPermiso == null )
			throw new PermisoNotFoundException("No se pudo parsistir la información del permiso");
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedPermiso.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	
	@DeleteMapping("/permisos/{id}")
	public ResponseEntity<?> deletePermiso(@PathVariable int id){
		Permiso permiso = permisoDaoService.deletePermiso(id);
		
		return new ResponseEntity(permiso, HttpStatus.OK);
	}
}
