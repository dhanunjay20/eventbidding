package com.event.bidding.service.impl;

import com.event.bidding.dto.VendorRegistrationDto;
import com.event.bidding.entity.Vendor;
import com.event.bidding.repository.UserRepository;
import com.event.bidding.repository.VendorRepository;
import com.event.bidding.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class VendorServiceImpl implements VendorService {

    @Autowired
    private VendorRepository vendorRepo;
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void registerVendor(VendorRegistrationDto dto) {
        if (userRepo.existsByEmail(dto.getEmail()) || vendorRepo.existsByEmail(dto.getEmail()))
            throw new RuntimeException("Email already registered");
        if (userRepo.existsByMobile(dto.getMobile()) || vendorRepo.existsByMobile(dto.getMobile()))
            throw new RuntimeException("Mobile number already registered");
        if (vendorRepo.existsByVendorOrganizationId(dto.getVendorOrganizationId()))
            throw new RuntimeException("Organization ID already registered");
        Vendor vendor = new Vendor();
        vendor.setVendorOrganizationId(dto.getVendorOrganizationId());
        vendor.setBusinessName(dto.getBusinessName());
        vendor.setContactName(dto.getContactName());
        vendor.setEmail(dto.getEmail());
        vendor.setMobile(dto.getMobile());
        vendor.setPasswordHash(passwordEncoder.encode(dto.getPassword()));
        vendor.setAddresses(dto.getAddresses());
        vendor.setLicenseDocuments(dto.getLicenseDocuments());
        vendorRepo.save(vendor);
    }
}
