package io.datajek.tennisplayerrest;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import jakarta.transaction.Transactional;
import org.apache.el.util.ReflectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

@Service
public class PlayerService {

    @Autowired
    PlayerRepository repo;

    //Get all players
    public List<Player> getAllPlayers() {
        return repo.findAll();
    }

    //Get player by ID
    public Player getPlayer(int id) {
        Optional<Player> tempPlayer = repo.findById(id);

        if(tempPlayer.isEmpty())
            throw new PlayerNotFoundException("Player with id {"+ id +"} not found");

        return tempPlayer.get();
    }


    //Add a player
    public Player addPlayer(Player p) {
        return repo.save(p);
    }
        //Update a player
    public Player updatePlayer(int id, Player p) {
        //call repository method to update the player
		Optional<Player> tempPlayer = repo.findById(id);

		if(tempPlayer.isEmpty())
			throw new PlayerNotFoundException("Player with id {"+ id +"} not found");
		p.setId(id);
        return repo.save(p);
    }



    //Partial update

    public Player patch( int id, Map<String, Object> playerPatch) {
        Optional<Player> player = repo.findById(id);
        if (player.isPresent()){
            playerPatch.forEach((key, value)->
            {
                Field field = ReflectionUtils.findField(Player.class, key);
                ReflectionUtils.makeAccessible(field);
                ReflectionUtils.setField(field,player.get(),value);
            });
            //update fields using Map
        }
        if(player.isEmpty())
            throw new PlayerNotFoundException("Player with id {"+ id +"} not found");
        return repo.save(player.get());
    }
    @Transactional
    public void updateTitles(int id, int titles)
    {
        Optional<Player> tempPlayer = repo.findById(id);

        if(tempPlayer.isEmpty())
            throw new PlayerNotFoundException("Player with id {"+ id +"} not found");
        repo.updateTitles(id, titles);
    }


    //delete a player

    public String deletePlayer(int id) {
        Optional<Player> tempPlayer = repo.findById(id);
        if(tempPlayer.isEmpty())
            throw new PlayerNotFoundException("Player with id {"+ id +"} not found");

        repo.delete(tempPlayer.get());
        return "Deleted player with id: " + id;
    }
}
