package fr.kstars.forgenationsCore.event;

import fr.kstars.forgenationsCore.command.SpawnCommand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class PlayerRespawnListener implements Listener {
    @EventHandler(priority = EventPriority.LOW)
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        Player player = event.getPlayer();
        player.setRespawnLocation(SpawnCommand.WORLD_SPAWN_LOCATION);
    }
}
