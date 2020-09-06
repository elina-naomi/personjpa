package telran.ashkelon2020.person.controller;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import telran.ashkelon2020.person.dto.BirthDateDto;
import telran.ashkelon2020.person.dto.PersonDto;
import telran.ashkelon2020.person.dto.PersonUpdateDto;
import telran.ashkelon2020.person.service.PersonService;

@RestController
public class PersonController {
	
	@Autowired
	PersonService personService;
	
	@PostMapping("/person")
	public boolean addPerson(@RequestBody PersonDto personDto) {
		return personService.addPerson(personDto);
	}
	@GetMapping("/person/{id}")
	public PersonDto findPersonById(@PathVariable Integer id) {
		return personService.findPersonById(id);
	}
	
	@PutMapping("/person/{id}")
	public PersonDto editPerson(@PathVariable Integer id, @RequestBody PersonUpdateDto personUpdateDto) {
		return personService.editPerson(id, personUpdateDto);
	}
	
	@DeleteMapping("/person/{id}")
	public PersonDto removePerson(@PathVariable Integer id) {
		return personService.removePerson(id);
	}
	
	@Transactional
	@GetMapping("/persons/name/{name}")
	public Iterable<PersonDto> findByName(@PathVariable String name) {
		return personService.findByName(name);	
	}
	
	@Transactional
	@PostMapping("/persons/period")
	public Iterable<PersonDto> findByBithDates(@RequestBody BirthDateDto birthDateDto) {
		return personService.findByBirthDates(birthDateDto);
	}

}
