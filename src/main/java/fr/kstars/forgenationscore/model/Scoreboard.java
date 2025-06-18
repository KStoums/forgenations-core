package fr.kstars.forgenationscore.model;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

import java.util.Collection;

public class Scoreboard {

    public static void setScoreboard(Player player) {
        ScoreboardManager scoreboardManager = Bukkit.getScoreboardManager();
        org.bukkit.scoreboard.Scoreboard newScoreboard = scoreboardManager.getNewScoreboard();
        Objective objective = newScoreboard.registerNewObjective("scoreboard", Criteria.DUMMY, Component.text("ForgeNations", NamedTextColor.BLUE, TextDecoration.BOLD));
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        for (int i = 6; i >= 0; i--) {
            switch (i) {
                case 6, 3, 0:
                    Score invisibleScore = objective.getScore("§"+i);
                    invisibleScore.setScore(i);
                    continue;
                case 5:
                    Score playerInfoScore = objective.getScore("  §f§l● Information");
                    playerInfoScore.setScore(i);
                    continue;
                case 4:
                    Score nameScore = objective.getScore("  §fNom: §7" + player.getName());
                    nameScore.setScore(i);
                case 2:
                    Score serverInfoScore = objective.getScore("  §f§l● Serveur");
                    serverInfoScore.setScore(i);
                case 1:
                    Score onlinePlayersScore = objective.getScore("  §fEn ligne: §7" + Bukkit.getOnlinePlayers().size());
                    onlinePlayersScore.setScore(i);
            }
        }

        player.setScoreboard(newScoreboard);
    }

    public static void updatePlayersScoreboard() {
        Collection<? extends Player> onlinePlayers = Bukkit.getOnlinePlayers();
        for (Player onlinePlayer : onlinePlayers) {
            Scoreboard.setScoreboard(onlinePlayer);
        }
    }
}
