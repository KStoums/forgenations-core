package fr.kstars.forgenationsCore;

import fr.kstars.forgenationsCore.command.*;
import fr.kstars.forgenationsCore.event.*;
import fr.kstars.forgenationsCore.player.JsonPlayerFileLoader;
import fr.kstars.forgenationsCore.player.JsonPlayerRepository;
import fr.kstars.forgenationsCore.player.PlayerProfile;
import fr.kstars.forgenationsCore.util.ChatUtil;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public final class ForgenationsCorePlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info(ChatUtil.PLUGIN_PREFIX_WITHOUT_COLOR + "Initializing plugin...");

        try {
            //Player Repository
            JsonPlayerFileLoader jsonPlayerFileLoader = new JsonPlayerFileLoader();
            File jsonFile = jsonPlayerFileLoader.loadJsonPlayerFile();
            PlayerProfile[] jsonData = jsonPlayerFileLoader.getJsonData(jsonFile);
            JsonPlayerRepository jsonPlayerRepository = new JsonPlayerRepository(this.getLogger(), jsonFile,  new ArrayList<>(Arrays.asList(jsonData)));

            //Commands
            //TODO ADD TABCOMPLETER FOR ALL COMMANDS
            //TODO ADD BAN COMMAND
            Objects.requireNonNull(getCommand("clear")).setExecutor(new ClearCommand());
            Objects.requireNonNull(getCommand("feed")).setExecutor(new FeedCommand());
            Objects.requireNonNull(getCommand("fly")).setExecutor(new FlyCommand());
            Objects.requireNonNull(getCommand("gamemode")).setExecutor(new GamemodeCommand());
            Objects.requireNonNull(getCommand("heal")).setExecutor(new HealCommand());
            Objects.requireNonNull(getCommand("kick")).setExecutor(new KickCommand());
            Objects.requireNonNull(getCommand("kill")).setExecutor(new KillCommand());
            Objects.requireNonNull(getCommand("spawn")).setExecutor(new SpawnCommand());
            Objects.requireNonNull(getCommand("teleport")).setExecutor(new TeleportCommand());
            Objects.requireNonNull(getCommand("worldchange")).setExecutor(new WorldChangeCommand());

            //Events
            //TODO ADD EVENT WHEN PLAYER DIE WHEN RESPAWN IS TELEPORTED TO WORLD SPAWN LOCATION
            getServer().getPluginManager().registerEvents(new AsyncChatListener(), this);
            getServer().getPluginManager().registerEvents(new EntityDamageListener(), this);
            getServer().getPluginManager().registerEvents(new PlayerJoinListener(jsonPlayerRepository), this);
            getServer().getPluginManager().registerEvents(new PlayerQuitListener(), this);
            getServer().getPluginManager().registerEvents(new BlockBreakListener(), this);
        } catch (Exception e) {
            getLogger().severe(e.getMessage());
        }

        getLogger().info(ChatUtil.PLUGIN_PREFIX_WITHOUT_COLOR + "are now enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info(ChatUtil.PLUGIN_PREFIX_WITHOUT_COLOR + "are now disabled!");
    }
}
