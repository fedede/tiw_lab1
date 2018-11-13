package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the homes database table.
 * 
 */
@Entity
@Table(name="homes")
@NamedQuery(name="Home.findAll", query="SELECT h FROM Home h")


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
	/*
	public  getResultHomes(){
		this.
	}
	*/

}