package com.player.music.repository;

import com.player.music.model.Role;
import com.player.music.model.RoleName;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(RoleName role);

}