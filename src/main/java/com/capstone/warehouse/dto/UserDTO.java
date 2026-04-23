package com.capstone.warehouse.dto;

import com.capstone.warehouse.entity.User;
import lombok.Data;

@Data
public class UserDTO {
    private Integer id;
    private String name;
    private String email;
    private User.Role role;

    public static UserDTO from(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole());
        return dto;
    }
}