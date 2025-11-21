package com.event.bidding.controller;

import com.event.bidding.dto.LoginRequestDto;
import com.event.bidding.dto.AuthResponseDto;
import com.event.bidding.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public AuthResponseDto login(@RequestBody LoginRequestDto dto) {
        return authService.login(dto);
    }
}
