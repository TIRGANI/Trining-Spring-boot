package com.example.demo.controllers;

import com.example.demo.dtos.StudentDto;
import com.example.demo.exceptions.MessageException;
import com.example.demo.services.StudentServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(value = "*", exposedHeaders = "")
@RestController
@RequestMapping("api/v1/student")

public class StudentController {
    private final StudentServices studentServices;

    @Autowired
    public StudentController(StudentServices studentServices) {
        this.studentServices = studentServices;
    }

    @Operation(summary = "This is the list of all students that we have in the database")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "This is the list of all students that we have in the database", content = {@Content(mediaType = "application/json")}), @ApiResponse(responseCode = "404", description = " Page Not Found", content = @Content)})
    @GetMapping
    public List<StudentDto> getStudentList(){
        List<StudentDto> students =studentServices.getAll();
        return students;
    }

    @Operation(summary = "This is to get the details of particular student in the database")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = " students details fetched from database", content = {@Content(mediaType = "application/json")}), @ApiResponse(responseCode = "404", description = " Page Not Found", content = @Content)})
    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> getById(@PathVariable(name = "id") Long id) {
        StudentDto studentDto = studentServices.findByIdT(id).orElseThrow(() -> new MessageException("Student not found with id: " + id));
        return ResponseEntity.ok().body(studentDto);
    }

    @Operation(summary = "This is to update  the User in the database")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = " User details updated in database", content = {@Content(mediaType = "application/json")}), @ApiResponse(responseCode = "404", description = " Page Not Found", content = @Content)})
    @PutMapping("/{id}")
    public ResponseEntity<StudentDto> update(@PathVariable long id, @RequestBody StudentDto studentdto) {
        StudentDto studentUpdated = studentServices.update(id, studentdto);
        return ResponseEntity.ok().body(studentUpdated);

    }

    @Operation(summary = "This is to add  the users in the database")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = " user details saved in database", content = {@Content(mediaType = "application/json")}), @ApiResponse(responseCode = "404", description = " Page Not Found", content = @Content)})
    @PostMapping
    public ResponseEntity<StudentDto> create(@RequestBody StudentDto studentReq) {
        StudentDto user = studentServices.save(studentReq);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
}
