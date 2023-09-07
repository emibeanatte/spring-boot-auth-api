package dev.beanatte.crud.Security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import dev.beanatte.crud.Security.entity.Usuario;



public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    
    Optional<Usuario> findByNombreUsuario(String nombreUsuario);

    boolean existsByNombreUsuario(String nombreUsuario);

    boolean existsByEmail(String email);

}
