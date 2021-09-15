package com.homecook.homecookadmin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/admin/api/v1/health")
public class AdminHealthCheckController
{
    @GetMapping
    public String healthCheck() {
        return "OK";
    }
}
