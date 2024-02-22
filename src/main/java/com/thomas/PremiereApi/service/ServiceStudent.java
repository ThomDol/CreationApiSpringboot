package com.thomas.PremiereApi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thomas.PremiereApi.Exception.StudentNotFoundException;
import com.thomas.PremiereApi.model.Student;
import com.thomas.PremiereApi.repository.StudentRepository;


@Service //indique à Spring que cette classe aura des composants metiers
public class ServiceStudent {
	private StudentRepository studentRepository;
	
	
	@Autowired //Va injecter la classe StudentRepository et creer une instance de celle ci
	public ServiceStudent(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}
	
	
	public Student saveStudent (Student student) {
	return this.studentRepository.save(student);
	}
	
	public List<Student> getAllStudent (){
		return this.studentRepository.findAll();
	}
	
	public Optional<Student> getOneStudent(long id){ //Optional qd renvoi qqchose qui peut ne pas exister - oblige à gerer l'exception
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
		Student existingStudent = studentExist.get();
	    
	    existingStudent.setNom(student.getNom());
	    existingStudent.setPrenom(student.getPrenom());
	    existingStudent.setEmail(student.getEmail());
	    
	    return this.studentRepository.save(existingStudent);
	}
	
	public void removeStudent (long id) {
		Optional<Student>student= this.studentRepository.findById(id);
		if(!student.isPresent()) {
			throw new StudentNotFoundException(String.format("Student with id %s not found", id));
		}
		this.studentRepository.delete(student.get());
	}

	
}
