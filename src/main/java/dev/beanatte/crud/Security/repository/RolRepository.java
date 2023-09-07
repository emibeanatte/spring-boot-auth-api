package dev.beanatte.crud.Security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import dev.beanatte.crud.Security.entity.Rol;
import dev.beanatte.crud.Security.enums.RolNombre;

public interface RolRepository extends JpaRepository<Rol, Integer> {

    Optional<Rol> findByRolNombre(RolNombre rolNombre);
    
}
