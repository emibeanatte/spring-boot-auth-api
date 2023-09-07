package dev.beanatte.crud.Security.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.beanatte.crud.Security.entity.Usuario;
import dev.beanatte.crud.Security.jwt.JwtProvider;
import dev.beanatte.crud.Security.service.UsuarioService;

@RestController
@RequestMapping("/admin")
public class AdminController {
    
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    UsuarioService usuarioService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/listar-usuarios")
    public ResponseEntity<?> getAllUsuarios(){

        List<Usuario> usuarios = usuarioService.findAll();
        
        return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);
    }

}
