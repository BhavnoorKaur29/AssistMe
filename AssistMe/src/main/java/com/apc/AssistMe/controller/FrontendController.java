package com.apc.AssistMe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FrontendController {

    @GetMapping({"/", "/index"})
    public String index() {
        return "index";
    }



    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/services")
    public String services() {
        return "services";
    }

    @GetMapping("/service-options")
    public String serviceOptions() {
        return "service-options";
    }

    @GetMapping("/childcare")
    public String childcare() {
        return "childcare";
    }

    @GetMapping("/cleaning")
    public String cleaning() {
        return "cleaning";
    }

    @GetMapping("/elderlycare")
    public String elderlyCare() {
        return "elderlycare";
    }

    @GetMapping("/grocery")
    public String grocery() {
        return "grocery";
    }

    @GetMapping("/offer-help")
    public String offerHelp() {
        return "offer-help";
    }

    @GetMapping("/request-help")
    public String requestHelp() {
        return "request-help";
    }

    @GetMapping("/performances")
    public String performances() {
        return "performances";
    }

    @GetMapping("/petowner")
    public String petOwner() {
        return "petowner";
    }

    @GetMapping("/tutor")
    public String tutor() {
        return "tutor";
    }

    @GetMapping("/payment")
    public String payment() {
        return "payment";
    }

    @GetMapping("/reviews")
    public String reviews() {
        return "reviews";
    }

    @GetMapping("/thankyou")
    public String thankyou() {
        return "thankyou";
    }

    @GetMapping("/404")
    public String notFound() {
        return "404";
    }
}
