package fr.kstars.forgenationsCore.event;

import fr.kstars.forgenationsCore.command.SpawnCommand;
import fr.kstars.forgenationsCore.util.ChatUtil;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.Objects;

public class EntityDamageListener implements Listener {
    @EventHandler
    public void onEntityDeath(org.bukkit.event.entity.EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Player player)) {
            return;
        }

        if (event.isCancelled()) {
            return;
        }

        if (event.getFinalDamage() < player.getHealth()) {
            return;
        }

        event.setCancelled(true);
        player.setHealth(Objects.requireNonNull(player.getAttribute(Attribute.MAX_HEALTH)).getBaseValue());
        player.teleport(SpawnCommand.WordSpawnLocation);
        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_DEATH, 1, 1);

        //TODO CHECK IF PLAYER IS DEAD BY ENTITY OR NOT
        Bukkit.broadcast(ChatUtil.PLUGIN_PREFIX_WITH_COLOR.
                append(Component.text(player.getName(), NamedTextColor.BLUE).
                        decoration(TextDecoration.BOLD, false)).
                appendSpace().
                append(Component.text("est mort.", NamedTextColor.WHITE).
                        decoration(TextDecoration.BOLD, false)));
    }
}
