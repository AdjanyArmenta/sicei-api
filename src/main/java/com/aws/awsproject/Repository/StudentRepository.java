package com.aws.awsproject.Repository;

import com.aws.awsproject.Entity.Student;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StudentRepository {
    List<Student> findAll();
    Optional<Student> findById(Integer id);
    Student save(Student student);
    Student update(Integer id, Student student);
    boolean deleteById(Integer id);

}
