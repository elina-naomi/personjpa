package telran.ashkelon2020.person.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import telran.ashkelon2020.person.dto.BirthDateDto;
import telran.ashkelon2020.person.dto.CityPopulationDto;
import telran.ashkelon2020.person.dto.PersonDto;
import telran.ashkelon2020.person.dto.PersonUpdateDto;
import telran.ashkelon2020.person.service.PersonService;

@RestController
public class PersonController {
	
	@Autowired
	PersonService personService;
	
	@Transactional
	@PostMapping("/person")
	public boolean addPerson(@RequestBody PersonDto personDto) {
		return personService.addPerson(personDto);
	}
	
	@GetMapping("/person/{id}")
	public PersonDto findPersonById(@PathVariable Integer id) {
		return personService.findPersonById(id);
	}
	
	@Transactional
	@PutMapping("/person/{id}")
	public PersonDto editPerson(@PathVariable Integer id, @RequestBody PersonUpdateDto personUpdateDto) {
		return personService.editPerson(id, personUpdateDto);
	}
	
	@Transactional
	@DeleteMapping("/person/{id}")
	public PersonDto removePerson(@PathVariable Integer id) {
		return personService.removePerson(id);
	}
	
	@Transactional
	@GetMapping("/persons/name/{name}")
	public Iterable<PersonDto> findByName(@PathVariable String name) {
		return personService.findByName(name);	
	}
	
	@Transactional(readOnly = true)
	@PostMapping("/persons/period")
	public Iterable<PersonDto> findByAge(@RequestBody BirthDateDto birthDateDto) {
		return personService.findByAge(birthDateDto);
	}
	
	@Transactional(readOnly = true)
	@GetMapping("/persons/city/{city}")
	public Iterable<PersonDto> findByCity(@PathVariable String city) {
		return personService.findByCity(city);
	}
	
	@Transactional(readOnly = true)
	@GetMapping("/population/city")
	public Iterable<CityPopulationDto> getCityPopulatin() {
		return personService.getCityPopulation();
	}
	
	@Transactional(readOnly = true)
	@GetMapping("/persons/children")
	public Iterable<PersonDto> getChildren() {
		return personService.getChildren();
	}
	
	@Transactional(readOnly = true)
	@GetMapping("/persons/employees/salary/from/{min}/to/{max}")
	public Iterable<PersonDto> findEmployeeBySalary(@PathVariable Integer min, @PathVariable Integer max) {
		return personService.findEmployeeBySalary(min, max);
	}
	

}
