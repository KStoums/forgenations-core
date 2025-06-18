package fr.kstars.forgenationscore.event;

import fr.kstars.forgenationscore.model.Scoreboard;
import fr.kstars.forgenationscore.model.Tablist;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.title.Title;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.time.Duration;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onJoin(org.bukkit.event.player.PlayerJoinEvent event) {
        event.joinMessage(Component.empty());

        Player player = event.getPlayer();

        Title joinTitle = Title.title(
                Component.text("ForgeNations", NamedTextColor.BLUE, TextDecoration.BOLD),
                Component.text("Forgez votre empire, dominez le monde !", NamedTextColor.WHITE),
                Title.Times.times(Duration.ofSeconds(1), Duration.ofSeconds(3), Duration.ofSeconds(1))
        );
        player.showTitle(joinTitle);
        player.setGameMode(GameMode.ADVENTURE);

        Tablist.setTablist(player);
        Scoreboard.updatePlayersScoreboard();
    }
}
