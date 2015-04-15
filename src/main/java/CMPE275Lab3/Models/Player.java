package CMPE275Lab3.Models;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "players")
public class Player {

	private static int counter;
	
	private Integer id;
	private String firstname;
	private String lastname;
	private String email;
	private String description;
	private Address address;
	private Sponsor sponsor;
	private List<Integer> opponents;
	
	public Player()
	{
	
	}
	
	public Player(String firstname, String lastname, String email) {
		super();
		id = counter++;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
	}
	
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return the firstname
	 */
	public String getFirstname() {
		return firstname;
	}
	/**
	 * @param firstname the firstname to set
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	/**
	 * @return the lastname
	 */
	public String getLastname() {
		return lastname;
	}
	/**
	 * @param lastname the lastname to set
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
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
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the address
	 */
	public Address getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(Address address) {
		this.address = address;
	}
	/**
	 * @return the sponsor
	 */
	public Sponsor getSponsor() {
		return sponsor;
	}
	/**
	 * @param sponsor the sponsor to set
	 */
	public void setSponsor(Sponsor sponsor) {
		this.sponsor = sponsor;
	}
	/**
	 * @return the opponents
	 */
	public List<Integer> getOpponents() {
		return opponents;
	}
	/**
	 * @param opponents the opponents to set
	 */
	public void setOpponents(Integer o_id) {
		this.opponents.add(o_id);
	}
	
	
	public boolean isOpponent(Integer p_id)
	{
		for(Integer i : opponents)
		{
			if(i.equals(p_id))
			{
				return true;
			}
		}
		return false;
		
	}
	
	public void removeOpponent(Integer p_id)
	{
		for(Integer i : opponents)
		{
			if(i.equals(p_id))
			{
				opponents.remove(i);
			}
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Player [id=" + id + ", firstname=" + firstname + ", lastname="
				+ lastname + ", email=" + email + ", sponsor=" + sponsor
				+ ", opponents=" + opponents + "]";
	}
	
}
