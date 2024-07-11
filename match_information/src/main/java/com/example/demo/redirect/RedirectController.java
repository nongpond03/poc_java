package com.example.demo.redirect;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;

@CrossOrigin
@RestController
@RequestMapping(path = "/api/v1/redirect")
public class RedirectController {

    @Autowired
    private RedirectService service;

    @GetMapping
    public RedirectView redirectToExternalUrl(
            @RequestParam(name = "state", required = false) String state) {
        System.out.println("state: " + state);
        String redirectUrl = service.kcc();
        System.out.println("redirectUrl: " + redirectUrl);
        RedirectView redirectView = new RedirectView(redirectUrl);
        return redirectView;
    }

    @RequestMapping("/foo")
    void handleFoo(HttpServletResponse response) throws IOException {
        String redirectUrl = service.kcc();
        response.sendRedirect(redirectUrl);
    }
}
