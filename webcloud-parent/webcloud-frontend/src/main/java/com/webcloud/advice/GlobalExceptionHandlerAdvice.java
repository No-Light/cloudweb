package com.webcloud.advice;

import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandlerAdvice {

    @ExceptionHandler(value = Exception.class)
    public ModelAndView exception(Exception exception, HttpRequest httpRequest){

        ModelAndView modelAndView = new ModelAndView("/error");
        modelAndView.addObject("errorMessage",exception.getMessage());
        modelAndView.addObject("stackTrace",exception.getStackTrace());
        return modelAndView;

    }
}
