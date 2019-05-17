package chattables;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.HashMap;
import java.util.UUID;


public class ChannelChattables extends JavaPlugin implements Listener {
    private HashMap<UUID, String> chatMap = new HashMap<>();

    public void onEnable() {
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(this, this);
    }
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player)sender;
        if (label.equalsIgnoreCase("admin")&& sender.hasPermission("channelchat.permission")){
        }if(sender.hasPermission("channelchat.permission"))
        {
            if(chatMap.containsKey(player.getUniqueId()))
            {
                chatMap.remove(player.getUniqueId(), "admin");
                sender.sendMessage("You have exited ADMIN CHAT");
            }
            else
            {
                chatMap.put(player.getUniqueId(), "admin");
                sender.sendMessage("You are in ADMIN CHAT");
            }
        }
        return true;
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String dname = player.getName();
        String chatLabel = ChatColor.translateAlternateColorCodes('&', getConfig().getString("chatLabel"));
        if(chatMap.get(player.getUniqueId()).equals("admin")){
            event.setCancelled(true);
            for(Player p:Bukkit.getOnlinePlayers()){
                {
                    if(p.hasPermission("channelchat.permission")){
                        player.sendMessage(ChatColor.WHITE + chatLabel + " " + dname + ": " + event.getMessage());
                    }
                }
            }
        }
    }
}

