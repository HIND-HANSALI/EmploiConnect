package com.emploiconnect.controller;

import com.emploiconnect.dto.request.GrantAuthoritiesRequestDto;
import com.emploiconnect.dto.request.GrantRoleToUserRequestDto;
import com.emploiconnect.dto.request.RoleRequestDTO;
import com.emploiconnect.dto.request.UpdateUserRoleRequest;
import com.emploiconnect.dto.response.AuthenticationResponse;
import com.emploiconnect.dto.response.RoleResponseDTO;
import com.emploiconnect.entity.Role;
import com.emploiconnect.service.RoleService;
import com.emploiconnect.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/v1/role")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
public class RoleController {
    private final RoleService roleService;
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<RoleResponseDTO>> getAll() {
        List<Role> roles = roleService.getAll();
        if (roles == null) return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        else if (roles.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else return new ResponseEntity<>(roles.stream().map(RoleResponseDTO::fromRole).toList(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<RoleResponseDTO> save(@RequestBody RoleRequestDTO roleToSave) {
        Role role = roleService.save(roleToSave.toRole(), false);
        if (role == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else return new ResponseEntity<>(RoleResponseDTO.fromRole(role), HttpStatus.OK);
    }

    @PutMapping("/{userId}/updateRole")
    public ResponseEntity<AuthenticationResponse> updateUserRole(@PathVariable Long userId, @RequestBody UpdateUserRoleRequest request) {

        AuthenticationResponse response = roleService.updateUserRole(userId,request);
        return ResponseEntity.ok().body(response);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (roleService.getById(id).isPresent()) {
            roleService.delete(id);
            return ResponseEntity.ok().build();
        } else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }





//    @PutMapping("/grant_authorities")
//    public ResponseEntity<RoleResponseDTO> grantAuthorities(@RequestBody GrantAuthoritiesRequestDto rolesAuthorities) {
//        Role role = roleService.grantAuthorities(rolesAuthorities.getAuthorityId(), rolesAuthorities.getRoleId());
//        if (role == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        } else return new ResponseEntity<>(RoleResponseDTO.fromRole(role), HttpStatus.OK);
//    }

//    @PutMapping("/revoke_authorities")
//    public ResponseEntity<RoleResponseDTO> revokeAuthorities(@RequestBody GrantAuthoritiesRequestDto rolesAuthorities) {
//        Role role = roleService.revokeAuthorities(rolesAuthorities.getAuthorityId(), rolesAuthorities.getRoleId());
//        if (role == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        else return new ResponseEntity<>(RoleResponseDTO.fromRole(role), HttpStatus.OK);
//    }



    //grant role to user
//    @PostMapping("/grant_role_to_user")
//    public ResponseEntity<RoleResponseDTO> grantRoleToUser(@RequestBody GrantRoleToUserRequestDto grantRoleToUserRequestDto) {
//        Role role = userService.grantRoleToUser(grantRoleToUserRequestDto.getUserId(), grantRoleToUserRequestDto.getRoleId());
//
//        if (role == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        } else {
//            return new ResponseEntity<>(RoleResponseDTO.fromRole(role), HttpStatus.OK);
//        }
//    }
}
