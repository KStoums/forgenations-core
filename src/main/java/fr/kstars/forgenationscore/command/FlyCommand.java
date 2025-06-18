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

public class FlyCommand implements CommandExecutor {
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
                    append(Component.text("/fly <joueur> [OPTIONNEL]")));
            return false;
        }

        if (args.length == 1) {
            Player targetPlayer = Bukkit.getPlayer(args[0]);
            if (targetPlayer == null) {
                player.sendMessage(ChatUtil.ERR_PLAYER_NOT_ONLINE);
                return false;
            }

            targetPlayer.setAllowFlight(!targetPlayer.getAllowFlight());
            if (targetPlayer.getAllowFlight()) {
                targetPlayer.sendMessage(ChatUtil.PLUGIN_PREFIX_WITH_COLOR.
                        append(Component.text("Un").
                                decoration(TextDecoration.BOLD, false)).
                        appendSpace().
                        append(Component.text("Maître du Jeu", NamedTextColor.BLUE).
                                decoration(TextDecoration.BOLD, false)).
                        appendSpace().
                        append(Component.text("vous a", NamedTextColor.WHITE).
                                decoration(TextDecoration.BOLD, false)).
                        append(Component.text("autorisé", NamedTextColor.GREEN).
                                decoration(TextDecoration.BOLD, false)).
                        appendSpace().
                        append(Component.text("a voler.", NamedTextColor.WHITE).
                                decoration(TextDecoration.BOLD, false)));

                player.sendMessage(ChatUtil.PLUGIN_PREFIX_WITH_COLOR.
                        append(Component.text("Vous avez", NamedTextColor.WHITE).
                                decoration(TextDecoration.BOLD, false)).
                        appendSpace().
                        append(Component.text("autorisé", NamedTextColor.GREEN).
                                decoration(TextDecoration.BOLD, false)).
                        appendSpace().
                        append(Component.text("le vol à", NamedTextColor.WHITE).
                                decoration(TextDecoration.BOLD, false)).
                        appendSpace().
                        append(Component.text(targetPlayer.getName(), NamedTextColor.BLUE).
                                decoration(TextDecoration.BOLD, false)).
                        append(Component.text(".", NamedTextColor.WHITE).
                                decoration(TextDecoration.BOLD, false)));
            } else {
                targetPlayer.sendMessage(ChatUtil.PLUGIN_PREFIX_WITH_COLOR.
                        append(Component.text("Un").
                                decoration(TextDecoration.BOLD, false)).
                        appendSpace().
                        append(Component.text("Maître du Jeu", NamedTextColor.BLUE).
                                decoration(TextDecoration.BOLD, false)).
                        appendSpace().
                        append(Component.text("vous a", NamedTextColor.WHITE).
                                decoration(TextDecoration.BOLD, false)).
                        append(Component.text("retiré", NamedTextColor.RED).
                                decoration(TextDecoration.BOLD, false)).
                        appendSpace().
                        append(Component.text("a voler.", NamedTextColor.WHITE).
                                decoration(TextDecoration.BOLD, false)));

                player.sendMessage(ChatUtil.PLUGIN_PREFIX_WITH_COLOR.
                        append(Component.text("Vous avez", NamedTextColor.WHITE).
                                decoration(TextDecoration.BOLD, false)).
                        appendSpace().
                        append(Component.text("désactivé", NamedTextColor.RED).
                                decoration(TextDecoration.BOLD, false)).
                        appendSpace().
                        append(Component.text("le vol à", NamedTextColor.WHITE).
                                decoration(TextDecoration.BOLD, false)).
                        appendSpace().
                        append(Component.text(targetPlayer.getName(), NamedTextColor.BLUE).
                                decoration(TextDecoration.BOLD, false)).
                        append(Component.text(".", NamedTextColor.WHITE).
                                decoration(TextDecoration.BOLD, false)));
            }
            targetPlayer.playSound(targetPlayer.getLocation(), Sound.ENTITY_PARROT_FLY, 1, 1);
            return true;
        }

        player.setAllowFlight(!player.getAllowFlight());
        if (player.getAllowFlight()) {
            player.sendMessage(ChatUtil.PLUGIN_PREFIX_WITH_COLOR.
                    append(Component.text("Vous pouvez désormais voler.", NamedTextColor.WHITE).
                            decoration(TextDecoration.BOLD, false)));
        } else {
            player.sendMessage(ChatUtil.PLUGIN_PREFIX_WITH_COLOR.
                    append(Component.text("Vous ne pouvez plus voler.", NamedTextColor.WHITE).
                            decoration(TextDecoration.BOLD, false)));
        }
        player.playSound(player.getLocation(), Sound.ENTITY_PARROT_FLY, 1, 1);
        return true;
    }
}
