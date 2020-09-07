package telran.ashkelon2020.person.service;

import telran.ashkelon2020.person.dto.BirthDateDto;
import telran.ashkelon2020.person.dto.CityPopulationDto;
import telran.ashkelon2020.person.dto.PersonDto;
import telran.ashkelon2020.person.dto.PersonUpdateDto;

public interface PersonService {
	
	boolean addPerson(PersonDto personDto);
	
	PersonDto findPersonById(Integer id);
	
	PersonDto editPerson(Integer id, PersonUpdateDto personUpdateDto);
	
	PersonDto removePerson(Integer id);
	
	Iterable<PersonDto> findByName(String name);
	
	Iterable<PersonDto> findByAge(BirthDateDto birthDateDto);
	
	Iterable<PersonDto> findByCity(String city);
	
	Iterable<CityPopulationDto> getCityPopulation();
	
	Iterable<PersonDto> findEmployeeBySalary(int min, int max);
	
	Iterable<PersonDto> getChildren();

}
