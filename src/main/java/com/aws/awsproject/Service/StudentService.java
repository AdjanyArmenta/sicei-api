package com.aws.awsproject.Service;

import com.aws.awsproject.dto.StudentDTO;
import com.aws.awsproject.Entity.Student;
import com.aws.awsproject.exception.ResourceNotFoundException;
import com.aws.awsproject.Repository.StudentRepository;
import com.aws.awsproject.utils.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(Integer id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));
    }

    public Student createStudent(StudentDTO studentDTO) {
        Student student = new Student();
        student.setId(studentDTO.getId());
        student.setNombres(StringUtils.cleanAndCapitalize(studentDTO.getNombres()));
        student.setApellidos(StringUtils.cleanAndCapitalize(studentDTO.getApellidos()));
        student.setMatricula(StringUtils.cleanAndUpperCase(studentDTO.getMatricula()));
        student.setPromedio(studentDTO.getPromedio());
        return studentRepository.save(student);
    }

    public Student updateStudent(Integer id, StudentDTO studentDTO) {
        if (!studentRepository.findById(id).isPresent()) {
            throw new ResourceNotFoundException("Student not found with id: " + id);
        }
        Student student = new Student();
        student.setNombres(StringUtils.cleanAndCapitalize(studentDTO.getNombres()));
        student.setApellidos(StringUtils.cleanAndCapitalize(studentDTO.getApellidos()));
        student.setMatricula(StringUtils.cleanAndUpperCase(studentDTO.getMatricula()));
        student.setPromedio(studentDTO.getPromedio());
        return studentRepository.update(id, student);
    }

    public void deleteStudent(Integer id) {
        if (!studentRepository.deleteById(id)) {
            throw new ResourceNotFoundException("Student not found with id: " + id);
        }
    }
}
