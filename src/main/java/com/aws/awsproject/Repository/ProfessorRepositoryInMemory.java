package com.aws.awsproject.Repository;

import com.aws.awsproject.Entity.Professor;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class ProfessorRepositoryInMemory implements ProfessorRepository {

    private final Map<Integer, Professor> database = new ConcurrentHashMap<>();

    @Override
    public List<Professor> findAll() {
        return new ArrayList<>(database.values());
    }

    @Override
    public Optional<Professor> findById(Integer id) {
        return Optional.ofNullable(database.get(id));
    }

    @Override
    public Professor save(Professor professor) {
        database.put(professor.getId(), professor);
        return professor;
    }

    @Override
    public Professor update(Integer id, Professor professor) {
        professor.setId(id);
        database.put(id, professor);
        return professor;
    }

    @Override
    public boolean deleteById(Integer id) {
        return database.remove(id) != null;
    }
}
