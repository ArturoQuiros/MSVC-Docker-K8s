package dev.arturoquiros.springcloud.msvc.cursos.controllers;

import dev.arturoquiros.springcloud.msvc.cursos.models.Usuario;
import dev.arturoquiros.springcloud.msvc.cursos.models.entity.Curso;
import dev.arturoquiros.springcloud.msvc.cursos.services.CursoService;
import feign.FeignException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController

public class CursoController {

    @Autowired
    private CursoService service;

    @GetMapping("/")
    public List<Curso> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detalle(@PathVariable Long id) {
        Optional<Curso> cursoOptional = service.porId(id);
        if (cursoOptional.isPresent()) {
            return ResponseEntity.ok(cursoOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@RequestBody Curso usuario, @PathVariable Long id) {
        Optional<Curso> o = service.porId(id);
        if (o.isPresent()) {
            Curso usuarioDb = o.get();
            usuarioDb.setNombre(usuario.getNombre());
            return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(usuarioDb));
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/")
    public ResponseEntity<?> crear(@Valid @RequestBody Curso curso , BindingResult result) {
        if (result.hasErrors()){
            return validar(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(curso));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@Valid @PathVariable Long id , BindingResult result) {

        if (result.hasErrors()){
            return validar(result);
        }

        Optional<Curso> o = service.porId(id);
        if (o.isPresent()) {
            service.eliminar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/asignar-usuario/{cursoId}")
    public ResponseEntity<?> asignarUsuario (@RequestBody Usuario usuario, @PathVariable Long cursoId){
        Optional<Usuario> o ;

        try {
            o = service.asignarUsuario(usuario, cursoId);
        }catch (FeignException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("Mensaje", "No existe el usuario por ID o error en la comunicación: " + e.getMessage()));
        }


        if (o.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(o.get());
        }
        return ResponseEntity.notFound().build();
    }


    @PostMapping("/crear-usuario/{cursoId}")
    public ResponseEntity<?> crearUsuario (@RequestBody Usuario usuario, @PathVariable Long cursoId){
        Optional<Usuario> o ;

        try {
            o = service.crearUsuario(usuario, cursoId);
        }catch (FeignException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("Mensaje", "No se pudo crear el usuario o error en la comunicación: " + e.getMessage()));
        }


        if (o.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(o.get());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/eliminar-usuario/{cursoId}")
    public ResponseEntity<?> eliminarUsuario (@RequestBody Usuario usuario, @PathVariable Long cursoId){
        Optional<Usuario> o ;

        try {
            o = service.eliminarUsuario(usuario, cursoId);
        }catch (FeignException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("Mensaje", "No se pudo borrar el usuario o error en la comunicación: " + e.getMessage()));
        }


        if (o.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(o.get());
        }
        return ResponseEntity.notFound().build();
    }


    private static ResponseEntity<Map<String, String>> validar(BindingResult result) {
        Map<String, String> errores = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errores.put(err.getField(), "El campo" + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errores);
    }

}
