package dev.arturoquiros.springcloud.msvc.cursos.clients;

import dev.arturoquiros.springcloud.msvc.cursos.models.Usuario;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name="msvc-usuarios", url="msvc-usuarios:8001")
public interface UsuarioClientRest {

    @GetMapping("/{id}")
    Usuario detalle(@PathVariable Long id);

    @PostMapping("/")
    Usuario crear (@RequestBody Usuario usuario);

    @GetMapping("/usuarios-por-curso")
    List<Usuario> obteneterAlumnosPorCurso(@RequestParam Iterable<Long> ids);


}
