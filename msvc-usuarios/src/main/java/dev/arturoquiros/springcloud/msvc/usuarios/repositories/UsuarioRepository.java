package dev.arturoquiros.springcloud.msvc.usuarios.repositories;

import dev.arturoquiros.springcloud.msvc.usuarios.models.entity.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepository extends CrudRepository<Usuario,Long> {



}
