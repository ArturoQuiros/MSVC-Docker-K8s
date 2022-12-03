package dev.arturoquiros.springcloud.msvc.usuarios.Services;

import dev.arturoquiros.springcloud.msvc.usuarios.models.entity.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    List<Usuario> listar();
    Optional<Usuario> porId(Long id);
    Usuario guardar(Usuario usuario);
    void  eliminar (Long id);

}