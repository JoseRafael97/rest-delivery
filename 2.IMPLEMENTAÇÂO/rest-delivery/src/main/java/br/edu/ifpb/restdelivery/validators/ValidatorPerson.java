package br.edu.ifpb.restdelivery.validators;

import br.edu.ifpb.restdelivery.entities.Person;
import br.edu.ifpb.restdelivery.entities.User;
import br.edu.ifpb.restdelivery.exceptions.ConstraintViolateException;
import br.edu.ifpb.restdelivery.services.impl.PersonService;
import br.edu.ifpb.restdelivery.services.impl.UserService;

/**
 * Classe que contém alguns validadores para entidade Person
 * 
 * @author rafaelfeitosa - <a href="https://github.com/JoseRafael97 ></a>
 *
 */
public class ValidatorPerson {

	/**
	 * Método que verifica se o cpf da pessoa passado no parâmetro é único.
	 * @param p
	 * @param personService
	 * @throws ConstraintViolateException
	 */
	public static void validateUniqueCpf(Person p, PersonService personService) throws ConstraintViolateException {
		Person person = personService.findByCpf(p.getCpf());
		
		if (person != null && p.getId().equals(person.getId())) {
			return;
		
		}else if(person != null){
			throw new ConstraintViolateException("CPF já cadastrado");
		}
	}

	/**
	 * Método que verifica se O loginn da pessoa passada como parâmetro é único.
	 * @param p
	 * @param service
	 * @throws ConstraintViolateException
	 */
	public static void validateUniqueLoginUser(Person p, UserService service) throws ConstraintViolateException {
		User u = service.findByName(p.getUser().getLogin());

		if (u != null && p.getUser().getId() != null && p.getUser().getId().equals(u.getId())) {
			return;
		
		} else if (u != null) {
			throw new ConstraintViolateException("Login já cadastrado");
		}

	}
	

	/**
	 * Método que verifica se e-mail da pessoa passada no parâmetro é único.
	 * @param p
	 * @param service
	 * @throws ConstraintViolateException
	 */
	public static void validateUniqueEmailUser(Person p, UserService service) throws ConstraintViolateException {
		User u = service.findByEmail(p.getUser().getEmail());

		if (u != null && p.getUser().getId() != null && p.getUser().getId().equals(u.getId())) {
			return;
		
		} else if (u != null) {
			throw new ConstraintViolateException("Email já cadastrado");
		}
	}
	
	/**
	 * Método que agrega validadores para pessoa, verificando a unicidade do cpf, e-mail e login
	 * @param p
	 * @param userService
	 * @param personService
	 * @throws ConstraintViolateException
	 */
	public static void validatePerson(Person p, UserService userService, PersonService personService) throws ConstraintViolateException{
		validateUniqueCpf(p, personService);
		validateUniqueEmailUser(p, userService);
		validateUniqueLoginUser(p, userService);
	}

}
