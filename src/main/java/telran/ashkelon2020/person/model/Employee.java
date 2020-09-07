package telran.ashkelon2020.person.model;

import java.time.LocalDate;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@AllArgsConstructor
@Entity
public class Employee extends Person {
	String company;
	Integer salary;
	public Employee(Integer id, String name, LocalDate birthDate, Address address, String company, Integer salary) {
		super(id, name, birthDate, address);
		this.company = company;
		this.salary = salary;
	}
	
	

}
