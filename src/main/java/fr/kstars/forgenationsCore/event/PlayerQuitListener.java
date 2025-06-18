package fr.kstars.forgenationsCore.event;

import fr.kstars.forgenationsCore.model.Scoreboard;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

public class PlayerQuitListener implements Listener {
    @EventHandler(priority = EventPriority.LOW)
    public void onPlayerQuit(org.bukkit.event.player.PlayerQuitEvent event){
        Scoreboard.updatePlayersScoreboard();
    }
}
