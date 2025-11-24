package com.event.bidding.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.event.bidding.entity.Address;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateDto {
    private String firstName;
    private String lastName;
    private String mobile;
    private List<Address> addresses; // allow replacing or setting addresses
    private Address address; // keep single-address convenience for adding
    private String password; // Optional: only processed if not null/empty
    private String profileUrl; // allow updating profileUrl
}
