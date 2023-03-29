package com.example.demo;

import com.example.demo.entities.Student;
import com.example.demo.enums.Mention;
import com.example.demo.repositories.StudentRepository;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
//documentaion for swagger
@OpenAPIDefinition(info = @Info(title = "tirgani API", description = "Tirgani Application for learning  Information"))
public class DemoApplication implements CommandLineRunner {
    @Autowired
    private StudentRepository studentRepository;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Override
    public void run(String... args) throws Exception {
         studentRepository.save(new Student("tirgani1",10.1, Mention.BIEN));
        studentRepository.save(new Student("tirgani2",11.1,Mention.TERRIEN));
        studentRepository.save(new Student("tirgani3",12.1,Mention.PASSABLE));
        studentRepository.save(new Student("test",13.1,Mention.REDOUBLANT));
        studentRepository.save(new Student("tirgani5",14.1,Mention.TERRIEN));

        System.out.println(":::::::::::::::::::::::::::");
        Student student =studentRepository.findById(1L).get();
        System.out.println(student.getName());
        System.out.println(":::::::::::::::::::::::::::");
       // List<Patient> patients =patientRepository.findByNomContains("0");
       // patients.stream().map(patient1 -> String.format("%s (%d)", patient1.getNom(),patient1.getScore())).forEach(System.out::println);
       // System.out.println("::::::::::pages:::::::::::::::::");
       // Page<Student> patientP =studentRepository.findByNameContains("t", PageRequest.of(1,2));
       // System.out.println("nbr page  : "+patientP.getTotalPages());
       // patientP.getContent().stream().map(patient1 -> String.format("name  is %s ", patient1.getName())).forEach(System.out::println);

    }
}
