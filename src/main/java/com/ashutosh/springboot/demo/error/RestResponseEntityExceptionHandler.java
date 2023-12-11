package com.ashutosh.springboot.demo.error;

import com.ashutosh.springboot.demo.entity.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/*
   This class clears the unnecessary messages from browser and shows a simple error message that
  can be easily understood by the user

 */

@ControllerAdvice
@ResponseStatus
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DepartmentNotFoundException.class)
    public ResponseEntity<ErrorMessage> departmentNotFoundException(DepartmentNotFoundException exception,
                                                                   WebRequest request){

        /* Error Message Class created in Controller Package
           --> What we want to show in error message is specified in that class
         */

        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());

        /* What response we want to show the user, in this case:
           ONLY --> HTTP error code & error message
         */
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }
}
