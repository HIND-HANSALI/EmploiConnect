package com.emploiconnect.dto.response;

import com.emploiconnect.entity.Authority;
import com.emploiconnect.entity.Role;
import com.emploiconnect.enums.AuthorityEnum;


import java.util.List;

public record RoleResponseDTO(
        Long id,
        String name,
        List<AuthorityEnum> authorities,
        boolean isDefault
) {
    public static RoleResponseDTO fromRole(Role role){
        return new RoleResponseDTO(
                role.getId(),
                role.getName(),
                role.getAuthorities().stream().map(Authority::getName).toList(),
                role.isDefault()
        );
    }
}
