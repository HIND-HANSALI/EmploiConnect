package com.emploiconnect.dto.request;

import com.emploiconnect.entity.Authority;
import com.emploiconnect.entity.Role;

import java.util.List;

public record RoleRequestDTO(
    String name,
    List<Authority> authorities,
    boolean isDefault
){
        public Role toRole(){
            return Role.builder()
                    .name(name)
                    .isDefault(isDefault)
                    .authorities(authorities)
                    .build();
        }
    }