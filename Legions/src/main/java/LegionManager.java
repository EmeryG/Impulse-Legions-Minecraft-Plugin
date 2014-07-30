import lombok.Getter;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by WaterNode on 7/28/2014.
 */
public class LegionManager implements Listener, CommandExecutor {
    ArrayList<Legion> legions = new ArrayList<Legion>();
    static ArrayList<Chunk> claimedChunks = new ArrayList<Chunk>();
    static HashMap<String, String> PendingInvites = new HashMap<String, String>();
    FileConfiguration main;

    public LegionManager() {
        if(!new File(LegionsMain.getMain().getDataFolder() + "main.yml").exists()) {
            LegionsMain.getMain().saveResource("main.yml", false);
        }
        main = Config.getConfig("main.yml");
        initLegions();
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;
            switch(args[0].toLowerCase()) {
                case "map":
                    Lib.genMap(p, findLegion(p));
                    break;
                case "info":
                    Legion infoLegion = findLegion(args[1]);
                    if(infoLegion != null) {
                        Lib.giveLegionInfo(p, infoLegion, findLegion(p));
                    } else {
                        p.sendMessage(Config.getMessage("FakeLegion"));
                        return false;
                    }
                    break;
                case "accept": case "a":
                    if(PendingInvites.containsKey(args[1])) {
                        if(PendingInvites.get(args[1]).equalsIgnoreCase(p.getName())) {
                            Legion l = findLegion(p);
                            if(l == null) {
                                PendingInvites.remove(args[1]);
                                p.sendMessage(Config.getMessage("NoInvite"));
                            } else {
                                l.members.add(new LegionMember(Rank.NORMAL, p.getName()));
                            }
                        } else {
                            p.sendMessage(Config.getMessage("NoInvite"));
                        }
                    } else {
                        p.sendMessage(Config.getMessage("NoInvite"));
                    }
                    break;
                default:
                    Legion legion = findLegion(p);
                    if(legion != null) {
                        return legion.onCommand(p, args);
                    }
                    p.sendMessage(Config.getMessage("CmdNotFound"));
                    return false;
            }
            return true;
        } else {
            return false;
        }
    }

    public Legion findLegion(Player player) {
        for(Legion legion : legions) {
            if(legion.getMember(player.getName()) != null) {
                return legion;
            }
        }
        return null;
    }

    public Legion findLegion(String legionName) {
        for(Legion legion : legions) {
            if(legion.getName().equalsIgnoreCase(legionName)) {
                return legion;
            }
        }
        return null;
    }

    public static Chunk getChunk(int x, int z) {
        for(Chunk c : claimedChunks) {
            if(c.getX() == x && c.getZ() == z) {
                return c;
            }
        }
        return null; 
    }

    public void createLegion(Player p, String legionName) {
        legions.add(Config.createLegion(p, legionName));
    }

    public void initLegions() {
        for(String legionName : main.getStringList("legions")) {
            ArrayList<LegionMember> members = new ArrayList<LegionMember>();

            FileConfiguration config = Config.getConfig("/legions/" + legionName.toLowerCase() + ".yml");

            for(String player : config.getStringList("players")) {
                members.add(
                        new LegionMember(
                                Rank.valueOf(
                                        config.getString("players." + player)
                                ), player)
                );
            }
            legions.add(new Legion(legionName, members, config));
        }
    }
}
