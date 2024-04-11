package com.wiley.schoolJPA.dao;

import com.wiley.schoolJPA.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepo extends JpaRepository<Course, Integer> {
}
