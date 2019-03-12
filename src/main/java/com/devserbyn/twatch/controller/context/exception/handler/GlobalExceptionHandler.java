package com.devserbyn.twatch.controller.context.exception.handler;

import com.devserbyn.twatch.constant.VIEW_CONST;
import com.devserbyn.twatch.exception.TwatchException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    private static List<String> generateErrorMsgList(Exception exception) {
        List<String> errorMessages = new ArrayList<>();
        errorMessages.add(exception.getLocalizedMessage());
        return errorMessages;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView handleAll(Exception e) {
        log.error("Unknown exception occurred {}", e);

        return new ModelAndView(VIEW_CONST.ERROR_VIEW);
    }

    @ExceptionHandler(TwatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ModelAndView handleTwatchException(TwatchException e) {
        log.error("Twatch exception occurred", e);
        ModelAndView modelAndView = new ModelAndView(VIEW_CONST.ERROR_VIEW);
        modelAndView.addObject("errors", generateErrorMsgList(e));

        return modelAndView;
    }
}
