package ru.rybaltovskij.weather.advice;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

//@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ModelAndView handleGenericException(Exception e) {
        ModelAndView modelAndView = new ModelAndView("error");
        return modelAndView;
    }
}
