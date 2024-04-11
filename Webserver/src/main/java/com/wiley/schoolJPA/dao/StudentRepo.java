package com.wiley.schoolJPA.dao;

import com.wiley.schoolJPA.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepo extends JpaRepository<Student, Integer> {
}
