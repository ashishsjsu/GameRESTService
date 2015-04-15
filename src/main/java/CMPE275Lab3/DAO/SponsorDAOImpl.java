package CMPE275Lab3.DAO;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import CMPE275Lab3.Models.Player;
import CMPE275Lab3.Models.Sponsor;
import CMPE275Lab3.exceptions.MyExceptions;

public class SponsorDAOImpl implements SponsorDAO {

	private static MongoOperations mongoOps;
	private static final String SPONSOR_COLLECTION = "sponsors";
	   
	public SponsorDAOImpl(){
		if(mongoOps == null){
	    	
			AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
	    	mongoOps = (MongoOperations)context.getBean("mongoTemplate");
	    	
		}
	}
	
	
	@Override
	public void createSponsor(Sponsor sponsor) {
		
		mongoOps.insert(sponsor, SPONSOR_COLLECTION);
	}

	@Override
	public Sponsor findById(Integer sp_id) {
			
		Query query = new Query(Criteria.where("id").is(sp_id));
		Sponsor sponsor = mongoOps.findOne(query, Sponsor.class, SPONSOR_COLLECTION);
		
		if(sponsor == null)
		{
			throw new MyExceptions.UserNotFoundException();
		}
		
		return sponsor;
	}

	@Override
	public Sponsor updateSponsor(Sponsor sponsor, Integer sp_id) {

		if(!isExistingSponsor(sp_id))
		{
			throw new MyExceptions.UserNotFoundException();
		}
		
		Query query = new Query(Criteria.where("id").is(sp_id));
		
		Update update = new Update();
		
		if(sponsor.getName() != null)
		{
			update.set("firstname", sponsor.getName());
		}
		if(sponsor.getAddress().getCity() != null)
		{
			update.set("address.$.city", sponsor.getAddress().getCity());
		}
		if(sponsor.getAddress().getState() != null)
		{
			update.set("address.$.state", sponsor.getAddress().getState());
		}
		if(sponsor.getAddress().getCountry() != null)
		{
			update.set("address.$.country", sponsor.getAddress().getCountry());
		}
		if(sponsor.getAddress().getZipcode() != null)
		{
			update.set("address.$.zipcode", sponsor.getAddress().getZipcode());
		}
		
		
		mongoOps.findAndModify(query, update, Sponsor.class, SPONSOR_COLLECTION);
		Sponsor updatedSponsor = mongoOps.findOne(query, Sponsor.class, SPONSOR_COLLECTION);
		
		return updatedSponsor;
		
	}
	
	public boolean isExistingSponsor(Integer sp_id)
	{
		Sponsor existingsponsor = findById(sp_id);
		
		if(existingsponsor != null)
		{
			return true;
		}
		
		return false;
		
	}
	

}
