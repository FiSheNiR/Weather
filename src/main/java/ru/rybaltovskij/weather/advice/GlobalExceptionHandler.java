package ru.rybaltovskij.weather.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import ru.rybaltovskij.weather.exception.NotExistUserException;
import ru.rybaltovskij.weather.exception.NotUniqueUserException;
import ru.rybaltovskij.weather.exception.WrongPasswordException;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ModelAndView handleGenericException(Exception e) {
        ModelAndView modelAndView = new ModelAndView("error");
        log.error(e.getMessage(), e);
        return modelAndView;
    }

    @ExceptionHandler(NotUniqueUserException.class)
    public ModelAndView handleNotUniqueUserException(NotUniqueUserException e) {
        log.error(e.getMessage(), e);
        return new ModelAndView("sign-up-with-errors");
    }

}
