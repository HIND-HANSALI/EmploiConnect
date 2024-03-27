package com.emploiconnect.service;

import com.emploiconnect.dto.request.UpdateUserRoleRequest;
import com.emploiconnect.dto.response.AuthenticationResponse;
import com.emploiconnect.entity.Role;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
@Component
public interface RoleService {
    Role save(Role role, boolean isSeed);

    Optional<Role> findDefaultRole();

    Optional<Role> getById(Long id);
    AuthenticationResponse updateUserRole(Long userId, UpdateUserRoleRequest request);
    void delete(Long id);

    Optional<Role> getByName(String name);
    List<Role> getAll();




    //    Role update(Role role, Long id);
//    Role grantAuthorities(Long authorityId, Long roleId);
//
//    Role revokeAuthorities(Long authorityId, Long roleId);
}
