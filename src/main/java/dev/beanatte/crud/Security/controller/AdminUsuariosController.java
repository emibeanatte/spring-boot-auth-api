package dev.beanatte.crud.Security.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


import dev.beanatte.crud.Security.entity.Usuario;
import dev.beanatte.crud.Security.jwt.JwtProvider;
import dev.beanatte.crud.Security.service.RolService;
import dev.beanatte.crud.Security.service.UsuarioService;
import dev.beanatte.crud.dto.Mensaje;

@Controller
@RequestMapping("/admin/usuarios")
public class AdminUsuariosController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    RolService rolService;

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Mensaje> delete(@PathVariable("id") int id){
        if(!usuarioService.existsById(id))
            return new ResponseEntity<Mensaje>(new Mensaje("El usuario a eliminar no existe."), HttpStatus.NOT_FOUND);
        
        Usuario usuario = usuarioService.getOne(id).get();

        if (usuario.getRoles().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            return new ResponseEntity<Mensaje>(new Mensaje("No tiene permisos para eliminar otros Usuarios Admin."),HttpStatus.UNAUTHORIZED);
        }
        usuarioService.delete(id);
        return new ResponseEntity<Mensaje>(new Mensaje("Usuario eliminado con exito."),HttpStatus.OK);  
    }
    
}
