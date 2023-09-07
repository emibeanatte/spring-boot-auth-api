package dev.beanatte.crud.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.beanatte.crud.Entity.Producto;


public interface ProductRepository extends JpaRepository<Producto, Integer> {

    Optional<Producto> findByNombre(String nombre);

    boolean existsByNombre(String nombre);

}
