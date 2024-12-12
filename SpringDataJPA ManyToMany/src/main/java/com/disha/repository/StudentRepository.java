package com.disha.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.disha.models.Student;

import jakarta.transaction.Transactional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
	@Modifying
	@Transactional
	@Query(value="DELETE FROM student_course sc WHERE sc.course_id =?1",nativeQuery = true)
	void deleteCourseStudents(Integer courseId);
}
