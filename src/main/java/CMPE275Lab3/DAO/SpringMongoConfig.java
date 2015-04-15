package CMPE275Lab3.DAO;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.authentication.UserCredentials;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.MongoClient;

@Configuration
public class SpringMongoConfig {
	
	public @Bean MongoTemplate mongoTemplate() throws Exception{
		
		MongoTemplate mongoTemplate = new MongoTemplate(new MongoClient("ds033449.mongolab.com:33449"), "cmpe275", new UserCredentials("ashishsjsu", "ashishsjsu"));
		
		return mongoTemplate;
	}
}
