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
@Table(name="users")
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
	private static final long serialVersionUID = 1L;

	@Id
	private String email;

	@Column(name="is_admin")
	private byte[] isAdmin;

	private String name;

	private String password;

	private String surname;

	//bi-directional many-to-one association to Home
	@OneToMany(mappedBy="user")
	private List<Home> homes;

	//bi-directional many-to-one association to Message
	@OneToMany(mappedBy="user1")
	private List<Message> messages1;

	//bi-directional many-to-one association to Message
	@OneToMany(mappedBy="user2")
	private List<Message> messages2;

	//bi-directional many-to-one association to Transaction
	@OneToMany(mappedBy="user")
	private List<Transaction> transactions;

	public User() {
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public byte[] getIsAdmin() {
		return this.isAdmin;
	}

	public void setIsAdmin(byte[] isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSurname() {
		return this.surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public List<Home> getHomes() {
		return this.homes;
	}

	public void setHomes(List<Home> homes) {
		this.homes = homes;
	}

	public Home addHome(Home home) {
		getHomes().add(home);
		home.setUser(this);

		return home;
	}

	public Home removeHome(Home home) {
		getHomes().remove(home);
		home.setUser(null);

		return home;
	}

	public List<Message> getMessages1() {
		return this.messages1;
	}

	public void setMessages1(List<Message> messages1) {
		this.messages1 = messages1;
	}

	public Message addMessages1(Message messages1) {
		getMessages1().add(messages1);
		messages1.setUser1(this);

		return messages1;
	}

	public Message removeMessages1(Message messages1) {
		getMessages1().remove(messages1);
		messages1.setUser1(null);

		return messages1;
	}

	public List<Message> getMessages2() {
		return this.messages2;
	}

	public void setMessages2(List<Message> messages2) {
		this.messages2 = messages2;
	}

	public Message addMessages2(Message messages2) {
		getMessages2().add(messages2);
		messages2.setUser2(this);

		return messages2;
	}

	public Message removeMessages2(Message messages2) {
		getMessages2().remove(messages2);
		messages2.setUser2(null);

		return messages2;
	}

	public List<Transaction> getTransactions() {
		return this.transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	public Transaction addTransaction(Transaction transaction) {
		getTransactions().add(transaction);
		transaction.setUser(this);

		return transaction;
	}

	public Transaction removeTransaction(Transaction transaction) {
		getTransactions().remove(transaction);
		transaction.setUser(null);

		return transaction;
	}
	
	public static User create(UserTransaction ut, EntityManager em, String email, String name, String surname, String password) throws ServletException {
		boolean userExists = User.findByEmail(ut, em, email) != null;
		
		User user = null;
		if (!userExists) {
			user = new User();
			user.setEmail(email);
			user.setName(name);
			user.setSurname(surname);
			user.setPassword(password);
			user.setIsAdmin(new byte[1]);
			
			try {
				ut.begin();
				em.persist(user);
				ut.commit();
			} catch (SecurityException | IllegalStateException | RollbackException | HeuristicMixedException
					| HeuristicRollbackException | NotSupportedException | SystemException e) {
				return null;
			}
		}else {				
			return null;
		}
		
		return user;
	}
	
	private static List<User> _findBy(UserTransaction ut,EntityManager em, String namedQuery, String... parameters) throws ServletException {
		List<User> users = null;
		if (parameters.length % 2 != 0 ){
			throw new ServletException(
					"User._findBy received wrong number of parameters. "
					+ "Every parameter shall contain a value [paramName, "
					+ "paramValue, paramName2, paramValue2....,paramNameN, "
					+ "paramValueN]"
			);
		}
		try { 
			ut.begin();
			TypedQuery<User> tq = em.createNamedQuery(namedQuery, User.class);
			for (int i = 0; i < parameters.length; i+=2) {
				tq.setParameter(parameters[i], parameters[i+1]);
			}
			users =	tq.getResultList(); 
			ut.commit();
		} catch (SecurityException | IllegalStateException | RollbackException | HeuristicMixedException
				| HeuristicRollbackException | SystemException | NotSupportedException e) {
			return null;
		}
		return users;
	}
	
	public static User findByEmail(UserTransaction ut, EntityManager em, String email) throws ServletException{
		List<User> users = User._findBy(ut, em, "User.findByEmail", "email", email);
		if (users.isEmpty())
			return null;
		return users.get(0);
	}

	public static User findByEmailAndPass(UserTransaction ut, EntityManager em, String email, String password) throws ServletException{
		List<User> users = User._findBy(ut, em, "User.findByEmailAndPass", "email", email, "password", password);
		if (users.isEmpty())
			return null;
		return users.get(0);  
	}
	
	public static boolean updateByEmail(UserTransaction ut, EntityManager em, String email, 
			String name, String surname, String password) {
		try {
			ut.begin();
			em.createNamedQuery("User.updateByEmail")
				.setParameter("email", email)
				.setParameter("name", name)
				.setParameter("surname", surname)
				.setParameter("password", password)
				.executeUpdate();
			ut.commit();
		} catch (SecurityException | IllegalStateException | RollbackException | HeuristicMixedException
				| HeuristicRollbackException | SystemException | NotSupportedException e) {
			return false;
		}
		return true;
	}
}