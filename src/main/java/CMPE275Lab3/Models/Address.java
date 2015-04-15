package CMPE275Lab3.Models;

public class Address {

	private String street;
	private String city;
	private String state;
	private String zipcode;
	private String country;
	
	
	public Address()
	{
	
	}

	public Address(String street, String city, String state, String zipcode) {
		super();
		this.street = street;
		this.city = city;
		this.state = state;
		this.zipcode = zipcode;
	}
	

	/**
	 * @return the street
	 */
	public String getStreet() {
		return street;
	}


	/**
	 * @param street the street to set
	 */
	public void setStreet(String street) {
		this.street = street;
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
	 * @return the state
	 */
	public String getState() {
		return state;
	}


	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}


	/**
	 * @return the zipcode
	 */
	public String getZipcode() {
		return zipcode;
	}


	/**
	 * @param zipcode the zipcode to set
	 */
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}


	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}


	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

}
