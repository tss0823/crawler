/**
 * 
 */
package com.usefullc.crawler.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author  admin
 *
 * @2015-05-31 18
 */
@Controller
public class MainController extends BaseController {

    @RequestMapping(value = "/index.htm")
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/frame.htm")
    public String frame() {
        return "frame";
    }

    @RequestMapping(value = "/top.htm")
    public String top() {
        return "/main/top";
    }

    @RequestMapping(value = "/left.htm")
    public String left() {
        return "/main/left";
    }

    @RequestMapping(value = "/nav.htm")
    public String nav() {
        return "/main/nav";
    }

    @RequestMapping(value = "/right.htm")
    public String right() {
        return "/main/right";
    }

    @RequestMapping(value = "/main.htm")
    public String main() {
        return "/main/main";
    }
}
