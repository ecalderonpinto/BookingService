/**
 * If you really care for the license, look for the LICENSE.txt at the project root. Frankly, I 
 * really don't care.
 **/
package com.reportingtool.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * This controller facilitates actions related to authentication of the app.
 * 
 * @author Roshan Amadoru
 **/
@Controller
public class AuthenticationController {
    
    /**
     * The logger instance
     **/
    private static Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    /**
     * Handles requests to the /login.do page
     **/
    @RequestMapping(method = RequestMethod.GET, value =  "login.do" )
    public String showLoginPage(Model model) {
        logger.info("Page Request: /login.do");
        return "login";
    }
    
    /**
     * Handles requests to the /403.do page
     **/
    @RequestMapping(method = RequestMethod.GET, value = "error/403.do" )
    public String show403Page(Model model) {
        logger.info("Page Request: /error/403.do");
        return "403";
    }
}
