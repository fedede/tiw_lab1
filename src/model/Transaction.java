package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the transactions database table.
 * 
 */
@Entity
@Table(name="transaction")
@NamedQuery(name="Transaction.findAll", query="SELECT t FROM Transaction t")
public class Transaction implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;

	@Temporal(TemporalType.DATE)
	@Column(name="card_date", nullable = false)
	private Date cardDate;

	@Column(name="card_num", columnDefinition = "VARCHAR(16)", nullable = false)
	private String cardNum;

	@Column(name="cv2", nullable = false)
	private int cv2;

	@Temporal(TemporalType.DATE)
	@Column(name="end_date", nullable = false)
	private Date endDate;

	@Temporal(TemporalType.DATE)
	@Column(name="start_date", nullable = false)
	private Date startDate;

	@Column(name="status", nullable = false)
	private String status;

	//bi-directional many-to-one association to Home
	@ManyToOne
	@JoinColumn(name="house_id", nullable = false)
	private House house;

	//bi-directional many-to-one association to User
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="invoiced_id", nullable = false)
	private User invoiced;

	public Transaction() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCardDate() {
		return cardDate;
	}

	public void setCardDate(Date cardDate) {
		this.cardDate = cardDate;
	}

	public int getCardNum() {
		return cardNum;
	}

	public void setCardNum(int cardNum) {
		this.cardNum = cardNum;
	}

	public int getCv2() {
		return cv2;
	}

	public void setCv2(int cv2) {
		this.cv2 = cv2;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public House getHouse() {
		return house;
	}

	public void setHouse(House house) {
		this.house = house;
	}

	public User getInvoiced() {
		return invoiced;
	}

	public void setInvoiced(User invoiced) {
		this.invoiced = invoiced;
	}
	
	
}