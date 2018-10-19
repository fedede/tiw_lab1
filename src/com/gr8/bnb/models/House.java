package com.gr8.bnb.models;

import java.util.ArrayList;
import java.util.Date;

public class House {
	
	/* DATABASE EMULATION FIELDS */
	private static ArrayList<House> houses = new ArrayList<House>();
	private static int next_id = 0;
	
	/* DATABASE ROW FIELDS */
	private int id;
	private String name;
	private String fullDesc;
	private String shortDesc;
	private String city;
	private boolean isPrivate;
	private int guestNum;
	private int price;
	private Date initDate;
	private Date endDate;
	private String owner;
	private String photoName;

	
	/**
	 * @param id: Database id of the house.
	 * @param name: Name of the house.
	 * @param fullDesc: Full description of the house.
	 * @param shortDesc: Short description of the house.
	 * @param city: City where the house is.
	 * @param isPrivate: True if the house is private, else False.
	 * @param guestNum: Number of guests available in the house.
	 * @param price: Price per night of the house.
	 * @param initDate: First day that the house is available.
	 * @param endDate: Last day that the house is available.
	 * @param owner: Email of the user owning the house.
	 * @param photoName: Name of the image file for this house.
	 */
	public House(String name, String fullDesc, String shortDesc, String city, boolean isPrivate,
			int guestNum, int price, Date initDate, Date endDate, String owner, String photoName) {
		this.id = next_id++;
		this.name = name;
		this.fullDesc = fullDesc;
		this.shortDesc = shortDesc;
		this.city = city;
		this.isPrivate = isPrivate;
		this.guestNum = guestNum;
		this.price = price;
		this.initDate = initDate;
		this.endDate = endDate;
		this.owner = owner;
		this.photoName = photoName;
	}


	public static ArrayList<House> getHouses() {
		return houses;
	}


	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getFullDesc() {
		return fullDesc;
	}


	public void setFullDesc(String fullDesc) {
		this.fullDesc = fullDesc;
	}


	public String getShortDesc() {
		return shortDesc;
	}


	public void setShortDesc(String shortDesc) {
		this.shortDesc = shortDesc;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public boolean isisPrivate() {
		return isPrivate;
	}


	public void setisPrivate(boolean isPrivate) {
		this.isPrivate = isPrivate;
	}


	public int getGuestNum() {
		return guestNum;
	}


	public void setGuestNum(int guestNum) {
		this.guestNum = guestNum;
	}


	public int getPrice() {
		return price;
	}


	public void setPrice(int price) {
		this.price = price;
	}


	public Date getInitDate() {
		return initDate;
	}


	public void setInitDate(Date initDate) {
		this.initDate = initDate;
	}


	public Date getEndDate() {
		return endDate;
	}


	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}


	public String getOwner() {
		return owner;
	}


	public void setOwner(String owner) {
		this.owner = owner;
	}


	public String getPhotoName() {
		return photoName;
	}


	public void setPhotoName(String photoName) {
		this.photoName = photoName;
	}
	
	
	/* OBJECT FUNCTIONS */

	/**
	 * Check if a house exists on the database and insert it if not.
	 * @return: true if the user was inserted, false otherwise.
	 */
	public boolean save() {
		if (find(this.id) == null)	{
			houses.add(this);
			return true;
		}
		return false;
	}

	/**
	 * Check if a house exists on the database and update it.
	 * @return: true if the user was updated, false otherwise.
	 */
	public boolean update() {
		House prevHouse = find(this.id);
		if (prevHouse == null) {
			return false;
		}
		houses.remove(prevHouse); // remove previous user
		houses.add(this);        // add updated user
		return true;
	}

	/**
	 * Check if a house exists on the database and delete it if so.
	 * @return: true if the user was deleted, false otherwise.
	 */
	public boolean delete() {
		return houses.remove(this);
	}

	/* DATABASE EMULATION FUCTIONS */

	/**
	 * Create a new user and insert it in the database.
	 *
	 * @param id: Database id of the house.
	 * @param name: Name of the house.
	 * @param fullDesc: Full description of the house.
	 * @param shortDesc: Short description of the house.
	 * @param city: City where the house is.
	 * @param isPrivate: True if the house is private, else False.
	 * @param guestNum: Number of guests available in the house.
	 * @param price: Price per night of the house.
	 * @param initDate: First day that the house is available.
	 * @param endDate: Last day that the house is available.
	 * @param owner: Email of the user owning the house.
	 * @param photoName: Name of the image file for this house.
	 *
	 * @return: the created user if inserted in the database successfully,
	 */
	public static House create(String name, String fullDesc, 
			String shortDesc, String city, 
			boolean isPrivate, int guestNum, 
			int price, Date initDate, 
			Date endDate, String owner, 
			String photoName) {
		
		House newHouse = new House(
				name, fullDesc, 
				shortDesc, city, 
				isPrivate, guestNum, 
				price, initDate, 
				endDate, owner, 
				photoName
				);
		
		if (newHouse.save()) {
			return newHouse;
		}
		return null;
	}

	/**
	 * Find a house in the database by id
	 *
	 * @param id: House id in the database
	 *
	 * @return: the house if found or null otherwise.
	 */
	public static House find(int id) {
		for (House h : houses) {
			if (h.id == id) {
				return h;
			}
		}
		return null;
	}
}