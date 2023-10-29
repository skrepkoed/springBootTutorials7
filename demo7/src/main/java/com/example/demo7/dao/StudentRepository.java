package com.example.demo7.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo7.entity.Student;
public interface StudentRepository extends JpaRepository<Student,Long> {
}
