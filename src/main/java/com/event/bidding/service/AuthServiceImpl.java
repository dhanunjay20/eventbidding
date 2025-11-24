package com.event.bidding.service;

import com.event.bidding.dto.LoginRequestDto;
import com.event.bidding.dto.AuthResponseDto;
import com.event.bidding.entity.User;
import com.event.bidding.entity.Vendor;
import com.event.bidding.repository.UserRepository;
import com.event.bidding.repository.VendorRepository;
import com.event.bidding.service.AuthService;
import com.event.bidding.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepo;
    @Autowired
    private VendorRepository vendorRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public AuthResponseDto login(LoginRequestDto loginDto) {
        User user = userRepo.findByEmail(loginDto.getLogin()).orElse(
                userRepo.findByMobile(loginDto.getLogin()).orElse(null));
        if (user != null && passwordEncoder.matches(loginDto.getPassword(), user.getPasswordHash())) {
            String jwt = jwtUtil.generateToken(user.getEmail(), "USER");
            return AuthResponseDto.ofUser(user, jwt);
        }
        Vendor vendor = vendorRepo.findByEmail(loginDto.getLogin()).orElse(
                vendorRepo.findByMobile(loginDto.getLogin()).orElse(null));
        if (vendor != null && passwordEncoder.matches(loginDto.getPassword(), vendor.getPasswordHash())) {
            String jwt = jwtUtil.generateToken(vendor.getEmail(), "VENDOR");
            return AuthResponseDto.ofVendor(vendor, jwt);
        }
        throw new RuntimeException("Invalid login credentials");
    }
}
