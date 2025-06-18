package fr.kstars.forgenationsCore.event;

import fr.kstars.forgenationsCore.command.SpawnCommand;
import fr.kstars.forgenationsCore.model.Scoreboard;
import fr.kstars.forgenationsCore.model.Tablist;
import fr.kstars.forgenationsCore.player.PlayerProfile;
import fr.kstars.forgenationsCore.player.PlayerRepository;
import lombok.AllArgsConstructor;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.title.Title;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import java.time.Duration;
import java.util.ArrayList;

@AllArgsConstructor
public class PlayerJoinListener implements Listener {
    private final PlayerRepository playerRepository;

    @EventHandler(priority = EventPriority.LOW)
    public void onPlayerJoin(org.bukkit.event.player.PlayerJoinEvent event) {
        event.joinMessage(Component.empty());
        Player player = event.getPlayer();

        if (this.playerRepository.findById(player.getUniqueId()).isEmpty()) {
            this.playerRepository.add(new PlayerProfile(player.getUniqueId(), "", new ArrayList<>()));
        }

        Title joinTitle = Title.title(
                Component.text("ForgeNations", NamedTextColor.BLUE, TextDecoration.BOLD),
                Component.text("Forgez votre empire, dominez le monde !", NamedTextColor.WHITE),
                Title.Times.times(Duration.ofSeconds(1), Duration.ofSeconds(3), Duration.ofSeconds(1))
        );
        player.showTitle(joinTitle);
        Tablist.setTablist(player);
        Scoreboard.updatePlayersScoreboard();

        if (player.getWorld().getName().equals("world")) {
            player.setGameMode(GameMode.ADVENTURE);
            player.teleport(SpawnCommand.WordSpawnLocation);
        }
    }
}
