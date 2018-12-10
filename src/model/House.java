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
@Table(name="house")
public class House implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;

	@Column(name="name", nullable=false)
	private String name;
	
	@Column(name="city", nullable=false)
	private String city;
	
	@Temporal(TemporalType.DATE)
	@Column(name="start_date", nullable=false)
	private Date startDate;

	@Temporal(TemporalType.DATE)
	@Column(name="end_date", nullable=false)
	private Date endDate;

	@Column(name="full_description", nullable=false)
	private String fullDescription;
	
	@Column(name="short_description", nullable=false)
	private String shortDescription;

	@Column(name="max_guests", nullable=false)
	private int maxGuests;

	@Column(name="image_url", nullable=false)
	private String imageUrl;

	@Column(name="shared", nullable=false)
	private boolean shared;

	@Column(name="price", nullable=false)
	private float price;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="owner_id", nullable=false)
	private User owner;

	//bi-directional many-to-one association to Transaction
	@OneToMany(mappedBy="house")
	private List<Transaction> transactions;

	public House() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getFullDescription() {
		return fullDescription;
	}

	public void setFullDescription(String fullDescription) {
		this.fullDescription = fullDescription;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public int getMaxGuests() {
		return maxGuests;
	}

	public void setMaxGuests(int maxGuests) {
		this.maxGuests = maxGuests;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public boolean isPrivate() {
		return shared;
	}

	public void setPrivate(boolean shared) {
		this.shared = shared;
	}
	
	public void setShared(boolean shared) {
		this.shared = shared;
	}
	
	public boolean getShared(){
		return this.shared;
	}
	
	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}