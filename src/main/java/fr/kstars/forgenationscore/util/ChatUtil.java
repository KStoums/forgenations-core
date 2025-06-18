package fr.kstars.forgenationscore.util;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;

public class ChatUtil {
    public static Component PLUGIN_PREFIX_WITH_COLOR = Component.text("[ForgeNations]", NamedTextColor.BLUE, TextDecoration.BOLD).
            appendSpace();
    public static String PLUGIN_PREFIX_WITHOUT_COLOR = "[ForgeNations] ";

    //Errors
    public static Component ERR_PLAYER_NOT_ONLINE = Component.text("Erreur: Ce joueur n'est pas en ligne.", NamedTextColor.RED);
    public static Component ERR_NO_PERMISSION = Component.text("Erreur: Vous n'avez pas accès à cette commande.", NamedTextColor.RED);
    public static Component ERR_PREFIX = Component.text("Erreur:", NamedTextColor.RED).
            appendSpace();

    //Usage
    public static Component USAGE_PREFIX = Component.text("Utilisation:", NamedTextColor.GREEN).
            appendSpace();
}