package ru.repp.den.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
public class ExceptionHandlingController implements ErrorController {

    public static final String PATH = "/error";

//    // Total control - setup a model and return the view name yourself. Or consider
//    // subclassing ExceptionHandlerExceptionResolver (see below).
//    @ExceptionHandler(EntityNotFoundException.class)
//    @RequestMapping(PATH)
//    public ModelAndView handleError(HttpServletRequest req, EntityNotFoundException exception) {
//        ModelAndView mav = new ModelAndView();
//        mav.addObject("message", exception.getMessage());
//        mav.addObject("code", req.getAttribute("javax.servlet.error.status_code"));
//        mav.setViewName("error");
//        return mav;
//    }
//
//    @Override
//    public String getErrorPath() {
//        return PATH;
//    }

    @Autowired
    private ErrorAttributes errorAttributes;

    @RequestMapping(value = PATH)
    ErrorJson error(HttpServletRequest request, HttpServletResponse response) {
        // Appropriate HTTP response code (e.g. 404 or 500) is automatically set by Spring.
        // Here we just define response body.
        return new ErrorJson(response.getStatus(), getErrorAttributes(request, false));
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }

    private Map<String, Object> getErrorAttributes(HttpServletRequest request, boolean includeStackTrace) {
        RequestAttributes requestAttributes = new ServletRequestAttributes(request);
        return errorAttributes.getErrorAttributes(requestAttributes, includeStackTrace);
    }
}