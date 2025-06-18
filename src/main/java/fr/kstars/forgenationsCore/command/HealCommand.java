package fr.kstars.forgenationsCore.command;

import fr.kstars.forgenationsCore.util.ChatUtil;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class HealCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String message, @NotNull String @NotNull [] args) {
        if (!(sender instanceof Player player)) {
            return false;
        }

        if (args.length > 1) {
            player.sendMessage(ChatUtil.USAGE_PREFIX.
                    append(Component.text("/heal <joueur> [OPTIONNEL]")));
            return false;
        }

        if (args.length == 1) {
            Player targetPlayer = Bukkit.getPlayer(args[0]);
            if  (targetPlayer == null) {
                player.sendMessage(ChatUtil.ERR_PLAYER_NOT_ONLINE);
                return false;
            }

            targetPlayer.heal(Objects.requireNonNull(player.getAttribute(Attribute.MAX_HEALTH)).getBaseValue());
            targetPlayer.sendMessage(ChatUtil.PLUGIN_PREFIX_WITH_COLOR.
                    append(Component.text("Vous avez été soigné par un").
                            decoration(TextDecoration.BOLD, false)).
                    appendSpace().
                    append(Component.text("Maître du Jeu", NamedTextColor.BLUE).
                            decoration(TextDecoration.BOLD, false)).
                    append(Component.text(".", NamedTextColor.WHITE)));
            targetPlayer.playSound(targetPlayer.getLocation(), Sound.ENTITY_SPLASH_POTION_BREAK, 1, 1);

            player.sendMessage(ChatUtil.PLUGIN_PREFIX_WITH_COLOR.
                    append(Component.text("Vous avez soigné", NamedTextColor.WHITE).
                            decoration(TextDecoration.BOLD, false)).
                    appendSpace().
                    append(Component.text(targetPlayer.getName(), NamedTextColor.BLUE).
                            decoration(TextDecoration.BOLD, false)).
                    append(Component.text(".", NamedTextColor.WHITE).
                            decoration(TextDecoration.BOLD, false)));
            return true;
        }

        player.heal(Objects.requireNonNull(player.getAttribute(Attribute.MAX_HEALTH)).getBaseValue());
        player.playSound(player.getLocation(), Sound.ENTITY_SPLASH_POTION_BREAK, 1, 1);
        player.sendMessage(ChatUtil.PLUGIN_PREFIX_WITH_COLOR.
                append(Component.text("Vous vous êtes soigné.",  NamedTextColor.WHITE).
                        decoration(TextDecoration.BOLD, false)));
        return true;
    }
}
