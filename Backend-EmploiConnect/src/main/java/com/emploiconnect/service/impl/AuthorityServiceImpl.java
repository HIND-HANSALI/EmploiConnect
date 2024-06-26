package com.emploiconnect.service.impl;

import com.emploiconnect.entity.Authority;
import com.emploiconnect.enums.AuthorityEnum;
import com.emploiconnect.repository.AuthorityRepository;
import com.emploiconnect.service.AuthorityService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class AuthorityServiceImpl implements AuthorityService {
    private final AuthorityRepository authorityRepository;

    @Override
    public List<Authority> getAllByName(List<AuthorityEnum> authorities) {
        //List<String> usersAuthorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
        //if (usersAuthorities.contains(AuthorityEnum.VIEW_AUTHORITIES.toString())) {
            return authorityRepository.findAllByName(authorities);
        //}
        //return null;
    }
    @Override
    public Optional<Authority> getByName(AuthorityEnum authorityEnum) {
        return authorityRepository.findByName(authorityEnum);
    }

    @Override
    public Optional<Authority> getById(Long id) {
        return authorityRepository.findById(id);
    }
}
