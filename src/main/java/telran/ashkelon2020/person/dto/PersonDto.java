package telran.ashkelon2020.person.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import telran.ashkelon2020.person.model.Address;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
					include = JsonTypeInfo.As.PROPERTY,
					property = "type", visible = true)
@JsonSubTypes({
	@Type(value = ChildDto.class, name = "Child"),
	@Type(value = EmployeeDto.class, name = "Employee"),
	@Type(value = PersonDto.class, name = "Person")
})
public class PersonDto {
	
	Integer id;
	String name;
	LocalDate birthDate;
	Address address;
	
}
