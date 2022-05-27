package Q_A_Online.model;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;


import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class User {

	private static final int MIN_USERNAME_LENGTH = 3;
	private static final int MIN_PASSWORD_LENGTH = 8;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Length(min = MIN_USERNAME_LENGTH, message = "Username must be at least " + MIN_USERNAME_LENGTH
			+ " characters long")
	@NotEmpty(message = "Please enter username")
	@Column(name = "username", nullable = false, unique = true)
	private String username;

	@JsonIgnore
	@Length(min = MIN_PASSWORD_LENGTH, message = "Password must be at least " + MIN_PASSWORD_LENGTH
			+ " characters long")
	@NotEmpty(message = "Please enter the password")
	@Column(name = "password", nullable = false)
	private String password;
	
	@NotEmpty(message = "Please enter the fullname")
	private String fullname;
	
	@NotEmpty(message = "Please enter the email")
	private String email;
	
	@NotEmpty(message = "Please enter the phone")
	private String phone;
	@OneToMany(mappedBy = "user")
	private Collection<Question> questions;
	@OneToMany(mappedBy = "user")
	private Collection<Answer> answer;
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return username + "-" + id + "-" + password;
	}
}
