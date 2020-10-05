package com.in28minutes.rest.webservices.restfulwebservices.user;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;


//Para versiones de spring-boot menores o igual a 2.2.0
//import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
//import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
//import org.springframework.hateoas.Resource;
//import org.springframework.hateoas.mvc.ControllerLinkBuilder;

//Para versiones de spring-boot mayores a 2.2.0
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import org.springframework.hateoas.server.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserController {

	@Autowired
	private UserDaoService userDaoService;
	
	@GetMapping("/users")
	public List<User> retrieveUsers(){
		List<User> users = userDaoService.findAll();
		
		return users;
	}
	
// Con está versión se obtiene la información del user.	
//	@GetMapping("/users/{id}")
//	public User retrieveUser(@PathVariable int id) {
//		User user = userDaoService.findOne(id);
//		
//		return user;
//	}
	
// Con esta versión se devuelve la URI del user consultado como parte de los Headers
//	@GetMapping("/users/{id}")
//	public ResponseEntity<?> retrieveUser(@PathVariable int id) {
//		User user = userDaoService.findOne(id);
//		
//		if( user == null )
//			throw new UserNotFoundException("User not found id-"+id);
//		
//		//NOTA: En esta ocación se sustituye la parte de path("/{id}").buildAndExpand(saveUser.getId()).toUri() por .build().toUri() 
//		URI location = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
//		
//		return ResponseEntity.created(location).build();
//	} 	

	//Con esta versión se obtiene además de la información del user, los enlaces (URL's) para consultar a todos los user.
	@GetMapping("/users/{id}")
	public EntityModel<User> retrieveUser(@PathVariable int id) {
		User user = userDaoService.findOne(id);
		
		//Para versiones de spring-boot menores o igual a 2.2.0
		//Resource<User> resource = new Resource<User>(user);
		//ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
		//resource.add(linkTo.withRel("all-users"));
		//return resource;
		
		//Para versiones de spring-boot mayores a 2.2.0
		EntityModel<User> resource = EntityModel.of(user);		
		WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveUsers());
		resource.add(linkTo.withRel("all-users"));
		
		return resource;
	} 	
	
	
//	@PostMapping("/users")
//	public User createUser(@RequestBody User user) {
//		User usr = userDaoService.save(user);
//		
//		return usr;
//	}
	
//	@PostMapping("/users")
//	public ResponseEntity<?> createUser(@RequestBody User user) {
//		User saveUser = userDaoService.save(user);
//		
//		//Obtiene la URI del user persistido.
//		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(saveUser.getId()).toUri();
//		
//		return ResponseEntity.created(location).build();
//	}
	
	@PostMapping("/users")
	public ResponseEntity<?> createUser(@Valid @RequestBody User user) {
		User saveUser = userDaoService.save(user);
		
		//Obtiene la URI del user persistido.
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(saveUser.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}	
	
	@DeleteMapping("/users/{id}")
	public User deleteUser(@PathVariable int id){
		User deletedUser = userDaoService.deleteUser(id);
		
		if( deletedUser == null )
			throw new UserNotFoundException("User do not exist id-"+id);
		
		return deletedUser;
	}
}

//@Valid para validar el contenido de la información que se pasa en el request y que se mapea en los Bean de Java. (Se ubica en a API de validación JavaX)
//       las espedificaciones que debe cumplir la información se definen en los ENTITY, DTO, POJO, ETC...
//		 Por ejemplo: @Size(min=2, message="El nombre debe contener al menos dos caracteres.")	
//       NOTA: Si por alguna razón, no se carga la dependencia requerida, actualizar el proyecto. (Clic derecho sobre proyecto --> Maven --> Update project )
//		 En caso de un erro, se debe personalizar el mensaje de error a través de la clase que maneja las exceptions.


