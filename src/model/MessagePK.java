package model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the messages database table.
 * 
 */
@Embeddable
public class MessagePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private String sender;

	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date time;

	@Column(insertable=false, updatable=false)
	private String receiver;

	public MessagePK() {
	}
	public String getSender() {
		return this.sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public java.util.Date getTime() {
		return this.time;
	}
	public void setTime(java.util.Date time) {
		this.time = time;
	}
	public String getReceiver() {
		return this.receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof MessagePK)) {
			return false;
		}
		MessagePK castOther = (MessagePK)other;
		return 
			this.sender.equals(castOther.sender)
			&& this.time.equals(castOther.time)
			&& this.receiver.equals(castOther.receiver);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.sender.hashCode();
		hash = hash * prime + this.time.hashCode();
		hash = hash * prime + this.receiver.hashCode();
		
		return hash;
	}
}