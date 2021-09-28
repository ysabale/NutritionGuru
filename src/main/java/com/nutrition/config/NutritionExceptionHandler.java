package com.nutrition.config;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.nutrition.model.ErrorMessage;


@RestControllerAdvice
public class NutritionExceptionHandler {

	@ExceptionHandler(NutritionCustomException.class)
	public ResponseEntity<ErrorMessage> getInsuranceCustomException(NutritionCustomException exception) {
		ErrorMessage error = new ErrorMessage();
		error.setErrorCode("INVALID_INPUT_DATA");
		error.setErrorMessage(exception.getMessage());
		return new ResponseEntity<ErrorMessage>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorMessage> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap();
		ErrorMessage errorMessage = new ErrorMessage();
		errorMessage.setErrorCode("INVALID_INPUT_DATA");

		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String emsg = error.getDefaultMessage();
			errors.put(fieldName, emsg);
			errorMessage.setErrorMessage(error.getDefaultMessage());
		});
		return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.BAD_REQUEST);
	}

}
