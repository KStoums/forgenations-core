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

public class GamemodeCommand implements CommandExecutor {

    private final static String GAMEMODE_SURVIVAL = "survival";
    private final static String GAMEMODE_SURVIVAL_NUMBER = "0";

    private final static String GAMEMODE_CREATIVE = "creative";
    private final static String GAMEMODE_CREATIVE_NUMBER = "1";


    private final static String GAMEMODE_ADVENTURE = "adventure";
    private final static String GAMEMODE_ADVENTURE_NUMBER = "2";

    private final static String GAMEMODE_SPECTATOR = "spectator";
    private final static String GAMEMODE_SPECTATOR_NUMBER = "3";

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String message, @NotNull String @NotNull [] args) {
        if (!(sender instanceof Player player)) {
            return false;
        }

        if (args.length > 2) {
            player.sendMessage(ChatUtil.USAGE_PREFIX.
                    append(Component.text("/gamemode <gamemode> <player> [OPTIONNEL]")));
            return false;
        }

        org.bukkit.GameMode selectedGameMode = null;
        String selectedGameModeName = switch (args[0].toLowerCase()) {
            case GAMEMODE_CREATIVE, GAMEMODE_CREATIVE_NUMBER -> {
                selectedGameMode = org.bukkit.GameMode.CREATIVE;
                yield GAMEMODE_CREATIVE.toUpperCase();
            }
            case GAMEMODE_SURVIVAL, GAMEMODE_SURVIVAL_NUMBER -> {
                selectedGameMode = org.bukkit.GameMode.SURVIVAL;
                yield GAMEMODE_SURVIVAL.toUpperCase();
            }
            case GAMEMODE_ADVENTURE, GAMEMODE_ADVENTURE_NUMBER -> {
                selectedGameMode = org.bukkit.GameMode.ADVENTURE;
                yield GAMEMODE_ADVENTURE.toUpperCase();
            }
            case GAMEMODE_SPECTATOR, GAMEMODE_SPECTATOR_NUMBER -> {
                selectedGameMode = org.bukkit.GameMode.SPECTATOR;
                yield GAMEMODE_SPECTATOR.toUpperCase();
            }
            default -> "";
        };

        if  (selectedGameMode == null) {
            player.sendMessage(ChatUtil.ERR_PREFIX.
                    append(Component.text("Le GameMode sélectionné n'existe pas.")));
            return false;
        }

        if (args.length == 2) {
            Player targetPlayer = Bukkit.getPlayer(args[1]);
            if (targetPlayer == null) {
                player.sendMessage(ChatUtil.ERR_PLAYER_NOT_ONLINE);
                return false;
            }

            targetPlayer.setGameMode(selectedGameMode);
            targetPlayer.sendMessage(ChatUtil.PLUGIN_PREFIX_WITH_COLOR.
                    append(Component.text("Votre GameMode a été défini en", NamedTextColor.WHITE).
                            decoration(TextDecoration.BOLD, false)).
                    appendSpace().
                    append(Component.text(selectedGameModeName, NamedTextColor.BLUE).
                            decoration(TextDecoration.BOLD, false)).
                    appendSpace().
                    append(Component.text("par un", NamedTextColor.WHITE).
                            decoration(TextDecoration.BOLD, false)).
                    appendSpace().
                    append(Component.text("Maître du Jeu", NamedTextColor.BLUE).
                            decoration(TextDecoration.BOLD, false)).
                    append(Component.text(".", NamedTextColor.WHITE).
                            decoration(TextDecoration.BOLD, false)));
            targetPlayer.playSound(targetPlayer.getLocation(), Sound.BLOCK_AMETHYST_BLOCK_PLACE, 1, 1);

            player.sendMessage(ChatUtil.PLUGIN_PREFIX_WITH_COLOR.
                    append(Component.text("Vous avez modifié le GameMode de", NamedTextColor.WHITE).
                            decoration(TextDecoration.BOLD, false)).
                    appendSpace().
                    append(Component.text(targetPlayer.getName(), NamedTextColor.BLUE).
                            decoration(TextDecoration.BOLD, false)).
                    appendSpace().
                    append(Component.text("en", NamedTextColor.WHITE).
                            decoration(TextDecoration.BOLD, false)).
                    appendSpace().
                    append(Component.text(selectedGameModeName, NamedTextColor.BLUE).
                            decoration(TextDecoration.BOLD, false)).
                    append(Component.text(".", NamedTextColor.WHITE).
                            decoration(TextDecoration.BOLD, false)));
            return true;
        }

        player.setGameMode(selectedGameMode);
        player.playSound(player.getLocation(), Sound.BLOCK_AMETHYST_BLOCK_PLACE, 1, 1);
        player.sendMessage(ChatUtil.PLUGIN_PREFIX_WITH_COLOR.
                append(Component.text("Vous avez modifié votre GameMode en", NamedTextColor.WHITE).
                        decoration(TextDecoration.BOLD, false)).
                appendSpace().
                append(Component.text(selectedGameModeName, NamedTextColor.BLUE).
                        decoration(TextDecoration.BOLD, false)).
                append(Component.text(".", NamedTextColor.WHITE).
                        decoration(TextDecoration.BOLD, false)));
        return true;
    }
}
