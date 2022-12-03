package dev.arturoquiros.springcloud.msvc.usuarios.controllers;

import dev.arturoquiros.springcloud.msvc.usuarios.Services.UsuarioService;
import dev.arturoquiros.springcloud.msvc.usuarios.models.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @GetMapping
    public List<Usuario> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detalle(@PathVariable Long id){
        Optional<Usuario> usuarioOptional = service.porId(id);
        if ( usuarioOptional.isPresent() ){
            return  ResponseEntity.ok(usuarioOptional.get());
        }
        return  ResponseEntity.notFound().build();
    }

}
