package model;

import java.io.Serializable;
import javax.persistence.*;
import javax.servlet.ServletException;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import java.util.List;


/**
 * The persistent class for the users database table.
 * 
 */
@Entity
@Table(name="user")
@NamedQueries({
	@NamedQuery(name="User.findAll", 
			query="SELECT u FROM User u"),
	@NamedQuery(name="User.findByEmail", 
			query="SELECT u FROM User u WHERE u.email = :email"),
	@NamedQuery(name="User.findByEmailAndPass",
			query="SELECT u FROM User u WHERE u.email = :email AND u.password = :password"),
	@NamedQuery(name="User.updateByEmail",
			query="UPDATE User u SET u.name = :name, u.surname = :surname, u.password = :password WHERE u.email = :email"),
})
public class User implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="email", unique = true, nullable = false)
	private String email;

	@Column(name="name", nullable = false)
	private String name;
	
	@Column(name="is_admin", nullable = false)
	private boolean admin;

	@Column(name="password", nullable = false)
	private String password;

	@Column(name="surname", nullable = false)
	private String surname;

	//bi-directional many-to-one association to Home
	@OneToMany(mappedBy="owner")
	private List<House> homes;

	//bi-directional many-to-one association to Transaction
	@OneToMany(mappedBy="invoiced")
	private List<Transaction> transactions;

	@OneToMany(mappedBy="receiver")
	private List<Message> messages;
	
	public User() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public List<House> getHomes() {
		return homes;
	}

	public void setHomes(List<House> homes) {
		this.homes = homes;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}
	
}