package com.example.demo.entities;

import com.example.demo.enums.Mention;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Loader;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "students")
@Data
@AllArgsConstructor
@NoArgsConstructor
//
@JsonIdentityInfo(scope = Student.class,generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
@SQLDelete(sql =  "update Student " +
        "SET deleted = true " +
        "where id = ?")
@Loader(namedQuery = "findStudentById")
@NamedQuery(name = "findStudentById", query =
        "select u " +
                "from Student u " +
                "where " +
                "    u.id = ?1 and " +
                "    u.deleted = false")
@Where(clause = "deleted = false")
public class Student extends BaseEntity{


    private String name;

    private Double age;

    @Enumerated(EnumType.STRING)
    private Mention mention;


}
