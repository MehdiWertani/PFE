package io.autotest.autotest.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {

    @GetMapping("/all")
    public String allAccess() {
        return "this application allows you to test SMS marketing campaigns.";
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('ROLE_COLLABORATOR') or hasRole('ROLE_DELIVERY_MANAGER') or hasRole('ROLE_ADMIN')")
    public String userAccess() {
        return "User Content.";
    }

    @GetMapping("/bil")
    @PreAuthorize("hasRole('ROLE_DELIVERY_MANAGER')")
    public String moderatorAccess() {
        return "BILLINGMANAGER Board.";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String adminAccess() {
        return "Admin Board.";
    }
}
