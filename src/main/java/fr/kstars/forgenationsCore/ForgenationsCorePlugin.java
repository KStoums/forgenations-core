package fr.kstars.forgenationsCore;

import fr.kstars.forgenationsCore.command.*;
import fr.kstars.forgenationsCore.event.AsyncChatListener;
import fr.kstars.forgenationsCore.event.EntityDamageListener;
import fr.kstars.forgenationsCore.event.PlayerJoinListener;
import fr.kstars.forgenationsCore.event.PlayerQuitListener;
import fr.kstars.forgenationsCore.util.ChatUtil;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class ForgenationsCorePlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info(ChatUtil.PLUGIN_PREFIX_WITHOUT_COLOR + "Initializing plugin...");

        //Commands
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
        getServer().getPluginManager().registerEvents(new AsyncChatListener(), this);
        getServer().getPluginManager().registerEvents(new EntityDamageListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerQuitListener(), this);

        getLogger().info(ChatUtil.PLUGIN_PREFIX_WITHOUT_COLOR + "are now enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info(ChatUtil.PLUGIN_PREFIX_WITHOUT_COLOR + "are now disabled!");
    }
}
