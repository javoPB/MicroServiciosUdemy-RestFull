package com.in28minutes.rest.webservices.restfulwebservices.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.in28minutes.rest.webservices.restfulwebservices.user.UserNotFoundException;


@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	
	//Para el uso exclusivo de exceptions del tipo Exception 
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> HandleAllExceptions(Exception ex, WebRequest request){
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
		
		//Se define manejo de exceptions del tipo HttpStatus.INTERNAL_SERVER_ERROR
		return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	//Para el uso exclusivo de exceptions de tipo UserNotFoundException 
	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<Object> HandleUserNotFoundExceptions(UserNotFoundException ex, WebRequest request){
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
		
		//Se define manejo de exceptions del tipo HttpStatus.NOT_FOUND
		return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
	}
	
	/**
	 * Exception para el manejo de validaciones en la información que se pasa en los request y se mapea a los bean de Java.
	 * 
	 */
//	@Override
//	protected ResponseEntity<Object> handleMethodArgumentNotValid(
//			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
//
//		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), ex.getBindingResult().toString());
//		
//		return handleExceptionInternal(ex, null, headers, status, request);
//	}	
	
	//Personalizando el método para el caso en el que no se pasan argumentos validos en el request.
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), ex.getBindingResult().toString());
		
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}		
	
}


//ResponseEntityExceptionHandler -- Para extender del manejo de Exceptions que nos proporciona spring.
//@RestController -- Para indicar que es un controller. 
//@ControllerAdvice -- Para indicar que se aplique a todos los controller. (Para indicar que este manejador de exceptions se aplique a todos los controller)
//                  -- Para indicar  que este recurso se debe compartir entre todos los controller.


//@ExceptionHandler(Exception.class) -- Para indicar que se manejan todas las exceptions