package io.datajek.tennisplayerrest;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PlayerController {

    @Autowired
    PlayerService service;

    @GetMapping("/players")
    public List<Player> allPlayers() {
        return service.getAllPlayers();
    }

    @GetMapping("/players/{id}")
    public Player getPlayer(@PathVariable int id){
        return service.getPlayer(id);
    }

    @PostMapping("/players")
    public Player addPlayer(@RequestBody Player player) {
        player.setId(0);
        return service.addPlayer(player);
    }
    @PutMapping("/players/{id}")
    public Player updatePlayer(@RequestBody Player player, @PathVariable int id) {

        return service.updatePlayer(id, player);
    }
    @PatchMapping("/players/{id}")
    public Player partialUpdate(@PathVariable int id, @RequestBody Map<String, Object> playerPath)
    {
        return  service.patch(id,playerPath);
    }

    @PatchMapping("/players/{id}/titles")
    public void updateTitles(@PathVariable int id, @RequestBody int titles) {
        service.updateTitles(id, titles);
    }


    @DeleteMapping("/players/{id}")
    public String deletePlayer(@PathVariable int id) {
        //call service layer method
        return  service.deletePlayer(id);
    }
}