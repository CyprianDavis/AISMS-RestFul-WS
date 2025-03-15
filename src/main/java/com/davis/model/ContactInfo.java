package com.davis.model;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;

/**
 * 
 * @author CYPRIAN DAVIS
 *
 */
@Embeddable
@Access(AccessType.FIELD)
public class ContactInfo {
	
	private String phoneNumber;   //Telphone number
	private String contact;	//Contact two
	private String email;	//Email address
	/**
	 * @param phoneNumber
	 * @param contact
	 * @param email
	 */
	public ContactInfo(String phoneNumber, String contact, String email) {
		super();
		this.phoneNumber = phoneNumber;
		this.contact = contact;
		this.email = email;
	}
	public ContactInfo() {
		
	}
	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}
	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	/**
	 * @return the contact
	 */
	public String getContact() {
		return contact;
	}
	/**
	 * @param contact the contact to set
	 */
	public void setContact(String contact) {
		this.contact = contact;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	

}
