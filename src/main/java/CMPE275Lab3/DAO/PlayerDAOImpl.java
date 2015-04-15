package CMPE275Lab3.DAO;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.mongodb.MongoServerSelectionException;


import CMPE275Lab3.Models.Player;
import CMPE275Lab3.exceptions.MyExceptions;

public class PlayerDAOImpl implements PlayerDAO {

	private static MongoOperations mongoOps;
	private static final String Player_COLLECTION = "players";
	SponsorDAO sponsorDAO = new SponsorDAOImpl();   
	
	public PlayerDAOImpl(){
		if(mongoOps == null){
	    	
			AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
	    	mongoOps = (MongoOperations)context.getBean("mongoTemplate");
	    	
		}
	}
	
	@Override
	public void createPlayer(Player player) {
		
		
		while(isExistingPlayer(player.getId()))
		{
			player.setId(player.getId() + 1);

		}
		
		if(isDuplicateEmail(player.getEmail()))
		{
			throw new MyExceptions.DuplicatePlayerEmailException();
		}
		
		System.out.println("mongoOps " + mongoOps);
		mongoOps.insert(player, Player_COLLECTION);
	}

	@Override
	public Player getById(Integer p_id) {
		Query query = new Query(Criteria.where("id").is(p_id));
		Player player = mongoOps.findOne(query, Player.class, Player_COLLECTION);

		return player;
	}

	@Override
	public Player updatePlayer(Player player, Integer id) {

		if(!isExistingPlayer(id))
		{
			throw new MyExceptions.UserNotFoundException();
		}
		
		Query query = new Query(Criteria.where("id").is(id));
		
		Update update = new Update();
		
		if(player.getFirstname() != null)
		{
			update.set("firstname", player.getFirstname());
		}
		if(player.getLastname() != null)
		{
			update.set("lastname", player.getLastname());			
		}
		if(player.getSponsor() != null)
		{
			update.set("sponsor", player.getSponsor());
		}
		if(player.getAddress().getCity() != null)
		{
			update.set("address.$.city", player.getAddress().getCity());
		}
		if(player.getAddress().getState() != null)
		{
			update.set("address.$.state", player.getAddress().getState());
		}
		if(player.getAddress().getCountry() != null)
		{
			update.set("address.$.country", player.getAddress().getCountry());
		}
		if(player.getAddress().getZipcode() != null)
		{
			update.set("address.$.zipcode", player.getAddress().getZipcode());
		}
		
		
		mongoOps.findAndModify(query, update, Player.class, Player_COLLECTION);
		Player updatedplayer = mongoOps.findOne(query, Player.class, Player_COLLECTION);
		
		return updatedplayer;
	}
	
	
	@Override
	public Player deletePlayer(Integer p_id) {
	
		Query query = new Query(Criteria.where("id").is(p_id));
		Player player = mongoOps.findOne(query, Player.class, Player_COLLECTION);
		
		if(player == null)
		{
			throw new MyExceptions.UserNotFoundException();
		}
		
		mongoOps.remove(query, Player.class, Player_COLLECTION);
		
		return player;
	}

	
/*========== HELPER FUNCTIONS ==========*/	
	public boolean isExistingPlayer(Integer id)
	{
		Player existingplayer = getById(id);
		
		if(existingplayer != null)
		{
			return true;
		}
		
		return false;
		
	}
	
	public boolean isDuplicateEmail(String email)
	{
		Query query = new Query(Criteria.where("email").is(email));
		Player player = mongoOps.findOne(query, Player.class, Player_COLLECTION);
		
		if(player!=null)
		{
			return false;
		}
		
		return true;
	}

	


}
