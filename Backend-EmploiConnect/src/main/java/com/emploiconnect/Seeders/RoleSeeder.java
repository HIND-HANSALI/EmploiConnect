package com.emploiconnect.Seeders;

import com.emploiconnect.entity.Authority;
import com.emploiconnect.entity.Role;
import com.emploiconnect.enums.AuthorityEnum;
import com.emploiconnect.repository.AuthorityRepository;
import com.emploiconnect.repository.RoleRepository;
import com.emploiconnect.service.AuthorityService;
import com.emploiconnect.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@RequiredArgsConstructor
@Component
public class RoleSeeder implements CommandLineRunner {
    private final RoleService roleService;
    private final AuthorityService authorityService;
    private final RoleRepository roleRepository;
    private final AuthorityRepository authorityRepository;

    @Override
    public void run(String... args) {
        if (roleRepository.count() == 0) {
            seedRoles();
        }
    }

    private void seedRoles() {
        // create authorities
        Authority viewRoles = authorityService.getByName(AuthorityEnum.VIEW_ROLES)
                .orElseThrow(() -> new RuntimeException("VIEW_ROLES authority not found"));
        Authority createRole = authorityService.getByName(AuthorityEnum.CREATE_ROLE)
                .orElseThrow(() -> new RuntimeException("CREATE_ROLE authority not found"));
        Authority viewUsers = authorityService.getByName(AuthorityEnum.VIEW_USERS)
                .orElseThrow(() -> new RuntimeException("VIEW_USERS authority not found"));
        /*Authority grantAuthorityToRole = authorityService.getByName(AuthorityEnum.GRANT_AUTHORITY_TO_ROLE)
                .orElseThrow(() -> new RuntimeException("GRANT_AUTHORITY_TO_ROLE authority not found"));
        Authority revokeAuthorityFromRole = authorityService.getByName(AuthorityEnum.REVOKE_AUTHORITY_FROM_ROLE)
                .orElseThrow(() -> new RuntimeException("REVOKE_AUTHORITY_FROM_ROLE authority not found"));*/
        Authority deleteRole = authorityService.getByName(AuthorityEnum.DELETE_ROLE)
                .orElseThrow(() -> new RuntimeException("DELETE_ROLE authority not found"));

        // Create roles and associate authorities
        Role candidateRole = Role.builder()
                .name("CANDIDATE")
                .isDefault(true)
                .build();

        Role recruiterRole = Role.builder()
                .name("RECRUITER")
                .authorities(Arrays.asList(viewRoles, viewUsers))
                .build();

        Role superAdminRole = Role.builder()
                .name("SUPER_ADMIN")
                .authorities(authorityRepository.findAll())
                .build();

        roleService.save(candidateRole, true);
        roleService.save(recruiterRole, true);
        roleService.save(superAdminRole, true);
    }
}
