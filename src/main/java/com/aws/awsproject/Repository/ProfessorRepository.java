package com.aws.awsproject.Repository;

import com.aws.awsproject.Entity.Professor;
import java.util.List;
import java.util.Optional;

public interface ProfessorRepository {
    List<Professor> findAll();
    Optional<Professor> findById(Integer id);
    Professor save(Professor professor);
    Professor update(Integer id, Professor professor);
    boolean deleteById(Integer id);
}