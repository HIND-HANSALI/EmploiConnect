package com.emploiconnect.service;

import com.emploiconnect.entity.Authority;
import com.emploiconnect.enums.AuthorityEnum;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
@Component
public interface AuthorityService {
    Optional<Authority> getByName(AuthorityEnum authorityEnum);
    List<Authority> getAllByName(List<AuthorityEnum> authorities);
    Optional<Authority> getById(Long id);
}
