package telran.ashkelon2020.person.dto.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class PersonNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	
	public PersonNotFoundException(Integer id) {
		super("User with id " + id + " not found");
	}

}
