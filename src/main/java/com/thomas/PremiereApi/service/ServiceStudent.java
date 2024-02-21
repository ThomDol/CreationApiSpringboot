package com.thomas.PremiereApi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.thomas.PremiereApi.Exception.StudentNotFoundException;
import com.thomas.PremiereApi.model.Student;
import com.thomas.PremiereApi.repository.StudentRepository;


public class ServiceStudent  extends Student{
	private StudentRepository studentRepository;
	
	
	@Autowired
	public ServiceStudent(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}
	
	
	public Student saveStudent (Student student) {
	return this.studentRepository.save(student);
	}
	
	public List<Student> getAllStudent (){
		return this.studentRepository.findAll();
	}
	
	public Optional<Student> getOneStudent(long id){
		Optional<Student>student=this.studentRepository.findById(id);
		if(!student.isPresent()) {
			throw new StudentNotFoundException (String.format("Student with id %s not found" , id));
		}
		return student;
	}
	
	public Student updateStudent(Student student,long id){
		Optional<Student> studentExist = this.studentRepository.findById(id);
		if(!studentExist.isPresent()) {
			throw new StudentNotFoundException (String.format("Student with id %s not found", id));
		}
		return this.studentRepository.save(student);
	}
	
	public void removeStudent (long id) {
		Optional<Student>student= this.studentRepository.findById(id);
		if(!student.isPresent()) {
			throw new StudentNotFoundException(String.format("Student with id %s not found", id));
		}
		this.studentRepository.delete(student.get());
	}

	
}
