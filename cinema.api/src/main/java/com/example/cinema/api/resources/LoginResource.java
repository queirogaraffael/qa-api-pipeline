package com.example.cinema.api.resources;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Login")
@RestController
@RequestMapping("/api/login")
public class LoginResource {
}
