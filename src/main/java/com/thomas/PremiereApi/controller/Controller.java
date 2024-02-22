package com.thomas.PremiereApi.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.thomas.PremiereApi.model.Student;
import com.thomas.PremiereApi.service.ServiceStudent;

@RestController
@RequestMapping(path="api/rest",name="app_student")
public class Controller {
	private ServiceStudent serviceStudent;

	
	@Autowired //pour injecter l'instanciation de la classe Student Service - 
	//pas besoin de creer une instance de service. Spring  l'injectera via le constructeur 
	public Controller(ServiceStudent serviceStudent) {
		this.serviceStudent = serviceStudent;
	}
	
	@PostMapping(path="/student",name="create")
	@ResponseStatus(HttpStatus.CREATED)
	public Student add (@RequestBody Student student) {//pour extraire les données de la requête http entrante
		return this.serviceStudent.saveStudent(student);
	}

	
	@GetMapping(path="/student",name="list")
	@ResponseStatus(HttpStatus.OK)
	public List<Student> list(){
		return this.serviceStudent.getAllStudent();
	}
	
	
	@GetMapping(path="/student/{id}", name="read")
	@ResponseStatus(HttpStatus.OK)
	public Optional<Student> read (@RequestBody Student student,@PathVariable Long id) {//PathVariable idem RequestBody
		return this.serviceStudent.getOneStudent(id);
	}
	
	@PutMapping(path="/student/{id}",name="update")
	@ResponseStatus(HttpStatus.OK)
	public Student update(@RequestBody Student student,@PathVariable Long id){
		return this.serviceStudent.updateStudent(student, id);
	}
	
	
	@DeleteMapping(path="/student/{id}",name="delete")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remove (@PathVariable Long id){
		this.serviceStudent.removeStudent(id);
	}

}
