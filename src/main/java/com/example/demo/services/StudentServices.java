package com.example.demo.services;

import com.example.demo.dtos.StudentDto;
import com.example.demo.entities.Student;
import com.example.demo.exceptions.MessageException;
import com.example.demo.repositories.StudentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.beans.FeatureDescriptor;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class StudentServices implements BaseService<StudentDto>{

    private final StudentRepository studentRepository;

    private ModelMapper modelMapper;
    @Autowired
    public StudentServices(StudentRepository studentRepository, ModelMapper modelMapper){
        this.studentRepository= studentRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public StudentDto save(StudentDto obj) {
        Student studentsaved = studentRepository.save(modelMapper.map(obj, Student.class));
        return modelMapper.map(studentsaved , StudentDto.class);
    }

    @Override
    public StudentDto update(long id, StudentDto studentReq) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new MessageException("No user registered with id " + id));
        if (studentReq != null) {
            BeanUtils.copyProperties(studentReq, student, Stream.of(new BeanWrapperImpl(studentReq).getPropertyDescriptors()).map(FeatureDescriptor::getName).filter(propertyName -> new BeanWrapperImpl(studentReq).getPropertyValue(propertyName) == null).toArray(String[]::new));

        }
        Student u1 = studentRepository.save(student);
        StudentDto studentSavedDto = modelMapper.map(u1, StudentDto.class);

        return studentSavedDto;
    }

    @Override
    public void delete(Long id) {
        studentRepository.delete(id);
    }

    @Override
    public Optional<StudentDto> findByIdT(Long id) {
        Optional<Student> sc = studentRepository.findById(id);
        Optional<StudentDto> scs = sc.map(user -> modelMapper.map(user, StudentDto.class));
        return Optional.ofNullable(scs.orElse(null));
    }

    @Override
    public List<StudentDto> getAll() {
        List<Student> students = studentRepository.findAll(); // Assuming UserRepository is an interface extending JpaRepository<User, Long>
        List<StudentDto> studentDtos = new ArrayList<>();
        for(Student s:students){
            StudentDto userDto1 = modelMapper.map(s,StudentDto.class);
            studentDtos.add(userDto1);
        }
        return studentDtos;
    }
}
