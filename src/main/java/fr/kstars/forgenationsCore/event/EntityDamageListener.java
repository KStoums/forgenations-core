package fr.kstars.forgenationsCore.event;

import fr.kstars.forgenationsCore.util.ChatUtil;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

public class EntityDamageListener implements Listener {
    @EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
    public void onEntityDamage(org.bukkit.event.entity.EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Player player)) {
            return;
        }

        if (event.getFinalDamage() < player.getHealth()) {
            return;
        }

        //TODO CHECK IF PLAYER IS DEAD BY ENTITY OR NOT TO CREATE CUSTOM MESSAGE
        Bukkit.broadcast(ChatUtil.PLUGIN_PREFIX_WITH_COLOR.
                append(Component.text(player.getName(), NamedTextColor.BLUE).
                        decoration(TextDecoration.BOLD, false)).
                appendSpace().
                append(Component.text("est mort.", NamedTextColor.WHITE).
                        decoration(TextDecoration.BOLD, false)));
    }
}
