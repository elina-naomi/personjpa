package telran.ashkelon2020.person.service;

import telran.ashkelon2020.person.dto.BirthDateDto;
import telran.ashkelon2020.person.dto.PersonDto;
import telran.ashkelon2020.person.dto.PersonUpdateDto;

public interface PersonService {
	
	boolean addPerson(PersonDto personDto);
	
	PersonDto findPersonById(Integer id);
	
	PersonDto editPerson(Integer id, PersonUpdateDto personUpdateDto);
	
	PersonDto removePerson(Integer id);
	
	Iterable<PersonDto> findByName(String name);
	
	Iterable<PersonDto> findByBirthDates(BirthDateDto birthDateDto);

}
