package com.emsi.Site_de_Reservation.DTO;

import com.emsi.Site_de_Reservation.model.Role;
import lombok.*;

import java.sql.Blob;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private byte[] userAvatar; // Convert Blob to byte[]
    private byte[] userCover; // Convert Blob to byte[]
    private Role role;
}

