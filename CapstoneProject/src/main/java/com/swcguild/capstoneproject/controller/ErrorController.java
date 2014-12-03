/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.capstoneproject.controller;

import java.text.MessageFormat;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author apprentice
 */
@Controller
public class ErrorController {

    @RequestMapping(value = "error", method = RequestMethod.GET)
    public String displayError(HttpServletRequest request, Model model) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        HttpStatus httpStatus = HttpStatus.valueOf(statusCode);
        
        Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
        String throwableMessage;
        if(throwable==null){
            throwableMessage="";
        }
        else{
            throwableMessage=throwable.getMessage();
        }
        
        String exceptionMessage = httpStatus.getReasonPhrase();

        String requestUri = (String) request.getAttribute("javax.servlet.error.request_uri");
        if (requestUri == null) {
            requestUri = "Unknown";
        }

        String message = MessageFormat.format("{0} returned for {1}: {2} {3}",
                statusCode, requestUri, exceptionMessage, throwableMessage);
        model.addAttribute("errorMessage", message);
        return "customError";
    }
}
