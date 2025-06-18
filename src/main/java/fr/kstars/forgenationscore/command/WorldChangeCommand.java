package fr.kstars.forgenationscore.command;

import fr.kstars.forgenationscore.util.ChatUtil;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.File;

public class WorldChangeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String message, @NotNull String @NotNull [] args) {
        if (!(sender instanceof Player player)) {
            return false;
        }

        if (args.length == 0) {
            player.sendMessage(ChatUtil.USAGE_PREFIX.
                    append(Component.text("/worldchange <monde>")));
            return false;
        }

        String worldName = String.join(" ", args);
        World world = Bukkit.getWorld(worldName);
        if (world == null) {
            player.sendMessage(ChatUtil.PLUGIN_PREFIX_WITH_COLOR.
                    append(Component.empty().decoration(TextDecoration.BOLD, false)).
                    appendSpace().
                    append(Component.text("Monde introuvable, tentative de chargement...")));

            world = loadWorld(worldName);
            if (world == null) {
                player.sendMessage(ChatUtil.ERR_PREFIX.
                        append(Component.text("Ce monde n'existe pas.")));
                return false;
            }
        }

        player.teleport(world.getSpawnLocation());
        player.sendMessage(ChatUtil.PLUGIN_PREFIX_WITH_COLOR.
                append(Component.empty().decoration(TextDecoration.BOLD, false)).
                appendSpace().
                append(Component.text("Vous avez été téléporté dans le monde", NamedTextColor.WHITE)).
                appendSpace().
                append(Component.text(world.getName(), NamedTextColor.BLUE)).
                append(Component.text(".", NamedTextColor.WHITE)));
        player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1);
        return true;
    }

    private static World loadWorld(String worldName) {
        File worldFolder = new File(Bukkit.getServer().getWorldContainer(), worldName);
        if (!worldFolder.exists() || !worldFolder.isDirectory()) {
            return null;
        }

        WorldCreator worldCreator = new WorldCreator(worldName);
        return worldCreator.createWorld();
    }
}
