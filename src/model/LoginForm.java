package model;

import java.io.Serializable;
import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the users database table.
 * 
 */
public class LoginForm implements Serializable {	
	public String email;

	public String password;
	
	public LoginForm() {
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

}