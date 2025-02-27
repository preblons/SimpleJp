package github.com.preblons.commands;

import github.com.preblons.Main;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.configuration.file.FileConfiguration;

public class JumppadCommand implements CommandExecutor {

    private final Main plugin;

    public JumppadCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length != 2) {
            sender.sendMessage("§cUso correto: /jp <força> <cima/olho>");
            return false;
        }

        if (!(sender instanceof Player)) {
            sender.sendMessage("§cApenas jogadores podem usar este comando.");
            return false;
        }

        Player player = (Player) sender;

        if (!player.hasPermission("preblons.jp")) {
            player.sendMessage("§cVocê não tem permissão para usar este comando.");
            return false;
        }

        double force;
        try {
            force = Double.parseDouble(args[0]);
        } catch (NumberFormatException e) {
            player.sendMessage("§cPor favor, forneça um número válido para a força.");
            return false;
        }

        String direction = args[1].toLowerCase();
        if (!direction.equals("cima") && !direction.equals("olho")) {
            player.sendMessage("§cPor favor, forneça uma direção válida: §eCima§c ou §eOlho§c.");
            return false;
        }

        Block block = player.getLocation().subtract(0, 1, 0).getBlock();
        Material blockType = block.getType();

        if (blockType == Material.AIR) {
            player.sendMessage("§cVocê precisa estar em cima de um bloco válido (não ar).");
            return false;
        }

        String path = "jumps." + block.getWorld().getName() + "." + block.getX() + "." + block.getY() + "." + block.getZ();
        if (plugin.getJumppadConfig().contains(path)) {
            player.sendMessage("§cJá existe um Jumppad configurado nesse bloco.");
            return false;
        }

        saveJumpPadConfig(block, force, direction);

        player.sendMessage("§aJumppad configurado com sucesso! §7Força: §e" + force + " §7, Direção: §e" + direction);
        return true;
    }

    private void saveJumpPadConfig(Block block, double force, String direction) {
        FileConfiguration config = plugin.getJumppadConfig();
        String path = "jumps." + block.getWorld().getName() + "." + block.getX() + "." + block.getY() + "." + block.getZ();

        config.set(path + ".force", force);
        config.set(path + ".direction", direction);
        plugin.saveJumppadConfig();
    }
}
