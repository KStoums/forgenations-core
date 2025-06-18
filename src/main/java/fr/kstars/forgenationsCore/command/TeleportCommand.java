package fr.kstars.forgenationsCore.command;

import fr.kstars.forgenationsCore.util.ChatUtil;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class TeleportCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String message, @NotNull String @NotNull [] args) {
        if (!(sender instanceof Player player)) {
            return false;
        }

        if (args.length == 0 || args.length > 2) {
            player.sendMessage(ChatUtil.USAGE_PREFIX.
                    append(Component.text("/teleport <joueur> <joueur> [OPTIONNEL]")));
            return false;
        }

        Player targetPlayer = Bukkit.getPlayer(args[0]);

        if (targetPlayer == null) {
            player.sendMessage(ChatUtil.ERR_PLAYER_NOT_ONLINE);
            return false;
        }

        if (args.length == 2) {
            Player destinationPlayer = Bukkit.getPlayer(args[1]);
            if (destinationPlayer == null) {
                player.sendMessage(ChatUtil.ERR_PLAYER_NOT_ONLINE);
                return false;
            }

            targetPlayer.teleport(destinationPlayer);
            targetPlayer.sendMessage(ChatUtil.PLUGIN_PREFIX_WITH_COLOR.
                    append(Component.text("Vous avez été téléporté à", NamedTextColor.WHITE).
                            decoration(TextDecoration.BOLD, false)).
                    appendSpace().
                    append(Component.text(destinationPlayer.getName(), NamedTextColor.BLUE).
                            decoration(TextDecoration.BOLD, false)).
                    appendSpace().
                    append(Component.text("par un Maître du Jeu.", NamedTextColor.WHITE).
                            decoration(TextDecoration.BOLD, false)));
            targetPlayer.playSound(targetPlayer.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1);
            return true;
        }

        player.teleport(targetPlayer);
        player.sendMessage(ChatUtil.PLUGIN_PREFIX_WITH_COLOR.
                append(Component.text("Vous avez été téléporté à", NamedTextColor.WHITE).
                        decoration(TextDecoration.BOLD, false)).
                appendSpace().
                append(Component.text(targetPlayer.getName(), NamedTextColor.BLUE).
                        decoration(TextDecoration.BOLD, false)).
                appendSpace().
                append(Component.text(".", NamedTextColor.WHITE).
                        decoration(TextDecoration.BOLD, false)));
        player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1);
        return false;
    }
}
