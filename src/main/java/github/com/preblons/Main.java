package github.com.preblons;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.configuration.file.FileConfiguration;
import java.io.File;
import github.com.preblons.commands.JumppadCommand;
import github.com.preblons.listeners.JumpPadListener;

public class Main extends JavaPlugin {

    private File jumppadFile;
    private FileConfiguration jumppadConfig;

    @Override
    public void onEnable() {
        getLogger().info("Plugin Ativado");

        saveDefaultConfig();
        saveResource("jumppad.yml", false);

        jumppadFile = new File(getDataFolder(), "jumppad.yml");
        jumppadConfig = YamlConfiguration.loadConfiguration(jumppadFile);

        getCommand("jp").setExecutor(new JumppadCommand(this));

        getServer().getPluginManager().registerEvents(new JumpPadListener(this), this);
    }

    @Override
    public void onDisable() {
        getLogger().info("Plugin Desativado");

        saveJumppadConfig();
    }

    public void saveJumppadConfig() {
        try {
            jumppadConfig.save(jumppadFile);
        } catch (Exception e) {
            e.printStackTrace();
            getLogger().severe("Erro ao salvar o arquivo jumppad.yml");
        }
    }

    public FileConfiguration getJumppadConfig() {
        return jumppadConfig;
    }
}
