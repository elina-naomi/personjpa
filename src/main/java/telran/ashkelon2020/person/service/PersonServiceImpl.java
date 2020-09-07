package telran.ashkelon2020.person.service;

import java.time.LocalDate;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import telran.ashkelon2020.person.dao.PersonRepository;
import telran.ashkelon2020.person.dto.BirthDateDto;
import telran.ashkelon2020.person.dto.PersonDto;
import telran.ashkelon2020.person.dto.PersonUpdateDto;
import telran.ashkelon2020.person.dto.exceptions.PersonExistsException;
import telran.ashkelon2020.person.dto.exceptions.PersonNotFoundException;
import telran.ashkelon2020.person.model.Person;

@Service
public class PersonServiceImpl implements PersonService {
	@Autowired
	PersonRepository personRepository;
	@Autowired
	ModelMapper modelMapper;

	@Override
	public boolean addPerson(PersonDto personDto) {
		if (personRepository.existsById(personDto.getId())) {
			throw new PersonExistsException(personDto.getId());
		}
		Person person = modelMapper.map(personDto, Person.class);
		return personRepository.save(person) != null;
	}

	@Override
	public PersonDto findPersonById(Integer id) {
		Person person = personRepository.findById(id)
				.orElseThrow(() -> new PersonNotFoundException(id));
		return modelMapper.map(person, PersonDto.class);
	}

	@Override
	public PersonDto editPerson(Integer id, PersonUpdateDto personUpdateDto) {
		Person person = personRepository.findById(id)
				.orElseThrow(() -> new PersonNotFoundException(id));
		if(personUpdateDto.getName()!=null) {
			person.setName(personUpdateDto.getName());
		}
		personRepository.save(person);
		return modelMapper.map(person, PersonDto.class);
	}

	@Override
	public PersonDto removePerson(Integer id) {
		Person person = personRepository.findById(id)
				.orElseThrow(() -> new PersonNotFoundException(id));
		personRepository.deleteById(id);
		return modelMapper.map(person, PersonDto.class);
	}

	@Override
	public Iterable<PersonDto> findByName(String name) {
		return personRepository.findByName(name)
				.map(n -> modelMapper.map(n, PersonDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public Iterable<PersonDto> findByAge(BirthDateDto birthDateDto) {
		return personRepository.findByBirthDateBetween(LocalDate.now().minusYears(birthDateDto.getTo()), LocalDate.now().minusYears(birthDateDto.getFrom()))
				.map(n -> modelMapper.map(n, PersonDto.class))
				.collect(Collectors.toList());
	}





}
