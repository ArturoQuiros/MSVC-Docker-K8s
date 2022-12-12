package dev.arturoquiros.springcloud.msvc.cursos.services;


import dev.arturoquiros.springcloud.msvc.cursos.models.entity.Curso;
import dev.arturoquiros.springcloud.msvc.cursos.repositories.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CursoServiceImpl implements  CursoService{

    @Autowired
    private CursoRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Curso> listar(){
        return  (List<Curso>) repository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Curso> porId(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public Curso guardar(Curso curso) {
        return repository.save(curso);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        repository.deleteById(id);
    }

}
