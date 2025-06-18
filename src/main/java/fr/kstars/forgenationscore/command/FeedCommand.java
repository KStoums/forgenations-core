package fr.kstars.forgenationscore.command;

import fr.kstars.forgenationscore.util.ChatUtil;
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

public class FeedCommand implements CommandExecutor {
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
                    append(Component.text("/feed <joueur> [OPTIONNEL]")));
            return false;
        }

        if (args.length == 1) {
            Player targetPlayer = Bukkit.getPlayer(args[0]);
            if (targetPlayer == null) {
                player.sendMessage(ChatUtil.ERR_PLAYER_NOT_ONLINE);
                return false;
            }

            targetPlayer.setFoodLevel(20);
            targetPlayer.sendMessage(ChatUtil.PLUGIN_PREFIX_WITH_COLOR.
                    append(Component.text("Vous avez été rassasié par un", NamedTextColor.WHITE).
                            decoration(TextDecoration.BOLD, false)).
                    append(Component.text("Maître du Jeu", NamedTextColor.BLUE).
                            decoration(TextDecoration.BOLD, false)).
                    append(Component.text(".", NamedTextColor.WHITE).
                            decoration(TextDecoration.BOLD, false)));
            targetPlayer.playSound(targetPlayer.getLocation(), Sound.ENTITY_GENERIC_EAT, 1, 1);

            player.sendMessage(ChatUtil.PLUGIN_PREFIX_WITH_COLOR.
                    append(Component.text("Vous avez rassasié", NamedTextColor.WHITE)).
                    appendSpace().
                    append(Component.text(targetPlayer.getName(), NamedTextColor.BLUE).
                            decoration(TextDecoration.BOLD, false)).
                    append(Component.text(".", NamedTextColor.WHITE).
                            decoration(TextDecoration.BOLD, false)));
            return true;
        }

        player.setFoodLevel(20);
        player.sendMessage(ChatUtil.PLUGIN_PREFIX_WITH_COLOR.
                append(Component.text("Vous avez été rassasié.",  NamedTextColor.WHITE).
                        decoration(TextDecoration.BOLD, false)));
        player.playSound(player.getLocation(), Sound.ENTITY_GENERIC_EAT, 1, 1);
        return true;
    }
}
