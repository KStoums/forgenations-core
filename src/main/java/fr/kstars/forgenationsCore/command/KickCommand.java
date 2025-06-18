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

import java.util.Arrays;

public class KickCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String message, @NotNull String @NotNull [] args) {
        if (!(sender instanceof Player player)) {
            return false;
        }

        if (!player.isOp()) {
            player.sendMessage(ChatUtil.ERR_NO_PERMISSION);
            return false;
        }

        if (args.length == 0) {
            player.sendMessage(ChatUtil.USAGE_PREFIX.
                    append(Component.text("/kick <joueur> <raison>")));
            return false;
        }

        Player targetPlayer = Bukkit.getPlayer(args[0]);
        if (targetPlayer == null) {
            player.sendMessage(ChatUtil.ERR_PLAYER_NOT_ONLINE);
            return false;
        }

        String reason = "Aucune raison défini";
        if (args.length > 1) {
            reason = String.join(" ", Arrays.copyOfRange(args, 1, args.length));
        }

        targetPlayer.kick(Component.text("Vous avez été exclu de ForgeNation !", NamedTextColor.BLUE).
                append(Component.text("\nRaison:", NamedTextColor.GRAY)).
                appendSpace().
                append(Component.text(reason, NamedTextColor.WHITE)));
        targetPlayer.getWorld().strikeLightning(targetPlayer.getLocation());

        player.sendMessage(ChatUtil.PLUGIN_PREFIX_WITH_COLOR.
                append(Component.text("Vous avez exclu", NamedTextColor.WHITE).
                        decoration(TextDecoration.BOLD, false)).
                appendSpace().
                append(Component.text(targetPlayer.getName(), NamedTextColor.BLUE).
                        decoration(TextDecoration.BOLD, false)).
                appendSpace().
                append(Component.text(". Raison:", NamedTextColor.WHITE).
                        decoration(TextDecoration.BOLD, false)).
                appendSpace().
                append(Component.text(reason, NamedTextColor.BLUE).
                        decoration(TextDecoration.BOLD, false)).
                append(Component.text(".", NamedTextColor.WHITE).
                        decoration(TextDecoration.BOLD, false)));
        return true;
    }
}
