package com.disha.models;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String title;
	
	@ManyToMany(mappedBy = "courses", fetch = FetchType.EAGER)
	private Set<Student> students = new HashSet<>();
	
	public Course(String title){
		this.title = title;
	}
	public Course() {
		
	}

	public Set<Student> getStudents() {
		return students;
	}
	public void addStudent(Student stu) {
		this.students.add(stu);
		 stu.getCourse().add(this); /// Maintain consistency
	}
	 public void removeStudent(Student student) {
	        this.students.remove(student);
	        student.getCourse().remove(this); // Maintain consistency
	    }
	@Override
	public String toString() {
		return "id=" + id + ", title=" + title ;
	}
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	
	

}
