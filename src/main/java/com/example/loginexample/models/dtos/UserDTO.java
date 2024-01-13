package com.example.loginexample.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    int id;
    String username;
    String firstname;
    String lastname;
    String country;

    public UserDTO(int i, String username, String password, String firstname, String lastname, String country, String role) {
    }
}
