package fr.kstars.forgenationsCore.model;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.entity.Player;

public class Tablist {
    private final static String GAMEMASTER_TABLIST_NAMETAG = "§9§L[MJ] ";
    private final static String MAPMAKER_CHAT_NAMETAG = "§2§l[MC] ";

    public static void setTablist(Player player) {
         Component header = Component.text("───────", NamedTextColor.DARK_GRAY, TextDecoration.STRIKETHROUGH).
                 append(Component.text("ForgeNations", NamedTextColor.BLUE, TextDecoration.BOLD).
                         decoration(TextDecoration.STRIKETHROUGH, false)).
                 append(Component.text("───────", NamedTextColor.DARK_GRAY, TextDecoration.STRIKETHROUGH).
                         decoration(TextDecoration.BOLD, false));
        Component footer = Component.text("Forgez votre empire, dominez le monde !", NamedTextColor.WHITE);

        player.sendPlayerListHeaderAndFooter(header, footer);

        if (player.isOp()) {
            player.playerListName(Component.text(GAMEMASTER_TABLIST_NAMETAG + "§f" + player.getName()));
            return;
        }

        if (player.hasPermission("forgenations.builder")) {
            player.playerListName(Component.text(MAPMAKER_CHAT_NAMETAG + "§f" + player.getName()));
        }
    }
}
