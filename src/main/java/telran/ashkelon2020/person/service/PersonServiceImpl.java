package telran.ashkelon2020.person.service;

import java.time.LocalDate;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import telran.ashkelon2020.person.dao.PersonRepository;
import telran.ashkelon2020.person.dto.BirthDateDto;
import telran.ashkelon2020.person.dto.CityPopulationDto;
import telran.ashkelon2020.person.dto.PersonDto;
import telran.ashkelon2020.person.dto.PersonUpdateDto;
import telran.ashkelon2020.person.dto.exceptions.PersonExistsException;
import telran.ashkelon2020.person.dto.exceptions.PersonNotFoundException;
import telran.ashkelon2020.person.dto.exceptions.PersonTypeException;
import telran.ashkelon2020.person.model.Person;

@Service
public class PersonServiceImpl implements PersonService {
	@Autowired
	PersonRepository personRepository;
	@Autowired
	ModelMapper modelMapper;

	final static String modelPath = "telran.ashkelon2020.person.model.";
	final static String dtoPath = "telran.ashkelon2020.person.dto.";

	@Override
	public boolean addPerson(PersonDto personDto) {
		if (personRepository.existsById(personDto.getId())) {
			throw new PersonExistsException(personDto.getId());
		}
		Person person = convertDtoToModel(personDto);
		return personRepository.save(person) != null;
	}

	@Override
	public PersonDto findPersonById(Integer id) {
		Person person = personRepository.findById(id).orElseThrow(() -> new PersonNotFoundException(id));
		return convertModeltoDto(person);
	}

	@Override
	public PersonDto editPerson(Integer id, PersonUpdateDto personUpdateDto) {
		Person person = personRepository.findById(id).orElseThrow(() -> new PersonNotFoundException(id));
		if (personUpdateDto.getName() != null) {
			person.setName(personUpdateDto.getName());
		}
		personRepository.save(person);
		return convertModeltoDto(person);
	}

	@Override
	public PersonDto removePerson(Integer id) {
		Person person = personRepository.findById(id).orElseThrow(() -> new PersonNotFoundException(id));
		personRepository.deleteById(id);
		return convertModeltoDto(person);
	}

	@Override
	public Iterable<PersonDto> findByName(String name) {
		return personRepository.findByName(name)
				.map(n -> convertModeltoDto(n)).collect(Collectors.toList());
	}

	@Override
	public Iterable<PersonDto> findByAge(BirthDateDto birthDateDto) {
		return personRepository
				.findByBirthDateBetween(LocalDate.now().minusYears(birthDateDto.getTo()),
						LocalDate.now().minusYears(birthDateDto.getFrom()))
				.map(n -> convertModeltoDto(n)).collect(Collectors.toList());
	}

	@Override
	public Iterable<PersonDto> findByCity(String city) {
		return personRepository.findByAddressCity(city)
				.map(n -> convertModeltoDto(n)).collect(Collectors.toList());
	}

	@Override
	public Iterable<CityPopulationDto> getCityPopulation() {
		return personRepository.getCityPopulation();
	}

	@Override
	public Iterable<PersonDto> findEmployeeBySalary(int min, int max) {
		return personRepository.findEmployeeBySalary(min, max)
				.map(n -> convertModeltoDto(n)).collect(Collectors.toList());
	}

	@Override
	public Iterable<PersonDto> getChildren() {
		return personRepository.getChild()
				.map(n -> convertModeltoDto(n)).collect(Collectors.toList());
	}

	private Person convertDtoToModel(PersonDto personDto) {
		String modelClass = personDto.getClass().getSimpleName().replaceAll("Dto", "");
		try {
			Person person = (Person) modelMapper.map(personDto, Class.forName(modelPath + modelClass));
			return person;
		} catch (ClassNotFoundException e) {
			throw new PersonTypeException();
		}
	}
	
	private PersonDto convertModeltoDto(Person person) {
		
		try {
			return (PersonDto) modelMapper.map(person,
					Class.forName(dtoPath + person.getClass().getSimpleName() + "Dto"));
		} catch (ClassNotFoundException e) {
			throw new PersonTypeException();
		}
	}

}
