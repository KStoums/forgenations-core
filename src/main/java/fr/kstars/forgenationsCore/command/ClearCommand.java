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

public class ClearCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String message, @NotNull String @NotNull [] args) {
        if (!(sender instanceof Player player)) {
            return false;
        }

        if (args.length > 1) {
            player.sendMessage(ChatUtil.USAGE_PREFIX.
                    append(Component.text("/clear <joueur> [OPTIONNEL]")));
            return false;
        }

        if (args.length == 1) {
            Player targetPlayer = Bukkit.getPlayer(args[0]);
            if (targetPlayer == null) {
                player.sendMessage(ChatUtil.ERR_PLAYER_NOT_ONLINE);
                return false;
            }

            targetPlayer.getInventory().clear();

            targetPlayer.sendMessage(ChatUtil.PLUGIN_PREFIX_WITH_COLOR.
                    append(Component.text("Votre inventaire a été", NamedTextColor.WHITE).
                            decoration(TextDecoration.BOLD, false)).
                    appendSpace().
                    append(Component.text("supprimé", NamedTextColor.BLUE).
                            decoration(TextDecoration.BOLD, false)).
                    appendSpace().
                    append(Component.text("par un", NamedTextColor.WHITE).
                            decoration(TextDecoration.BOLD, false)).
                    appendSpace().
                    append(Component.text("Maître du Jeu", NamedTextColor.BLUE).
                            decoration(TextDecoration.BOLD, false)).
                    append(Component.text(".", NamedTextColor.WHITE).
                            decoration(TextDecoration.BOLD, false)));
            targetPlayer.playSound(targetPlayer.getLocation(), Sound.BLOCK_LAVA_EXTINGUISH, 1, 1);

            player.sendMessage(ChatUtil.PLUGIN_PREFIX_WITH_COLOR.
                    append(Component.text("Vous avez", NamedTextColor.WHITE).
                            decoration(TextDecoration.BOLD, false)).
                    appendSpace().
                    append(Component.text("supprimé", NamedTextColor.BLUE).
                            decoration(TextDecoration.BOLD, false)).
                    appendSpace().
                    append(Component.text("l'inventaire de",  NamedTextColor.WHITE).
                            decoration(TextDecoration.BOLD, false)).
                    appendSpace().
                    append(Component.text(targetPlayer.getName(), NamedTextColor.BLUE).
                            decoration(TextDecoration.BOLD, false)).
                    append(Component.text(".", NamedTextColor.WHITE).
                            decoration(TextDecoration.BOLD, false)));
            return true;
        }

        player.getInventory().clear();
        player.sendMessage(ChatUtil.PLUGIN_PREFIX_WITH_COLOR.
                append(Component.text("Vous avez", NamedTextColor.WHITE).
                        decoration(TextDecoration.BOLD, false)).
                appendSpace().
                append(Component.text("supprimé", NamedTextColor.BLUE).
                        decoration(TextDecoration.BOLD, false)).
                appendSpace().
                append(Component.text("votre inventaire.", NamedTextColor.WHITE).
                        decoration(TextDecoration.BOLD, false)));
        player.playSound(player.getLocation(), Sound.BLOCK_LAVA_EXTINGUISH, 1, 1);
        return true;
    }
}
