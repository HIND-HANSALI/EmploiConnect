package com.emploiconnect.service.impl;

import com.emploiconnect.configuration.JwtService;
import com.emploiconnect.dto.request.UpdateUserRoleRequest;
import com.emploiconnect.dto.response.AuthenticationResponse;
import com.emploiconnect.entity.Authority;
import com.emploiconnect.entity.Company;
import com.emploiconnect.entity.Role;
import com.emploiconnect.entity.User;
import com.emploiconnect.handler.exception.ResourceNotFoundException;
import com.emploiconnect.repository.CompanyRepository;
import com.emploiconnect.repository.RoleRepository;
import com.emploiconnect.repository.UserRepository;
import com.emploiconnect.service.AuthorityService;
import com.emploiconnect.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.emploiconnect.handler.request.CustomException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final AuthorityService authorityService;
    private final RoleRepository roleRepository;
    private  final UserRepository userRepository;
    private final CompanyRepository companyRepository;
    private final JwtService jwtService;
    @Override
    public List<Role> getAll(){
        List<String> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
        if (authorities.contains("VIEW_ROLES"))
            return roleRepository.findAll();
        else return null;
    }

    @Override
    public Role save(Role role, boolean isSeed) {
        /*Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<String> authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();
        if (!isSeed && authentication != null) {


            if (!authorities.contains("CREATE_ROLE")) {
                throw new CustomException("Insufficient authorities", HttpStatus.UNAUTHORIZED);
            }
        }*/

        if (findDefaultRole().isPresent() && role.isDefault()) {
            throw new CustomException("There is already a default role", HttpStatus.UNAUTHORIZED);
        }
        //if (authorities.contains("CREATE_ROLE"))
            return roleRepository.save(role);
        //else return null;

    }
    @Override
    public Optional<Role> findDefaultRole(){
        return roleRepository.findByIsDefaultTrue();
    }
    @Override
    public Optional<Role> getById(Long id){
        return roleRepository.findById(id);
    }

    @Override
    public Optional<Role> getByName(String name){
        return roleRepository.findByName(name);
    }

    @Override
    public void delete(Long id){
        List<String> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
        if (authorities.contains("DELETE_ROLE"))getById(id).ifPresent(roleRepository::delete);
    }


    @Override
    public AuthenticationResponse updateUserRole(Long userId, UpdateUserRoleRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Role role = roleRepository.findById(request.getRoleId())
                .orElseThrow(() -> new ResourceNotFoundException("Role not found"));

        Company company = companyRepository.findById(request.getCompanyId())
                .orElseThrow(() -> new ResourceNotFoundException("Company not found"));

        user.setRole(role);
        user.setCompany(company);

        userRepository.save(user);

        //String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                //.token(jwtToken)
                .firstName(user.getFirstName())
                .familyName(user.getFamilyName())
                .email(user.getEmail())
                .role(user.getRole())
                .company(user.getCompany())
                .build();
    }












//    @Override
//    public Role grantAuthorities(Long authorityId, Long roleId){
//        List<String> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
//        if (authorities.contains("GRANT_AUTHORITY_TO_ROLE")){
//            Role role = roleRepository.findById(roleId).orElse(null);
//            Authority authority = authorityService.getById(authorityId).orElse(null);
//            if (role != null && authority != null){
//                List<Authority> currentAuthorities = role.getAuthorities();
//                if (currentAuthorities.stream().anyMatch(a -> a.getId().equals(authorityId))) return null;
//                currentAuthorities.add(authority);
//                role.setAuthorities(currentAuthorities);
//                return roleRepository.save(role);
//            }
//            return null;
//        }
//        return null;
//    }


//    @Override
//    public Role revokeAuthorities(Long authorityId, Long roleId){
//        List<String> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
//        if (authorities.contains("REVOKE_AUTHORITY_FROM_ROLE")){
//            Role role = roleRepository.findById(roleId).orElse(null);
//            Authority authority = authorityService.getById(authorityId).orElse(null);
//            if (role != null && authority != null){
//                List<Authority> currentAuthorities = role.getAuthorities();
//                currentAuthorities = currentAuthorities.stream().filter(a -> !a.getId().equals(authorityId)).collect(Collectors.toList());
//                role.setAuthorities(currentAuthorities);
//                return roleRepository.save(role);
//            }
//            return null;
//        }
//        return null;
//    }

//    @Override
//    public Role update(Role role, Long id){
//        List<String> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
//        if (authorities.contains("UPDATE_ROLE")){
//            Role existingRole = getById(id).orElse(null);
//            if (existingRole != null){
//                existingRole.setName(role.getName());
//                existingRole.setAuthorities(role.getAuthorities());
//                if (role.isDefault() && findDefaultRole().isPresent()) throw new CustomException("There is already a default role", HttpStatus.UNAUTHORIZED);
//                existingRole.setDefault(role.isDefault());
//                return roleRepository.save(existingRole);
//            }
//            return null;
//        }return null;
//    }



}
