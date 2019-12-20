package com.improving.bootcamp;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice //any exceptionHandler methods in here will be applied to ALL controllers
public class ErrorHandler {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class) //tell Spring what exception to handle with this method
    public String exceptionHandler(Exception e, Model model) { // Spring will pass the exception through to the method
        model.addAttribute("errorMessage", e.getMessage());
        return "somethingbadhappened"; //resolve to the "error" view
    }

    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    @ExceptionHandler(TeapotException.class) //tell Spring what exception to handle with this method
    public String exceptionHandler(TeapotException e, Model model) { // Spring will pass the exception through to the method
        model.addAttribute("errorMessage", e.getMessage());
        return "somethingbadhappened"; //resolve to the "error" view
    }
}
