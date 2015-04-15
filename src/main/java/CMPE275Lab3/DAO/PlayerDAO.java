package CMPE275Lab3.DAO;

import CMPE275Lab3.Models.Player;

public interface PlayerDAO {

		public void createPlayer(Player player);
		
		public Player getById(Integer p_id);
		
		public Player updatePlayer(Player player, Integer id);
			
		public Player deletePlayer(Integer p_id);
}
