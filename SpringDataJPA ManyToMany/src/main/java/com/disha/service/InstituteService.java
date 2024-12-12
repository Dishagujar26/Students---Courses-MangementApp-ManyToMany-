package com.disha.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.disha.models.Course;
import com.disha.models.Student;
import com.disha.repository.CourseRepository;
import com.disha.repository.StudentRepository;

import jakarta.transaction.Transactional;

@Service
public class InstituteService {
    private StudentRepository sRepo;
    private CourseRepository cRepo;
    
    public InstituteService(StudentRepository sRepo, CourseRepository cRepo) {
    	this.cRepo=cRepo;
    	this.sRepo=sRepo;
    }
    
    // add student
    public void addStudent(String name){
    	Student stu = new Student(name);
    	sRepo.save(stu);
    	System.out.println("Student Added !");
		
	}
    
    // add course
    public void addCourse(String title) {
    	Course course = new Course(title);
    	cRepo.save(course);
    	System.out.println("Course added !");
    }
    
    // enroll student in the course
    public void addStudentToCourse(Integer stuId , Integer courseId ) {
    	Student student = sRepo.findById(stuId).orElse(null);
    	Course course = cRepo.findById(courseId).orElse(null);
    	
    	if(!student.getCourse().contains(course)){
    		student.getCourse().add(course); //will maintain both side consistency
    		sRepo.save(student); // Save student and cascade changes to the course 
    		System.out.println("Student added to the course !");
    	}
    	else {
			System.out.println("Student already enrolled in the course !");
		}	
    }
    
    // Retrieve all students 
    public void listOfStudent() {
    	 List<Student> students = sRepo.findAll();
    	    if (students.isEmpty()) {
    	        System.out.println("No students found.");
    	    } else {
    	        for (Student s : students) {
    	            System.out.println("Name of Student: " + s.getName());
    	            if(s.getCourse().isEmpty()) {
    	            	System.out.println("Courses : No Course Enrolled !");
    	            }
    	            else {
						System.out.println("Courses : ");
						for(Course course : s.getCourse()) {
							System.out.println(" - " + course.getTitle());
						}
					}
    	            System.out.println("-------------------------------------");
    	        }
    	    }
    	    
    }
    
 // Retrieve all Courses 
    public void listOfCourses() {
    	 List<Course> courseList = cRepo.findAll();
    	    if (courseList.isEmpty()) {
    	        System.out.println("No courses found.");
    	    } else {
    	        for (Course course : courseList) {
    	            System.out.println("Name of the course : " + course.getTitle());
    	            if(course.getStudents().isEmpty()) {
    	            	System.out.println("Students : No Student Enrolled in the course !");
    	            }
    	            else {
						System.out.println("Stduents Enrolled  : ");
						for(Student student : course.getStudents()) {
							System.out.println(" - " + student.getName());
						}
					}
    	            System.out.println("-------------------------------------");
    	        }
    	    }
    	    
    }
    
    @Transactional
    public void deleteCourse(Integer courseId) {
        Course course = cRepo.findById(courseId).orElse(null);
        if (course != null) {
            // Remove the course from all associated students
            for (Student student : course.getStudents()) {
                student.getCourse().remove(course);
            }
            course.getStudents().clear(); // Clear the students list for the course
            cRepo.delete(course);         // Delete the course
            System.out.println("Course deleted!");
        } else {
            System.out.println("Course not found!");
        }
    }

    @Transactional
    public void deleteStudent(Integer stuId) {
        Student student = sRepo.findById(stuId).orElse(null);
        if (student != null) {
            // Remove the student from all associated courses
            for (Course course : student.getCourse()) {
                course.getStudents().remove(student);
            }
            student.getCourse().clear(); // Clear the courses list for the student
            sRepo.delete(student);       // Delete the student
            System.out.println("Student deleted!");
        } else {
            System.out.println("Student not found!");
        }
    }

    
   
    
}
