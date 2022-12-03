package dev.arturoquiros.springcloud.msvc.usuarios.controllers;

import dev.arturoquiros.springcloud.msvc.usuarios.Services.UsuarioService;
import dev.arturoquiros.springcloud.msvc.usuarios.models.entity.Usuario;
import feign.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> crear (@RequestBody Usuario usuario){
         return  ResponseEntity.status(HttpStatus.CREATED).body(service.guardar((usuario)));
    }

    @PutMapping("/{id}")
    public  ResponseEntity<?> editar (@RequestBody Usuario usuario, @PathVariable Long id){
        Optional<Usuario> o = service.porId(id);

        if (o.isPresent()){
            Usuario usuarioDB = o.get();
            usuarioDB.setNombre(usuario.getNombre());
            usuarioDB.setEmail(usuario.getEmail());
            usuarioDB.setPassword(usuario.getPassword());
            return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar((usuarioDB)));
        }
        return  ResponseEntity.notFound().build();

    }



}
