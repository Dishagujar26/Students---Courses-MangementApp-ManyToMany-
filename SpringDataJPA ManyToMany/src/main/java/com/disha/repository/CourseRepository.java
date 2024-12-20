package com.disha.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.disha.models.Course;

@Repository
public interface CourseRepository  extends JpaRepository<Course, Integer>{

}
