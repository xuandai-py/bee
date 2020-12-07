package com.beebrick.repository;

import com.beebrick.entity.Authority.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
        Role findByname(String name);

}
