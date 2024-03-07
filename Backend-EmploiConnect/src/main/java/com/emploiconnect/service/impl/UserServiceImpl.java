package com.emploiconnect.service.impl;


import com.emploiconnect.repository.RoleRepository;
import com.emploiconnect.repository.UserRepository;
import com.emploiconnect.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;













//    @Override
//    public Role grantRoleToUser(Long userId, Long roleId) {
//        List<String> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
//        if (authorities.contains("ASSIGN_ROLE_TO_USER")) {
//            Role role = roleRepository.findById(roleId).orElse(null);
//            User user = userRepository.findById(userId).orElse(null);
//            if (role != null && user != null) {
//                user.setRole(role);
//                userRepository.save(user);
//                return role;
//            }
//            throw new youcode.security.handler.request.CustomException("Role or user not found", HttpStatus.NOT_FOUND);
//        }throw new youcode.security.handler.request.CustomException("Insufficient authorities", HttpStatus.UNAUTHORIZED);
//    }




}
