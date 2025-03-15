package com.davis.model;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;

@Embeddable
@Access(AccessType.FIELD)
public class Address {
	
	private String city;
	private String district;
	private String Zip_Code;  
	public Address(String city, String district, String zip_Code) {
		this.city = city;
		this.district = district;
		Zip_Code = zip_Code;
	}
	public Address() {
		
	}
	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * @return the district
	 */
	public String getDistrict() {
		return district;
	}
	/**
	 * @param district the district to set
	 */
	public void setDistrict(String district) {
		this.district = district;
	}
	/**
	 * @return the zip_Code
	 */
	public String getZip_Code() {
		return Zip_Code;
	}
	/**
	 * @param zip_Code the zip_Code to set
	 */
	public void setZip_Code(String zip_Code) {
		this.Zip_Code = zip_Code;
	}
	


}

