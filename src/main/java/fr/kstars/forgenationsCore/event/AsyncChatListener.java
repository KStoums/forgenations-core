package fr.kstars.forgenationsCore.event;

import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

public class AsyncChatListener implements Listener {
    private final static String GAMEMASTER_CHAT_NAMETAG = "§9§l[MJ] ";
    private final static String MAPMAKER_CHAT_NAMETAG = "§2§l[MC] ";

    @EventHandler(priority = EventPriority.LOW)
    public void onChat(io.papermc.paper.event.player.AsyncChatEvent event) {
        Player player = event.getPlayer();
        String playerPrefix = player.getName();
        if (player.isOp()) {
            playerPrefix = GAMEMASTER_CHAT_NAMETAG + "§f" + player.getName();
        } else if (player.hasPermission("forgenations.builder")) {
            playerPrefix = MAPMAKER_CHAT_NAMETAG + "§f" + player.getName();
        }

        Component newPlayerName = Component.text(playerPrefix);
        event.renderer((p, displayName, message, audience) ->
                Component.text()
                    .append(newPlayerName)
                    .append(Component.text(" §f: "))
                    .append(message)
                    .build()
        );
    }
}
