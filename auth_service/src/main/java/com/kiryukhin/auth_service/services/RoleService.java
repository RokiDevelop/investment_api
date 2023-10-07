package com.kiryukhin.auth_service.services;

import com.kiryukhin.auth_service.repositories.RoleRepo;
import com.kiryukhin.auth_service.securityEntities.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepo roleRepo;

    public Role findByName(String roleUser) {
        return roleRepo.findByName(roleUser).get();
    }
}
