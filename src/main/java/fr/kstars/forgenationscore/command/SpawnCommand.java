package fr.kstars.forgenationscore.command;

import fr.kstars.forgenationscore.util.ChatUtil;
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
import org.jetbrains.annotations.NotNull;

public class SpawnCommand implements CommandExecutor {

    public static final Location WordSpawnLocation = new Location(Bukkit.getWorld("world"), -256.514, 266, -171.514, 0f, 0);

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String message, @NotNull String @NotNull [] args) {
        if (!(sender instanceof Player player)) {
            return false;
        }

        //TODO SET COOLDOWN

        player.teleport(WordSpawnLocation);
        player.sendMessage(ChatUtil.PLUGIN_PREFIX_WITH_COLOR.
                append(Component.text("Vous avez été téléporté au spawn",  NamedTextColor.WHITE).
                        decoration(TextDecoration.BOLD, false)));
        player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1);
        return true;
    }
}
