package com.example.demo.repositories;

import com.example.demo.entities.Student;
import javax.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository< Student, Long> {
    public Page<Student> findByNameContains(String name, Pageable pageable);
    @Modifying
    @Transactional
    @Query(value = "UPDATE students u " +
            "SET u.deleted = true, u.deleted_at = CURRENT_TIMESTAMP, u.is_active = false, u.updated_at = CURRENT_TIMESTAMP, " +
            "WHERE u.id = :id", nativeQuery = true)
    void delete(@Param("id") Long id);
}
