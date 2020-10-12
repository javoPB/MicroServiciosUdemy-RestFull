package com.in28minutes.rest.webservices.restfulwebservices.user;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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
public class UserJpaController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@GetMapping("/jpa/users")
	public List<User> retrieveUsers(){
		List<User> users = userRepository.findAll();
		
		return users;
	}
	
	@GetMapping("/jpa/users/{id}")
	public User retrieveUser(@PathVariable int id) {
		Optional<User> user = userRepository.findById(id);
		
		if( !user.isPresent() )
			throw new UserNotFoundException("User not found id-"+id);
		
		return user.get();
	}
	
	@PostMapping("/jpa/users")
	public ResponseEntity<?> createUser(@Valid @RequestBody User user) {
		User saveUser = userRepository.save(user);
		
		//Obtiene la URI del user persistido.
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(saveUser.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}	
	
	@DeleteMapping("/jpa/users/{id}")
	public void deleteUser(@PathVariable int id){
		userRepository.deleteById(id);
	}	
	

	@GetMapping("/jpa/users/{id}/post")
	public List<Post> retrieveAllPostByUser(@PathVariable int id) {
		Optional<User> user = userRepository.findById(id);
		
		if( !user.isPresent() )
			throw new UserNotFoundException("User not found id-"+id);
		
		return user.get().getPosts();
	}
	
	@PostMapping("/jpa/users/{id}/post")
	public ResponseEntity<?> createPostByUser(@PathVariable int id, @Valid @RequestBody Post post) {
		Optional<User> userFind = userRepository.findById(id);
		
		if( !userFind.isPresent() )
			throw new UserNotFoundException("User not found id-"+id);
		
		User user = userFind.get();
		post.setUser(user);
		
		Post savePost = postRepository.save(post);
		
		
		//Obtiene la URI del user persistido.
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savePost.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}	
}

//@Valid para validar el contenido de la información que se pasa en el request y que se mapea en los Bean de Java. (Se ubica en a API de validación JavaX)
//       las espedificaciones que debe cumplir la información se definen en los ENTITY, DTO, POJO, ETC...
//		 Por ejemplo: @Size(min=2, message="El nombre debe contener al menos dos caracteres.")	
//       NOTA: Si por alguna razón, no se carga la dependencia requerida, actualizar el proyecto. (Clic derecho sobre proyecto --> Maven --> Update project )
//		 En caso de un erro, se debe personalizar el mensaje de error a través de la clase que maneja las exceptions.


