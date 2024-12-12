package com.disha.models;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.query.sqm.FetchClauseType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

@Entity
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
     private Integer id;
     private String name;
     
     @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
     @JoinTable(name = "student_course", 
     joinColumns = @JoinColumn(name="student_id"), 
     inverseJoinColumns = @JoinColumn(name="course_id"))
     
     private Set<Course> courses = new HashSet<>();  
     
     public Student(String name) {
    	 this.name = name;
     }
     
     public Student() {
    	
     }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Student [id : " + id + ", name : " + name + "courses : \n"+ getCourse() + "]";
	}
     
	public Set<Course> getCourse(){
		return courses;
	}
	public void addCourse(Course cs) {
		this.courses.add(cs);
		cs.addStudent(this); //back reference
	}
	public void removeCourse(Course cs) {
		this.courses.remove(cs);
		cs.removeStudent(this); //back reference
	}
     
}
