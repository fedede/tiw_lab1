package model;

import java.io.Serializable;
import java.text.SimpleDateFormat;

import javax.jms.ConnectionFactory;
import javax.persistence.*;
import javax.servlet.ServletException;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the homes database table.
 * 
 */
@Entity
@Table(name="homes")
@NamedQueries({
	@NamedQuery(name="Home.findAll", 
			query="SELECT h FROM Home h"),
	@NamedQuery(name="Home.findById",
			query="SELECT h FROM Home h WHERE h.id = :id"),
})
public class Home implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue
	private int id;

	private String city;

	@Temporal(TemporalType.DATE)
	@Column(name="end_date")
	private Date endDate;

	@Lob
	@Column(name="full_desc")
	private String fullDesc;

	@Column(name="guest_num")
	private int guestNum;

	private String img;

	@Temporal(TemporalType.DATE)
	@Column(name="init_date")
	private Date initDate;

	@Column(name="is_private")
	private byte[] isPrivate;

	private String name;

	private int price;

	@Lob
	@Column(name="short_desc")
	private String shortDesc;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="owner")
	private User user;

	//bi-directional many-to-one association to Transaction
	@OneToMany(mappedBy="home")
	private List<Transaction> transactions;

	public Home() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getFullDesc() {
		return this.fullDesc;
	}

	public void setFullDesc(String fullDesc) {
		this.fullDesc = fullDesc;
	}

	public int getGuestNum() {
		return this.guestNum;
	}

	public void setGuestNum(int guestNum) {
		this.guestNum = guestNum;
	}

	public String getImg() {
		return this.img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public Date getInitDate() {
		return this.initDate;
	}

	public void setInitDate(Date initDate) {
		this.initDate = initDate;
	}

	public byte[] getIsPrivate() {
		return this.isPrivate;
	}

	public void setIsPrivate(byte[] isPrivate) {
		this.isPrivate = isPrivate;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return this.price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getShortDesc() {
		return this.shortDesc;
	}

	public void setShortDesc(String shortDesc) {
		this.shortDesc = shortDesc;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Transaction> getTransactions() {
		return this.transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	public Transaction addTransaction(Transaction transaction) {
		getTransactions().add(transaction);
		transaction.setHome(this);

		return transaction;
	}

	public Transaction removeTransaction(Transaction transaction) {
		getTransactions().remove(transaction);
		transaction.setHome(null);

		return transaction;
	}
	
	public static Home create(EntityManager em, UserTransaction ut,String name, User owner, String city, String fullDesc, 
			String shortDesc,int guestNum, boolean isPrivate, String imageUrl, int price, 
			Date initDate, Date endDate) {
		Home home = new Home();
		home.setName(name);
		home.setCity(city.toUpperCase());
		home.setFullDesc(fullDesc);
		home.setShortDesc(shortDesc);
		home.setGuestNum(guestNum);
		//home.setTransactions(new ArrayList<Transaction>());
		if(isPrivate){
			home.setIsPrivate(new byte[]{0});
		}else{
			home.setIsPrivate(new byte[]{1});
		}
		home.setImg(imageUrl);
		home.setPrice(price);
		home.setInitDate(initDate);
		home.setEndDate(endDate);
		home.setUser(owner);

		try {
			ut.begin();
			em.persist(home);
			ut.commit();
		} catch (SecurityException | IllegalStateException | RollbackException | HeuristicMixedException| HeuristicRollbackException | SystemException | NotSupportedException e) {
			return null;
		}
		return home;
	}

	private static List<Home> _findBy(UserTransaction ut,EntityManager em, String namedQuery, Object... parameters) throws ServletException {
		List<Home> homes = null;
		if (parameters.length % 2 != 0 ){
			throw new ServletException(
					"Home._findBy received wrong number of parameters. "
					+ "Every parameter shall contain a value [paramName, "
					+ "paramValue, paramName2, paramValue2....,paramNameN, "
					+ "paramValueN]"
			);
		}
		try { 
			ut.begin();
			TypedQuery<Home> tq = em.createNamedQuery(namedQuery, Home.class);
			for (int i = 0; i < parameters.length; i+=2) {
				tq.setParameter((String)parameters[i], parameters[i+1]);
			}
			homes =	tq.getResultList(); 
			ut.commit();
		} catch (SecurityException | IllegalStateException | RollbackException | HeuristicMixedException
				| HeuristicRollbackException | SystemException | NotSupportedException e) {
			return null;
		}
		return homes;
	}
	
	public static Home findById(UserTransaction ut, EntityManager em, int id) throws ServletException{
		List<Home> homes = Home._findBy(ut, em, "Home.findById", "id", id);
		if (homes.isEmpty())
			return null;
		return homes.get(0);
	}
}