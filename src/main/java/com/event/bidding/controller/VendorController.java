package com.event.bidding.controller;

import com.event.bidding.dto.VendorRegistrationDto;
import com.event.bidding.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vendor")
public class VendorController {

    @Autowired
    private VendorService vendorService;

    @PostMapping("/register")
    public void register(@RequestBody VendorRegistrationDto dto) {
        vendorService.registerVendor(dto);
    }
}
