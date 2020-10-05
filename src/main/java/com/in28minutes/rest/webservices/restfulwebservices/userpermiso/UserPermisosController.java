package com.in28minutes.rest.webservices.restfulwebservices.userpermiso;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.in28minutes.rest.webservices.restfulwebservices.permiso.Permiso;

@RestController
public class UserPermisosController {
	
	@Autowired
	private UserPermisosDaoService userPermisosDaoService;
	
	@GetMapping("/userPermisos/{idUser}")
	public ResponseEntity<?> retriveAllPermisosPorUser(@PathVariable int idUser) {
		List<Permiso> permisosDelUser = userPermisosDaoService.findAllPermisosPorUser(idUser);
		
		if( permisosDelUser == null )
			throw new UserPermisosNotFoundException("No existe el user id-"+idUser);
		
		return new ResponseEntity(permisosDelUser, HttpStatus.OK);
	}
	
	@PostMapping("/userPermisos/{idUser}")
	public ResponseEntity<?> createPermisoParaUser(@PathVariable int idUser, @RequestBody Permiso permiso){
		List<Permiso> permisosUser = userPermisosDaoService.savePermisosPorUser(idUser, permiso);
		
		if( permisosUser == null )
			throw new UserPermisosNotFoundException("No se genero el nuevo permiso para el user id-"+idUser);
		
		return new ResponseEntity(permisosUser, HttpStatus.OK);
	}
	
	@GetMapping("/userPermisos/{idUser}/{idPermiso}")
	public ResponseEntity<?> findOnePermisoPorUser(@PathVariable int idUser, @PathVariable int idPermiso) {
		Permiso permiso = userPermisosDaoService.findPermisoDeUser(idUser, idPermiso);
		
		if( permiso == null )
			throw new UserPermisosNotFoundException("No se encontro el permiso id-"+idPermiso+" para el user id-"+idUser);
		
		return new ResponseEntity(permiso, HttpStatus.OK);
	}
	
}
