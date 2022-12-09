package dev.arturoquiros.springcloud.msvc.cursos.repositories;

import dev.arturoquiros.springcloud.msvc.cursos.models.entity.Curso;
import org.springframework.data.repository.CrudRepository;

public interface CursoRepository extends CrudRepository<Curso, Long > {
}
