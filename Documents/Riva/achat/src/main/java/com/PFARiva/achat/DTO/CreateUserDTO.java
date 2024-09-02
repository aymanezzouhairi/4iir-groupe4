package com.PFARiva.achat.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserDTO {
    private String username;
    private String password;
    private String role;
}
