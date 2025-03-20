package com.biocorp.intento2.demo;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/manager")
@PreAuthorize("hasRole('MANAGER')")
public class ManagerController {
    @GetMapping
    @PreAuthorize("hasAuthority('manager:read')")
    public String get() {
        return "MANAGER::GET";
    }

    @PostMapping
    @PreAuthorize("hasAuthority('manager:create')")
    public String post() {
        return "MANAGER::POST";
    }

    @PutMapping
    @PreAuthorize("hasAuthority('manager:update')")
    public String put() {
        return "MANAGER::PUT";
    }

    @DeleteMapping
    @PreAuthorize("hasAuthority('manager:delete')")
    public String delete() {
        return "MANAGER::DELETE";
    }
}
