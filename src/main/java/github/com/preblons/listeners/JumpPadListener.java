package github.com.preblons.listeners;

import github.com.preblons.Main;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;
import org.bukkit.Sound;

public class JumpPadListener implements Listener {

    private final Main plugin;

    public JumpPadListener(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        Block block = player.getLocation().subtract(0, 1, 0).getBlock();
        String path = "jumps." + block.getWorld().getName() + "." + block.getX() + "." + block.getY() + "." + block.getZ();

        if (plugin.getJumppadConfig().contains(path)) {
            double force = plugin.getJumppadConfig().getDouble(path + ".force");
            String direction = plugin.getJumppadConfig().getString(path + ".direction");

            if (direction.equals("cima")) {
                Vector velocity = player.getVelocity();
                velocity.setY(force);
                player.setVelocity(velocity);
            } else if (direction.equals("olho")) {
                Vector directionVector = player.getLocation().getDirection().normalize();
                directionVector.setY(0);
                player.setVelocity(directionVector.multiply(force).setY(1));
            }

            if (plugin.getConfig().getBoolean("sound.activate")) {
                String soundName = plugin.getConfig().getString("sound.som");
                try {
                    Sound sound = Sound.valueOf(soundName.toUpperCase());
                    player.getWorld().playSound(player.getLocation(), sound, 1.0F, 1.0F);
                } catch (IllegalArgumentException e) {
                    plugin.getLogger().warning("Som não encontrado: " + soundName);
                }
            }
        }
    }
}
