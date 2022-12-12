package dev.arturoquiros.springcloud.msvc.cursos.controllers;

import dev.arturoquiros.springcloud.msvc.cursos.models.entity.Curso;
import dev.arturoquiros.springcloud.msvc.cursos.services.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<?> crear(@RequestBody Curso curso) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(curso));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        Optional<Curso> o = service.porId(id);
        if (o.isPresent()) {
            service.eliminar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
