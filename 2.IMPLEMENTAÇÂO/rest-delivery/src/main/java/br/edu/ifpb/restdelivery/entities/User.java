package br.edu.ifpb.restdelivery.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import br.edu.ifpb.restdelivery.enumerations.Grupo;

/**
 * Classe que representa um usuário para acesso do sistema, podendo ser um cliente, operador, entregador ou administrador.
 * 
 * @author rafaelfeitosa - <a href="https://github.com/JoseRafael97 ></a>
 *
 */

@NamedQueries({ @NamedQuery(name = "user.findByName", query = "SELECT u FROM User u WHERE u.login = :login"),
		@NamedQuery(name = "user.findByEmail", query = "SELECT u FROM User u WHERE u.email = :email") })
@Entity
@Table(name = "tb_user", uniqueConstraints = {
		@UniqueConstraint(name = "uc_tb_user", columnNames = { "login", "email" }) })
public class User extends SuperId {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String login;
	private String password;
	private String email;
	private Grupo grupo;
	
	private Person person;

	public User() {
	}

	
	@OneToOne(mappedBy = "user")
	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	@NotNull
	@Email
	@Column(unique = true)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@NotBlank
	@Column(unique = true)
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	@NotNull
	@Enumerated(EnumType.STRING)
	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo group) {
		this.grupo = group;
	}

	@NotEmpty
	@Column
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((grupo == null) ? 0 : grupo.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((person == null) ? 0 : person.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		User other = (User) obj;
		if (email == null) {
			if (other.email != null) {
				return false;
			}
		} else if (!email.equals(other.email)) {
			return false;
		}
		if (grupo != other.grupo) {
			return false;
		}
		if (login == null) {
			if (other.login != null) {
				return false;
			}
		} else if (!login.equals(other.login)) {
			return false;
		}
		if (password == null) {
			if (other.password != null) {
				return false;
			}
		} else if (!password.equals(other.password)) {
			return false;
		}
		if (person == null) {
			if (other.person != null) {
				return false;
			}
		} else if (!person.equals(other.person)) {
			return false;
		}
		return true;
	}


	@Override
	public String toString() {
		return "User [login=" + login + ", password=" + password + ", email=" + email + ", grupo=" + grupo + ", person="
				+ person + "]";
	}
	
	
}
