package CMPE275Lab3.Controllers;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import CMPE275Lab3.DAO.SpringMongoConfig;
import CMPE275Lab3.exceptions.MyExceptions;
import CMPE275Lab3.DAO.PlayerDAO;
import CMPE275Lab3.DAO.PlayerDAOImpl;
import CMPE275Lab3.DAO.SponsorDAO;
import CMPE275Lab3.DAO.SponsorDAOImpl;
import CMPE275Lab3.Models.Address;
import CMPE275Lab3.Models.Player;
import CMPE275Lab3.Models.Sponsor;
import CMPE275Lab3.exceptions.MyExceptions;

import javax.validation.*;

@RestController
@RequestMapping("/GameLab")
public class ApplicationController {
	
	private PlayerDAO playerDAO = new PlayerDAOImpl();
	private SponsorDAO sponsorDAO = new SponsorDAOImpl();
	
	//update player
	@RequestMapping(value="/player/{id}", method = RequestMethod.PUT)
	public @ResponseBody Player updatePlayer(@PathVariable(value="p_id") Integer p_id, @RequestParam(value="firstname", required = true) String firstname,
			@RequestParam(value="lastname", required=true) String lastname,
			@RequestParam(value="email", required=true) String email,
			@RequestParam(value="description", required=false) String description,
			@RequestParam(value="street", required=false) String street,
			@RequestParam(value="city", required=false) String city,
			@RequestParam(value="state", required=false) String state,
			@RequestParam(value="zipcode", required=false) String zipcode,
			@RequestParam(value="country", required=false) String country,
			@RequestParam(value="sponsor", required=false) Integer sponsor_id)
			{
				Player player = new Player();
				Address address = new Address();
				
				if(sponsor_id != null)
				{
					Sponsor sponsor = sponsorDAO.findById(sponsor_id);
					player.setSponsor(sponsor);
				}
				
				if(city != null)
				{
					address.setCity(city);
				}
				if(state != null)
				{
					address.setState(state);
				}
				if(street != null)
				{
					address.setStreet(street);
				}
				if(country != null)
				{
					address.setCountry(country);
				}
			
				player.setAddress(address);
				
				playerDAO.updatePlayer(player, p_id);
				
				return null;
			}
	
	//create new player
	@RequestMapping(value="/player", method = RequestMethod.POST)
	public @ResponseBody Player createPlayer(@RequestParam(value="firstname", required = true) String firstname,
			@RequestParam(value="lastname", required=true) String lastname,
			@RequestParam(value="email", required=true) String email,
			@RequestParam(value="description", required=false) String description,
			@RequestParam(value="street", required=false) String street,
			@RequestParam(value="city", required=false) String city,
			@RequestParam(value="state", required=false) String state,
			@RequestParam(value="zipcode", required=false) String zipcode,
			@RequestParam(value="sponsor", required=false) Integer sponsor_id)
			{
				isValidPlayer(firstname, lastname, email);
				
				Player player = new Player(firstname, lastname, email);
				Address address = new Address(street, city, state, zipcode);
				player.setAddress(address);
				
				if(sponsor_id != null)
				{
					System.out.println(sponsor_id.getClass().getName());
					Sponsor sponsor = sponsorDAO.findById(sponsor_id);
					player.setSponsor(sponsor);
				}
				
				playerDAO.createPlayer(player);
				return player;
			}
	
    @RequestMapping( method=RequestMethod.GET,value="/player/{id}")
    public @ResponseBody Player getPlayer(@PathVariable(value="id")Integer p_id) {
    	
    	Player player = playerDAO.getById(p_id);
    	
    	return player;
    }

    //delete player
    @RequestMapping(method=RequestMethod.DELETE,value="/player/{id}")
    public @ResponseBody String deletePlayer(@PathVariable(value="id")Integer p_id){
    	
    	Player player = playerDAO.deletePlayer(p_id);
    	return "Player deleted : " + player;
    }
	
    @RequestMapping( method=RequestMethod.GET,value="/sponsor/{id}")
    public @ResponseBody Sponsor getSponsor(@PathVariable(value="id")String p_id) {
    	
    	Sponsor sponsor = sponsorDAO.findById(Integer.parseInt(p_id));
    	
    	return sponsor;
    }
    
	
	@RequestMapping(value="/sponsor", method=RequestMethod.POST)
	public @ResponseBody Sponsor createSponsor(@RequestParam(value="name", required = true) String name,
			@RequestParam(value="email", required=false) String email,
			@RequestParam(value="description", required=false) String description,
			@RequestParam(value="street", required=false) String street,
			@RequestParam(value="city", required=false) String city,
			@RequestParam(value="state", required=false) String state,
			@RequestParam(value="zipcode", required=false) String zipcode)
	{
	
			Sponsor sponsor = new Sponsor(name);
			Address address= new Address(street,city,state,zipcode);
			sponsor.setAddress(address);
		    sponsor.setDescription(description);
		    
		    sponsorDAO.createSponsor(sponsor);
			System.out.println("Sponsor : " + sponsor+"id="+sponsor.getId());  		  
		    return sponsor;
	}
	
	
	
	
	public void isValidSponsor(String name)
	{
		if(name == null)
		{
			throw new MyExceptions.InvalidParametersException();
		}
	}
	
	public void isValidPlayer(String firstname, String lastname, String email)
	{
		if(firstname == null || lastname == null || email == null)
		{
			throw new MyExceptions.InvalidParametersException();
		}
	}

}
