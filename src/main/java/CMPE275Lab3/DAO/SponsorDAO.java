package CMPE275Lab3.DAO;

import CMPE275Lab3.Models.Sponsor;

public interface SponsorDAO {

	public void createSponsor(Sponsor sponsor);
	
	public Sponsor findById(Integer sp_id);
	
	public Sponsor updateSponsor(Sponsor sponsor, Integer sp_id);
}
