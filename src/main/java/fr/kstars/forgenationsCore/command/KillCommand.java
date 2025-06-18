package fr.kstars.forgenationsCore.command;

import fr.kstars.forgenationsCore.util.ChatUtil;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class KillCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String message, @NotNull String @NotNull [] args) {
        if (!(sender instanceof Player player)) {
            return false;
        }

        if (!player.isOp()) {
            player.sendMessage(ChatUtil.ERR_NO_PERMISSION);
            return false;
        }

        if (args.length > 1) {
            player.sendMessage(ChatUtil.USAGE_PREFIX.
                    append(Component.text("/kill <joueur> [OPTIONNEL]")));
            return false;
        }

        if (args.length == 1) {
            Player targetPlayer = Bukkit.getPlayer(args[0]);
            if (targetPlayer == null) {
                player.sendMessage(ChatUtil.ERR_PLAYER_NOT_ONLINE);
                return false;
            }

            targetPlayer.damage(targetPlayer.getHealth());
            targetPlayer.sendMessage(ChatUtil.PLUGIN_PREFIX_WITH_COLOR.
                    append(Component.text("Vous avez été tué par un", NamedTextColor.WHITE).
                            decoration(TextDecoration.BOLD, false)).
                    appendSpace().
                    append(Component.text("Maître du Jeu", NamedTextColor.BLUE).
                            decoration(TextDecoration.BOLD, false)).
                    appendSpace().
                    append(Component.text(".", NamedTextColor.WHITE).
                            decoration(TextDecoration.BOLD, false)));
            targetPlayer.getWorld().strikeLightning(targetPlayer.getLocation());

            player.sendMessage(ChatUtil.PLUGIN_PREFIX_WITH_COLOR.
                    append(Component.text("Vous avez tué", NamedTextColor.WHITE).
                            decoration(TextDecoration.BOLD, false)).
                    appendSpace().
                    append(Component.text(targetPlayer.getName(), NamedTextColor.BLUE).
                            decoration(TextDecoration.BOLD, false)).
                    appendSpace().
                    append(Component.text(".", NamedTextColor.WHITE).
                            decoration(TextDecoration.BOLD, false)));
            return true;
        }

        player.damage(player.getHealth());
        player.sendMessage(ChatUtil.PLUGIN_PREFIX_WITH_COLOR.
                append(Component.text("Vous vous êtes suicidé.", NamedTextColor.WHITE).
                        decoration(TextDecoration.BOLD, false)));
        player.getWorld().strikeLightning(player.getLocation());
        return true;
    }
}
