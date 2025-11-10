package com.aws.awsproject.Repository;

import com.aws.awsproject.Entity.Student;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class StudentRepositoryInMemory implements StudentRepository {

    private final Map<Integer, Student> database = new ConcurrentHashMap<>();

    @Override
    public List<Student> findAll() {
        return new ArrayList<>(database.values());
    }

    @Override
    public Optional<Student> findById(Integer id) {
        return Optional.ofNullable(database.get(id));
    }

    @Override
    public Student save(Student student) {
        database.put(student.getId(), student);
        return student;
    }

    @Override
    public Student update(Integer id, Student student) {
        student.setId(id);
        database.put(id, student);
        return student;
    }

    @Override
    public boolean deleteById(Integer id) {
        return database.remove(id) != null;
    }
}