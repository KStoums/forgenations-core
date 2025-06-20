package fr.kstars.forgenationsCore.event;

import fr.kstars.forgenationsCore.command.SpawnCommand;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMoveListener implements Listener {
    @EventHandler(priority = EventPriority.LOW)
    public void onPlayerMove(PlayerMoveEvent event) {
        if (!SpawnCommand.playersInTeleportCooldown.contains(event.getPlayer().getUniqueId())) {
            return;
        }

        if (event.getTo().getBlockX() == event.getFrom().getBlockX() && event.getTo().getBlockY() == event.getFrom().getBlockY() && event.getTo().getBlockZ() == event.getFrom().getBlockZ()) {
            return;
        }

        SpawnCommand.playersInTeleportCooldown.remove(event.getPlayer().getUniqueId());
    }
}
