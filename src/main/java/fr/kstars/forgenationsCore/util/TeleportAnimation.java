package fr.kstars.forgenationsCore.util;

import com.google.common.collect.Lists;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class TeleportAnimation {
    private static final List<Player> playersTeleportationInProgress = Lists.newArrayList();
    private static final int teleportDelay = 4;

    public static void startTeleportAnimation(JavaPlugin plugin, Player player, Location location) {
        if (playerTeleportationIsInProgress(player)) {
            player.sendMessage("§4Error: You are already teleporting.");
            return;
        }

        addPlayerTeleportationInProgress(player);
        player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 1000000000,10));
        player.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, 1000000000,10));

        new BukkitRunnable() {
            Integer teleportDelayCopy = teleportDelay;

            @Override
            public void run() {
                switch (teleportDelayCopy) {
                    case teleportDelay:
                        player.playSound(player.getLocation(), Sound.BLOCK_WOODEN_DOOR_OPEN, 1, 1);
                        break;

                    case 1:
                        player.teleport(location);
                        player.playSound(player.getLocation(), Sound.BLOCK_WOODEN_DOOR_OPEN, 1, 1);
                        break;

                    case 0:
                        player.clearActivePotionEffects();
                        removePlayerTeleportationInProgress(player);
                        cancel();
                        break;

                    default:
                        player.playSound(player.getLocation(), Sound.BLOCK_GRAVEL_STEP, 1, 1);
                }

                teleportDelayCopy--;
            }
        }.runTaskTimer(plugin, 20L, 20L);
    }

    private static void addPlayerTeleportationInProgress(Player player) {
        playersTeleportationInProgress.add(player);
    }

    private static void removePlayerTeleportationInProgress(Player player) {
        playersTeleportationInProgress.remove(player);
    }

    private static boolean playerTeleportationIsInProgress(Player player) {
        return playersTeleportationInProgress.contains(player);
    }
}
