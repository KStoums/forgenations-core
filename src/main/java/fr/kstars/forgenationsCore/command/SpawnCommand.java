package fr.kstars.forgenationsCore.command;

import fr.kstars.forgenationsCore.util.ChatUtil;
import lombok.AllArgsConstructor;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.UUID;

@AllArgsConstructor
public class SpawnCommand implements CommandExecutor {
    public static final ArrayList<UUID> playersInTeleportCooldown =  new ArrayList<>();
    private final Plugin plugin;

    public static final Location WORLD_SPAWN_LOCATION = new Location(Bukkit.getWorld("world"), -256.514, 266, -171.514, 0f, 0);

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String message, @NotNull String @NotNull [] args) {
        if (!(sender instanceof Player player)) {
            return false;
        }

        if (playersInTeleportCooldown.contains(player.getUniqueId())) {
            player.sendMessage(ChatUtil.PLUGIN_PREFIX_WITH_COLOR.
                    append(Component.text("Vous êtes déjà en cours de téléportation...", NamedTextColor.WHITE).
                            decoration(TextDecoration.BOLD, false)));
            return false;
        }

        playersInTeleportCooldown.add(player.getUniqueId());
        player.sendMessage(ChatUtil.PLUGIN_PREFIX_WITH_COLOR.
                append(Component.text("Téléportation à",  NamedTextColor.WHITE).
                        decoration(TextDecoration.BOLD, false)).
                appendSpace().
                append(Component.text("l'Odysseia dans", NamedTextColor.WHITE).
                        decoration(TextDecoration.BOLD, false)).
                appendSpace().
                append(Component.text("3 secondes...", NamedTextColor.BLUE).
                        decoration(TextDecoration.BOLD, false)));

        new BukkitRunnable() {
            @Override
            public void run() {
                if (!playersInTeleportCooldown.contains(player.getUniqueId())) {
                    player.sendMessage(ChatUtil.PLUGIN_PREFIX_WITH_COLOR.
                            append(Component.text("Votre téléportation à l'Odysseia a été car vous avez bougé.", NamedTextColor.WHITE).
                                    decoration(TextDecoration.BOLD, false)));
                    return;
                }

                player.teleport(WORLD_SPAWN_LOCATION);
                player.sendMessage(ChatUtil.PLUGIN_PREFIX_WITH_COLOR.
                        append(Component.text("Vous avez été téléporté à l'Odysseia.",  NamedTextColor.WHITE).
                                decoration(TextDecoration.BOLD, false)));
                player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1);
                playersInTeleportCooldown.remove(player.getUniqueId());
                cancel();
            }
        }.runTaskLater(this.plugin, 60L);
        return true;
    }
}
